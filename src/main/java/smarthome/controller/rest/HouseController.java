package smarthome.controller.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.house.House;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.service.IHouseService;
import smarthome.utils.dto.HouseDTO;
import smarthome.utils.dto.data_dto.HouseDataDTO;

@RestController
@RequestMapping("/houses")
public class HouseController {

  private final IHouseService houseService;
  private final IAssembler<House, HouseDTO> houseAssembler;

  /**
   * Constructor for HouseController
   */
  @Autowired
  public HouseController(IHouseService houseService,
      IAssembler<House, HouseDTO> houseAssembler) {
    this.houseAssembler = houseAssembler;
    this.houseService = houseService;
  }

  /**
   * Method to configure house location
   *
   * @param houseDataDTO is the house data DTO
   * @return ResponseEntity<EntityModel<HouseDTO>> is the response entity
   */
  @PostMapping
  public ResponseEntity<EntityModel<HouseDTO>> createHouseLocation(
      @Valid @RequestBody HouseDataDTO houseDataDTO) {
    Address address = new Address(houseDataDTO.street, houseDataDTO.doorNumber,
        houseDataDTO.postalCode, houseDataDTO.countryCode, new PostalCodeFactory());
    GPS gps = new GPS(houseDataDTO.latitude, houseDataDTO.longitude);
    House house = houseService.addHouse(address, gps);
    HouseDTO dto = houseAssembler.domainToDTO(house);

    Link selfLink = linkTo(methodOn(HouseController.class).createHouseLocation(houseDataDTO))
        .withSelfRel();
    Link houseLink = linkTo(methodOn(HouseController.class).getHouse())
        .withRel("house");

    EntityModel<HouseDTO> resource = EntityModel.of(dto, selfLink, houseLink);

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);
  }

  /**
   * Method to get the house
   */
  @GetMapping()
  public ResponseEntity<EntityModel<HouseDTO>> getHouse() {
    Optional<House> house = houseService.getHouse();
    if (house.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    HouseDTO dto = houseAssembler.domainToDTO(house.get());

    Link selfLink = linkTo(methodOn(HouseController.class).getHouse()).withSelfRel();

    EntityModel<HouseDTO> resource = EntityModel.of(dto, selfLink);

    return ResponseEntity.status(HttpStatus.OK).body(resource);
  }
}
