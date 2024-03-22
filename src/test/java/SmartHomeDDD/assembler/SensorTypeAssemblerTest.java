package SmartHomeDDD.assembler;

import SmartHomeDDD.ValueObject.MeasurementTypeDescription;
import SmartHomeDDD.domain.SensorType.SensorType;
import SmartHomeDDD.DTO.SensorTypeDTO;
import SmartHomeDDD.ValueObject.TypeDescription;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SensorTypeAssemblerTest {

    /**
     * Tests the conversion of a sensor type to a sensor type DTO.
     */
    @Test
    public void shouldConvertSensorTypeToSensorTypeDTO_whenSensorTypeIsValid() {
        // Arrange
        String sensorTypeDescription = "Temperature";
        String unit = "Celsius";

        String expected = "SensorTypeDTO{" +
                ", _sensorTypeDescription='" + sensorTypeDescription + '\'' +
                ", _unit='" + unit + '\'' +
                '}';

        TypeDescription sensorTypeDescriptionDouble = mock(TypeDescription.class);
        when(sensorTypeDescriptionDouble.toString()).thenReturn(sensorTypeDescription);

        MeasurementTypeDescription unitDouble = mock(MeasurementTypeDescription.class);
        when(unitDouble.toString()).thenReturn(unit);

        SensorType sensorTypeDouble = mock(SensorType.class);
        when(sensorTypeDouble.getID()).thenReturn(sensorTypeDescriptionDouble);
        when(sensorTypeDouble.getUnit()).thenReturn(unitDouble);

        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        // Act
        SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorTypeDouble);

        // Assert
        assertEquals(expected, sensorTypeDTO.toString());
        assertEquals(sensorTypeDescription, sensorTypeDTO.sensorTypeDescription);
        assertEquals(unit, sensorTypeDTO.unit);
    }

    /**
     * Tests the conversion of a sensor type to a sensor type DTO when the sensor type is null.
     */
    @Test
    public void shouldThrowException_whenSensorTypeIsNull() {
        // Arrange
        SensorType sensorType = null;

        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        String expected = "Sensor type cannot be null.";

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sensorTypeAssembler.domainToDTO(sensorType);
        });

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Tests the conversion of a list of sensor types to a list of sensor type DTOs.
     */
    @Test
    public void shouldConvertListOfSensorTypesToListOfSensorTypeDTOs_whenSensorTypesAreValid() {
        // Arrange
        String sensorTypeDescription1 = "Temperature";
        String unit1 = "Celsius";

        String sensorTypeDescription2 = "Humidity";
        String unit2 = "Percentage";

        String expected1 = "SensorTypeDTO{" +
                ", _sensorTypeDescription='" + sensorTypeDescription1 + '\'' +
                ", _unit='" + unit1 + '\'' +
                '}';

        String expected2 = "SensorTypeDTO{" +
                ", _sensorTypeDescription='" + sensorTypeDescription2 + '\'' +
                ", _unit='" + unit2 + '\'' +
                '}';

        TypeDescription sensorTypeDescriptionDouble1 = mock(TypeDescription.class);
        when(sensorTypeDescriptionDouble1.toString()).thenReturn(sensorTypeDescription1);

        MeasurementTypeDescription unitDouble1 = mock(MeasurementTypeDescription.class);
        when(unitDouble1.toString()).thenReturn(unit1);

        SensorType sensorTypeDouble1 = mock(SensorType.class);
        when(sensorTypeDouble1.getID()).thenReturn(sensorTypeDescriptionDouble1);
        when(sensorTypeDouble1.getUnit()).thenReturn(unitDouble1);

        TypeDescription sensorTypeDescriptionDouble2 = mock(TypeDescription.class);
        when(sensorTypeDescriptionDouble2.toString()).thenReturn(sensorTypeDescription2);

        MeasurementTypeDescription unitDouble2 = mock(MeasurementTypeDescription.class);
        when(unitDouble2.toString()).thenReturn(unit2);

        SensorType sensorTypeDouble2 = mock(SensorType.class);
        when(sensorTypeDouble2.getID()).thenReturn(sensorTypeDescriptionDouble2);
        when(sensorTypeDouble2.getUnit()).thenReturn(unitDouble2);

        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        List<SensorType> sensorTypes = List.of(sensorTypeDouble1, sensorTypeDouble2);

        // Act
        List<SensorTypeDTO> sensorTypesDTO = sensorTypeAssembler.domainToDTO(sensorTypes);

        // Assert
        assertEquals(expected1, sensorTypesDTO.get(0).toString());
//        assertEquals(sensorTypeDescription1, sensorTypesDTO.get(0).sensorTypeDescription);
//        assertEquals(unit1, sensorTypesDTO.get(0).unit);

        assertEquals(expected2, sensorTypesDTO.get(1).toString());
//        assertEquals(sensorTypeDescription2, sensorTypesDTO.get(1).sensorTypeDescription);
//        assertEquals(unit2, sensorTypesDTO.get(1).unit);
    }

    /**
     * Tests the conversion of a list of sensor types to a list of sensor type DTOs when the list is null.
     */
    @Test
    public void shouldThrowException_whenListOfSensorTypesIsNull() {
        // Arrange
        List<SensorType> sensorTypes = null;

        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        String expected = "The list of sensor types cannot be null or empty.";

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sensorTypeAssembler.domainToDTO(sensorTypes);
        });

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Tests the conversion of a list of sensor types to a list of sensor type DTOs when the list is empty.
     */
    @Test
    public void shouldThrowException_whenListOfSensorTypesIsEmpty() {
        // Arrange
        List<SensorType> sensorTypes = List.of();

        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        String expected = "The list of sensor types cannot be null or empty.";

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sensorTypeAssembler.domainToDTO(sensorTypes);
        });

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Tests the conversion of a list of sensor types to a list of sensor types DTOs when the list contains a null sensor type.
     */
    @Test
    public void shouldThrowException_whenListOfSensorTypesContainsNullSensorType() {
        // Arrange
        String sensorTypeDescription1 = "Temperature";
        String unit1 = "Celsius";

        TypeDescription sensorTypeDescriptionDouble1 = mock(TypeDescription.class);
        when(sensorTypeDescriptionDouble1.toString()).thenReturn(sensorTypeDescription1);

        MeasurementTypeDescription unitDouble1 = mock(MeasurementTypeDescription.class);
        when(unitDouble1.toString()).thenReturn(unit1);

        SensorType sensorTypeDouble1 = mock(SensorType.class);
        when(sensorTypeDouble1.getID()).thenReturn(sensorTypeDescriptionDouble1);
        when(sensorTypeDouble1.getUnit()).thenReturn(unitDouble1);

        SensorType sensorTypeDouble2 = null;

        SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();

        List<SensorType> sensorTypes = new ArrayList<>();
        sensorTypes.add(sensorTypeDouble1);
        sensorTypes.add(sensorTypeDouble2);

        String expected = "Sensor type cannot be null.";

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sensorTypeAssembler.domainToDTO(sensorTypes);
        });

        // Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

}
