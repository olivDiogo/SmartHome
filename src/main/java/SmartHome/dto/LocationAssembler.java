package SmartHome.dto;


import SmartHome.domain.Location;

public class LocationAssembler
{
    static public LocationDTO domain2DTO(Location location )
    {
        return new LocationDTO( location );
    }
}
