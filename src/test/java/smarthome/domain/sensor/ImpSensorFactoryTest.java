package smarthome.domain.sensor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import smarthome.domain.sensor.solar_irradiance_sensor.SolarIrradianceSensor;
import smarthome.domain.sensor.sunrise_time_sensor.SunriseTimeSensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ImpSensorFactoryTest {

    /*
     * Test for creating a SolarIrradianceSensor, which has a constructor with 4 parameters.
     */
    @Test
    void shouldCreateSolarIrradianceSensor_whenAllFourConstructorParametersAreValid(){
        // Arrange
        DeviceID deviceIdMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("smarthome.domain.sensor.solar_irradiance_sensor.SolarIrradianceSensor");

        SensorTypeID sensorTypeIdMock = mock(SensorTypeID.class);
        when(sensorTypeIdMock.getID()).thenReturn("SolarIrradiance");
        SensorName sensorNameMock = mock(SensorName.class);

        SensorFactoryImpl impSensorFactory = new SensorFactoryImpl();

        // Act
        SolarIrradianceSensor sensor = (SolarIrradianceSensor) impSensorFactory.create(deviceIdMock, modelPathMock, sensorTypeIdMock, sensorNameMock);

        // Assert
        assertNotNull(sensor);
    }

    /*
     * Test for creating a SunriseTimeSensor, which has a constructor with 5 parameters.
     */
    @Test
    void shouldCreateSunriseTimeSensor_whenAllFiveConstructorParametersAreValid(){
        // Arrange
        DeviceID deviceIdMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("smarthome.domain.sensor.sunrise_time_sensor.SunriseTimeSensor");

        SensorTypeID sensorTypeIdMock = mock(SensorTypeID.class);
        when(sensorTypeIdMock.getID()).thenReturn("SunriseTime");
        SensorName sensorNameMock = mock(SensorName.class);
        GPS gpsMock = mock(GPS.class);

        SensorFactoryImpl impSensorFactory = new SensorFactoryImpl();

        // Act
        SunriseTimeSensor sensor = (SunriseTimeSensor) impSensorFactory.create(deviceIdMock, modelPathMock, sensorTypeIdMock, sensorNameMock, gpsMock);

        // Assert
        assertNotNull(sensor);
    }

    /*
     * Test for providing a wrong model path, which should return null.
     */
    @Test
    void shouldReturnNull_whenModelPathIsWrong(){
        // Arrange
        DeviceID deviceIdMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("smarthome.domain.sensor.WrongSensor");

        SensorTypeID sensorTypeIdMock = mock(SensorTypeID.class);
        SensorName sensorNameMock = mock(SensorName.class);

        SensorFactoryImpl impSensorFactory = new SensorFactoryImpl();

        // Act + Assert
        ISensor result = impSensorFactory.create(deviceIdMock, modelPathMock, sensorTypeIdMock, sensorNameMock);

        // Assert
        assertNull(result);
    }

    /**
     * Test for providing wrong object type in constructor parameters, which should return null.
     */
    @Test
    void shouldReturnNull_whenWrongObjectTypeInConstructorParameters(){
        // Arrange
        DeviceID deviceIdMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("smarthome.domain.sensor.SunriseTimeSensor.SunriseTimeSensor");

        SensorTypeID sensorTypeIdMock = mock(SensorTypeID.class);
        SensorName sensorNameMock = mock(SensorName.class);
        String wrongObject = "wrong object";

        SensorFactoryImpl impSensorFactory = new SensorFactoryImpl();

        // Act + Assert
        ISensor result = impSensorFactory.create(deviceIdMock, modelPathMock, sensorTypeIdMock, sensorNameMock, wrongObject);

        // Assert
        assertNull(result);
    }

    /**
     * Test for providing less than 4 constructor parameters, which should return null.
     */
    @Test
    void shouldThrowException_whenWrongNumberOfConstructorParameters(){
        // Arrange
        DeviceID deviceIdMock = mock(DeviceID.class);
        ModelPath modelPathMock = mock(ModelPath.class);
        when(modelPathMock.toString()).thenReturn("smarthome.domain.sensor.SunriseTimeSensor.SunriseTimeSensor");

        SensorName sensorNameMock = mock(SensorName.class);

        SensorFactoryImpl impSensorFactory = new SensorFactoryImpl();

        String expectedMessage = "At least 4 parameters are required.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            impSensorFactory.create(deviceIdMock, modelPathMock, sensorNameMock);
        });

        // Assert
        String actualMessage = e.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
