package SmartHomeDDD.domain.House;

import SmartHomeDDD.valueObject.GPS;
import SmartHomeDDD.valueObject.Address;

/**
 * Implementation of the {@link HouseFactory} interface, responsible for creating
 * {@link House} instances. This factory encapsulates the logic for house creation,
 * ensuring that all necessary validations and initializations are performed before
 * a {@link House} object is returned to the caller.
 */
public class ImpHouseFactory implements HouseFactory {

    /**
     * Creates a new {@link House} instance using the provided address, zip code, and GPS location.
     * This method ensures that a {@link House} object is instantiated with valid and non-null
     * parameters, leveraging the House constructor for validation and initialization.
     *
     * @param address the address of the new house, must not be null
     * @param gps the GPS coordinates of the new house, must not be null
     * @return a fully initialized {@link House} instance
     * @throws IllegalArgumentException if any of the parameters are null, handled by the {@link House} constructor
     */
    @Override
    public House createHouse(Address address, GPS gps) throws IllegalArgumentException {
        return new House(address, gps);
    }
}

