package SmartHomeDDD.domain.SensorType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import SmartHomeDDD.ValueObject.TypeDescription;
import SmartHomeDDD.ValueObject.UnitDescription;

import java.util.List;

import org.mockito.MockedConstruction;

public class SensorTypeTest {

    /**
     * Test of constructor of class SensorType, when arguments are valid.
     */
    @Test
    public void shouldCreateSensorType_whenAttributesAreValid() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        // Act
        new SensorType(typeDescriptionDouble, unitDescriptionDouble);
    }

    /**
     * Test of constructor of class SensorType, when typeDescription is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenTypeDescriptionIsNull() {
        // Arrange
        TypeDescription typeDescriptionDouble = null;
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        String expectedMessage = "Sensor type name must not be null.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SensorType(typeDescriptionDouble, unitDescriptionDouble);
        });

        // Assert
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test of constructor of class SensorType, when unit is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenUnitIsNull() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitDescription unitDescriptionDouble = null;

        String expectedMessage = "Unit must not be null.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SensorType(typeDescriptionDouble, unitDescriptionDouble);
        });

        // Assert
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test of getSensorTypeName method, of class SensorType.
     */
    @Test
    public void shouldReturnSensorTypeName_whenGetSensorTypeNameIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDescriptionDouble);

        // Act
        TypeDescription result = sensorType.getSensorTypeName();

        // Assert
        assertEquals(typeDescriptionDouble, result);
    }

    /**
     * Test of getUnit method, of class SensorType.
     */
    @Test
    public void shouldReturnUnit_whenGetUnitIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDescriptionDouble);

        // Act
        UnitDescription result = sensorType.getUnit();

        // Assert
        assertEquals(unitDescriptionDouble, result);
    }

    /**
     * Test of equals method, of class SensorType, when comparing sensorType with itself.
     */
    @Test
    public void shouldGetTrue_whenSensorTypeIsEqualToItself(){
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDescriptionDouble);

        // Act
        boolean result = sensorType.equals(sensorType);

        // Assert
        assertTrue(result);
    }

    /**
     * Test of equals method, of class SensorType, when comparing sensorType with another object with same ID.
     */
    @Test
    public void shouldGetTrue_whenSensorTypeIsEqualToAnotherSensorTypeWithSameID(){
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);

        UnitDescription unitDescriptionDouble1 = mock(UnitDescription.class);
        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDescriptionDouble1);

        UnitDescription unitDescriptionDouble2 = mock(UnitDescription.class);
        SensorType anotherSensorType = new SensorType(typeDescriptionDouble, unitDescriptionDouble2);

        // Act
        boolean result = sensorType.equals(anotherSensorType);

        // Assert
        assertTrue(result);
    }

    /**
     * Test of equals method, of class SensorType, when comparing sensorType with another object with different ID.
     */
    @Test
    public void shouldGetFalse_whenSensorTypeIsComparedToAnotherSensorTypeWithDifferentID(){
        // Arrange
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        TypeDescription typeDescriptionDouble1 = mock(TypeDescription.class);
        SensorType sensorType = new SensorType(typeDescriptionDouble1, unitDescriptionDouble);

        TypeDescription typeDescriptionDouble2 = mock(TypeDescription.class);
        SensorType anotherSensorType = new SensorType(typeDescriptionDouble2, unitDescriptionDouble);

        // Act
        boolean result = sensorType.equals(anotherSensorType);

        // Assert
        assertFalse(result);
    }

    @Test
    public void shouldReturnID_whenGetIDIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDescriptionDouble);

        // Act
        TypeDescription result = sensorType.getID();

        // Assert
        assertEquals(typeDescriptionDouble, result);
    }

    /**
     * Test of toString method, of class SensorType.
     */
    @Test
    public void shouldGetStringWithAttributes_whenToStringIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        when(typeDescriptionDouble.toString()).thenReturn("Temperature");

        UnitDescription unitDescriptionDouble = mock(UnitDescription.class);
        when(unitDescriptionDouble.toString()).thenReturn("Celsius");

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDescriptionDouble);

        String expectedString = "TypeDescription: Temperature\nUnit: Celsius";

        // Act
        String result = sensorType.toString();

        // Assert
        assertEquals(expectedString, result);
    }
}
