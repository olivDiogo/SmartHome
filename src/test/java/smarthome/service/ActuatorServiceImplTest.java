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
import smarthome.domain.actuator.IActuator;
import smarthome.domain.actuator.IActuatorFactory;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.device.Device;
import smarthome.domain.repository.IActuatorRepository;
import smarthome.domain.repository.IActuatorTypeRepository;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.mem.ActuatorRepository;
import smarthome.persistence.mem.DeviceRepository;

/** Test class for the ActuatorService class. */
class ActuatorServiceImplTest {

  /** Tests the instantiation of ActuatorService with valid parameters. */
  @Test
  void shouldInstantiateActuatorService_WhenParametersAreValid() {
    // Arrange
    IActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
    IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
    IDeviceRepository deviceRepository = mock(DeviceRepository.class);
    IActuatorTypeRepository actuatorTypeRepository = mock(IActuatorTypeRepository.class);

    // Act
    ActuatorServiceImpl ActuatorServiceImpl =
        new ActuatorServiceImpl(actuatorRepository, actuatorFactory, deviceRepository, actuatorTypeRepository);

    // Assert
    assertNotNull(ActuatorServiceImpl);
  }

  /** Tests throwing an exception when the actuator repository is null. */
  @Test
  void shouldThrowException_WhenActuatorRepositoryIsNull() {
    // Arrange
    IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IActuatorTypeRepository actuatorTypeRepository = mock(IActuatorTypeRepository.class);

    String expectedMessage = "Actuator repository is required";

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new ActuatorServiceImpl(null, actuatorFactory, deviceRepository, actuatorTypeRepository));


    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Tests throwing an exception when the actuator factory is null. */
  @Test
  void shouldThrowException_WhenActuatorFactoryIsNull() {
    // Arrange
    IActuatorRepository actuatorRepository = mock(IActuatorRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IActuatorTypeRepository actuatorTypeRepository = mock(IActuatorTypeRepository.class);

    String expectedMessage = "Actuator factory is required";

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new ActuatorServiceImpl(actuatorRepository, null, deviceRepository, actuatorTypeRepository));

    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Tests throwing an exception when the device repository is null. */
  @Test
  void shouldThrowException_WhenDeviceRepositoryIsNull() {
    // Arrange
    IActuatorRepository actuatorRepository = mock(IActuatorRepository.class);
    IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
    IActuatorTypeRepository actuatorTypeRepository = mock(IActuatorTypeRepository.class);

    String expectedMessage = "Device repository is required";

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new ActuatorServiceImpl(actuatorRepository, actuatorFactory, null, actuatorTypeRepository));


    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowException_WhenActuatorTypeRepositoryIsNull() {
    // Arrange
    IActuatorRepository actuatorRepository = mock(IActuatorRepository.class);
    IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IActuatorTypeRepository actuatorTypeRepository = null;

    String expectedMessage = "Actuator type repository is required";

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new ActuatorServiceImpl(actuatorRepository, actuatorFactory, deviceRepository, actuatorTypeRepository));

    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Tests adding an actuator when parameters are valid. */
  @Test
  void shouldAddActuator_WhenParametersAreValid() {
    // Arrange
    ActuatorID actuatorID = mock(ActuatorID.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    IActuatorTypeRepository actuatorTypeRepository = mock(IActuatorTypeRepository.class);

    ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
    IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
    DeviceRepository deviceRepository = mock(DeviceRepository.class);

    ActuatorServiceImpl ActuatorServiceImpl =
        new ActuatorServiceImpl(actuatorRepository, actuatorFactory, deviceRepository, actuatorTypeRepository);

    Device mockDevice = mock(Device.class);
    DeviceStatus deviceStatus = mock(DeviceStatus.class);

    when(deviceStatus.getStatus()).thenReturn(true);
    when(mockDevice.getDeviceStatus()).thenReturn(deviceStatus);

    IActuator mockActuator = mock(IActuator.class);
    ActuatorType actuatorType = mock(ActuatorType.class);

    when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));
    when(actuatorTypeRepository.ofIdentity(actuatorTypeID)).thenReturn(
        Optional.ofNullable(actuatorType));
    when(mockActuator.getID()).thenReturn(actuatorID);
    when(actuatorFactory.create(deviceID, modelPath, actuatorTypeID, actuatorName))
        .thenReturn(mockActuator);

    // Act
    IActuator actuator =
        ActuatorServiceImpl.addActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

    assertNotNull(actuator);
  }

  /** Tests throwing an exception when the device is not found. */
  @Test
  void shouldThrowException_WhenDeviceIsNotFound() {
    // Arrange
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    IActuatorTypeRepository actuatorTypeRepository = mock(IActuatorTypeRepository.class);

    IActuatorRepository actuatorRepository = mock(IActuatorRepository.class);
    IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);

    ActuatorServiceImpl ActuatorServiceImpl =
        new ActuatorServiceImpl(actuatorRepository, actuatorFactory, deviceRepository, actuatorTypeRepository);

