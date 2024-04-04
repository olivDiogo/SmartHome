package SmartHomeDDD.controller;

import SmartHomeDDD.Controller.US01ConfigureHouseLocationController;
import SmartHomeDDD.DTO.HouseDTO;
import SmartHomeDDD.DTO.HouseDataDTO;
import SmartHomeDDD.assembler.HouseAssembler;
import SmartHomeDDD.domain.House.ImpHouseFactory;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.service.HouseService;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertThrows;


/**
 * Tests for the US01ConfigureHouseLocationController class.
 */

class US01ConfigureHouseLocationControllerTest {

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
        new US01ConfigureHouseLocationController(houseService, houseAssembler);
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
        assertThrows(IllegalArgumentException.class, () -> {new US01ConfigureHouseLocationController(houseService, houseAssembler);});
    }

    /**
     * Verifies that an exception is thrown when the houseService parameter is null.
     */
    @Test
    void shouldThrowException_whenHouseServiceIsNull() {
        // Arrange: Initialize required components
        HouseRepository houseRepository = new HouseRepository();
        ImpHouseFactory houseFactory = new ImpHouseFactory();
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseService houseService = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {new US01ConfigureHouseLocationController(houseService, houseAssembler);});
    }

    /**
     * Verifies that an exception is thrown when both houseService and houseAssembler parameters are null.
     */
    @Test
    void shouldThrowException_whenHouseServiceIsNullAndHouseAssemblerIsNull() {
        // Arrange: Initialize required components
        HouseRepository houseRepository = new HouseRepository();
        ImpHouseFactory houseFactory = new ImpHouseFactory();
        HouseAssembler houseAssembler = null;
        HouseService houseService = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {new US01ConfigureHouseLocationController(houseService, houseAssembler);});
    }

    /**
     * Verifies that an exception is thrown when houseService, houseAssembler, and houseRepository parameters are null.
     */
    @Test
    void shouldThrowException_whenHouseServiceIsNullAndHouseAssemblerIsNullAndHouseRepositoryIsNull() {
        // Arrange: Initialize required components
        HouseRepository houseRepository = null;
        ImpHouseFactory houseFactory = null;
        HouseAssembler houseAssembler = null;
        HouseService houseService = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {new US01ConfigureHouseLocationController(houseService, houseAssembler);});
    }

    @Test
    void shouldThrowException_whenHouseServiceIsNullAndHouseAssemblerIsNullAndHouseRepositoryIsNullAndHouseFactoryIsNull() {
       // Arrange: Initialize required components
       HouseRepository houseRepository = new HouseRepository();
       ImpHouseFactory houseFactory = new ImpHouseFactory();
       HouseAssembler houseAssembler = new HouseAssembler();
       HouseService houseService = new HouseService(houseFactory, houseRepository);

       // Act & Assert
       assertThrows(IllegalArgumentException.class, () -> {new US01ConfigureHouseLocationController(null, null);});
    }

        /**
         * Verifies that an exception is thrown when houseService, houseAssembler, houseRepository, houseFactory, and houseID parameters are all null.
         */
    @Test
    void shouldThrowException_whenHouseServiceIsNullAndHouseAssemblerIsNullAndHouseRepositoryIsNullAndHouseFactoryIsNullAndHouseIDIsNull() {
        // Arrange: Initialize required components
       HouseRepository houseRepository = new HouseRepository();
       ImpHouseFactory houseFactory = new ImpHouseFactory();
       HouseAssembler houseAssembler = new HouseAssembler();
       HouseService houseService = new HouseService(houseFactory, houseRepository);

       // Act & Assert
       assertThrows(IllegalArgumentException.class, () -> {new US01ConfigureHouseLocationController(null, null);});
    }

        /**
         * Verifies that an exception is thrown when houseService, houseAssembler, houseRepository, houseFactory, houseID, and address parameters are all null.
         */
    @Test
    void shouldThrowException_whenHouseServiceIsNullAndHouseAssemblerIsNullAndHouseRepositoryIsNullAndHouseFactoryIsNullAndHouseIDIsNullAndAddressIsNull() {
        // Arrange: Initialize required components
        HouseRepository houseRepository = new HouseRepository();
        ImpHouseFactory houseFactory = new ImpHouseFactory();
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseService houseService = new HouseService(houseFactory, houseRepository);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {new US01ConfigureHouseLocationController(null, null);});
    }

        // Additional test for the configureHouseLocation method in the US01ConfigureHouseLocationController class

     @Test
     void shouldReturnHouseDTO_whenHouseIsConfigured() {
        // Arrange: Initialize required components
        HouseRepository houseRepository = new HouseRepository();
        ImpHouseFactory houseFactory = new ImpHouseFactory();
        HouseAssembler houseAssembler = new HouseAssembler();
        HouseService houseService = new HouseService(houseFactory, houseRepository);
        US01ConfigureHouseLocationController us01ConfigureHouseLocationController = new US01ConfigureHouseLocationController(houseService, houseAssembler);
        HouseDataDTO houseDataDTO = new HouseDataDTO("Rua do Ouro", "123", "4000-000", "PT", 41.14961, -8.61099);

        // Act & Assert
        HouseDTO result = us01ConfigureHouseLocationController.configureHouseLocation(houseDataDTO);
        }

}