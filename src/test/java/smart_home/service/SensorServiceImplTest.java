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


class SensorServiceImplTest {

    /* test the constructor with mock objects */
    @Test
    void shouldInstantiateSensorService_whenGivenValidParameters() {
        // Arrange
        SensorServiceImpl sensorServiceImpl;
        SensorRepository sensorRepository = mock(SensorRepository.class);
        ISensorFactory sensorFactory = mock(ISensorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        // Act
        sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository);

        // Assert
        assertNotNull(sensorServiceImpl);
    }


    /* test the constructor with null sensor repository */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullSensorRepository() {
        // Arrange
        SensorRepository sensorRepository = null;
        ISensorFactory sensorFactory = mock(ISensorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        // Act Assert
        assertThrows(IllegalArgumentException.class, () -> new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository));
    }

    /* test the constructor with null sensor factory */
    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullSensorFactory() {
        // Arrange
        SensorRepository sensorRepository = mock(SensorRepository.class);
        ISensorFactory sensorFactory = null;
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        // Act Assert
        assertThrows(IllegalArgumentException.class, () -> new SensorServiceImpl(sensorRepository, sensorFactory , deviceRepository));
    }

    /* test the constructor with null device repository */

    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullDeviceRepository() {
        // Arrange
        SensorRepository sensorRepository = mock(SensorRepository.class);
        ISensorFactory sensorFactory = mock(ISensorFactory.class);
        DeviceRepository deviceRepository = null;

        // Act Assert
        assertThrows(IllegalArgumentException.class, () -> new SensorServiceImpl(sensorRepository, sensorFactory , deviceRepository));
    }



    @Test
    void testAddSensor_DeviceFound_Success() {
        // Arrange
        SensorRepository sensorRepository = mock(SensorRepository.class);
        ISensorFactory sensorFactory = mock(ISensorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository);

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
        SensorName sensorName = new SensorName("sensorName");
        Device mockDevice = mock(Device.class);


        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));

        ISensor mockSensor = mock(ISensor.class);

        when(sensorFactory.create(deviceID, modelPath, sensorTypeID, sensorName)).thenReturn(mockSensor);

        // Act
        ISensor actualSensor = sensorServiceImpl.addSensor(deviceID, modelPath, sensorTypeID, sensorName);

        // Assert
        assertNotNull(actualSensor);
        assertEquals(mockSensor, actualSensor);
    }
    /**
     * Test method addSensor To trow exception when device not found
     */
    @Test
    void testAddSensor_DeviceNotFound_ThrowException() {
        // Arrange
        SensorRepository sensorRepository = mock(SensorRepository.class);
        ISensorFactory sensorFactory = mock(ISensorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository);

        DeviceID deviceID = new DeviceID("deviceID");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
        SensorName sensorName = new SensorName("sensorName");

        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.empty());

        // Act Assert
        assertThrows(IllegalArgumentException.class, () -> sensorServiceImpl.addSensor(deviceID, modelPath, sensorTypeID, sensorName));
    }
}
