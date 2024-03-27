package SmartHomeDDD.service;

import SmartHomeDDD.domain.MeasurementType.MeasurementType;
import SmartHomeDDD.domain.MeasurementType.MeasurementTypeFactory;
import SmartHomeDDD.repository.MeasurementTypeRepository;
import SmartHomeDDD.valueObject.MeasurementID;
import SmartHomeDDD.valueObject.MeasurementTypeDescription;
import SmartHomeDDD.valueObject.MeasurementTypeUnit;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MeasurementTypeServiceTest {

    /**
     * Test that the constructor of MeasurementTypeService is instantiated correctly.
     */
    @Test
    void shouldInstantiateMeasurementTypeService_WhenConstructorInvoked() {
        // Arrange
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        MeasurementTypeFactory measurementTypeFactoryDouble = mock(MeasurementTypeFactory.class);

        // Act & Assert
        new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble);
    }

    /**
     * Test that the MeasurementTypeService can throw an IllegalArgumentException when the MeasurementType repository is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenMeasurementTypeRepositoryIsNull() {
        // Arrange
        MeasurementTypeRepository measurementTypeRepositoryDouble = null;
        MeasurementTypeFactory measurementTypeFactoryDouble = mock(MeasurementTypeFactory.class);

        String expectedMessage = "Please enter a valid measurement type repository.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble));

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
        MeasurementTypeFactory measurementTypeFactoryDouble = null;

        String expectedMessage = "Please enter a valid measurement type factory.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble));

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
        MeasurementTypeFactory measurementTypeFactoryDouble = mock(MeasurementTypeFactory.class);

        MeasurementTypeService measurementTypeService = new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble);

        MeasurementTypeDescription description = mock(MeasurementTypeDescription.class);
        MeasurementTypeUnit unit = mock(MeasurementTypeUnit.class);
        MeasurementType measurementTypeDouble = mock(MeasurementType.class);

        when(description.getDescription()).thenReturn("Temperature");
        when(unit.getUnit()).thenReturn("Celsius");

        when(measurementTypeFactoryDouble.createMeasurement(description, unit)).thenReturn(measurementTypeDouble);
        when(measurementTypeRepositoryDouble.save(measurementTypeDouble)).thenReturn(measurementTypeDouble);

        // Act
        MeasurementType measurementType = measurementTypeService.createAndSaveMeasurementType(description, unit);

        // Assert
        assertEquals(measurementTypeDouble, measurementType);
    }

    /**
     * Test that the MeasurementTypeService can throw an IllegalArgumentException when the MeasurementType description is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenMeasurementTypeDescriptionIsNull() {
        // Arrange
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        MeasurementTypeFactory measurementTypeFactoryDouble = mock(MeasurementTypeFactory.class);

        MeasurementTypeService measurementTypeService = new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble);

        MeasurementTypeDescription description = null;
        MeasurementTypeUnit unit = mock(MeasurementTypeUnit.class);

        String expectedMessage = "Measurement type description cannot be null or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeService.createAndSaveMeasurementType(description, unit));

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
        MeasurementTypeFactory measurementTypeFactoryDouble = mock(MeasurementTypeFactory.class);

        MeasurementTypeService measurementTypeService = new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble);

        MeasurementTypeDescription description = mock(MeasurementTypeDescription.class);
        MeasurementTypeUnit unit = mock(MeasurementTypeUnit.class);

        when(description.getDescription()).thenReturn("");

        String expectedMessage = "Measurement type description cannot be null or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeService.createAndSaveMeasurementType(description, unit));

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
        MeasurementTypeFactory measurementTypeFactoryDouble = mock(MeasurementTypeFactory.class);

        MeasurementTypeService measurementTypeService = new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble);

        MeasurementTypeDescription description = mock(MeasurementTypeDescription.class);
        MeasurementTypeUnit unit = mock(MeasurementTypeUnit.class);

        when(description.getDescription()).thenReturn(" ");

        String expectedMessage = "Measurement type description cannot be null or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeService.createAndSaveMeasurementType(description, unit));

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
        MeasurementTypeFactory measurementTypeFactoryDouble = mock(MeasurementTypeFactory.class);

        MeasurementTypeService measurementTypeService = new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble);

        MeasurementTypeDescription description = mock(MeasurementTypeDescription.class);
        when(description.getDescription()).thenReturn("Temperature");
        MeasurementTypeUnit unit = null;

        String expectedMessage = "Measurement type unit cannot be null or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeService.createAndSaveMeasurementType(description, unit));

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
        MeasurementTypeFactory measurementTypeFactoryDouble = mock(MeasurementTypeFactory.class);

        MeasurementTypeService measurementTypeService = new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble);

        MeasurementTypeDescription description = mock(MeasurementTypeDescription.class);
        when(description.getDescription()).thenReturn("Temperature");
        MeasurementTypeUnit unit = mock(MeasurementTypeUnit.class);

        when(unit.getUnit()).thenReturn("");

        String expectedMessage = "Measurement type unit cannot be null or empty.";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeService.createAndSaveMeasurementType(description, unit));

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
        MeasurementTypeFactory measurementTypeFactoryDouble = mock(MeasurementTypeFactory.class);

        MeasurementTypeService measurementTypeService = new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble);

        MeasurementID measurementID = mock(MeasurementID.class);
        when(measurementID.toString()).thenReturn("1");
        MeasurementType measurementTypeDouble = mock(MeasurementType.class);

        // Wrap the measurementTypeDouble in an Optional
        when(measurementTypeRepositoryDouble.ofIdentity(measurementID)).thenReturn(Optional.of(measurementTypeDouble));

        // Act
        Optional<MeasurementType> result = measurementTypeService.findMeasurementTypeById(measurementID);

        // Assert
        assertTrue(result.isPresent()); // Ensure the result is present
        assertEquals(measurementTypeDouble, result.get()); // Compare the actual MeasurementType object
    }

    /**
     * Test findMeasurementTypeById method of MeasurementTypeService, when the MeasurementID is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenFindByIdWithNullIDInvoked() {
        // Arrange
        MeasurementTypeRepository measurementTypeRepositoryDouble = mock(MeasurementTypeRepository.class);
        MeasurementTypeFactory measurementTypeFactoryDouble = mock(MeasurementTypeFactory.class);

        MeasurementTypeService measurementTypeService = new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble);

        String expectedMessage = "Please enter a valid sensor type ID.";
        MeasurementID measurementID = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeService.findMeasurementTypeById(measurementID));

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
        MeasurementTypeFactory measurementTypeFactoryDouble = mock(MeasurementTypeFactory.class);

        MeasurementTypeService measurementTypeService = new MeasurementTypeService(measurementTypeRepositoryDouble, measurementTypeFactoryDouble);

        // Act
        List<MeasurementType> result = measurementTypeService.findAllMeasurementTypes();

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
        MeasurementTypeFactory measurementTypeFactory = mock(MeasurementTypeFactory.class);
        MeasurementTypeService measurementTypeService = new MeasurementTypeService(measurementTypeRepository, measurementTypeFactory);
        MeasurementType measurementType = mock(MeasurementType.class);
        List<MeasurementType> availableTypes = Arrays.asList(measurementType);

        when(measurementTypeRepository.findAll()).thenReturn(availableTypes);

        // Act
        List<MeasurementType> result = measurementTypeService.findAllMeasurementTypes();

        // Assert
        assertFalse(result.isEmpty());
    }

}