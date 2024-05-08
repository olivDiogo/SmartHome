package smarthome.controller.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.house.House;
import smarthome.domain.service.IHouseService;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.utils.dto.HouseDTO;
import smarthome.utils.dto.HouseDataDTO;

@RestController
@RequestMapping("/house")
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
   * @param houseDataDTO
   * @return
   */
  @PostMapping("/configure")
  public ResponseEntity<EntityModel<HouseDTO>> configureHouseLocation(
      @Valid @RequestBody HouseDataDTO houseDataDTO) {
    Address address = new Address(houseDataDTO.street, houseDataDTO.doorNumber,
        houseDataDTO.postalCode, houseDataDTO.countryCode, new PostalCodeFactory());
    GPS gps = new GPS(houseDataDTO.latitude, houseDataDTO.longitude);
    House house = houseService.addHouse(address, gps);
    HouseDTO dto = houseAssembler.domainToDTO(house);

    EntityModel<HouseDTO> resource = EntityModel.of(dto,
        linkTo(methodOn(HouseController.class).configureHouseLocation(houseDataDTO)).withSelfRel());

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);
  }
}
