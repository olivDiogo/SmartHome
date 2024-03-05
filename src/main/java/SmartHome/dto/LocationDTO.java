package SmartHome.dto;


import SmartHome.domain.Location;

public class LocationDTO
{
    public final String _address;
    public final String _gps;

    public LocationDTO( Location location )
    {
        this._address = location.getAddress().toString();
        this._gps = location.getGpsLocation().toString();
    }
    @Override
    public String toString() {
        return "Location{" +
                _address +
                _gps +
                '}';
    }
}
