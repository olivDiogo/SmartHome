/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest.unit_testing_deprecated;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import smarthome.controller.rest.HouseController;
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.mapper.HouseAssembler;
import smarthome.service.IHouseService;
import smarthome.utils.dto.HouseDTO;
import smarthome.utils.dto.data_dto.HouseDataDTO;

@SpringBootTest
class HouseControllerTest {

  @MockBean IHouseService houseService;
  @MockBean IHouseFactory houseFactory;
  @MockBean HouseAssembler houseAssembler;

  private HouseController houseController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    this.houseController = new HouseController(houseService, houseAssembler);
  }

  /**
   * Unit test to configure House location method
   */
  @Test
  void shouldCreateHouse_WhenParametersAreValid()  {
    // Arrange
    String street = "Rua de Sao Bento";
    String doorNumber = "123";
    String postalCode = "1200-109";
    String countryCode = "PT";
    double latitude = 38.7143;
    double longitude = -9.1459;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode, latitude, longitude);

    House mockHouse = mock(House.class);
    Address mockAddress = mock(Address.class);
    GPS mockGps = mock(GPS.class);
    HouseID mockId = mock(HouseID.class);
    when(mockAddress.toString()).thenReturn("Rua de Sao Bento 123 1200-109 PT");
    when(mockGps.toString()).thenReturn("38.7143 -9.1459");
    when(mockId.toString()).thenReturn("1");
    when(mockHouse.getAddress()).thenReturn(mockAddress);
    when(mockHouse.getGps()).thenReturn(mockGps);
    when(mockHouse.getID()).thenReturn(mockId);

    HouseDTO houseDTO = new HouseDTO("Rua de Sao Bento 123 1200-109 PT", "38.7143 -9.1459", "1");
    when(houseService.addHouse(any(Address.class), any(GPS.class))).thenReturn(mockHouse);
    when(houseAssembler.domainToDTO(mockHouse)).thenReturn(houseDTO);

    // Act
    ResponseEntity<EntityModel<HouseDTO>> response = houseController.createHouseLocation(houseDataDTO);

    // Assert
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("Rua de Sao Bento 123 1200-109 PT", response.getBody().getContent().address);
  }
}
