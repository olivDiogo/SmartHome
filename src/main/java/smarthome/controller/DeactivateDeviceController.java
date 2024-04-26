package smarthome.controller;

import java.util.Collections;
import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.service.IDeviceService;
import smarthome.domain.value_object.DeviceID;
import smarthome.utils.Validator;
import smarthome.utils.dto.DeviceDTO;

public class DeactivateDeviceController {

  private final IDeviceService _deviceService;
  private final IAssembler<Device, DeviceDTO> _deviceAssembler;

  /**
   * Constructor for US08DeactivateDevice.
   *
   * @param deviceService   is the service for the device.
   * @param deviceAssembler is the assembler for the device.
   */
  public DeactivateDeviceController(IDeviceService deviceService,
      IAssembler<Device, DeviceDTO> deviceAssembler) {
    Validator.validateNotNull(deviceService, "Device service");
    _deviceService = deviceService;
    Validator.validateNotNull(deviceAssembler, "Device assembler");
    _deviceAssembler = deviceAssembler;
  }


  /**
   * Requests all devices.
   *
   * @return a list of devices.
   */
  public List<DeviceDTO> requestAllDevices() {
    List<Device> deviceList = _deviceService.getAllDevices();
    if (deviceList.isEmpty()) {
      return Collections.emptyList();
    }
    return List.copyOf(_deviceAssembler.domainToDTO(deviceList));
  }


  public DeviceDTO requestDeactivateDevice(DeviceDTO deviceDTO) {
    try {
      DeviceID deviceID = new DeviceID(deviceDTO.deviceID);
      Device device = _deviceService.deactivateDeviceByID(deviceID);
      return _deviceAssembler.domainToDTO(device);
    } catch (IllegalArgumentException e) {
      return new DeviceDTO("Device not found.", "", "", "");
    }
  }
}
