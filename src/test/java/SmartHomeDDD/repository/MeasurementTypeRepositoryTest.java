package SmartHomeDDD.repository;

import SmartHomeDDD.domain.MeasurementType.MeasurementType;
import SmartHomeDDD.valueObject.MeasurementID;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MeasurementTypeRepositoryTest {

    /**
     * Test of MeasurementTypeRepository constructor.
     */
    @Test
    void shouldInstantiateMeasurementTypeRepository() {
        new MeasurementTypeRepository();
    }

    /**
     * Test of save method when given valid MeasurementType.
     */
    @Test
    void shouldSaveSensorType_whenGivenValidMeasurementType() {
        //Arrange
        MeasurementType MeasurementType = mock(MeasurementType.class);

        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();

        //Act
        MeasurementType savedMeasurementType = measurementTypeRepository.save(MeasurementType);

        //Assert
        assertEquals(MeasurementType, savedMeasurementType);
    }

    /**
     * Test of save method when given null MeasurementType.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullMeasurementType() {
        //Arrange
        MeasurementType measurementType = null;
        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();
        String expectedMessage = "MeasurementType cannot be null.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeRepository.save(measurementType));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test of save method when MeasurementType already exists.
     */
    @Test
    void shouldThrowException_whenMeasurementTypeAlreadyExists() {
        //Arrange
        MeasurementType measurementType = mock(MeasurementType.class);

        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();

        measurementTypeRepository.save(measurementType);
        String expectedMessage = "MeasurementType already exists.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeRepository.save(measurementType));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test of findAll method when there are MeasurementTypes saved.
     */
    @Test
    void shouldReturnAllMeasurementTypes_whenFindAllIsCalled() {
        //Arrange
        MeasurementType measurementType1 = mock(MeasurementType.class);
        MeasurementID measurementID1 = mock(MeasurementID.class);
        when(measurementType1.getID()).thenReturn(measurementID1);

        MeasurementType measurementType2 = mock(MeasurementType.class);
        MeasurementID measurementID2 = mock(MeasurementID.class);
        when(measurementType2.getID()).thenReturn(measurementID2);

        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();

        measurementTypeRepository.save(measurementType1);
        measurementTypeRepository.save(measurementType2);
        List<MeasurementType> expectedList = List.of(measurementType1, measurementType2);

        //Act
        List<MeasurementType> allMeasurementTypes = measurementTypeRepository.findAll();

        //Assert
        assertEquals(expectedList, allMeasurementTypes);
    }

    /**
     * Test of findAll method when there are no MeasurementTypes saved.
     */
    @Test
    void shouldReturnEmptyList_whenNoMeasurementTypesAreSaved() {
        //Arrange
        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();

        //Act
        List<MeasurementType> allMeasurementTypes = measurementTypeRepository.findAll();

        //Assert
        assertTrue(allMeasurementTypes.isEmpty());
    }

    /**
     * Test of ofIdentity method when given valid ID.
     */
    @Test
    void shouldReturnMeasurementType_whenGivenValidID() {
        //Arrange
        MeasurementType measurementType = mock(MeasurementType.class);
        MeasurementID measurementTypeID = mock(MeasurementID.class);
        when(measurementType.getID()).thenReturn(measurementTypeID);

        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();
        measurementTypeRepository.save(measurementType);

        //Act
        MeasurementType returnedMeasurementType = measurementTypeRepository.ofIdentity(measurementTypeID).get();

        //Assert
        assertEquals(measurementType, returnedMeasurementType);
    }

    /**
     * Test of ofIdentity method when given invalid ID.
     */
    @Test
    void shouldReturnOptionalEmpty_whenGivenInvalidID() {
        //Arrange
        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();

        MeasurementType measurementType = mock(MeasurementType.class);
        MeasurementID measurementTypeID = mock(MeasurementID.class);
        when(measurementType.getID()).thenReturn(measurementTypeID);

        measurementTypeRepository.save(measurementType);

        MeasurementID nonExistentID = mock(MeasurementID.class);

        //Act
        Optional<MeasurementType> returnedMeasurementType = measurementTypeRepository.ofIdentity(nonExistentID);

        //Assert
        assertTrue(returnedMeasurementType.isEmpty());
    }

    /**
     * Test of containsOfIdentity method when given valid ID.
     */
    @Test
    void shouldReturnTrue_whenGivenValidID() {
        //Arrange
        MeasurementType measurementType = mock(MeasurementType.class);
        MeasurementID measurementTypeID = mock(MeasurementID.class);
        when(measurementType.getID()).thenReturn(measurementTypeID);

        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();
        measurementTypeRepository.save(measurementType);

        //Act
        boolean containsMeasurementType = measurementTypeRepository.containsOfIdentity(measurementTypeID);

        //Assert
        assertTrue(containsMeasurementType);
    }

    /**
     * Test of containsOfIdentity method when given invalid ID.
     */
    @Test
    void shouldReturnFalse_whenGivenInvalidID() {
        //Arrange
        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();

        MeasurementType measurementType = mock(MeasurementType.class);
        MeasurementID measurementTypeID = mock(MeasurementID.class);
        when(measurementType.getID()).thenReturn(measurementTypeID);

        measurementTypeRepository.save(measurementType);

        MeasurementID nonExistentID = mock(MeasurementID.class);

        //Act
        boolean containsMeasurementType = measurementTypeRepository.containsOfIdentity(nonExistentID);

        //Assert
        assertFalse(containsMeasurementType);
    }

}
