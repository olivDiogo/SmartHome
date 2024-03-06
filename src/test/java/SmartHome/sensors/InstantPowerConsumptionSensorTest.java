package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InstantPowerConsumptionSensorTest {

    /**
     * Test if the sensor type is created correctly.
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    public void testConstructorInstantPowerConsumptionSensor() throws InstantiationException {
        //Arrange
        String description = "InstantPowerConsumption";

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        //Act
        new InstantPowerConsumptionSensor(catalogueSensorDouble);
    }

    /**
     * Test if the Instant Power Consumption Constructor returns a String Message because it is invalid.
     */
    @Test
    public void invalidInstantPowerConsumptionConstructor() {
        //Arrange
        String description = "InstantPowerConsumption";

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(null);

        String expectedMessage = "SensorType with description 'Instant Power Consumption' does not exist.";

        //Act + Assert
        Exception exception = assertThrows(InstantiationException.class, () ->
                new InstantPowerConsumptionSensor(catalogueSensorDouble));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test if the sensor type is returned correctly.
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    public void testGetSensorType() throws InstantiationException {
        String description = "InstantPowerConsumption";

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(catalogueSensorDouble);

        //Act
        SensorType sensorType = instantPowerConsumptionSensor.getSensorType();

        //Assert
        assertEquals(sensorType, sensorTypeDouble);
    }

    /**
     * Test if the sensor type is not returned correctly.
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    public void testGetWrongSensorType() throws InstantiationException {
        String description = "InstantPowerConsumption";

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorType wrongSensorType = mock(SensorType.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(catalogueSensorDouble);

        //act
        SensorType sensorType = instantPowerConsumptionSensor.getSensorType();

        //Assert
        assertNotEquals(sensorType, wrongSensorType);
    }

    /**
     * Test if the getValue method returns a positive instant power consumption value within the expected range.
     *
     * @throws InstantiationException If the sensor type "InstantPowerConsumption" does not exist.
     */
    @Test
    public void testGetValue() throws InstantiationException {
        //Arrange
        String description = "InstantPowerConsumption";
        double value = 25.0;

        int expectedSize = 1;

        try (MockedConstruction<InstantPowerConsumptionValue> instantPowerConsumptionValueDouble = mockConstruction(InstantPowerConsumptionValue.class, (mock, context) ->
                when(mock.toString()).thenReturn(Double.toString(value)))) {

            CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
            SensorType sensorTypeDouble = mock(SensorType.class);

            when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

            InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(catalogueSensorDouble);

            //Act
            instantPowerConsumptionSensor.getValue();

            //Assert
            List<InstantPowerConsumptionValue> instantPowerConsumptionValue = instantPowerConsumptionValueDouble.constructed();
            assertEquals(expectedSize, instantPowerConsumptionValue.size());
            assertEquals(Double.toString(value), instantPowerConsumptionValue.get(0).toString());

        }
    }

    /**
     * Test if the getValue method returns a positive instant power consumption value within the expected range.
     *
     * @throws InstantiationException If the sensor type "InstantPowerConsumption" does not exist.
     */
    @Test
    public void getNegativeValue() throws InstantiationException {
        //Arrange
        String description = "InstantPowerConsumption";
        double value = -25.0;

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        try (MockedConstruction<InstantPowerConsumptionValue> instantPowerConsumptionValueDouble = mockConstruction(InstantPowerConsumptionValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn(Double.toString(value));
        })) {
            InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(catalogueSensorDouble);

            //Act
            double instantPowerConsumptionValue = Double.parseDouble(instantPowerConsumptionSensor.getValue().toString());

            //Assert
            assertFalse(instantPowerConsumptionValue >= 0, "The instant power consumption value should be positive.");
        }

    }
}