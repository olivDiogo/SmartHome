package smarthome.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.ISensorRepository;
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor.ISensorFactory;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.mem.DeviceRepository;
import smarthome.persistence.mem.SensorRepository;


class SensorServiceImplTest {

  /**
   * Test method shouldInstantiateSensorService_whenGivenValidParameters
   */
  @Test
  void shouldInstantiateSensorService_whenGivenValidParameters() {
    // Arrange
    SensorServiceImpl sensorServiceImpl;
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = mock(DeviceRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);

    // Act
    sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository,
        sensorTypeRepository);

    // Assert
    assertNotNull(sensorServiceImpl);
  }


  /**
   * Should throw an exception when the sensor repository is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullSensorRepository() {
    // Arrange
    SensorRepository sensorRepository = null;
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = mock(DeviceRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);

    // Act Assert
    assertThrows(IllegalArgumentException.class,
        () -> new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository,
            sensorTypeRepository));
  }

  /**
   * Should throw an exception when the sensor factory is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullSensorFactory() {
    // Arrange
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = null;
    DeviceRepository deviceRepository = mock(DeviceRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);

    // Act Assert
    assertThrows(IllegalArgumentException.class,
        () -> new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository,
            sensorTypeRepository));
  }

  /**
   * Should throw an exception when the device repository is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDeviceRepository() {
    // Arrange
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = null;
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);

    // Act Assert
    assertThrows(IllegalArgumentException.class,
        () -> new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository,
            sensorTypeRepository));
  }

  /**
   * should add sensor when device is found
   */
  @Test
  void shouldAddSensor_whenDeviceIsFound() {
    // Arrange
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = mock(DeviceRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);

    SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory,
        deviceRepository, sensorTypeRepository);

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
    SensorName sensorName = new SensorName("sensorName");
    Device mockDevice = mock(Device.class);
    DeviceStatus mockDeviceStatus = mock(DeviceStatus.class);

    SensorType sensorType = mock(SensorType.class);
    when(sensorType.getID()).thenReturn(sensorTypeID);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));
    when(mockDevice.getDeviceStatus()).thenReturn(mockDeviceStatus);
    when(mockDeviceStatus.getStatus()).thenReturn(true);

    ISensor mockSensor = mock(ISensor.class);

    when(sensorFactory.create(deviceID, modelPath, sensorTypeID, sensorName)).thenReturn(
        mockSensor);

    // Act
    ISensor actualSensor = sensorServiceImpl.addSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    // Assert
    assertNotNull(actualSensor);
    assertEquals(mockSensor, actualSensor);
  }

  /**
   * Test method addSensor To trow exception when device not found
   */
  @Test
  void shouldThrowException_whenDeviceNotFound() {
    // Arrange
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = mock(DeviceRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
    SensorType sensorType = mock(SensorType.class);
    when(sensorType.getID()).thenReturn(sensorTypeID);

    SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory,
        deviceRepository, sensorTypeRepository);

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");

    SensorName sensorName = new SensorName("sensorName");

    when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.empty());
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    String expectedMessage = "Device with ID " + deviceID + " not found.";

    // Act Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorServiceImpl.addSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test method addSensor To trow exception when device is deactivated
   */
  @Test
  void shouldThrowException_whenDeviceIsOff() {
    // Arrange
    SensorRepository sensorRepository = mock(SensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    DeviceRepository deviceRepository = mock(DeviceRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    SensorType sensorType = mock(SensorType.class);
    SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
    when(sensorType.getID()).thenReturn(sensorTypeID);


    SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory,
        deviceRepository, sensorTypeRepository);

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");

    SensorName sensorName = new SensorName("sensorName");
    Device mockDevice = mock(Device.class);
    DeviceStatus mockDeviceStatus = mock(DeviceStatus.class);

    when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));
    when(mockDevice.getDeviceStatus()).thenReturn(mockDeviceStatus);
    when(mockDeviceStatus.getStatus()).thenReturn(false);

    // Act Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorServiceImpl.addSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    assertEquals("Device with ID " + deviceID + " is deactivated.", exception.getMessage());
  }

  /**
   * Should return a sensor by its ID
   */
  @Test
  void shouldGetSensorByID_WhenSensorIsFound () {
    SensorID sensorID = mock(SensorID.class);

    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);

    SensorServiceImpl sensorService = new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository, sensorTypeRepository);

    ISensor mockedSensor = mock(ISensor.class);
    when(sensorRepository.ofIdentity(sensorID)).thenReturn(Optional.of(mockedSensor));

    //Act
    Optional<ISensor> sensor = sensorService.getSensorByID(sensorID);

    //Assert
    assertTrue(sensor.isPresent());
  }

  /**
   * should return empty when no sensor is found
   */
  @Test
  void shouldReturnEmptyOptional_WhenSensorIsNotFound () {
    SensorID sensorID = mock(SensorID.class);

    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);

    SensorServiceImpl sensorService = new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository, sensorTypeRepository);

    ISensor mockedSensor = mock(ISensor.class);
    when(sensorRepository.ofIdentity(sensorID)).thenReturn(Optional.empty());

    //Act
    Optional<ISensor> sensor = sensorService.getSensorByID(sensorID);

    //Assert
    assertTrue(sensor.isEmpty());
  }

  /**
   * should return a list of all sensors when sensors are found
   */
  @Test
  void shouldReturnAListOfAllSensors_WhenSensorsAreFound () {
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);

    SensorServiceImpl sensorService = new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository, sensorTypeRepository);

    ISensor mockedSensor = mock(ISensor.class);
    ISensor mockedSensor2 = mock(ISensor.class);
    when(sensorRepository.findAll()).thenReturn(List.of(mockedSensor, mockedSensor2));

    int expectedSensors = 2;
    //Act
    List<ISensor> sensor = sensorService.getAllSensors();

    //Assert
    assertEquals(expectedSensors, sensor.size());
  }

  /**
   * should return an empty list when no sensors are found
   */
  @Test
  void shouldReturnEmptyList_WhenNoSensorsAreFound () {
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorFactory sensorFactory = mock(ISensorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);

    SensorServiceImpl sensorService = new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository, sensorTypeRepository);
    when(sensorRepository.findAll()).thenReturn(List.of());

    //Act
    List<ISensor> sensor = sensorService.getAllSensors();

    //Assert
    assertTrue(sensor.isEmpty());
  }
}
