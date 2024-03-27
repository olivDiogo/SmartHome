package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.MeasurementTypeDTO;
import SmartHomeDDD.valueObject.MeasurementID;
import SmartHomeDDD.valueObject.MeasurementTypeDescription;
import SmartHomeDDD.domain.MeasurementType.MeasurementType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MeasurementTypeAssemblerTest {

    @Test
    void shouldReturnMeasurementTypeDTO_WhenDomainToDTOIsCalledWithMeasurementType() {
        // Arrange
        MeasurementTypeAssembler measurementTypeAssembler = new MeasurementTypeAssembler();

        MeasurementID measurementID = mock(MeasurementID.class);
        MeasurementTypeDescription measurementTypeDescription = mock(MeasurementTypeDescription.class);
        MeasurementType measurementType = mock(MeasurementType.class);
        when(measurementType.getID()).thenReturn(measurementID);
        when(measurementID.toString()).thenReturn("f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1d");
        when(measurementType.getUnitDescription()).thenReturn(measurementTypeDescription);
        when(measurementTypeDescription.toString()).thenReturn("Water Temperature in Celcius");
        // Act
        MeasurementTypeDTO measurementTypeDTO = measurementTypeAssembler.domainToDTO(measurementType);

        // Assert
        assertEquals("Water Temperature in Celcius", measurementTypeDTO.description);
    }
    @Test
    void shouldThrowIllegalArgumentException_WhenDomainToDTOIsCalledWithNullMeasurementType() {
        // Arrange
        MeasurementTypeAssembler measurementTypeAssembler = new MeasurementTypeAssembler();
        String expectedMessage = "The MeasurementType cannot be null.";
        MeasurementType measurementType = null;
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeAssembler.domainToDTO(measurementType));
        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldReturnMeasurementTypeDTOList_WhenDomainToDTOIsCalledWithListOfMeasurementType() {
        // Arrange
        MeasurementTypeAssembler measurementTypeAssembler = new MeasurementTypeAssembler();

        MeasurementID measurementID = mock(MeasurementID.class);
        MeasurementTypeDescription measurementTypeDescription = mock(MeasurementTypeDescription.class);

        String measurementIDData = "f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1d";
        String measurementTypeDescriptionData = "Water Temperature in Celcius";


        MeasurementType measurementType = mock(MeasurementType.class);
        when(measurementType.getID()).thenReturn(measurementID);
        when(measurementID.toString()).thenReturn(measurementIDData);
        when(measurementType.getUnitDescription()).thenReturn(measurementTypeDescription);
        when(measurementTypeDescription.toString()).thenReturn(measurementTypeDescriptionData);

        MeasurementType measurementType2 = mock(MeasurementType.class);
        MeasurementID measurementID2 = mock(MeasurementID.class);

        String measurementIDData2 = "f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1e";
        String measurementTypeDescriptionData2 = "Air Temperature in Celcius";

        MeasurementTypeDescription measurementTypeDescription2 = mock(MeasurementTypeDescription.class);
        when(measurementType2.getID()).thenReturn(measurementID2);
        when(measurementID2.toString()).thenReturn(measurementIDData2);
        when(measurementType2.getUnitDescription()).thenReturn(measurementTypeDescription2);
        when(measurementTypeDescription2.toString()).thenReturn(measurementTypeDescriptionData2);

        List <MeasurementType> measurementTypeList = List.of(measurementType, measurementType2);

        //Expected list
        MeasurementTypeDTO measurementTypeDTO = new MeasurementTypeDTO(measurementIDData, measurementTypeDescriptionData);
        MeasurementTypeDTO measurementTypeDTO2 = new MeasurementTypeDTO(measurementIDData2, measurementTypeDescriptionData2);
        List <MeasurementTypeDTO> expectedList = List.of(measurementTypeDTO, measurementTypeDTO2);


        // Act
        List<MeasurementTypeDTO> actualList = measurementTypeAssembler.domainToDTO(measurementTypeList);

        // Assert
        assertEquals(expectedList.toString(), actualList.toString());

        }

    @Test
    void shouldThrowIllegalArgumentException_WhenDomainToDTOIsCalledWithNullListOfMeasurementType() {
        // Arrange
        MeasurementTypeAssembler measurementTypeAssembler = new MeasurementTypeAssembler();
        String expectedMessage = "The list of MeasurementTypes cannot be null.";
        List<MeasurementType> measurementTypeList = null;
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> measurementTypeAssembler.domainToDTO(measurementTypeList));
        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

}