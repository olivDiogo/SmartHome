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
    void shouldInstantiateDewPointSensor() throws InstantiationException {
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
    void shouldThrowException_whenSensorTypeIsIncorrect() {
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
    void shouldReturnSensorType() throws InstantiationException {
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
    void shouldThrowException_whenSensorTypeIsNull() throws InstantiationException {
        // arrange
        String description = "DewPoint";

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
     * Tests if the value is returned correctly.
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void shouldReturnDewPointValue() throws InstantiationException {
        //Arrange
        String description = "DewPoint";

        int expectedSize = 1;

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        DewPointSensor dewPoint = new DewPointSensor(catalogueSensorDouble);

        try (MockedConstruction<DewPointValue> dewPointValueDouble = mockConstruction(DewPointValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
                }))
                 {
            //Act
            Value value = dewPoint.getValue();

            //Assert
            List<DewPointValue> dewPointValues = dewPointValueDouble.constructed();
            assertEquals(expectedSize, dewPointValues.size());
            assertEquals("25", value.toString());
            assertEquals(dewPointValues.get(0).toString(), value.toString());

        }
    }

}