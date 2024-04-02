package SmartHomeDDD.service;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.valueObject.Address;
import SmartHomeDDD.valueObject.GPS;
import SmartHomeDDD.valueObject.HouseID;
import SmartHomeDDD.valueObject.ZipCode;
import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.domain.House.HouseFactory;
import SmartHomeDDD.repository.HouseRepository;

public class HouseService {

    final HouseFactory houseFactory;

    final Repository<HouseID,House> houseRepository;

    /**
     * Constructor for the HouseService class.
     *
     * @param houseFactory    the factory for creating House instances
     * @param houseRepository the repository for storing House instances
     */
    public HouseService(HouseFactory houseFactory, Repository<HouseID,House> houseRepository) {
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
    private void validateHouseFactory(HouseFactory houseFactory) {
        if (houseFactory == null) {
            throw new IllegalArgumentException("HouseFactory cannot be null.");
        }
    }

    /**
     * Validates that the HouseRepository is not null.
     *
     * @param houseRepository the HouseRepository to validate
     */
    private void validateHouseRepository(Repository<HouseID,House> houseRepository) {
        if (houseRepository == null) {
            throw new IllegalArgumentException("HouseRepository cannot be null.");
        }
    }

    public House addHouse(Address address, ZipCode zipCode, GPS gps) {
        House house = houseFactory.createHouse(address, zipCode, gps);
        houseRepository.save(house);
        return house;
    }
}

