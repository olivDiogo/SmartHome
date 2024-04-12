package smartHome.service;

import smartHome.ddd.IRepository;
import smartHome.domain.house.House;
import smartHome.domain.house.IHouseFactory;
import smartHome.valueObject.Address;
import smartHome.valueObject.GPS;
import smartHome.valueObject.HouseID;

public class HouseService {

    final IHouseFactory houseFactory;

    final IRepository<HouseID, House> houseRepository;

    /**
     * Constructor for the HouseService class.
     *
     * @param houseFactory    the factory for creating House instances
     * @param houseRepository the repository for storing House instances
     */
    public HouseService(IHouseFactory houseFactory, IRepository<HouseID, House> houseRepository) {
        validateHouseFactory(houseFactory);
        validateHouseRepository(houseRepository);
        this.houseFactory = houseFactory;
        this.houseRepository = houseRepository;
    }

    /**
     * Validates that the HouseFactory is not null.
     *
     * @param houseFactory the HouseFactory to validate
     */
    private void validateHouseFactory(IHouseFactory houseFactory) {
        if (houseFactory == null) {
            throw new IllegalArgumentException("HouseFactory cannot be null.");
        }
    }

    /**
     * Validates that the HouseRepository is not null.
     *
     * @param houseRepository the HouseRepository to validate
     */
    private void validateHouseRepository(IRepository<HouseID, House> houseRepository) {
        if (houseRepository == null) {
            throw new IllegalArgumentException("HouseRepository cannot be null.");
        }
    }

    /**
     * Adds a new House to the repository.
     *
     * @param address the address of the house
     * @param gps     the GPS coordinates of the house
     * @return the newly created House
     */
    public House addHouse(Address address, GPS gps) {
        House house = houseFactory.createHouse(address, gps);
        houseRepository.save(house);
        return house;
    }
}

