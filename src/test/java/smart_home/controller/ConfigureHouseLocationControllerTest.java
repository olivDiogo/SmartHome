package smart_home.controller;

import org.junit.jupiter.api.Test;
import smart_home.assembler.HouseAssembler;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.dto.HouseDTO;
import smart_home.dto.HouseDataDTO;
import smart_home.persistence.mem.HouseRepository;
import smart_home.service.HouseServiceImpl;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertThrows;


/**
 * Tests for the US01ConfigureHouseLocationController class.
 */

class ConfigureHouseLocationControllerTest {

    /**
     * Verifies that US01ConfigureHouseLocationController can be instantiated with valid constructor arguments.
     */
    @Test
    void shouldInstantiateUS01ConfigureHouseLocationController_whenConstructorArgumentsAreValid() {
        // Arrange
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

        // Act
        ConfigureHouseLocationController result = new ConfigureHouseLocationController(houseServiceImpl, houseAssembler);

        // Assert
        assertNotNull(result);
    }

    /**
     * Verifies that an exception is thrown when the houseAssembler parameter is null.
     */
    @Test
    void shouldThrowException_whenHouseAssemblerIsNull() {
        // Arrange: Initialize required components
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseAssembler houseAssembler = null;
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {new ConfigureHouseLocationController(houseServiceImpl, houseAssembler);});
    }

    /**
     * Verifies that an exception is thrown when the houseService parameter is null.
     */
    @Test
    void shouldThrowException_whenHouseServiceIsNull() {
        // Arrange: Initialize required components
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseServiceImpl houseServiceImpl = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {new ConfigureHouseLocationController(houseServiceImpl, houseAssembler);});
    }

    /**
     * Verifies that the configureHouseLocation method returns a HouseDTO when a house is configured.
     */
     @Test
     void shouldReturnHouseDTO_whenHouseIsConfigured() {
        // Arrange: Initialize required components
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

        ConfigureHouseLocationController configureHouseLocationController = new ConfigureHouseLocationController(houseServiceImpl, houseAssembler);

        String street = "Rua do Ouro";
        String doorNumber = "123";
        String postalCode = "4000-000";
        String countryCode = "PT";
        double latitude = 41.14961;
        double longitude = -8.61099;
        HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode, latitude, longitude);

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
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

        ConfigureHouseLocationController configureHouseLocationController = new ConfigureHouseLocationController(houseServiceImpl, houseAssembler);

        String street = "Calle de la Paz";
        String doorNumber = "123";
        String postalCode = "28012";
        String countryCode = "ES";
        double latitude = 40.41536;
        double longitude = -3.70739;
        HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode, latitude, longitude);

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
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

        ConfigureHouseLocationController configureHouseLocationController = new ConfigureHouseLocationController(houseServiceImpl, houseAssembler);

        String street = "123 Main St";
        String doorNumber = "123";
        String postalCode = "K1A 0B1";
        String countryCode = "CA";
        double latitude = 45.42153;
        double longitude = -75.69719;
        HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode, latitude, longitude);

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
        HouseRepository houseRepository = new HouseRepository();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

        ConfigureHouseLocationController configureHouseLocationController = new ConfigureHouseLocationController(houseServiceImpl, houseAssembler);

        String street = "1600 Amphitheatre Parkway";
        String doorNumber = "123";
        String postalCode = "94043";
        String countryCode = "US";
        double latitude = 37.4220;
        double longitude = -122.0841;
        HouseDataDTO houseDataDTO = new HouseDataDTO(street, doorNumber, postalCode, countryCode, latitude, longitude);

        // Act
        HouseDTO result = configureHouseLocationController.configureHouseLocation(houseDataDTO);

        // Assert
        assertNotNull(result);
    }
}