package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InstantPowerConsumptionSensorTest {

    /**
     * Test to get a valid InstantPowerConsumptionSensor object
     *
     * @throws InstantiationException If the sensor type does not exist.
     */
    @Test
    public void shouldInstantiateInstantPowerConsumptionSensor_WhenArgumentsAreValid() throws InstantiationException {
        //Arrange
        String description = "InstantPowerConsumption";

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        //Act
        new InstantPowerConsumptionSensor(catalogueSensorDouble);
    }

    /**
     * Test to get an invalid InstantPowerConsumptionSensor object should throw an InstantiationException.
     */
    @Test
    public void shouldThrowException_whenSensorIsNull() {
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
    public void shouldReturnSensorType() throws InstantiationException {
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
    public void shouldThrowException_WhenSensorTypeIsNull() throws InstantiationException {
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
    public void shouldReturnInstantPowerConsumptionValue() throws InstantiationException {
        //Arrange
        String description = "InstantPowerConsumption";

        int expectedSize = 1;

        CatalogueSensor catalogueSensorDouble = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogueSensorDouble.getSensorType(description)).thenReturn(sensorTypeDouble);

        InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(catalogueSensorDouble);

        try (MockedConstruction<InstantPowerConsumptionValue> instantPowerConsumptionValueDouble = mockConstruction(InstantPowerConsumptionValue.class, (mock, context) -> {
            when(mock.toString()).thenReturn(context.arguments().get(0).toString());
        })) {

            //Act
            Value value = instantPowerConsumptionSensor.getValue();

            //Assert
            List<InstantPowerConsumptionValue> instantPowerConsumptionValue = instantPowerConsumptionValueDouble.constructed();
            assertEquals(expectedSize, instantPowerConsumptionValue.size());
            assertEquals("25.0", value.toString());
            assertEquals (instantPowerConsumptionValue.get(0).toString(), value.toString());

        }
    }

}