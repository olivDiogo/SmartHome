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
}
