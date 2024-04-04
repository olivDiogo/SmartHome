package SmartHomeDDD.domain.SensorType;


import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockedConstruction;

import SmartHomeDDD.valueObject.SensorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.UnitID;

public class SensorTypeTest {

    /**
     * Test of constructor of class SensorType, when arguments are valid.
     */
    @Test
    public void shouldCreateSensorType_whenAttributesAreValid() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        // Act
        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        // Assert
        assertNotNull(sensorType);
    }

    /**
     * Test of constructor of class SensorType, when typeDescription is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenTypeDescriptionIsNull() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = null;
        UnitID unitDouble = new UnitID(unitID);

        String expectedMessage = "Sensor type name must not be null.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SensorType(typeDescriptionDouble, unitDouble);
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
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = null;

        String expectedMessage = "Unit must not be null.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SensorType(typeDescriptionDouble, unitDouble);
        });

        // Assert
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test of getID method, of class SensorType.
     */
    @Test
    public void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        String sensorTypeID = "1";

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

            // Act
            SensorTypeID sensorTypeIDResult = sensorType.getID();

            // Assert
            assertEquals(sensorTypeID, sensorTypeIDResult.toString());
        }
    }

    /**
     * Test of getName method, of class SensorType.
     */
    @Test
    public void shouldReturnSensorTypeName_whenGetSensorTypeNameIsCalled() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);


        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        // Act
        TypeDescription sensorTypeName = sensorType.getName();

        // Assert
        assertEquals(sensorTypeName, typeDescriptionDouble);

    }

    /**
     * Test of getUnit method, of class SensorType.
     */
    @Test
    public void shouldReturnUnit_whenGetUnitIsCalled() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        // Act
        UnitID unit = sensorType.getUnit();

        // Assert
        assertEquals(unit, unitDouble);

    }


    /**
     * Test of equals method, of class SensorType, when comparing sensorType with itself.
     */
    @Test
    public void shouldGetTrue_whenSensorTypeIsEqualToItself() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        String sensorTypeID = "1";

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

            // Act
            boolean result = sensorType.equals(sensorType);

            // Assert
            assertTrue(result);

        }
    }

    /**
     * Test of equals method, of class SensorType, when comparing sensorType with another object with same ID.
     */
    @Test
    public void shouldGetTrue_whenSensorTypeIsEqualToAnotherSensorTypeWithSameID() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        TypeDescription typeDescriptionDouble2 = new TypeDescription(typeDescription);
        UnitID unitDouble2 = new UnitID(unitID);

        String sensorTypeID = "1";

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            SensorType sensorType1 = new SensorType(typeDescriptionDouble, unitDouble);
            SensorType sensorType2 = new SensorType(typeDescriptionDouble2, unitDouble2);

            // Act
            boolean result = sensorType1.equals(sensorType2);

            // Assert
            assertTrue(result);
        }
    }

    /**
     * Test of equals method, of class SensorType, when comparing sensorType with another object with different ID.
     */
    @Test
    public void shouldGetFalse_whenSensorTypeIsComparedToAnotherSensorTypeWithDifferentID() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        String typeDescription = "DewPointSensor";
        String typeDescription2 = "TemperatureSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        TypeDescription typeDescriptionDouble2 = new TypeDescription(typeDescription2);
        UnitID unitDouble2 = new UnitID(unitID);

        SensorType sensorType1 = new SensorType(typeDescriptionDouble, unitDouble);
        SensorType sensorType2 = new SensorType(typeDescriptionDouble2, unitDouble2);

        // Act
        boolean result = sensorType1.equals(sensorType2);

        // Assert
        assertFalse(result);

    }

    /**
     * Test of equals method, of class SensorType, when comparing sensorType with null object.
     */
    @Test
    void shouldReturnFalse_WhenComparingWithNullObject () {
        //Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        //Act
        boolean result = sensorType.equals(null);

        //Assert
        assertFalse(result);
    }

    /**
     * Should return the ID of the sensor type.
     */
    @Test
    public void shouldReturnID_whenGetIDIsCalled() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        String sensorTypeIDExpected = "DewPointSensor";

        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        // Act
        SensorTypeID result = sensorType.getID();

        // Assert
        assertEquals(sensorTypeIDExpected, result.toString());


    }

    /**
     * Test of toString method, of class SensorType.
     */
    @Test
    public void shouldGetStringWithAttributes_whenToStringIsCalled() {
        // Arrange
        String typeDescription = "DewPointSensor";
        String unitID = "Celsius";

        TypeDescription typeDescriptionDouble = new TypeDescription(typeDescription);
        UnitID unitDouble = new UnitID(unitID);

        String sensorTypeID = "1";

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

            String expected = "ID: 1" +
                    "\nTypeDescription: " + typeDescriptionDouble +
                    "\nUnit: " + unitDouble;

            // Act
            String result = sensorType.toString();

            // Assert
            assertEquals(expected, result);
        }
    }
}
