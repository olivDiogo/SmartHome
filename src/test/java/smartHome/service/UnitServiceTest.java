package smartHome.service;

import smartHome.domain.unit.Unit;
import smartHome.domain.unit.IUnitFactory;
import smartHome.persistence.mem.UnitRepository;
import smartHome.valueObject.UnitDescription;
import smartHome.valueObject.UnitID;
import smartHome.valueObject.UnitSymbol;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UnitServiceTest {

    /**
     * Test that the constructor of MeasurementTypeService is instantiated correctly.
     */
    @Test
    void shouldInstantiateMeasurementTypeService_WhenConstructorInvoked() {
        // Arrange
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);
        IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

        // Act
        UnitService unitService = new UnitService(unitRepositoryDouble, unitFactoryDouble);

        // Assert
        assertNotNull(unitService);
    }

    /**
     * Test that the MeasurementTypeService can throw an IllegalArgumentException when the MeasurementType repository is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenMeasurementTypeRepositoryIsNull() {
        // Arrange
        UnitRepository unitRepositoryDouble = null;
        IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

        String expectedMessage = "Please enter a valid measurement type repository.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitService(unitRepositoryDouble, unitFactoryDouble));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the MeasurementTypeService can throw an IllegalArgumentException when the MeasurementType factory is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenMeasurementTypeFactoryIsNull() {
        // Arrange
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);
        IUnitFactory unitFactoryDouble = null;

        String expectedMessage = "Please enter a valid measurement type factory.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitService(unitRepositoryDouble, unitFactoryDouble));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the MeasurementTypeService can create and save a MeasurementType.
     */
    @Test
    void shouldCreateAndSaveMeasurementType_WhenParameterAreValid() {
        // Arrange
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);
        IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

        UnitService unitService = new UnitService(unitRepositoryDouble, unitFactoryDouble);

        UnitDescription description = mock(UnitDescription.class);
        UnitSymbol unit = mock(UnitSymbol.class);
        Unit unitDouble = mock(Unit.class);

        when(description.getDescription()).thenReturn("Temperature");
        when(unit.getUnit()).thenReturn("Celsius");

        when(unitFactoryDouble.createUnit(description, unit)).thenReturn(unitDouble);
        when(unitRepositoryDouble.save(unitDouble)).thenReturn(unitDouble);

        // Act
        Unit measurementUnit = unitService.createAndSaveMeasurementType(description, unit);

        // Assert
        assertEquals(unitDouble, measurementUnit);
    }

    /**
     * Test that the MeasurementTypeService can throw an IllegalArgumentException when the MeasurementType description is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenMeasurementTypeDescriptionIsNull() {
        // Arrange
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);
        IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

        UnitService unitService = new UnitService(unitRepositoryDouble, unitFactoryDouble);

        UnitDescription description = null;
        UnitSymbol unit = mock(UnitSymbol.class);

        String expectedMessage = "Measurement type description cannot be null or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> unitService.createAndSaveMeasurementType(description, unit));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the MeasurementTypeService can throw an IllegalArgumentException when the MeasurementType description is empty.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenMeasurementTypeDescriptionIsEmpty() {
        // Arrange
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);
        IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

        UnitService unitService = new UnitService(unitRepositoryDouble, unitFactoryDouble);

        UnitDescription description = mock(UnitDescription.class);
        UnitSymbol unit = mock(UnitSymbol.class);

        when(description.getDescription()).thenReturn("");

        String expectedMessage = "Measurement type description cannot be null or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> unitService.createAndSaveMeasurementType(description, unit));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the MeasurementTypeService can throw an IllegalArgumentException when the MeasurementType description is blank.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenMeasurementTypeDescriptionIsBlank() {
        // Arrange
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);
        IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

        UnitService unitService = new UnitService(unitRepositoryDouble, unitFactoryDouble);

        UnitDescription description = mock(UnitDescription.class);
        UnitSymbol unit = mock(UnitSymbol.class);

        when(description.getDescription()).thenReturn(" ");

        String expectedMessage = "Measurement type description cannot be null or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> unitService.createAndSaveMeasurementType(description, unit));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the MeasurementTypeService can throw an IllegalArgumentException when the MeasurementType unit is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenMeasurementTypeUnitIsNull() {
        // Arrange
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);
        IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

        UnitService unitService = new UnitService(unitRepositoryDouble, unitFactoryDouble);

        UnitDescription description = mock(UnitDescription.class);
        when(description.getDescription()).thenReturn("Temperature");
        UnitSymbol unit = null;

        String expectedMessage = "Measurement type unit cannot be null or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> unitService.createAndSaveMeasurementType(description, unit));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the MeasurementTypeService can throw an IllegalArgumentException when the MeasurementType unit is empty.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenMeasurementTypeUnitIsEmpty() {
        // Arrange
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);
        IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

        UnitService unitService = new UnitService(unitRepositoryDouble, unitFactoryDouble);

        UnitDescription description = mock(UnitDescription.class);
        when(description.getDescription()).thenReturn("Temperature");
        UnitSymbol unit = mock(UnitSymbol.class);

        when(unit.getUnit()).thenReturn("");

        String expectedMessage = "Measurement type unit cannot be null or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> unitService.createAndSaveMeasurementType(description, unit));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test findMeasurementTypeById method of MeasurementTypeService.
     */
    @Test
    void shouldReturnMeasurementType_WhenFindByIdInvoked() {
        // Arrange
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);
        IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

        UnitService unitService = new UnitService(unitRepositoryDouble, unitFactoryDouble);

        UnitID unitID = mock(UnitID.class);
        when(unitID.toString()).thenReturn("1");
        Unit unitDouble = mock(Unit.class);

        // Wrap the measurementTypeDouble in an Optional
        when(unitRepositoryDouble.ofIdentity(unitID)).thenReturn(Optional.of(unitDouble));

        // Act
        Optional<Unit> result = unitService.findMeasurementTypeById(unitID);

        // Assert
        assertTrue(result.isPresent()); // Ensure the result is present
        assertEquals(unitDouble, result.get()); // Compare the actual MeasurementType object
    }

    /**
     * Test findMeasurementTypeById method of MeasurementTypeService, when the MeasurementID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenFindByIdWithNullIDInvoked() {
        // Arrange
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);
        IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

        UnitService unitService = new UnitService(unitRepositoryDouble, unitFactoryDouble);

        String expectedMessage = "Please enter a valid sensor type ID.";
        UnitID unitID = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> unitService.findMeasurementTypeById(unitID));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }


    /**
     * Test that the MeasurementTypeService can return all MeasurementTypes in the repository.
     * Aims to ensure that the interaction between the service and the repository occurs as expected,ensuring that the data are effectively fetched from the repository.
     * This test is a good example of a test that ensures the correct interaction between the service and the repository.
     */
    @Test
    void shouldReturnAllMeasurementTypes_WhenFindAllMeasurementTypesInvoked() {
        // Arrange
        UnitRepository unitRepositoryDouble = mock(UnitRepository.class);
        IUnitFactory unitFactoryDouble = mock(IUnitFactory.class);

        UnitService unitService = new UnitService(unitRepositoryDouble, unitFactoryDouble);

        // Act
        List<Unit> result = unitService.getAllMeasurementTypes();

        // Assert
        assertEquals(result, unitRepositoryDouble.findAll());
    }

    /**
     * Test that the MeasurementTypeService can return a non-empty list when measurement types are available.
     * Focuses on ensuring that the service correctly handles the data received from the repository, especially in scenarios where data exists to be returned.
     * This test is a good example of a test that ensures the correct handling of data by the service.
     */
    @Test
    void shouldNotReturnEmptyList_WhenFindAllMeasurementTypesIsCalledWithAvailableTypes() {
        // Arrange
        UnitRepository unitRepository = mock(UnitRepository.class);
        IUnitFactory unitFactory = mock(IUnitFactory.class);
        UnitService unitService = new UnitService(unitRepository, unitFactory);
        Unit unit = mock(Unit.class);
        List<Unit> availableTypes = Arrays.asList(unit);

        when(unitRepository.findAll()).thenReturn(availableTypes);

        // Act
        List<Unit> result = unitService.getAllMeasurementTypes();

        // Assert
        assertFalse(result.isEmpty());
    }

}