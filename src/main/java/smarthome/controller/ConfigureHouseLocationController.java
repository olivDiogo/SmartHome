package smarthome.controller;

import smarthome.ddd.IAssembler;
import smarthome.domain.house.House;
import smarthome.domain.service.IHouseService;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.utils.Validator;
import smarthome.utils.dto.HouseDTO;
import smarthome.utils.dto.HouseDataDTO;

/**
 * Controller responsible for configuring the location of a house.
 */
public class ConfigureHouseLocationController {

  private final IHouseService houseService;
  private final IAssembler<House, HouseDTO> houseAssembler;

  /**
   * Constructs a new instance of US01ConfigureHouseLocationController with the provided
   * dependencies.
   *
   * @param houseService   The service responsible for house-related operations.
   * @param houseAssembler The assembler responsible for converting between domain and DTO objects.
   * @throws IllegalArgumentException If either houseService or houseAssembler is null.
   */
  public ConfigureHouseLocationController(IHouseService houseService,
      IAssembler<House, HouseDTO> houseAssembler) {
    Validator.validateNotNull(houseService, "House service");
    Validator.validateNotNull(houseAssembler, "House assembler");

    this.houseAssembler = houseAssembler;
    this.houseService = houseService;
  }


  /**
   * Configures the location of a house based on the provided house data DTO.
   *
   * @param houseDataDTO The data transfer object containing house location information.
   * @return The DTO representing the configured house.
   */
  public HouseDTO configureHouseLocation(HouseDataDTO houseDataDTO) {
    Address address = new Address(houseDataDTO.street, houseDataDTO.doorNumber,
        houseDataDTO.postalCode, houseDataDTO.countryCode, new PostalCodeFactory());
    GPS gps = new GPS(houseDataDTO.latitude, houseDataDTO.longitude);

    House house = houseService.addHouse(address, gps);

    return houseAssembler.domainToDTO(house);
  }
}
