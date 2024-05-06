package smarthome.controller.rest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.service.IDeviceService;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.DeviceDataDTO;

@RestController
@RequestMapping("/device")
public class DeviceController {

  private final IDeviceService deviceService;
  private final IAssembler<Device, DeviceDTO> deviceAssembler;

  /**
   * Constructor
   */
  @Autowired
  public DeviceController(IDeviceService deviceService,
      IAssembler<Device, DeviceDTO> deviceAssembler) {
    this.deviceAssembler = deviceAssembler;
    this.deviceService = deviceService;
  }

  @PostMapping("/add")
  public ResponseEntity<DeviceDTO> addDevice(@Valid @RequestBody DeviceDataDTO data){
    RoomID roomID = new RoomID(data.roomID);
    DeviceTypeID deviceTypeID = new DeviceTypeID(data.deviceTypeID);
    DeviceName deviceName = new DeviceName(data.deviceName);
    DeviceStatus deviceStatus = new DeviceStatus(data.deviceStatus);

    Device device = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

    DeviceDTO deviceDTO = deviceAssembler.domainToDTO(device);

    return ResponseEntity.status(HttpStatus.CREATED).body(deviceDTO);
  }

}
