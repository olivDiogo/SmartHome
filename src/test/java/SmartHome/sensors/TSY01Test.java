package SmartHome.sensors;

import SmartHome.domain.CatalogueSensors;
import SmartHome.domain.SensorType;
import SmartHome.domain.SensorTypeFactory;
import SmartHome.domain.Unit;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TSY01Test {
    @Test
    void NewValidTSY01() throws InstantiationException
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        CatalogueSensors catalogue = new CatalogueSensors( "config.properties" );
        SensorType sensorType = catalogue.addSensorType( "Humidity", Unit.Humidity, new SensorTypeFactory());
        // act
        TSY01 tsy01 = new TSY01( catalogue );

        // assert
        assertEquals( tsy01.getSensorType(), sensorType );
        double value = Double.parseDouble( tsy01.getValue().toString() );
        assertTrue( value >= 0 && value <= 100 );
    }

    @Test
    void NewInexistentSensorTypeForTSY01() throws InstantiationException {
        // arrange
        CatalogueSensors catalogue = new CatalogueSensors( "config.properties" );
        //SensorType sensorType = catalogue.addSensorType( "Humidity", Unit.Humidity, new SensorTypeFactory() );
        String expectedMessage = "SensorType with description 'Humidity' does not exist.";

        // act + assert
        Exception exception = assertThrows( InstantiationException.class, () ->
                new TSY01( catalogue )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}