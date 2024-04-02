package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.UnitDTO;
import SmartHomeDDD.valueObject.UnitID;
import SmartHomeDDD.valueObject.UnitDescription;
import SmartHomeDDD.domain.Unit.Unit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UnitAssemblerTest {

    @Test
    void shouldReturnMeasurementTypeDTO_WhenDomainToDTOIsCalledWithMeasurementType() {
        // Arrange
        UnitAssembler unitAssembler = new UnitAssembler();

        UnitID unitID = mock(UnitID.class);
        UnitDescription unitDescription = mock(UnitDescription.class);
        Unit unit = mock(Unit.class);
        when(unit.getID()).thenReturn(unitID);
        when(unitID.toString()).thenReturn("f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1d");
        when(unit.getUnitDescription()).thenReturn(unitDescription);
        when(unitDescription.toString()).thenReturn("Water Temperature in Celcius");
        // Act
        UnitDTO unitDTO = unitAssembler.domainToDTO(unit);

        // Assert
        assertEquals("Water Temperature in Celcius", unitDTO.description);
    }
    @Test
    void shouldThrowIllegalArgumentException_WhenDomainToDTOIsCalledWithNullMeasurementType() {
        // Arrange
        UnitAssembler unitAssembler = new UnitAssembler();
        String expectedMessage = "The MeasurementType cannot be null.";
        Unit unit = null;
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> unitAssembler.domainToDTO(unit));
        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldReturnMeasurementTypeDTOList_WhenDomainToDTOIsCalledWithListOfMeasurementType() {
        // Arrange
        UnitAssembler unitAssembler = new UnitAssembler();

        UnitID unitID = mock(UnitID.class);
        UnitDescription unitDescription = mock(UnitDescription.class);

        String measurementIDData = "f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1d";
        String measurementTypeDescriptionData = "Water Temperature in Celcius";


        Unit unit = mock(Unit.class);
        when(unit.getID()).thenReturn(unitID);
        when(unitID.toString()).thenReturn(measurementIDData);
        when(unit.getUnitDescription()).thenReturn(unitDescription);
        when(unitDescription.toString()).thenReturn(measurementTypeDescriptionData);

        Unit unit2 = mock(Unit.class);
        UnitID unitID2 = mock(UnitID.class);

        String measurementIDData2 = "f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1e";
        String measurementTypeDescriptionData2 = "Air Temperature in Celcius";

        UnitDescription unitDescription2 = mock(UnitDescription.class);
        when(unit2.getID()).thenReturn(unitID2);
        when(unitID2.toString()).thenReturn(measurementIDData2);
        when(unit2.getUnitDescription()).thenReturn(unitDescription2);
        when(unitDescription2.toString()).thenReturn(measurementTypeDescriptionData2);

        List <Unit> unitList = List.of(unit, unit2);

        //Expected list
        UnitDTO unitDTO = new UnitDTO(measurementIDData, measurementTypeDescriptionData);
        UnitDTO unitDTO2 = new UnitDTO(measurementIDData2, measurementTypeDescriptionData2);
        List <UnitDTO> expectedList = List.of(unitDTO, unitDTO2);


        // Act
        List<UnitDTO> actualList = unitAssembler.domainToDTO(unitList);

        // Assert
        assertEquals(expectedList.toString(), actualList.toString());

        }

    @Test
    void shouldThrowIllegalArgumentException_WhenDomainToDTOIsCalledWithNullListOfMeasurementType() {
        // Arrange
        UnitAssembler unitAssembler = new UnitAssembler();
        String expectedMessage = "The list of MeasurementTypes cannot be null.";
        List<Unit> unitList = null;
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> unitAssembler.domainToDTO(unitList));
        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

}