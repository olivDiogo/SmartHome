package SmartHomeDDD.domain;

import SmartHomeDDD.ValueObject.GPS;
import SmartHomeDDD.ValueObject.Address;
import SmartHomeDDD.ValueObject.ZipCode;

/**
 * Interface defining a factory for creating {@link House} instances.
 * Provides a method to create a house with specific address, zip code, and GPS coordinates.
 */
public interface HouseFactory {

    /**
     * Creates and returns a new {@link House} instance with the provided address, zip code, and GPS location.
     *
     * @param address the address of the new house
     * @param zipCode the zip code of the new house's location
     * @param gps the GPS coordinates of the new house
     * @return a newly created House instance
     */
    public House createHouse(Address address, ZipCode zipCode, GPS gps);
}

