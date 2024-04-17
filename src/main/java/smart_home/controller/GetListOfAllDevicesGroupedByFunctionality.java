package smart_home.controller;

import smart_home.assembler.DeviceAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.device_type.DeviceType;
import smart_home.dto.DeviceDTO;
import smart_home.service.DeviceServiceImpl;
import smart_home.service.DeviceTypeServiceImpl;

import java.util.*;

public class GetListOfAllDevicesGroupedByFunctionality {
    private DeviceServiceImpl _deviceServiceImpl;
    private DeviceTypeServiceImpl _deviceTypeServiceImpl;
    private DeviceAssembler _deviceAssembler;


    /**
     * Constructor for GetListOfAllDevicesGroupedByFunctionality.
     *
     * @param deviceServiceImpl
     * @param deviceAssembler
     * @param deviceTypeServiceImpl
     */
    public GetListOfAllDevicesGroupedByFunctionality(DeviceServiceImpl deviceServiceImpl, DeviceAssembler deviceAssembler, DeviceTypeServiceImpl deviceTypeServiceImpl) {
        validateDeviceService(deviceServiceImpl);
        validateDeviceAssembler(deviceAssembler);
        validateDeviceTypeService(deviceTypeServiceImpl);
    }

    /**
     * Validate the DeviceService.
     *
     * @param deviceServiceImpl The DeviceService to validate.
     */
    private void validateDeviceService(DeviceServiceImpl deviceServiceImpl) {
        if (deviceServiceImpl == null) {
            throw new IllegalArgumentException("DeviceService cannot be null.");
        }
        _deviceServiceImpl = deviceServiceImpl;
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
     * @param deviceTypeServiceImpl The DeviceTypeRepository to validate.
     */
    private void validateDeviceTypeService(DeviceTypeServiceImpl deviceTypeServiceImpl) {
        if (deviceTypeServiceImpl == null) {
            throw new IllegalArgumentException("DeviceTypeService cannot be null.");
        }
        _deviceTypeServiceImpl = deviceTypeServiceImpl;
    }


    public Map<DeviceType, List<DeviceDTO>> getDevicesDTOGroupedByFunctionality() {
        List<Device> devices = _deviceServiceImpl.getAllDevices();

        if (devices.isEmpty()) {
            throw new IllegalArgumentException("No devices found.");
        }

        Map<DeviceType, List<DeviceDTO>> devicesGroupedByFunctionality = new LinkedHashMap<>();

        for (Device device : devices) {

            Optional<DeviceType> deviceType = _deviceTypeServiceImpl.getDeviceTypeByID(device.getDeviceTypeID());

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
