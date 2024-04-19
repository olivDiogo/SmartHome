package smart_home.mapper;

import org.junit.jupiter.api.Test;
import smart_home.domain.unit.Unit;
import smart_home.dto.UnitDTO;
import smart_home.value_object.UnitDescription;
import smart_home.value_object.UnitID;
import smart_home.value_object.UnitSymbol;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UnitAssemblerTest {

  /** Test if the constructor of the UnitAssembler class can be called. */
  @Test
  void shouldInstantiateANewUnitAssembler() {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();

    // Act + Assert
    assertNotNull(unitAssembler);
  }

  /** Test if the domainToDTO method returns a UnitDTO object when the unit is valid. */
  @Test
  void shouldReturnMeasurementTypeDTO_WhenDomainToDTOIsCalledWithMeasurementType() {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();

    UnitID unitID = mock(UnitID.class);
    UnitDescription unitDescription = mock(UnitDescription.class);
    Unit unit = mock(Unit.class);
    UnitSymbol unitSymbol = mock(UnitSymbol.class);

    when(unit.getID()).thenReturn(unitID);
    when(unitID.toString()).thenReturn("f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1d");
    when(unit.getUnitDescription()).thenReturn(unitDescription);
    when(unitDescription.toString()).thenReturn("Celcius");
    when(unit.getUnitSymbol()).thenReturn(unitSymbol);
    when(unitSymbol.toString()).thenReturn("C");

    String expected = unitID + " " + unitDescription + " " + unitSymbol;
    // Act
    UnitDTO unitDTO = unitAssembler.domainToDTO(unit);

    // Assert
    assertEquals(expected, unitDTO.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the MeasurementType is
   * null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDomainToDTOIsCalledWithNullMeasurementType() {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();
    String expectedMessage = "The MeasurementType cannot be null.";
    Unit unit = null;
    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> unitAssembler.domainToDTO(unit));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Test if the domainToDTO method returns a UnitDTO object when the unit is valid. */
  @Test
  void shouldReturnMeasurementTypeDTOList_WhenDomainToDTOIsCalledWithListOfMeasurementType() {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();

    UnitID unitID = mock(UnitID.class);
    UnitDescription unitDescription = mock(UnitDescription.class);
    UnitSymbol unitSymbol = mock(UnitSymbol.class);

    String measurementIDData = "f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1d";
    String measurementTypeDescriptionData = "Celcius";
    String measurementTypeSymbolData = "C";

    Unit unit = mock(Unit.class);
    when(unit.getID()).thenReturn(unitID);
    when(unitID.toString()).thenReturn(measurementIDData);
    when(unit.getUnitDescription()).thenReturn(unitDescription);
    when(unitDescription.toString()).thenReturn(measurementTypeDescriptionData);
    when(unit.getUnitSymbol()).thenReturn(unitSymbol);
    when(unitSymbol.toString()).thenReturn(measurementTypeSymbolData);

    Unit unit2 = mock(Unit.class);
    UnitID unitID2 = mock(UnitID.class);
    UnitSymbol unitSymbol2 = mock(UnitSymbol.class);
    String measurementIDData2 = "f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1e";
    String measurementTypeDescriptionData2 = "Percentage";
    String measurementTypeSymbolData2 = "%";

    UnitDescription unitDescription2 = mock(UnitDescription.class);
    when(unit2.getID()).thenReturn(unitID2);
    when(unitID2.toString()).thenReturn(measurementIDData2);
    when(unit2.getUnitDescription()).thenReturn(unitDescription2);
    when(unitDescription2.toString()).thenReturn(measurementTypeDescriptionData2);
    when(unit2.getUnitSymbol()).thenReturn(unitSymbol2);
    when(unitSymbol2.toString()).thenReturn(measurementTypeSymbolData2);

    List<Unit> unitList = Arrays.asList(unit, unit2);

    // Expected list
    UnitDTO unitDTO =
        new UnitDTO(measurementIDData, measurementTypeDescriptionData, measurementTypeSymbolData);
    UnitDTO unitDTO2 =
        new UnitDTO(
            measurementIDData2, measurementTypeDescriptionData2, measurementTypeSymbolData2);
    List<UnitDTO> expectedList = List.of(unitDTO, unitDTO2);

    // Act
    List<UnitDTO> actualList = unitAssembler.domainToDTO(unitList);

    // Assert
    assertEquals(expectedList.toString(), actualList.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the list of
   * MeasurementTypes is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDomainToDTOIsCalledWithNullListOfMeasurementType() {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();
    String expectedMessage = "The list of MeasurementTypes cannot be null.";
    List<Unit> unitList = null;
    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> unitAssembler.domainToDTO(unitList));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the list of
   * MeasurementTypes is empty.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDomainToDTOIsCalledWithEmptyListOfMeasurementType() {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();
    String expectedMessage = "The list of MeasurementTypes cannot be null.";
    List<Unit> unitList = List.of();
    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> unitAssembler.domainToDTO(unitList));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the list of
   * MeasurementTypes contains null.
   */
  @Test
  void
      shouldThrowIllegalArgumentException_WhenDomainToDTOIsCalledWithListOfMeasurementTypeContainingNull() {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();
    String expectedMessage = "The list of MeasurementTypes cannot be null.";
    Unit unit = mock(Unit.class);
    List<Unit> unitList = Arrays.asList(unit, null);
    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> unitAssembler.domainToDTO(unitList));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }
}
