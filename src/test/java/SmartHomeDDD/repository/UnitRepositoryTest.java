package SmartHomeDDD.repository;

import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.valueObject.UnitID;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnitRepositoryTest {

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
        Unit Unit = mock(Unit.class);

        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();

        //Act
        Unit savedUnit = measurementTypeRepository.save(Unit);

        //Assert
        assertEquals(Unit, savedUnit);
    }

    /**
     * Test of save method when given null MeasurementType.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullMeasurementType() {
        //Arrange
        Unit unit = null;
        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();
        String expectedMessage = "MeasurementType cannot be null.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeRepository.save(unit));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test of save method when MeasurementType already exists.
     */
    @Test
    void shouldThrowException_whenMeasurementTypeAlreadyExists() {
        //Arrange
        Unit unit = mock(Unit.class);

        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();

        measurementTypeRepository.save(unit);
        String expectedMessage = "MeasurementType already exists.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeRepository.save(unit));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test of findAll method when there are MeasurementTypes saved.
     */
    @Test
    void shouldReturnAllMeasurementTypes_whenFindAllIsCalled() {
        //Arrange
        Unit unit1 = mock(Unit.class);
        UnitID unitID1 = mock(UnitID.class);
        when(unit1.getID()).thenReturn(unitID1);

        Unit unit2 = mock(Unit.class);
        UnitID unitID2 = mock(UnitID.class);
        when(unit2.getID()).thenReturn(unitID2);

        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();

        measurementTypeRepository.save(unit1);
        measurementTypeRepository.save(unit2);
        List<Unit> expectedList = List.of(unit1, unit2);

        //Act
        List<Unit> allUnits = measurementTypeRepository.findAll();

        //Assert
        assertEquals(expectedList, allUnits);
    }

    /**
     * Test of findAll method when there are no MeasurementTypes saved.
     */
    @Test
    void shouldReturnEmptyList_whenNoMeasurementTypesAreSaved() {
        //Arrange
        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();

        //Act
        List<Unit> allUnits = measurementTypeRepository.findAll();

        //Assert
        assertTrue(allUnits.isEmpty());
    }

    /**
     * Test of ofIdentity method when given valid ID.
     */
    @Test
    void shouldReturnMeasurementType_whenGivenValidID() {
        //Arrange
        Unit unit = mock(Unit.class);
        UnitID measurementTypeID = mock(UnitID.class);
        when(unit.getID()).thenReturn(measurementTypeID);

        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();
        measurementTypeRepository.save(unit);

        //Act
        Unit returnedUnit = measurementTypeRepository.ofIdentity(measurementTypeID).get();

        //Assert
        assertEquals(unit, returnedUnit);
    }

    /**
     * Test of ofIdentity method when given invalid ID.
     */
    @Test
    void shouldReturnOptionalEmpty_whenGivenInvalidID() {
        //Arrange
        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();

        Unit unit = mock(Unit.class);
        UnitID measurementTypeID = mock(UnitID.class);
        when(unit.getID()).thenReturn(measurementTypeID);

        measurementTypeRepository.save(unit);

        UnitID nonExistentID = mock(UnitID.class);

        //Act
        Optional<Unit> returnedMeasurementType = measurementTypeRepository.ofIdentity(nonExistentID);

        //Assert
        assertTrue(returnedMeasurementType.isEmpty());
    }

    /**
     * Test of containsOfIdentity method when given valid ID.
     */
    @Test
    void shouldReturnTrue_whenGivenValidID() {
        //Arrange
        Unit unit = mock(Unit.class);
        UnitID measurementTypeID = mock(UnitID.class);
        when(unit.getID()).thenReturn(measurementTypeID);

        MeasurementTypeRepository measurementTypeRepository = new MeasurementTypeRepository();
        measurementTypeRepository.save(unit);

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

        Unit unit = mock(Unit.class);
        UnitID measurementTypeID = mock(UnitID.class);
        when(unit.getID()).thenReturn(measurementTypeID);

        measurementTypeRepository.save(unit);

        UnitID nonExistentID = mock(UnitID.class);

        //Act
        boolean containsMeasurementType = measurementTypeRepository.containsOfIdentity(nonExistentID);

        //Assert
        assertFalse(containsMeasurementType);
    }

}
