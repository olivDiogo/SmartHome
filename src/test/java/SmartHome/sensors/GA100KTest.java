package SmartHome.sensors;

import SmartHome.domain.CatalogueSensors;
import SmartHome.domain.SensorType;
import SmartHome.domain.SensorTypeFactory;
import SmartHome.domain.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GA100KTest {
    @Test
    void newValidGA100K() throws InstantiationException
    {
        // arrange
        CatalogueSensors catalogue = new CatalogueSensors( "config.properties" );
        SensorType sensorType = catalogue.addSensorType( "Temperature", Unit.Temperature, new SensorTypeFactory());
        // act
        GA100K ga100k = new GA100K( catalogue );

        // assert
        assertEquals( ga100k.getSensorType(), sensorType );
        int value = Integer.parseInt( ga100k.getValue().toString() );
        assertTrue( value >= -70 && value <= 70 );
    }

    @Test
    void newNonexistentSensorTypeForGA100K() throws InstantiationException {
        // arrange
        CatalogueSensors catalogue = new CatalogueSensors( "config.properties" );
        //SensorType sensorType = catalogue.addSensorType( "Temperature", Unit.Temperature );
        String expectedMessage = "SensorType with description 'Temperature' does not exist.";

        // act + assert
        Exception exception = assertThrows( InstantiationException.class, () ->
                new GA100K( catalogue )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}