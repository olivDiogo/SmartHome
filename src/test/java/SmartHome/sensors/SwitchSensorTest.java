package SmartHome.sensors;

import SmartHome.domain.CatalogueSensors;
import SmartHome.domain.SensorTypeFactory;
import SmartHome.domain.Unit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwitchSensorTest {
    @Test
    void newValidSwitchSensor() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        String description = "Switch";
        CatalogueSensors catalogue = new CatalogueSensors( filePathName );
        catalogue.addSensorType( description, Unit.Switch, new SensorTypeFactory());

        // Act
        SwitchSensor switchSensor = new SwitchSensor( catalogue );

        // Assert
        assertNotNull( switchSensor );
    }

    @Test
    void newInvalidSwitchSensor() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        CatalogueSensors catalogue = new CatalogueSensors( filePathName);
        String expectedMessage = "SensorType with description 'Switch' does not exist.";

        // Act & Assert
        Exception exception = assertThrows( InstantiationException.class, () ->
                new SwitchSensor( catalogue )
        );
        assertEquals( expectedMessage, exception.getMessage() );
    }

    @Test
    void getStatusReturnOff() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        String description = "Switch";
        CatalogueSensors catalogue = new CatalogueSensors( filePathName );
        catalogue.addSensorType( description, Unit.Switch, new SensorTypeFactory());
        SwitchSensor switchSensor = new SwitchSensor( catalogue );

        // Act
        String status = switchSensor.getStatus();

        // Assert
        assertEquals( "Off", status );
    }
}