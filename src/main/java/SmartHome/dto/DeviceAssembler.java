package SmartHome.dto;

import SmartHome.domain.Device;
import SmartHome.domain.Room;
import SmartHome.domain.SensorType;

import java.util.*;

public class DeviceAssembler {

    /**
     * Converts a Device domain object to a DeviceDTO.
     *
     * @param device The Device domain object to be converted.
     * @return A DeviceDTO object that represents the given Device.
     */
    static public DeviceDTO domain2DTO(Device device) {
        DeviceDTO deviceDTO = new DeviceDTO(device);
        return deviceDTO;
    }

    /**
     * Converts a Device domain object and a Room domain object to a DeviceDTO.
     *
     * @param device The Device domain object to be converted.
     * @param room   The Room domain object to be associated with the DeviceDTO.
     * @return A DeviceDTO object that represents the given Device and Room.
     */
    static public DeviceDTO domain2DTO(Device device, Room room) {
        DeviceDTO deviceDTO = new DeviceDTO(device, room);
        return deviceDTO;
    }

    /**
     * Converts a list of Device domain objects to a list of DeviceDTOs.
     *
     * @param devices The list of Device domain objects to be converted.
     * @return A list of DeviceDTO objects that represent the given Devices.
     */
    static public List<DeviceDTO> domain2DTO(List<Device> devices) {
        List<DeviceDTO> deviceDTOs = devices.stream().map(DeviceDTO::new).toList();
        return deviceDTOs;
    }

    /**
     * Converts a list of Device domain objects to a map of DeviceDTOs and Devices.
     *
     * @param devices The list of Device domain objects to be converted.
     * @return A map where the keys are DeviceDTO objects that represent the given Devices, and the values are the original Device objects.
     */
    static public Map<DeviceDTO, Device> domain2DTOMapper(List<Device> devices) {
        Map<DeviceDTO, Device> devicesDTOAndDevices = new LinkedHashMap<>();

        devices.forEach(device -> {
            DeviceDTO deviceDTO = DeviceAssembler.domain2DTO(device);
            devicesDTOAndDevices.put(deviceDTO, device);
        });
        return devicesDTOAndDevices;
    }

    /**
     * Adds the sensor types as keys to the hashMap.
     *
     * @param sensorTypes is a list of SensorType.
     */
    static public HashMap<String, List<DeviceDTO>> listByFunctionality(List<SensorType> sensorTypes) {
        HashMap<String, List<DeviceDTO>> deviceDTOsByFunctionality = new HashMap<>();

        sensorTypes.forEach(sensorType -> {
            deviceDTOsByFunctionality.put(sensorType.getDescription(), new ArrayList<>());
        });

        return new HashMap<>(deviceDTOsByFunctionality);
    }

    /**
     * Adds a deviceDTO to the list of deviceDTOs by functionality.
     *
     * @param deviceDTO                 is an instance of DeviceDTO
     * @param deviceDTOsByFunctionality is a hashMap of String and List of DeviceDTO
     */
    static public void addDeviceDTOToListByFunctionality(DeviceDTO deviceDTO, HashMap<String, List<DeviceDTO>> deviceDTOsByFunctionality) {
        deviceDTO._deviceFunctionalities.forEach(functionality -> {
            deviceDTOsByFunctionality.get(functionality).add(deviceDTO);
        });

    }
}
