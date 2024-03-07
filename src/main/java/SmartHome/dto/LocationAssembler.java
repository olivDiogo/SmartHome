package SmartHome.dto;


import SmartHome.domain.Location;

public class LocationAssembler {
    /**
     * Converts a Location domain object to a LocationDTO.
     *
     * @param location The Location domain object to be converted.
     * @return A LocationDTO object that represents the given Location.
     */
    static public LocationDTO domain2DTO(Location location) {
        return new LocationDTO(location);
    }
}
