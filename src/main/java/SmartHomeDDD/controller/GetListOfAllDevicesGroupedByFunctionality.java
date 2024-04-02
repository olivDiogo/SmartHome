package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.DeviceDTO;
import SmartHomeDDD.assembler.DeviceAssembler;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.domain.DeviceType.DeviceType;
import SmartHomeDDD.service.DeviceService;
import SmartHomeDDD.service.DeviceTypeService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetListOfAllDevicesGroupedByFunctionality {
    private DeviceService _deviceService;
    private DeviceTypeService _deviceTypeService;
    private DeviceAssembler _deviceAssembler;


    /**
     * Constructor for GetListOfAllDevicesGroupedByFunctionality.
     *
     * @param deviceService
     * @param deviceAssembler
     */
    public GetListOfAllDevicesGroupedByFunctionality(DeviceService deviceService, DeviceAssembler deviceAssembler, DeviceTypeService deviceTypeService) {
        validateDeviceService(deviceService);
        _deviceService = deviceService;
        validateDeviceAssembler(deviceAssembler);
        _deviceAssembler = deviceAssembler;
        validateDeviceTypeService(deviceTypeService);
        _deviceTypeService = deviceTypeService;
    }

    /**
     * Validate the DeviceService.
     * @param deviceService The DeviceService to validate.
     */
    private void validateDeviceService(DeviceService deviceService) {
        if (deviceService == null) {
            throw new IllegalArgumentException("DeviceService cannot be null.");
        }
    }

    /**
     * Validate the DeviceAssembler.
     * @param deviceAssembler The DeviceAssembler to validate.
     */
    private void validateDeviceAssembler(DeviceAssembler deviceAssembler) {
        if (deviceAssembler == null) {
            throw new IllegalArgumentException("DeviceAssembler cannot be null.");
        }
    }

    /**
     * Validate the DeviceTypeRepository.
     * @param deviceTypeService The DeviceTypeRepository to validate.
     */
    private void validateDeviceTypeService(DeviceTypeService deviceTypeService) {
        if (deviceTypeService == null) {
            throw new IllegalArgumentException("DeviceTypeService cannot be null.");
        }
    }


    public Map<DeviceType, List<DeviceDTO>> getDevicesDTOGroupedByFunctionality() {
        List<Device> devices = _deviceService.getAllDevices();

        if (devices.isEmpty()) {
            throw new IllegalArgumentException("No devices found.");
        }

        Map<DeviceType, List<DeviceDTO>> devicesGroupedByFunctionality = new LinkedHashMap<>();

        for (Device device : devices) {
            DeviceType deviceType = _deviceTypeService.getDeviceTypeByID(device.getDeviceTypeID()).get();

            if (devicesGroupedByFunctionality.containsKey(deviceType)) {
                devicesGroupedByFunctionality.get(deviceType).add(_deviceAssembler.domainToDTO(device));
            } else {
                List<DeviceDTO> newDeviceList = new ArrayList<>();
                newDeviceList.add(_deviceAssembler.domainToDTO(device));
                devicesGroupedByFunctionality.put(deviceType, newDeviceList);
            }
        }
        return devicesGroupedByFunctionality;
    }
}
