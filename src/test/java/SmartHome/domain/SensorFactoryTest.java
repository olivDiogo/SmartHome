package SmartHome.domain;


import SmartHome.sensors.TemperatureSensor;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SensorFactoryTest {

    /**
     * Test to create a sensor.
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    public void createSensor() throws InstantiationException {
        //Arrange
        String model = "SmartHome.sensors.TemperatureSensor";
        String description = "Temperature";

        SensorFactory sensorFactory = new SensorFactory();

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);
        try (MockedConstruction<TemperatureSensor> sensorMockedConstruction = Mockito.mockConstruction(TemperatureSensor.class)) {

            //Act
            Sensor sensor = sensorFactory.createSensor(model, catalogueSensorDouble);

            //Assert
            assertNotNull(sensor);
            assertEquals(sensor, sensorMockedConstruction.constructed().get(0));
        }

    }

    /**
     * Test to create a sensor with an invalid model.
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    public void createSensorShouldFailForInvalidModel() throws InstantiationException {
        // Arrange
        String invalidModel = "InvalidModel";
        String description = "Temperature";

        SensorFactory sensorFactory = new SensorFactory();

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        // Act
        Sensor sensor = sensorFactory.createSensor(invalidModel, catalogueSensorDouble);

        // Assert
        assertNull(sensor);
    }
}