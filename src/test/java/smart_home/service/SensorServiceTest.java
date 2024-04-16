package smart_home.service;

import org.junit.jupiter.api.Test;
import smart_home.domain.device.Device;
import smart_home.domain.sensor.ISensor;
import smart_home.domain.sensor.ISensorFactory;
import smart_home.persistence.mem.DeviceRepository;
import smart_home.persistence.mem.SensorRepository;
import smart_home.value_object.DeviceID;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorName;
import smart_home.value_object.SensorTypeID;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class SensorServiceTest {

    /* test the constructor with mock objects */
    @Test
    public void shouldInstantiateSensorService_whenGivenValidParameters() {
        // Arrange
        SensorService sensorService;
        SensorRepository sensorRepository = mock(SensorRepository.class);
        ISensorFactory sensorFactory = mock(ISensorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        // Act
        sensorService = new SensorService(sensorRepository, sensorFactory, deviceRepository);

        // Assert
        assertNotNull(sensorService);
    }


    /* test the constructor with null sensor repository */
    @Test
    public void shouldThrowIllegalArgumentException_whenGivenNullSensorRepository() {
        // Arrange
        SensorRepository sensorRepository = null;
        ISensorFactory sensorFactory = mock(ISensorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        // Act Assert
        assertThrows(IllegalArgumentException.class, () -> new SensorService(sensorRepository, sensorFactory, deviceRepository));
    }

    /* test the constructor with null sensor factory */
    @Test
    public void shouldThrowIllegalArgumentException_whenGivenNullSensorFactory() {
        // Arrange
        SensorRepository sensorRepository = mock(SensorRepository.class);
        ISensorFactory sensorFactory = null;
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        // Act Assert
        assertThrows(IllegalArgumentException.class, () -> new SensorService(sensorRepository, sensorFactory , deviceRepository));
    }

    /* test the constructor with null device repository */

    @Test
    public void shouldThrowIllegalArgumentException_whenGivenNullDeviceRepository() {
        // Arrange
        SensorRepository sensorRepository = mock(SensorRepository.class);
        ISensorFactory sensorFactory = mock(ISensorFactory.class);
        DeviceRepository deviceRepository = null;

        // Act Assert
        assertThrows(IllegalArgumentException.class, () -> new SensorService(sensorRepository, sensorFactory , deviceRepository));
    }



    @Test
    public void testAddSensor_DeviceFound_Success() {
        // Arrange
        SensorRepository sensorRepository = mock(SensorRepository.class);
        ISensorFactory sensorFactory = mock(ISensorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        SensorService sensorService = new SensorService(sensorRepository, sensorFactory, deviceRepository);

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
        SensorName sensorName = new SensorName("sensorName");
        Device mockDevice = mock(Device.class);


        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));

        ISensor mockSensor = mock(ISensor.class);

        when(sensorFactory.create(deviceID, modelPath, sensorTypeID, sensorName)).thenReturn(mockSensor);

        // Act
        ISensor actualSensor = sensorService.addSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Assert
        assertNotNull(actualSensor);
        assertEquals(mockSensor, actualSensor);
    }
    /**
     * Test method addSensor To trow exception when device not found
     */
    @Test
    public void testAddSensor_DeviceNotFound_ThrowException() {
        // Arrange
        SensorRepository sensorRepository = mock(SensorRepository.class);
        ISensorFactory sensorFactory = mock(ISensorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        SensorService sensorService = new SensorService(sensorRepository, sensorFactory, deviceRepository);

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
        SensorName sensorName = new SensorName("sensorName");

        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.empty());

        // Act Assert
        assertThrows(IllegalArgumentException.class, () -> sensorService.addSensor(deviceID, modelPath, sensorTypeID, sensorName));
    }
}
