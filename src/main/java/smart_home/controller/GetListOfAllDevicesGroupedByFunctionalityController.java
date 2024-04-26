package smart_home.controller;

import smart_home.ddd.IAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.device_type.DeviceType;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.IDeviceTypeService;
import smart_home.dto.DeviceDTO;
import smart_home.utils.Validator;


import java.util.*;

public class GetListOfAllDevicesGroupedByFunctionalityController {
    private final IDeviceService _deviceService;
    private final IDeviceTypeService _deviceTypeService;
    private final IAssembler<Device, DeviceDTO> _deviceAssembler;


    /**
     * Constructor for GetListOfAllDevicesGroupedByFunctionality.
     *
     * @param deviceService  is the service for the device.
     * @param deviceAssembler is the assembler for the device.
     * @param deviceTypeService is the service for the device type.
     */
    public GetListOfAllDevicesGroupedByFunctionalityController(IDeviceService deviceService, IAssembler<Device, DeviceDTO> deviceAssembler, IDeviceTypeService deviceTypeService) {
      Validator.validateNotNull(deviceService, "Device service");
      Validator.validateNotNull(deviceAssembler, "Device assembler");
      Validator.validateNotNull(deviceTypeService, "Device type service");

      this._deviceAssembler = deviceAssembler;
      this._deviceService = deviceService;
      this._deviceTypeService = deviceTypeService;
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
