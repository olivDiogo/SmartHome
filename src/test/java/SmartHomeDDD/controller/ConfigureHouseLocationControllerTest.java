package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.HouseDTO;
import SmartHomeDDD.DTO.HouseDataDTO;
import SmartHomeDDD.assembler.HouseAssembler;
import SmartHomeDDD.domain.House.ImpHouseFactory;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.service.HouseService;
import org.junit.jupiter.api.Test;

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
        ImpHouseFactory houseFactory = new ImpHouseFactory();
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseService houseService = new HouseService(houseFactory, houseRepository);

        // Act
        ConfigureHouseLocationController result = new ConfigureHouseLocationController(houseService, houseAssembler);

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
        ImpHouseFactory houseFactory = new ImpHouseFactory();
        HouseAssembler houseAssembler = null;
        HouseService houseService = new HouseService(houseFactory, houseRepository);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {new ConfigureHouseLocationController(houseService, houseAssembler);});
    }

    /**
     * Verifies that an exception is thrown when the houseService parameter is null.
     */
    @Test
    void shouldThrowException_whenHouseServiceIsNull() {
        // Arrange: Initialize required components
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseService houseService = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {new ConfigureHouseLocationController(houseService, houseAssembler);});
    }

    /**
     * Verifies that the configureHouseLocation method returns a HouseDTO when a house is configured.
     */
     @Test
     void shouldReturnHouseDTO_whenHouseIsConfigured() {
        // Arrange: Initialize required components
        HouseRepository houseRepository = new HouseRepository();
        ImpHouseFactory houseFactory = new ImpHouseFactory();
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseService houseService = new HouseService(houseFactory, houseRepository);

        ConfigureHouseLocationController configureHouseLocationController = new ConfigureHouseLocationController(houseService, houseAssembler);

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

}