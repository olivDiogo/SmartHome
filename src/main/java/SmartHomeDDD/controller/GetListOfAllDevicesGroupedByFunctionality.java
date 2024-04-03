package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.DeviceDTO;
import SmartHomeDDD.assembler.DeviceAssembler;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.domain.DeviceType.DeviceType;
import SmartHomeDDD.service.DeviceService;
import SmartHomeDDD.service.DeviceTypeService;

import java.util.*;

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
        validateDeviceAssembler(deviceAssembler);
        validateDeviceTypeService(deviceTypeService);
    }

    /**
     * Validate the DeviceService.
     *
     * @param deviceService The DeviceService to validate.
     */
    private void validateDeviceService(DeviceService deviceService) {
        if (deviceService == null) {
            throw new IllegalArgumentException("DeviceService cannot be null.");
        }
        _deviceService = deviceService;
    }

    /**
     * Validate the DeviceAssembler.
     *
     * @param deviceAssembler The DeviceAssembler to validate.
     */
    private void validateDeviceAssembler(DeviceAssembler deviceAssembler) {
        if (deviceAssembler == null) {
            throw new IllegalArgumentException("DeviceAssembler cannot be null.");
        }
        _deviceAssembler = deviceAssembler;
    }

    /**
     * Validate the DeviceTypeRepository.
     *
     * @param deviceTypeService The DeviceTypeRepository to validate.
     */
    private void validateDeviceTypeService(DeviceTypeService deviceTypeService) {
        if (deviceTypeService == null) {
            throw new IllegalArgumentException("DeviceTypeService cannot be null.");
        }
        _deviceTypeService = deviceTypeService;
    }


    public Map<DeviceType, List<DeviceDTO>> getDevicesDTOGroupedByFunctionality() {
        List<Device> devices = _deviceService.getAllDevices();

        if (devices.isEmpty()) {
            throw new IllegalArgumentException("No devices found.");
        }

        Map<DeviceType, List<DeviceDTO>> devicesGroupedByFunctionality = new LinkedHashMap<>();

        for (Device device : devices) {

            Optional<DeviceType> deviceType = _deviceTypeService.getDeviceTypeByID(device.getDeviceTypeID());

            if (deviceType.isPresent()) {
                DeviceType deviceTypeObj = deviceType.get();
                
                if (devicesGroupedByFunctionality.containsKey(deviceTypeObj)) {
                    devicesGroupedByFunctionality.get(deviceTypeObj).add(_deviceAssembler.domainToDTO(device));
                } else {
                    List<DeviceDTO> newDeviceList = new ArrayList<>();
                    newDeviceList.add(_deviceAssembler.domainToDTO(device));
                    devicesGroupedByFunctionality.put(deviceTypeObj, newDeviceList);
                }
            } else {
                throw new IllegalArgumentException("DeviceType not found.");
            }
        }
        return devicesGroupedByFunctionality;
    }
}