    when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.empty());

    String expectedMessage = "Device with ID " + deviceID + " not found.";

    // Act & Assert
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> ActuatorServiceImpl.addActuator(deviceID, modelPath, actuatorTypeID, actuatorName));

    assertEquals(expectedMessage, exception.getMessage());

  }

  /** Tests getting an actuator by its ID. */
  @Test
  void shouldGetActuatorByID_WhenActuatorIsFound() {
    // Arrange
    ActuatorID actuatorID = mock(ActuatorID.class);
    IActuatorTypeRepository actuatorTypeRepository = mock(IActuatorTypeRepository.class);

    IActuatorRepository actuatorRepository = mock(IActuatorRepository.class);
    IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);

    ActuatorServiceImpl ActuatorServiceImpl =
        new ActuatorServiceImpl(actuatorRepository, actuatorFactory, deviceRepository, actuatorTypeRepository);

    IActuator mockActuator = mock(IActuator.class);
    when(actuatorRepository.ofIdentity(actuatorID)).thenReturn(Optional.of(mockActuator));

    // Act
    Optional<IActuator> actuator = ActuatorServiceImpl.getActuatorByID(actuatorID);

    // Assert
    assertTrue(actuator.isPresent());
  }

  /**
   * Getting an actuator by its ID should return an empty Optional when the actuator is not found.
   */
  @Test
  void shouldReturnEmptyOptional_WhenActuatorIsNotFound() {
    // Arrange
    ActuatorID actuatorID = mock(ActuatorID.class);

    IActuatorRepository actuatorRepository = mock(IActuatorRepository.class);
    IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IActuatorTypeRepository actuatorTypeRepository = mock(IActuatorTypeRepository.class);

    ActuatorServiceImpl ActuatorServiceImpl =
        new ActuatorServiceImpl(actuatorRepository, actuatorFactory, deviceRepository, actuatorTypeRepository);

    when(actuatorRepository.ofIdentity(actuatorID)).thenReturn(Optional.empty());

    // Act
    Optional<IActuator> actuator = ActuatorServiceImpl.getActuatorByID(actuatorID);

    // Assert
    assertTrue(actuator.isEmpty());
  }

  /** Tests getting all actuators when actuators are found. */
  @Test
  void shouldGetAllActuators_WhenActuatorsAreFound() {
    // Arrange
    IActuatorRepository actuatorRepository = mock(IActuatorRepository.class);
    IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IActuatorTypeRepository actuatorTypeRepository = mock(IActuatorTypeRepository.class);

    ActuatorServiceImpl ActuatorServiceImpl =
        new ActuatorServiceImpl(actuatorRepository, actuatorFactory, deviceRepository, actuatorTypeRepository);

    IActuator mockActuator = mock(IActuator.class);
    IActuator mockActuator2 = mock(IActuator.class);
    when(actuatorRepository.findAll()).thenReturn(List.of(mockActuator, mockActuator2));

    int expectedActuators = 2;

    // Act
    List<IActuator> actuators = ActuatorServiceImpl.getAllActuators();

    // Assert
    assertEquals(expectedActuators, actuators.size());
  }

  /** Tests returning an empty list when no actuators are found. */
  @Test
  void shouldReturnEmptyList_WhenNoActuatorsAreFound() {
    // Arrange
    IActuatorRepository actuatorRepository = mock(IActuatorRepository.class);
    IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IActuatorTypeRepository actuatorTypeRepository = mock(IActuatorTypeRepository.class);

    ActuatorServiceImpl ActuatorServiceImpl =
        new ActuatorServiceImpl(actuatorRepository, actuatorFactory, deviceRepository, actuatorTypeRepository);

    when(actuatorRepository.findAll()).thenReturn(List.of());

    // Act
    List<IActuator> actuators = ActuatorServiceImpl.getAllActuators();

    // Assert
    assertTrue(actuators.isEmpty());
  }

  /** Tests method addActuator To trow exception when device is deactivated */
  @Test
  void shouldThrowException_whenDeviceIsDeactivated() {
    // Arrange
    IActuatorRepository actuatorRepository = mock(IActuatorRepository.class);
    IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    IActuatorTypeRepository actuatorTypeRepository = mock(IActuatorTypeRepository.class);

    ActuatorServiceImpl actuatorServiceImpl =
        new ActuatorServiceImpl(actuatorRepository, actuatorFactory, deviceRepository, actuatorTypeRepository);

    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    Device mockDevice = mock(Device.class);
    DeviceStatus mockDeviceStatus = mock(DeviceStatus.class);

    when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));
    when(mockDevice.getDeviceStatus()).thenReturn(mockDeviceStatus);
    when(mockDeviceStatus.getStatus()).thenReturn(false);

    String expectedMessage = "Device with ID " + deviceID + " is deactivated.";

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                actuatorServiceImpl.addActuator(deviceID, modelPath, actuatorTypeID, actuatorName));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }
}
