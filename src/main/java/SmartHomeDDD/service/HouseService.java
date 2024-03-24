package SmartHomeDDD.service;

import SmartHomeDDD.ValueObject.Address;
import SmartHomeDDD.ValueObject.GPS;
import SmartHomeDDD.ValueObject.ZipCode;
import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.domain.House.HouseFactory;
import SmartHomeDDD.repository.HouseRepository;

import java.util.ArrayList;
import java.util.List;

public class HouseService {

    final HouseFactory houseFactory;

    final HouseRepository houseRepository;

    /**
     * Constructor for the HouseService class.
     *
     * @param houseFactory    the factory for creating House instances
     * @param houseRepository the repository for storing House instances
     */
    public HouseService(HouseFactory houseFactory, HouseRepository houseRepository) {
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
    private void validateHouseRepository(HouseRepository houseRepository) {
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

