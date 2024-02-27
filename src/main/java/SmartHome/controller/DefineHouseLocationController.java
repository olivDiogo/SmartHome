package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.Location;
import SmartHome.dto.LocationAssembler;
import SmartHome.dto.LocationDTO;

public class DefineHouseLocationController {

    private final House _house;

    public DefineHouseLocationController(House house ) {
        if (isValidConstructorArguments(house))
            throw new IllegalArgumentException("Invalid arguments");
        _house = house;
    }
    private boolean isValidConstructorArguments(House house) {
        return house == null;
    }
    public LocationDTO defineHouseLocation(String street, String zipCode, int doorNumber, double latitude, double longitude) throws IllegalArgumentException {
            Location location = _house.defineLocation(street, zipCode, doorNumber, latitude, longitude);
            LocationDTO locationDTO = LocationAssembler.domain2DTO(location);
            return locationDTO;
    }
}
