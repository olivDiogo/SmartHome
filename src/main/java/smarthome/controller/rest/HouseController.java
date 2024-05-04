package smarthome.controller.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  public HouseController(IHouseService houseService,
      IAssembler<House, HouseDTO> houseAssembler) {
    this.houseAssembler = houseAssembler;
    this.houseService = houseService;
  }

  @PostMapping("/configure")
  public ResponseEntity<HouseDTO> configureHouseLocation(@Valid @RequestBody HouseDataDTO houseDataDTO) {
    Address address = new Address(houseDataDTO.street, houseDataDTO.doorNumber,
        houseDataDTO.postalCode, houseDataDTO.countryCode, new PostalCodeFactory());
    GPS gps = new GPS(houseDataDTO.latitude, houseDataDTO.longitude);

    House house = houseService.addHouse(address, gps);

    HouseDTO dto = houseAssembler.domainToDTO(house);
    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
  }
}
