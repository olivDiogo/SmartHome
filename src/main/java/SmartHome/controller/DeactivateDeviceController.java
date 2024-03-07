package SmartHome.controller;

import SmartHome.domain.Device;
import SmartHome.domain.House;
import SmartHome.dto.DeviceAssembler;
import SmartHome.dto.DeviceDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DeactivateDeviceController {
    private House _house;
    private Map<DeviceDTO, Device> _devicesDTOAndDevices;
    public DeactivateDeviceController(House house) throws IllegalArgumentException {
        if (!isValidConstructorArguments(house))
            throw new IllegalArgumentException("Invalid arguments");
        this._house = house;
    }

    private boolean isValidConstructorArguments(House house) {
        return house != null;
    }

    public List<DeviceDTO> getDeviceList() {
        List<Device> allDevices = _house.getAllDevices();

        _devicesDTOAndDevices = DeviceAssembler.domain2DTOMapper(allDevices);

        return List.copyOf(_devicesDTOAndDevices.keySet().stream().toList());
    }

    public Optional<DeviceDTO> deactivateDevice(DeviceDTO deviceDTO) {
        Optional<Device> device = Optional.ofNullable(_devicesDTOAndDevices.get(deviceDTO));
        if (device.isPresent()) {
            device.get().deactivateDevice();
            return Optional.of(DeviceAssembler.domain2DTO(device.get()));
        }
        return Optional.empty();
    }
}
