package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SolarIrradianceSensorTest {

    /**
     * Test to get a valid SolarIrradianceSensor object
     */
    @Test
    void getValidSolarIrradianceSensor() {
        //Arrange
        String description = "SolarIrradiance";

        CatalogueSensor catalogue = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogue.getSensorType(description)).thenReturn(sensorTypeDouble);

        //Act
        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(catalogue);

        //Assert
        assertNotNull(solarIrradianceSensor);
    }

    /**
     * Test to get an invalid SolarIrradianceSensor object should throw an IllegalArgumentException
     */
    @Test
    void invalidSolarIrradianceSensor() {
        // Arrange
        String description = "SolarIrradiance";

        CatalogueSensor catalogue = mock(CatalogueSensor.class);
        when(catalogue.getSensorType(description)).thenReturn(null);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SolarIrradianceSensor(catalogue));

        // Assert
        assert exception.getMessage().equals("SensorType with description 'SolarIrradiance' does not exist.");
    }

    /**
     * Test to get the sensor type
     */
    @Test
    void getSensorType() {
        //Arrange
        String description = "SolarIrradiance";

        CatalogueSensor catalogue = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogue.getSensorType(description)).thenReturn(sensorTypeDouble);

        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(catalogue);

        //Act
        SensorType sensorType = solarIrradianceSensor.getSensorType();

        //Assert
        assertEquals(sensorType, sensorTypeDouble);
    }

    /**
     * Test to get the value of the sensor
     */
    @Test
    void getValue() {
        //Arrange
        String description = "SolarIrradiance";
        String value = "4500";

        CatalogueSensor catalogue = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);
        when(catalogue.getSensorType(description)).thenReturn(sensorTypeDouble);

        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(catalogue);

        //Act
        Value result = solarIrradianceSensor.getValue();

        //Assert
        assertEquals(value, result.toString());

    }
}
