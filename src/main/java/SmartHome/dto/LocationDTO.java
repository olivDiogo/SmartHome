package SmartHome.dto;


import SmartHome.domain.Location;

public class LocationDTO {
    public final String _address;
    public final String _gps;

    /**
     * Constructor for the LocationDTO class.
     *
     * @param location The Location object from which to create the DTO.
     */
    public LocationDTO(Location location) {
        this._address = location.getAddress().toString();
        this._gps = location.getGpsLocation().toString();
    }

    /**
     * Returns a string representation of the LocationDTO object.
     *
     * @return A string representation of the LocationDTO object.
     */
    @Override
    public String toString() {
        return "Location{" +
                _address +
                _gps +
                '}';
    }
}
