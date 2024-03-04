package SmartHome.sensors;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DewPointSensorTest {

    /**
     * Test if the sensor type is created correctly.
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void newValidDewPoint() throws InstantiationException {
        // arrange
        String description = "DewPoint";

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // act
        new DewPointSensor(catalogueSensorDouble);
    }

    /**
     * Test if the sensor type is not created correctly.
     */
    @Test
    void newNonexistentSensorTypeForDewPoint() {
        // arrange
        String description = "Temperature";

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(null);

        String expectedMessage = "SensorType with description 'DewPoint' does not exist.";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
                new DewPointSensor(catalogueSensorDouble)
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the sensor type is returned correctly.
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void testGetSensorType() throws InstantiationException {
        // arrange
        String description = "DewPoint";

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        DewPointSensor dewPoint = new DewPointSensor(catalogueSensorDouble);

        // act
        SensorType sensorType = dewPoint.getSensorType();

        // assert
        assertEquals(sensorType, sensorTypeDouble);

    }

    /**
     * Tests if the sensor type is not returned correctly.
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void testGetWrongSensorType() throws InstantiationException {
        // arrange
        String description = "DewPoint";

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorType wrongSensorType = mock(SensorType.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        DewPointSensor dewPoint = new DewPointSensor(catalogueSensorDouble);

        // act
        SensorType sensorType = dewPoint.getSensorType();

        // assert
        assertNotEquals(sensorType, wrongSensorType);
    }

    /**
     * Tests if the value is returned correctly.
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void testGetValue() throws InstantiationException {
        // arrange
        String description = "DewPoint";
        int value = 25;

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        DewPointValue dewPointValueDouble = mock(DewPointValue.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        when(dewPointValueDouble.clone()).thenReturn(dewPointValueDouble);
        when(dewPointValueDouble.toString()).thenReturn(Integer.toString(value));

        DewPointSensor dewPoint = new DewPointSensor(catalogueSensorDouble);

        // act
        Value valueReturned = dewPoint.getValue();

        // assert
        assertEquals(valueReturned.toString(), Integer.toString(value));
    }

    /**
     * Tests if the value is not returned correctly.
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void testGetWrongValue() throws InstantiationException {
        // arrange
        String description = "DewPoint";
        int value = 25;

        int wrongValue = 0;

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        DewPointValue dewPointValueDouble = mock(DewPointValue.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        when(dewPointValueDouble.clone()).thenReturn(dewPointValueDouble);
        when(dewPointValueDouble.toString()).thenReturn(Integer.toString(value));

        DewPointSensor dewPoint = new DewPointSensor(catalogueSensorDouble);

        // act
        Value valueReturned = dewPoint.getValue();

        // assert
        assertNotEquals(valueReturned.toString(), Integer.toString(wrongValue));
    }

    /**
     * Tests if the value is returned correctly.
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void getValue() throws InstantiationException {
        //Arrange
        String description = "DewPoint";
        int value = 25;

        int expectedSize = 1;

        try (MockedConstruction<DewPointValue> dewPointValueDouble = mockConstruction(DewPointValue.class, (mock, context) ->
                when(mock.toString()).thenReturn(Integer.toString(value)))) {

            CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
            SensorType sensorTypeDouble = mock(SensorType.class);
            when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

            DewPointSensor dewPoint = new DewPointSensor(catalogueSensorDouble);

            //Act
            dewPoint.getValue();

            //Assert
            List<DewPointValue> dewPointValues = dewPointValueDouble.constructed();
            assertEquals(expectedSize, dewPointValues.size());
            assertEquals(Integer.toString(value), dewPointValues.get(0).toString());

        }
    }


}