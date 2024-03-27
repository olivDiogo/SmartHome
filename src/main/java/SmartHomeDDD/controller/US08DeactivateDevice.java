package SmartHomeDDD.controller;

import SmartHomeDDD.assembler.DeviceAssembler;
import SmartHomeDDD.DTO.DeviceDTO;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.service.DeviceService;
import SmartHomeDDD.valueObject.DeviceID;
import SmartHomeDDD.valueObject.DeviceStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class US08DeactivateDevice {

    private DeviceService _deviceService;
    private DeviceAssembler _deviceAssembler;

    /**
     * Constructor for US08DeactivateDevice.
     * @param deviceService is the service for the device.
     * @param deviceAssembler is the assembler for the device.
     */
    public US08DeactivateDevice(DeviceService deviceService, DeviceAssembler deviceAssembler) {
        _deviceService = deviceService;
        _deviceAssembler = deviceAssembler;
    }

    /**
     * Requests all devices.
     * @return a list of devices.
     */
    public List<DeviceDTO> requestAllDevices() {
        List<Device> deviceList = _deviceService.getAllDevices();
        if (deviceList == null || deviceList.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if there are no devices.
        }
        return List.copyOf(_deviceAssembler.domainToDTO(deviceList));
    }


    public DeviceDTO requestDeactivateDevice(DeviceDTO deviceDTO){
        try {
            DeviceID deviceID = new DeviceID(deviceDTO.deviceID);
            Device device = _deviceService.deactivateDeviceByID(deviceID);
            return _deviceAssembler.domainToDTO(device);
        } catch (IllegalArgumentException e) {
            return new DeviceDTO("Device not found.", "", "", "");
        }
    }
}
