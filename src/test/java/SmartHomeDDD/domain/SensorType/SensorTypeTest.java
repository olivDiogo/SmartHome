package SmartHomeDDD.domain.SensorType;


import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.MockedConstruction;

import SmartHomeDDD.valueObject.SensorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.MeasurementID;

public class SensorTypeTest {

    /**
     * Test of constructor of class SensorType, when arguments are valid.
     */
    @Test
    public void shouldCreateSensorType_whenAttributesAreValid() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        MeasurementID unitDouble = mock(MeasurementID.class);
        String sensorTypeID = "1";

        try(MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class,(mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            // Act
            new SensorType(typeDescriptionDouble, unitDouble);

            // Assert
        }
    }

    /**
     * Test of constructor of class SensorType, when typeDescription is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_whenTypeDescriptionIsNull() {
        // Arrange
        TypeDescription typeDescriptionDouble = null;
        MeasurementID unitDouble = mock(MeasurementID.class);

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
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        MeasurementID unitDouble = null;

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
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        MeasurementID unitDouble = mock(MeasurementID.class);
        String sensorTypeID = "1";

        try(MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class,(mock, context) -> {
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
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        MeasurementID unitDouble = mock(MeasurementID.class);
        String sensorTypeID = "1";

        try(MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class,(mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

            // Act
            TypeDescription sensorTypeName = sensorType.getName();

            // Assert
            assertEquals(sensorTypeName, typeDescriptionDouble);
        }
    }

    /**
     * Test of getUnit method, of class SensorType.
     */
    @Test
    public void shouldReturnUnit_whenGetUnitIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        MeasurementID unitDouble = mock(MeasurementID.class);
        String sensorTypeID = "1";

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

            // Act
            MeasurementID unit = sensorType.getUnit();

            // Assert
            assertEquals(unit, unitDouble);

        }
    }

    /**
     * Test of equals method, of class SensorType, when comparing sensorType with itself.
     */
    @Test
    public void shouldGetTrue_whenSensorTypeIsEqualToItself(){
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        MeasurementID unitDouble = mock(MeasurementID.class);
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
    public void shouldGetTrue_whenSensorTypeIsEqualToAnotherSensorTypeWithSameID(){
        // Arrange
        TypeDescription typeDescriptionDouble1 = mock(TypeDescription.class);
        MeasurementID unitDouble1 = mock(MeasurementID.class);

        TypeDescription typeDescriptionDouble2 = mock(TypeDescription.class);
        MeasurementID unitDouble2 = mock(MeasurementID.class);

        String sensorTypeID = "1";

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            SensorType sensorType1 = new SensorType(typeDescriptionDouble1, unitDouble1);
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
    public void shouldGetFalse_whenSensorTypeIsComparedToAnotherSensorTypeWithDifferentID() throws NoSuchFieldException {
        // Arrange
        TypeDescription typeDescriptionDouble1 = mock(TypeDescription.class);
        MeasurementID unitDouble1 = mock(MeasurementID.class);

        TypeDescription typeDescriptionDouble2 = mock(TypeDescription.class);
        MeasurementID unitDouble2 = mock(MeasurementID.class);

        SensorTypeID sensorTypeID1 = new SensorTypeID("1");
        SensorTypeID sensorTypeID2 = new SensorTypeID("2");

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
        })) {
            SensorType sensorType1 = new SensorType(typeDescriptionDouble1, unitDouble1);
            SensorType sensorType2 = new SensorType(typeDescriptionDouble2, unitDouble2);

            Field sensorTypeIDField = SensorType.class.getDeclaredField("_id");
            sensorTypeIDField.setAccessible(true);
            sensorTypeIDField.set(sensorType1, sensorTypeID1);
            sensorTypeIDField.set(sensorType2, sensorTypeID2);

            // Act
            boolean result = sensorType1.equals(sensorType2);

            // Assert
            assertFalse(result);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldReturnID_whenGetIDIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        MeasurementID unitDouble = mock(MeasurementID.class);
        String sensorTypeID = "1";

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

            // Act
            SensorTypeID result = sensorType.getID();

            // Assert
            assertEquals(sensorTypeID, result.toString());

        }
    }

    /**
     * Test of toString method, of class SensorType.
     */
    @Test
    public void shouldGetStringWithAttributes_whenToStringIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        MeasurementID unitDouble = mock(MeasurementID.class);
        String sensorTypeID = "1";

        String expected = "ID: 1" +
                "\nTypeDescription: " + typeDescriptionDouble.toString() +
                "\nUnit: " + unitDouble.toString();

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

            // Act
            String result = sensorType.toString();

            // Assert
            assertEquals(expected, result);

        }
    }
}
