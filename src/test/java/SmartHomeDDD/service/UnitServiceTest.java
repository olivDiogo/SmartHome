package SmartHomeDDD.service;

import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.domain.Unit.UnitFactory;
import SmartHomeDDD.repository.MeasurementTypeRepository;
import SmartHomeDDD.valueObject.UnitID;
import SmartHomeDDD.valueObject.UnitDescription;
import SmartHomeDDD.valueObject.UnitSymbol;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitServiceTest {

    /**
     * Test that the constructor of MeasurementTypeService is instantiated correctly.
     */
    @Test
    void shouldInstantiateMeasurementTypeService_WhenConstructorInvoked() {
        // Arrange
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactoryDouble = mock(UnitFactory.class);

        // Act & Assert
        new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble);
    }

    /**
     * Test that the MeasurementTypeService can throw an IllegalArgumentException when the MeasurementType repository is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenMeasurementTypeRepositoryIsNull() {
        // Arrange
        MeasurementTypeRepository measurementTypeRepositoryDouble = null;
        UnitFactory unitFactoryDouble = mock(UnitFactory.class);

        String expectedMessage = "Please enter a valid measurement type repository.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the MeasurementTypeService can throw an IllegalArgumentException when the MeasurementType factory is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenMeasurementTypeFactoryIsNull() {
        // Arrange
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactoryDouble = null;

        String expectedMessage = "Please enter a valid measurement type factory.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test that the MeasurementTypeService can create and save a MeasurementType.
     */
    @Test
    void shouldCreateAndSaveMeasurementType_WhenParameterAreValid() {
        // Arrange
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactoryDouble = mock(UnitFactory.class);

        UnitService unitService = new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble);

        UnitDescription description = mock(UnitDescription.class);
        UnitSymbol unit = mock(UnitSymbol.class);
        Unit unitDouble = mock(Unit.class);

        when(description.getDescription()).thenReturn("Temperature");
        when(unit.getUnit()).thenReturn("Celsius");

        when(unitFactoryDouble.createMeasurement(description, unit)).thenReturn(unitDouble);
        when(measurementTypeRepositoryDouble.save(unitDouble)).thenReturn(unitDouble);

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
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactoryDouble = mock(UnitFactory.class);

        UnitService unitService = new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble);

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
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactoryDouble = mock(UnitFactory.class);

        UnitService unitService = new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble);

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
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactoryDouble = mock(UnitFactory.class);

        UnitService unitService = new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble);

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
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactoryDouble = mock(UnitFactory.class);

        UnitService unitService = new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble);

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
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactoryDouble = mock(UnitFactory.class);

        UnitService unitService = new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble);

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
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactoryDouble = mock(UnitFactory.class);

        UnitService unitService = new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble);

        UnitID unitID = mock(UnitID.class);
        when(unitID.toString()).thenReturn("1");
        Unit unitDouble = mock(Unit.class);

        // Wrap the measurementTypeDouble in an Optional
        when(measurementTypeRepositoryDouble.ofIdentity(unitID)).thenReturn(Optional.of(unitDouble));

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
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactoryDouble = mock(UnitFactory.class);

        UnitService unitService = new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble);

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
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactoryDouble = mock(UnitFactory.class);

        UnitService unitService = new UnitService(measurementTypeRepositoryDouble, unitFactoryDouble);

        // Act
        List<Unit> result = unitService.findAllMeasurementTypes();

        // Assert
        assertEquals(result, measurementTypeRepositoryDouble.findAll());
    }

    /**
     * Test that the MeasurementTypeService can return a non-empty list when measurement types are available.
     * Focuses on ensuring that the service correctly handles the data received from the repository, especially in scenarios where data exists to be returned.
     * This test is a good example of a test that ensures the correct handling of data by the service.
     */
    @Test
    void shouldNotReturnEmptyList_WhenFindAllMeasurementTypesIsCalledWithAvailableTypes() {
        // Arrange
        MeasurementTypeRepository measurementTypeRepository = mock(MeasurementTypeRepository.class);
        UnitFactory unitFactory = mock(UnitFactory.class);
        UnitService unitService = new UnitService(measurementTypeRepository, unitFactory);
        Unit unit = mock(Unit.class);
        List<Unit> availableTypes = Arrays.asList(unit);

        when(measurementTypeRepository.findAll()).thenReturn(availableTypes);

        // Act
        List<Unit> result = unitService.findAllMeasurementTypes();

        // Assert
        assertFalse(result.isEmpty());
    }

}