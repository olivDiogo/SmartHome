package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SolarIrradianceSensorTest {

    /**
     * Test to get a valid SolarIrradianceSensor object
     */
    @Test
    void getValidSolarIrradianceSensor(){
        //Arrange
        String description = "SolarIrradiance";
        double value = 10;

        CatalogueSensor catalogue = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogue.getSensorType(description)).thenReturn(sensorTypeDouble);

        //Act
        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(catalogue, value);

        //Assert
        assertNotNull(solarIrradianceSensor);
    }

    /**
     * Test to get an invalid SolarIrradianceSensor object should throw an IllegalArgumentException
     */
    @Test
    void invalidSolarIrradianceSensor(){
        // Arrange
        String description = "SolarIrradiance";
        double value = 10;

        CatalogueSensor catalogue = mock(CatalogueSensor.class);
        when(catalogue.getSensorType(description)).thenReturn(null);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SolarIrradianceSensor(catalogue, value));

        // Assert
        assert exception.getMessage().equals("SensorType with description 'SolarIrradiance' does not exist.");
    }

    /**
     * Test to get the sensor type
     */
    @Test
    void getSensorType(){
        //Arrange
        String description = "SolarIrradiance";
        double value = 10;

        CatalogueSensor catalogue = mock(CatalogueSensor.class);
        SensorType sensorTypeDouble = mock(SensorType.class);

        when(catalogue.getSensorType(description)).thenReturn(sensorTypeDouble);

        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(catalogue, value);

        //Act
        SensorType sensorType = solarIrradianceSensor.getSensorType();

        //Assert
        assertEquals(sensorType, sensorTypeDouble);
    }

//    /**
//     * Test to get the value of the sensor
//     */
//    @Test
//    void getValue(){
//        //Arrange
//        String description = "SolarIrradiance";
//        double value = 10;
//        CatalogueSensor catalogue = mock(CatalogueSensor.class);
//        SensorType sensorTypeDouble = mock(SensorType.class);
//        SolarIrradianceValue solarIrradianceValueDouble = mock(SolarIrradianceValue.class);
//
//        SolarIrradianceSensor solarIrradianceSensor = new SolarIrradianceSensor(catalogue, value);
//
//        when(catalogue.getSensorType(description)).thenReturn(sensorTypeDouble);
//        when(solarIrradianceValueDouble.getValue()).thenReturn(value);
//
//        //Act
//        SolarIrradianceValue solarIrradianceValue = (SolarIrradianceValue) solarIrradianceSensor.getValue();
//
//        //Assert
//        assertEquals(solarIrradianceValue, solarIrradianceValueDouble);
//    }
}
