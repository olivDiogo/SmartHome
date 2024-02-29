package SmartHome.sensors;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SwitchSensorTest {
    @Test
    void newValidSwitchSensor() throws InstantiationException {
        // Arrange

        String description = "Switch";
        boolean initialState = false;

        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        SwitchSensor switchSensor = new SwitchSensor( catalogueDouble, new SwitchSensorValueFactory(), initialState );

        // Assert
        assertNotNull( switchSensor );
    }

    @Test
    void newInvalidSwitchSensor() {
        // Arrange
        String description = "Switch";
        boolean initialState = false;
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);

            when(catalogueDouble.getSensorType(description)).thenReturn(null);

            // Act
            Exception exception = assertThrows(InstantiationException.class, () -> new SwitchSensor( catalogueDouble, new SwitchSensorValueFactory(), initialState ));

            // Assert
            assertEquals( "SensorType with description 'Switch' does not exist.", exception.getMessage() );
    }

    @Test
    void getSensorTypeReturnsCorrectSensorType() throws InstantiationException {
        // Arrange
        String description = "Switch";
        boolean initialState = false;
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SwitchSensorValueFactory switchSensorValueFactoryDouble = mock(SwitchSensorValueFactory.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        SwitchSensor switchSensor = new SwitchSensor( catalogueDouble, switchSensorValueFactoryDouble, initialState);

        // Act
        SensorType result = switchSensor.getSensorType();

        // Assert
        assertEquals( sensorTypeDouble, result );
    }

    @Test
    void getSensorTypeReturnsWrongSensorType() throws InstantiationException {
        // Arrange
        String description = "Switch";
        boolean initialState = false;
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SwitchSensorValueFactory switchSensorValueFactoryDouble = mock(SwitchSensorValueFactory.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorType wrongSensorTypeDouble = mock(SensorType.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        SwitchSensor switchSensor = new SwitchSensor( catalogueDouble, switchSensorValueFactoryDouble, initialState);

        // Act
        SensorType result = switchSensor.getSensorType();

        // Assert
        assertNotEquals( wrongSensorTypeDouble, result );
    }

    @Test
    void getValueReturnsOff() throws InstantiationException {
        // Arrange
        String description = "Switch";
        boolean initialState = false;
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SwitchSensorValueFactory switchSensorValueFactoryDouble = mock(SwitchSensorValueFactory.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        SwitchSensorValue switchSensorValueDouble = mock(SwitchSensorValue.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        when(switchSensorValueFactoryDouble.create(initialState)).thenReturn(switchSensorValueDouble);
        // Configura o comportamento do clone() para o mock switchSensorValueDouble
        when(switchSensorValueDouble.clone()).thenReturn(switchSensorValueDouble);
        // Configura o comportamento esperado de toString() para o mock switchSensorValueDouble
        when(switchSensorValueDouble.toString()).thenReturn("Off");

        SwitchSensor switchSensor = new SwitchSensor(catalogueDouble, switchSensorValueFactoryDouble, initialState);

        // Act
        Value result = switchSensor.getValue();

        // Assert
        assertEquals("Off", result.toString());
    }


    @Test
    void getValueReturnsOn() throws InstantiationException {
        // Arrange
        String description = "Switch";
        boolean initialState = true;
        CatalogueSensor catalogueDouble = mock(CatalogueSensor.class);
        SwitchSensorValueFactory switchSensorValueFactoryDouble = mock(SwitchSensorValueFactory.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        SwitchSensorValue switchSensorValueDouble = mock(SwitchSensorValue.class);

        when(catalogueDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        when(switchSensorValueFactoryDouble.create(initialState)).thenReturn(switchSensorValueDouble);
        // Configura o comportamento do clone() para o mock switchSensorValueDouble
        when(switchSensorValueDouble.clone()).thenReturn(switchSensorValueDouble);
        // Configura o comportamento esperado de toString() para o mock switchSensorValueDouble
        when(switchSensorValueDouble.toString()).thenReturn("On");

        SwitchSensor switchSensor = new SwitchSensor(catalogueDouble, switchSensorValueFactoryDouble, initialState);

        // Act
        Value result = switchSensor.getValue();

        // Assert
        assertEquals("On", result.toString());
    }
}