package SmartHome.controller;

import SmartHome.domain.*;
import SmartHome.dto.DeviceAssembler;
import SmartHome.dto.DeviceDTO;

import java.util.HashMap;
import java.util.List;

public class GetAllDevicesGroupedByFunctionalityController {
    private CatalogueSensors _catalogue;
    private House _house;


    /**
     * Constructor
     *
     * @param house is an instance of House
     * @throws InstantiationException if an error occurs
     */
    public GetAllDevicesGroupedByFunctionalityController(House house, CatalogueSensors catalogue) throws InstantiationException {
        if (isValidConstructorArguments(house, catalogue))
            throw new InstantiationException("Arguments cannot be null!");

        this._house = house;
        this._catalogue = catalogue;
    }

    /**
     * Validates the constructor arguments
     *
     * @param house     is an instance of House
     * @param catalogue is an instance of Catalogue
     * @return true if the arguments are valid, false otherwise
     */
    private boolean isValidConstructorArguments(House house, CatalogueSensors catalogue) {
        return house == null || catalogue == null;
    }

    /**
     * Gets all devices grouped by functionality
     *
     * @return a HashMap with the devices grouped by functionality
     */
    public HashMap<String, List<DeviceDTO>> getAllDevicesGroupedByFunctionality() {
        List<SensorType> copyOfSensorTypes = _catalogue.getSensorTypes();
        HashMap<String, List<DeviceDTO>> devicesDTOByFunctionality = DeviceAssembler.listByFunctionality(copyOfSensorTypes);

        for (Room room : _house.getRooms()) {
            for (Device device : room.getDevices()) {
                DeviceDTO deviceDTO = DeviceAssembler.domain2DTO(device, room);
                DeviceAssembler.addDeviceDTOToListByFunctionality(deviceDTO, devicesDTOByFunctionality);
            }
        }

        return new HashMap<>(devicesDTOByFunctionality);
    }
}
