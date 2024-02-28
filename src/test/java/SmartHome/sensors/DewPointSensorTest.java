package SmartHome.sensors;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DewPointSensorTest {

    /**
     * Test if the sensor type is created correctly.
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void newValidDewPoint() throws InstantiationException
    {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorType sensorType = catalogue.addSensorType( "DewPoint", Unit.Temperature, new SensorTypeFactory());

        // act
        DewPointSensor dewPoint = new DewPointSensor( catalogue );

        // assert
        assertEquals( dewPoint.getSensorType(), sensorType );
    }

    /**
     * Test if the sensor type is not created correctly.
      * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void newNonexistentSensorTypeForDewPoint() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        String expectedMessage = "SensorType with description 'DewPoint' does not exist.";

        // act + assert
        Exception exception = assertThrows( InstantiationException.class, () ->
                new DewPointSensor( catalogue )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests if the sensor type is returned correctly.
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void testGetSensorType() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorType sensorType = catalogue.addSensorType( "DewPoint", Unit.Temperature, new SensorTypeFactory());

        // act
        DewPointSensor dewPoint = new DewPointSensor( catalogue );
        SensorType result = dewPoint.getSensorType();

        // assert
        assertEquals( sensorType, result );
    }

    /**
     * Tests if the sensor type is not returned correctly.
      * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void testGetWrongSensorType() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorType sensorType = catalogue.addSensorType( "DewPoint", Unit.Temperature, new SensorTypeFactory());
        SensorType sensorTypeWrong = catalogue.addSensorType( "Temperature", Unit.Temperature, new SensorTypeFactory());

        // act
        DewPointSensor dewPoint = new DewPointSensor( catalogue );
        SensorType result = dewPoint.getSensorType();

        // assert
        assertNotEquals( sensorTypeWrong, result );
    }

    /**
     * Tests if the value of the sensor is returned correctly.
      * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void shouldReturnTheDewPointValueDefined() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorType sensorType = catalogue.addSensorType( "DewPoint", Unit.Temperature, new SensorTypeFactory());
        int valueExpected = 25;

        // act
        DewPointSensor dewPoint = new DewPointSensor( catalogue );
        int valueResult = Integer.parseInt(dewPoint.getValue().toString());

        // assert
        assertEquals(valueExpected, valueResult);
    }

    /**
     * Tests if a wrong value of the sensor is not returned.
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    void testWrongDewPointValue() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor( "config.properties" );
        SensorType sensorType = catalogue.addSensorType( "DewPoint", Unit.Temperature, new SensorTypeFactory());

        int valueWrong = 30;

        // act
        DewPointSensor dewPoint = new DewPointSensor( catalogue );
        int valueResult = Integer.parseInt(dewPoint.getValue().toString());

        // assert
        assertNotEquals(valueWrong, valueResult);
    }

}