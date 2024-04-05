package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.HouseDTO;
import SmartHomeDDD.DTO.HouseDataDTO;
import SmartHomeDDD.assembler.HouseAssembler;
import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.service.HouseService;
import SmartHomeDDD.valueObject.Address;
import SmartHomeDDD.valueObject.GPS;
import SmartHomeDDD.valueObject.PostalCodeFactory;

/**
 * Controller responsible for configuring the location of a house.
 */
public class ConfigureHouseLocationController {
    private HouseService _houseService;
    private HouseAssembler _houseAssembler;

    /**
     * Constructs a new instance of US01ConfigureHouseLocationController with the provided dependencies.
     *
     * @param houseService   The service responsible for house-related operations.
     * @param houseAssembler The assembler responsible for converting between domain and DTO objects.
     * @throws IllegalArgumentException If either houseService or houseAssembler is null.
     */
    public ConfigureHouseLocationController(HouseService houseService, HouseAssembler houseAssembler) {
        validateHouseService(houseService);
        validateHouseAssembler(houseAssembler);
    }

    /**
     * Validates the provided houseService parameter.
     *
     * @param houseService The house service to validate.
     * @throws IllegalArgumentException If houseService is null.
     */
    private void validateHouseService(HouseService houseService) {
        if (houseService == null) {
            throw new IllegalArgumentException("HouseService cannot be null.");
        }
        this._houseService = houseService;
    }

    /**
     * Validates the provided houseAssembler parameter.
     *
     * @param houseAssembler The house assembler to validate.
     * @throws IllegalArgumentException If houseAssembler is null.
     */
    private void validateHouseAssembler(HouseAssembler houseAssembler) {
        if (houseAssembler == null) {
            throw new IllegalArgumentException("HouseAssembler cannot be null.");
        }
        this._houseAssembler = houseAssembler;
    }

    /**
     * Configures the location of a house based on the provided house data DTO.
     *
     * @param houseDataDTO The data transfer object containing house location information.
     * @return The DTO representing the configured house.
     */
    public HouseDTO configureHouseLocation(HouseDataDTO houseDataDTO) {
        Address address = new Address(houseDataDTO.street, houseDataDTO.doorNumber, houseDataDTO.postalCode, houseDataDTO.countryCode, new PostalCodeFactory());
        GPS gps = new GPS(houseDataDTO.latitude, houseDataDTO.longitude);

        House house = _houseService.addHouse(address, gps);
        HouseDTO houseDTO = _houseAssembler.domainToDTO(house);

        return houseDTO;
    }
}
