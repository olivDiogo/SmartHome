package smart_home.controller;

import smart_home.assembler.DeviceAssembler;
import smart_home.ddd.IAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.device_type.DeviceType;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.IDeviceTypeService;
import smart_home.dto.DeviceDTO;


import java.util.*;

public class GetListOfAllDevicesGroupedByFunctionalityController {
    private IDeviceService _deviceService;
    private IDeviceTypeService _deviceTypeService;
    private IAssembler<Device, DeviceDTO> _deviceAssembler;


    /**
     * Constructor for GetListOfAllDevicesGroupedByFunctionality.
     *
     * @param deviceService  is the service for the device.
     * @param deviceAssembler is the assembler for the device.
     * @param deviceTypeService is the service for the device type.
     */
    public GetListOfAllDevicesGroupedByFunctionalityController(IDeviceService deviceService, IAssembler<Device, DeviceDTO> deviceAssembler, IDeviceTypeService deviceTypeService) {
        validateDeviceService(deviceService);
        validateDeviceAssembler(deviceAssembler);
        validateDeviceTypeService(deviceTypeService);
    }

    /**
     * Validate the DeviceService.
     *
     * @param deviceService The DeviceService to validate.
     */
    private void validateDeviceService(IDeviceService deviceService) {
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
    private void validateDeviceAssembler(IAssembler<Device, DeviceDTO> deviceAssembler) {
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
    private void validateDeviceTypeService(IDeviceTypeService deviceTypeService) {
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
