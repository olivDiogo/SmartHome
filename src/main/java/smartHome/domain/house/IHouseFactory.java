package smartHome.domain.house;

import smartHome.valueObject.Address;
import smartHome.valueObject.GPS;

/**
 * Interface defining a factory for creating {@link House} instances.
 * Provides a method to create a house with specific address, zip code, and GPS coordinates.
 */
public interface IHouseFactory {

    /**
     * Creates and returns a new {@link House} instance with the provided address, zip code, and GPS location.
     *
     * @param address the address of the new house
     * @param gps     the GPS coordinates of the new house
     * @return a newly created House instance
     */
    House createHouse(Address address, GPS gps);
}

