package SmartHome.sensors;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwitchSensorTest {
    @Test
    void newValidSwitchSensor() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        String description = "Switch";
        CatalogueSensor catalogue = new CatalogueSensor( filePathName );
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
        CatalogueSensor catalogue = new CatalogueSensor( filePathName);
        String expectedMessage = "SensorType with description 'Switch' does not exist.";

        // Act & Assert
        Exception exception = assertThrows( InstantiationException.class, () ->
                new SwitchSensor( catalogue )
        );
        assertEquals( expectedMessage, exception.getMessage() );
    }

    @Test
    void getSensorTypeReturnsCorrectSensorType() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        String description = "Switch";
        CatalogueSensor catalogue = new CatalogueSensor( filePathName );
        catalogue.addSensorType( description, Unit.Switch, new SensorTypeFactory());
        SwitchSensor switchSensor = new SwitchSensor( catalogue );

        // Act
        SensorType result = switchSensor.getSensorType();

        // Assert
        assertEquals( description, result.getDescription() );
    }

    @Test
    void getSensorTypeReturnsWrongSensorType() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        String description = "Switch";
        CatalogueSensor catalogue = new CatalogueSensor( filePathName );
        catalogue.addSensorType( description, Unit.Switch, new SensorTypeFactory());
        SwitchSensor switchSensor = new SwitchSensor( catalogue );

        // Act
        SensorType result = switchSensor.getSensorType();

        // Assert
        assertNotEquals( "Temperature", result.getDescription() );
    }

    @Test
    void getValueReturnsOff() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        String description = "Switch";
        CatalogueSensor catalogue = new CatalogueSensor( filePathName );
        catalogue.addSensorType( description, Unit.Switch, new SensorTypeFactory());
        SwitchSensor switchSensor = new SwitchSensor( catalogue );

        // Act
        Value result = switchSensor.getValue();

        // Assert
        assertEquals( "Off", result.toString() );
    }
}