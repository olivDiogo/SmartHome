package smart_home.controller;

import smart_home.assembler.HouseAssembler;
import smart_home.domain.house.House;
import smart_home.domain.service.IHouseService;
import smart_home.dto.HouseDTO;
import smart_home.dto.HouseDataDTO;
import smart_home.value_object.Address;
import smart_home.value_object.GPS;
import smart_home.value_object.PostalCodeFactory;

/**
 * Controller responsible for configuring the location of a house.
 */
public class ConfigureHouseLocationController {
    private IHouseService _houseService;
    private HouseAssembler _houseAssembler;

    /**
     * Constructs a new instance of US01ConfigureHouseLocationController with the provided dependencies.
     *
     * @param houseService   The service responsible for house-related operations.
     * @param houseAssembler The assembler responsible for converting between domain and DTO objects.
     * @throws IllegalArgumentException If either houseService or houseAssembler is null.
     */
    public ConfigureHouseLocationController(IHouseService houseService, HouseAssembler houseAssembler) {
        validateHouseService(houseService);
        validateHouseAssembler(houseAssembler);
    }

    /**
     * Validates the provided houseService parameter.
     *
     * @param houseService The house service to validate.
     * @throws IllegalArgumentException If houseService is null.
     */
    private void validateHouseService(IHouseService houseService) {
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
