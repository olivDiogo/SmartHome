package smarthome.persistence.mem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitID;

class UnitRepositoryTest {

  /**
   * Test of save method when given valid MeasurementType.
   */
  @Test
  void shouldSaveSensorType_whenGivenValidMeasurementType() {
    //Arrange
    Unit Unit = mock(Unit.class);

    UnitRepository unitRepository = new UnitRepository();

    //Act
    Unit savedUnit = unitRepository.save(Unit);

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
    UnitRepository unitRepository = new UnitRepository();
    String expectedMessage = "MeasurementType is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> unitRepository.save(unit));

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

    UnitRepository unitRepository = new UnitRepository();

    unitRepository.save(unit);
    String expectedMessage = "MeasurementType already exists.";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> unitRepository.save(unit));

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

    UnitRepository unitRepository = new UnitRepository();

    unitRepository.save(unit1);
    unitRepository.save(unit2);
    List<Unit> expectedList = List.of(unit1, unit2);

    //Act
    List<Unit> allUnits = unitRepository.findAll();

    //Assert
    assertEquals(expectedList, allUnits);
  }

  /**
   * Test of findAll method when there are no MeasurementTypes saved.
   */
  @Test
  void shouldReturnEmptyList_whenNoMeasurementTypesAreSaved() {
    //Arrange
    UnitRepository unitRepository = new UnitRepository();

    //Act
    List<Unit> allUnits = unitRepository.findAll();

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

    UnitRepository unitRepository = new UnitRepository();
    unitRepository.save(unit);

    //Act
    Unit returnedUnit = unitRepository.ofIdentity(measurementTypeID).get();

    //Assert
    assertEquals(unit, returnedUnit);
  }

  /**
   * Test of ofIdentity method when given invalid ID.
   */
  @Test
  void shouldReturnOptionalEmpty_whenGivenInvalidID() {
    //Arrange
    UnitRepository unitRepository = new UnitRepository();

    Unit unit = mock(Unit.class);
    UnitID measurementTypeID = mock(UnitID.class);
    when(unit.getID()).thenReturn(measurementTypeID);

    unitRepository.save(unit);

    UnitID nonExistentID = mock(UnitID.class);

    //Act
    Optional<Unit> returnedMeasurementType = unitRepository.ofIdentity(nonExistentID);

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

    UnitRepository unitRepository = new UnitRepository();
    unitRepository.save(unit);

    //Act
    boolean containsMeasurementType = unitRepository.containsOfIdentity(measurementTypeID);

    //Assert
    assertTrue(containsMeasurementType);
  }

  /**
   * Test of containsOfIdentity method when given invalid ID.
   */
  @Test
  void shouldReturnFalse_whenGivenInvalidID() {
    //Arrange
    UnitRepository unitRepository = new UnitRepository();

    Unit unit = mock(Unit.class);
    UnitID measurementTypeID = mock(UnitID.class);
    when(unit.getID()).thenReturn(measurementTypeID);

    unitRepository.save(unit);

    UnitID nonExistentID = mock(UnitID.class);

    //Act
    boolean containsMeasurementType = unitRepository.containsOfIdentity(nonExistentID);

    //Assert
    assertFalse(containsMeasurementType);
  }

}
