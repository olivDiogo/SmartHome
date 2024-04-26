package smarthome.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.ddd.IAssembler;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.service.IHouseService;
import smarthome.mapper.HouseAssembler;
import smarthome.service.HouseServiceImpl;
import smarthome.utils.dto.HouseDTO;
import smarthome.utils.dto.HouseDataDTO;

class ConfigureHouseLocationControllerTest {

  /**
   * Verifies that US01ConfigureHouseLocationController can be instantiated with valid constructor
   * arguments.
   */
  @Test
  void shouldInstantiateUS01ConfigureHouseLocationController_whenConstructorArgumentsAreValid() {
    // Arrange
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IAssembler<House, HouseDTO> houseAssembler = new HouseAssembler();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    // Act
    ConfigureHouseLocationController result = new ConfigureHouseLocationController(houseServiceImpl,
        houseAssembler);

    // Assert
    assertNotNull(result);
  }

  /**
   * Verifies that an exception is thrown when the houseAssembler parameter is null.
   */
  @Test
  void shouldThrowException_whenHouseAssemblerIsNull() {
    // Arrange
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IAssembler<House, HouseDTO> houseAssembler = null;
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    String expectedMessage = "House assembler is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new ConfigureHouseLocationController(houseServiceImpl, houseAssembler);
    });

    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Verifies that an exception is thrown when the houseService parameter is null.
   */
  @Test
  void shouldThrowException_whenHouseServiceIsNull() {
    // Arrange: Initialize required components
    IAssembler<House, HouseDTO> houseAssembler = new HouseAssembler();
    IHouseService houseServiceImpl = null;

    String expectedMessage = "House service is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new ConfigureHouseLocationController(houseServiceImpl, houseAssembler);
    });

    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Verifies that the configureHouseLocation method returns a HouseDTO when a house is configured.
   */
  @Test
  void shouldReturnHouseDTO_whenHouseIsConfigured() {
    // Arrange: Initialize required components
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IAssembler<House, HouseDTO> houseAssembler = new HouseAssembler();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    ConfigureHouseLocationController configureHouseLocationController = new ConfigureHouseLocationController(
        houseServiceImpl, houseAssembler);

    String street = "Rua do Ouro";
    String doorNumber = "123";
    String postalCode = "4000-000";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act
    HouseDTO result = configureHouseLocationController.configureHouseLocation(houseDataDTO);

    // Assert
    assertNotNull(result);
  }

  /**
   * Verify that House is correctly configured when postal code is Spanish
   */
  @Test
  void shouldReturnHouseDTO_whenHouseIsConfiguredWithSpanishPostalCode() {
    // Arrange
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IAssembler<House, HouseDTO> houseAssembler = new HouseAssembler();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    ConfigureHouseLocationController configureHouseLocationController = new ConfigureHouseLocationController(
        houseServiceImpl, houseAssembler);

    String street = "Calle de la Paz";
    String doorNumber = "123";
    String postalCode = "28012";
    String countryCode = "ES";
    double latitude = 40.41536;
    double longitude = -3.70739;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act
    HouseDTO result = configureHouseLocationController.configureHouseLocation(houseDataDTO);

    // Assert
    assertNotNull(result);
  }

  /**
   * Verify that House is correctly configured when postal code is Canadian
   */
  @Test
  void shouldReturnHouseDTO_whenHouseIsConfiguredWithCanadianPostalCode() {
    // Arrange
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IAssembler<House, HouseDTO> houseAssembler = new HouseAssembler();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    ConfigureHouseLocationController configureHouseLocationController = new ConfigureHouseLocationController(
        houseServiceImpl, houseAssembler);

    String street = "123 Main St";
    String doorNumber = "123";
    String postalCode = "K1A 0B1";
    String countryCode = "CA";
    double latitude = 45.42153;
    double longitude = -75.69719;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act
    HouseDTO result = configureHouseLocationController.configureHouseLocation(houseDataDTO);

    // Assert
    assertNotNull(result);
  }

  /**
   * Verify that House is correctly configured when postal code is American
   */
  @Test
  void shouldReturnHouseDTO_whenHouseIsConfiguredWithAmericanPostalCode() {
    // Arrange
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IAssembler<House, HouseDTO> houseAssembler = new HouseAssembler();
    IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    ConfigureHouseLocationController configureHouseLocationController = new ConfigureHouseLocationController(
        houseServiceImpl, houseAssembler);

    String street = "1600 Amphitheatre Parkway";
    String doorNumber = "123";
    String postalCode = "94043";
    String countryCode = "US";
    double latitude = 37.4220;
    double longitude = -122.0841;
    HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode,
        latitude, longitude);

    // Act
    HouseDTO result = configureHouseLocationController.configureHouseLocation(houseDataDTO);

    // Assert
    assertNotNull(result);
  }
}
