package smarthome.controller.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.service.IDeviceService;
import smarthome.domain.service.IDeviceTypeService;
import smarthome.domain.service.IRoomService;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.mapper.DeviceTypeAssembler;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.DeviceTypeDTO;
import smarthome.utils.dto.data_dto.DeviceDataDTO;
import smarthome.utils.dto.data_dto.actuator_data_dto.IActuatorDataDTO;
import smarthome.utils.dto.data_dto.sensor_data_dto.ISensorDataDTO;

/** Class representing a REST controller for operations related to devices in the smart home. */
@RestController
@RequestMapping("/devices")
public class DeviceController {

  private final IDeviceService deviceService;
  private final IAssembler<Device, DeviceDTO> deviceAssembler;
  private final IDeviceTypeService deviceTypeService;
  private final DeviceTypeAssembler deviceTypeAssembler;
  private final IRoomService roomService;

  /**
   * Constructor for the DeviceController class.
   *
   * @param deviceService The service for device operations.
   * @param deviceAssembler The assembler for converting device objects to DTOs.
   */
  @Autowired
  public DeviceController(
      IDeviceService deviceService,
      IAssembler<Device, DeviceDTO> deviceAssembler,
      IDeviceTypeService deviceTypeService,
      DeviceTypeAssembler deviceTypeAssembler, IRoomService roomService) {
    this.deviceAssembler = deviceAssembler;
    this.deviceService = deviceService;
    this.deviceTypeService = deviceTypeService;
    this.deviceTypeAssembler = deviceTypeAssembler;
    this.roomService = roomService;
  }

  /**
   * Handles HTTP POST requests for adding a new device.
   *
   * @param data The data of the device to be added.
   * @return The response entity with the added device.
   */
  @PostMapping
  public ResponseEntity<EntityModel<DeviceDTO>> addDevice(@Valid @RequestBody DeviceDataDTO data) {
    RoomID roomID = new RoomID(data.roomID);
    TypeDescription deviceTypeDescription = new TypeDescription(data.deviceTypeDescription);
    DeviceName deviceName = new DeviceName(data.deviceName);

    DeviceType deviceType = deviceTypeService.addDeviceType(deviceTypeDescription);

    Device device = deviceService.addDevice(roomID, deviceName, deviceType.getID());

    DeviceDTO deviceDTO = deviceAssembler.domainToDTO(device);

    EntityModel<DeviceDTO> resource =
        EntityModel.of(
            deviceDTO, linkTo(methodOn(DeviceController.class).addDevice(data)).withSelfRel());

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);
  }

  /**
   * Handles HTTP GET requests for retrieving a device by its ID.
   *
   * @param id The ID of the device to be retrieved.
   * @return The response entity (link HAETOS) with the retrieved device.
   */
  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<DeviceDTO>> getDevice(
      @PathVariable String id,
      @RequestParam(required = false) IActuatorDataDTO actuatorDataDTO,
      @RequestParam(required = false) ISensorDataDTO sensorDataDTO)
  {
    DeviceID deviceID = new DeviceID(id);
    Optional<Device> device = deviceService.getDeviceByID(deviceID);

    if (device.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    DeviceDTO deviceDTO = deviceAssembler.domainToDTO(device.get());

    // Link to self
    Link selfLink =
        linkTo(methodOn(DeviceController.class).getDevice(id, actuatorDataDTO, sensorDataDTO))
            .withSelfRel();

    // Link to addActuator method in ActuatorController
    Link addActuatorLink =
        linkTo(methodOn(ActuatorController.class).addActuator(actuatorDataDTO))
            .withRel("add-actuator");

    // Link to addSensor method in SensorController
    Link addSensorLink =
        linkTo(methodOn(SensorController.class).addSensor(sensorDataDTO)).withRel("add-sensor");

    EntityModel<DeviceDTO> entityModel =
        EntityModel.of(deviceDTO, selfLink, addActuatorLink, addSensorLink);

    return ResponseEntity.ok(entityModel);
  }

  /** Handles HTTP GET requests for retrieving all devices. */
  @GetMapping
  public ResponseEntity<CollectionModel<DeviceDTO>> getAllDevices() {
    List<Device> devices = deviceService.getAllDevices();

    List<DeviceDTO> deviceDTOs = deviceAssembler.domainToDTO(devices);
    CollectionModel<DeviceDTO> resource =
        CollectionModel.of(
            deviceDTOs, linkTo(methodOn(DeviceController.class).getAllDevices()).withSelfRel());
    return ResponseEntity.ok(resource);
  }

  /** Handles HTTP GET requests for deactivating a device. */
  @PutMapping("/deactivate/{id}")
  public ResponseEntity<EntityModel<DeviceDTO>> deactivateDevice(@PathVariable String id) {
    DeviceID deviceID = new DeviceID(id);
    Optional<Device> device = deviceService.getDeviceByID(deviceID);

    if (device.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Device deactivatedDevice = deviceService.deactivateDeviceByID(deviceID);

    DeviceDTO deviceDTO = deviceAssembler.domainToDTO(deactivatedDevice);

    Link selfLink = linkTo(methodOn(DeviceController.class).deactivateDevice(id)).withSelfRel();

    EntityModel<DeviceDTO> entityModel = EntityModel.of(deviceDTO, selfLink);

    return ResponseEntity.ok(entityModel);
  }


  @GetMapping("/grouped")
  public Map<DeviceTypeDTO, List<DeviceDTO>> getAllDevicesGroupedByFunctionality() {
    List<DeviceType> deviceTypes = deviceTypeService.getAllDeviceTypes();
    Map<DeviceTypeDTO, List<DeviceDTO>> devicesGroupedByFunctionality = new LinkedHashMap<>();
    for (DeviceType deviceType : deviceTypes) {
      List<Device> devices = deviceService.getDevicesByDeviceTypeID(deviceType.getID());
      DeviceTypeDTO deviceTypeDTO = deviceTypeAssembler.domainToDTO(deviceType);
      List<DeviceDTO> deviceDTOs = deviceAssembler.domainToDTO(devices);
      devicesGroupedByFunctionality.put(deviceTypeDTO, deviceDTOs);
    }
    return devicesGroupedByFunctionality;
  }


  /**
   * Get all devices in a room
   *
   * @param idStr is the room ID
   * @return a list of all devices in the room with the given ID
   */
  @GetMapping("/{id}/room")
  public ResponseEntity<CollectionModel<DeviceDTO>> getDevicesInAGivenRoom(
      @PathVariable("id") String idStr) {
    RoomID id = new RoomID(idStr);

    roomService.getRoomById(id); // Check if room exists
    List<DeviceDTO> deviceDTOs = deviceService.getDevicesByRoomId(id)
        .stream()
        .map(deviceAssembler::domainToDTO)
        .toList();
    CollectionModel<DeviceDTO> resource = CollectionModel.of(deviceDTOs,
        linkTo(methodOn(DeviceController.class).getDevicesInAGivenRoom(idStr)).withSelfRel());
    return ResponseEntity.ok(resource);
  }

}
