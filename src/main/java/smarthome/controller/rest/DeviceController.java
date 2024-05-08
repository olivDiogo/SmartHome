package smarthome.controller.rest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.service.IDeviceService;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.DeviceDataDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.hateoas.CollectionModel;

/**
 * Class representing a REST controller for operations related to devices in the smart home.
 */

@RestController
@RequestMapping("/device")
public class DeviceController {

  private final IDeviceService deviceService;
  private final IAssembler<Device, DeviceDTO> deviceAssembler;

  /**
   * Constructor for the DeviceController class.
   * @param deviceService The service for device operations.
   * @param deviceAssembler The assembler for converting device objects to DTOs.
   */
  @Autowired
  public DeviceController(IDeviceService deviceService,
      IAssembler<Device, DeviceDTO> deviceAssembler) {
    this.deviceAssembler = deviceAssembler;
    this.deviceService = deviceService;
  }

  /**
   * Handles HTTP POST requests for adding a new device.
   * @param data The data of the device to be added.
   * @return The response entity with the added device.
   */
  @PostMapping("/add")
  public ResponseEntity<EntityModel<DeviceDTO>> addDevice(@Valid @RequestBody DeviceDataDTO data){
    RoomID roomID = new RoomID(data.roomID);
    DeviceTypeID deviceTypeID = new DeviceTypeID(data.deviceTypeID);
    DeviceName deviceName = new DeviceName(data.deviceName);
    DeviceStatus deviceStatus = new DeviceStatus(data.deviceStatus);

    Device device = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    DeviceDTO deviceDTO = deviceAssembler.domainToDTO(device);

    var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeviceController.class).addDevice(data)).withSelfRel();

    var entityModel = EntityModel.of(deviceDTO, selfLink);

    return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
  }

  /**
   * Handles HTTP GET requests for retrieving a device by its ID.
   * @param id The ID of the device to be retrieved.
   * @return The response entity (link HAETOS) with the retrieved device.
   */
  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<DeviceDTO>> getDevice(@PathVariable String id) {
    DeviceID deviceID = new DeviceID(id);
    Optional<Device> device = deviceService.getDeviceByID(deviceID);

    if (device.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    DeviceDTO deviceDTO = deviceAssembler.domainToDTO(device.get());

    Link selfLink = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(DeviceController.class).getDevice(id)).withSelfRel();

    EntityModel<DeviceDTO> entityModel = EntityModel.of(deviceDTO, selfLink);

    return ResponseEntity.ok(entityModel);
  }

  /**
   * Handles HTTP GET requests for retrieving all devices.
   */
  @GetMapping("/all")
  public ResponseEntity<CollectionModel<DeviceDTO>> getDevices() throws EmptyReturnException {
    List<Device> devices = deviceService.getAllDevices();
    if (devices.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    List<DeviceDTO> deviceDTOs = deviceAssembler.domainToDTO(devices);
    CollectionModel<DeviceDTO> resource = CollectionModel.of(deviceDTOs,
        WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(DeviceController.class).getDevices())
            .withSelfRel());
    return ResponseEntity.ok(resource);
  }

  /**
   * Handles HTTP GET requests for deactivating a device.
   */
  @PutMapping("/deactivate/{id}")
  public ResponseEntity<EntityModel<DeviceDTO>> deactivateDevice(@PathVariable String id) {
    DeviceID deviceID = new DeviceID(id);
    Optional<Device> device = deviceService.getDeviceByID(deviceID);

    if (device.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Device deactivatedDevice = deviceService.deactivateDeviceByID(deviceID);

    DeviceDTO deviceDTO = deviceAssembler.domainToDTO(deactivatedDevice);

    Link selfLink = WebMvcLinkBuilder.linkTo(
        WebMvcLinkBuilder.methodOn(DeviceController.class).deactivateDevice(id)).withSelfRel();

    EntityModel<DeviceDTO> entityModel = EntityModel.of(deviceDTO, selfLink);

    return ResponseEntity.ok(entityModel);
  }

}
