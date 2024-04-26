package smart_home.controller;

import smart_home.ddd.IAssembler;
import smart_home.domain.house.House;
import smart_home.domain.service.IHouseService;
import smart_home.dto.HouseDTO;
import smart_home.dto.HouseDataDTO;
import smart_home.utils.Validator;
import smart_home.value_object.Address;
import smart_home.value_object.GPS;
import smart_home.value_object.PostalCodeFactory;

/**
 * Controller responsible for configuring the location of a house.
 */
public class ConfigureHouseLocationController {
    private final IHouseService _houseService;
    private final IAssembler<House, HouseDTO> _houseAssembler;

    /**
     * Constructs a new instance of US01ConfigureHouseLocationController with the provided dependencies.
     *
     * @param houseService   The service responsible for house-related operations.
     * @param houseAssembler The assembler responsible for converting between domain and DTO objects.
     * @throws IllegalArgumentException If either houseService or houseAssembler is null.
     */
    public ConfigureHouseLocationController(IHouseService houseService, IAssembler<House, HouseDTO> houseAssembler) {
      Validator.validateNotNull(houseService, "House service");
      Validator.validateNotNull(houseAssembler, "House assembler");

      this._houseAssembler = houseAssembler;
      this._houseService = houseService;
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

      return _houseAssembler.domainToDTO(house);
    }
}
