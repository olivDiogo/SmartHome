package SmartHomeDDD.domain.SensorType;

import SmartHomeDDD.valueObject.SensorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.UnitID;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorTypeTest {

    /**
     * Test of constructor of class SensorType, when arguments are valid.
     */
    @Test
    void shouldCreateSensorType_whenAttributesAreValid() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitDouble = mock(UnitID.class);

        // Act
        SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

        // Assert
        assertNotNull(sensorType);
    }


    /**
     * Test of constructor of class SensorType, when typeDescription is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_whenTypeDescriptionIsNull() {
        // Arrange
        TypeDescription typeDescriptionDouble = null;
        UnitID unitDouble = mock(UnitID.class);

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
    void shouldThrowIllegalArgumentException_whenUnitIsNull() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
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
    void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitDouble = mock(UnitID.class);
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
    void shouldReturnSensorTypeName_whenGetSensorTypeNameIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitDouble = mock(UnitID.class);
        String sensorTypeID = "1";

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
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
    void shouldReturnUnit_whenGetUnitIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitDouble = mock(UnitID.class);
        String sensorTypeID = "1";

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

            // Act
            UnitID unit = sensorType.getUnit();

            // Assert
            assertEquals(unit, unitDouble);

        }
    }

    /**
     * Tests equality on the same object instance.
     */
    @Test
    void shouldGetTrue_whenSensorTypeIsEqualToItself() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitDouble = mock(UnitID.class);

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class)) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

            // Act
            boolean result = sensorType.equals(sensorType);

            // Assert
            assertTrue(result);

        }
    }
    /**
     * Tests inequality on objects with different IDs.
     */
    @Test
    void shouldGetFalse_whenSensorTypeIsNotEqualToAnotherSensorType() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitDouble = mock(UnitID.class);

        TypeDescription typeDescriptionDouble2 = mock(TypeDescription.class);
        UnitID unitDouble2 = mock(UnitID.class);

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class)) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);
            SensorType sensorType2 = new SensorType(typeDescriptionDouble2, unitDouble2);

            // Act
            boolean result = sensorType.equals(sensorType2);

            // Assert
            assertFalse(result);

        }
    }

    /**
     * Tests inequality with null.
     */
    @Test
    void shouldGetFalse_whenWhenComparingObjectWithNull(){
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitDouble = mock(UnitID.class);

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class)) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

            // Act
            boolean result = sensorType.equals(null);

            // Assert
            assertFalse(result);

        }
    }


    /**
     * Test of toString method, of class SensorType.
     */
    @Test
    void shouldReturnAttributes_whenToStringIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitDouble = mock(UnitID.class);
        String sensorTypeID = "1";

        try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn(sensorTypeID);
        })) {
            SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);

            String expected = "SensorType:" +
                    "id=" + sensorTypeID +
                    ", name=" + typeDescriptionDouble +
                    ", unit=" + unitDouble;

            // Act
            String result = sensorType.toString();

            // Assert
            assertEquals(expected, result);

        }
    }

        /**
         * Test of hashCode method, of class SensorType.
         */
        @Test
        void shouldReturnHashCode_whenHashCodeIsCalled() {
            // Arrange
            TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
            UnitID unitDouble = mock(UnitID.class);


            try (MockedConstruction<SensorTypeID> sensorTypeIdDouble = mockConstruction(SensorTypeID.class)) {
                SensorType sensorType = new SensorType(typeDescriptionDouble, unitDouble);
                int expected = sensorType.getID().hashCode();

                // Act
                int result = sensorType.hashCode();

                // Assert
                assertEquals(expected, result);

            }

        }

    }


