package smartHome.service;

import smartHome.domain.actuator.IActuator;
import smartHome.domain.actuator.IActuatorFactory;
import smartHome.domain.device.Device;
import smartHome.persistence.mem.ActuatorRepository;
import smartHome.persistence.mem.DeviceRepository;
import smartHome.valueObject.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for the ActuatorService class.
 */
class ActuatorServiceTest {

    /**
     * Tests the instantiation of ActuatorService with valid parameters.
     */
    @Test
    void shouldInstantiateActuatorService_WhenParametersAreValid() {
        //Arrange
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        //Act
        ActuatorService actuatorService = new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);

        //Assert
        assertNotNull(actuatorService);
    }

    /**
     * Tests throwing an exception when the actuator repository is null.
     */
    @Test
    void shouldThrowException_WhenActuatorRepositoryIsNull() {
        //Arrange
        IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorService(null, actuatorFactory, deviceRepository));

        //Assert
        assertEquals("Actuator repository cannot be null.", exception.getMessage());
    }

    /**
     * Tests throwing an exception when the actuator factory is null.
     */
    @Test
    void shouldThrowException_WhenActuatorFactoryIsNull() {
        //Arrange
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorService(actuatorRepository, null, deviceRepository));

        //Assert
        assertEquals("Actuator factory cannot be null.", exception.getMessage());
    }

    /**
     * Tests throwing an exception when the device repository is null.
     */
    @Test
    void shouldThrowException_WhenDeviceRepositoryIsNull() {
        //Arrange
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);

        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorService(actuatorRepository, actuatorFactory, null));

        //Assert
        assertEquals("Device repository cannot be null.", exception.getMessage());
    }

    /**
     * Tests adding an actuator when parameters are valid.
     */
    @Test
    void shouldAddActuator_WhenParametersAreValid() {
        //Arrange
        ActuatorID actuatorID = mock(ActuatorID.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ModelPath modelPath = mock(ModelPath.class);
        DeviceID deviceID = mock(DeviceID.class);

        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        ActuatorService actuatorService = new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);

        Device mockDevice = mock(Device.class);
        IActuator mockActuator = mock(IActuator.class);

        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));
        when(actuatorFactory.createActuator(deviceID, modelPath, actuatorTypeID, actuatorName)).thenReturn(mockActuator);

        //Act
        IActuator actuator = actuatorService.addActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        //Assert
        assertNotNull(actuator);
    }

    /**
     * Tests throwing an exception when the device is not found.
     */
    @Test
    void shouldThrowException_WhenDeviceIsNotFound() {
        //Arrange
        ActuatorID actuatorID = mock(ActuatorID.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ModelPath modelPath = mock(ModelPath.class);
        DeviceID deviceID = mock(DeviceID.class);

        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        ActuatorService actuatorService = new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);

        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> actuatorService.addActuator(deviceID, modelPath, actuatorTypeID, actuatorName));
    }

    /**
     * Tests getting an actuator by its ID.
     */
    @Test
    void shouldGetActuatorByID_WhenActuatorIsFound() {
        //Arrange
        ActuatorID actuatorID = mock(ActuatorID.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ModelPath modelPath = mock(ModelPath.class);
        DeviceID deviceID = mock(DeviceID.class);

        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        ActuatorService actuatorService = new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);

        IActuator mockActuator = mock(IActuator.class);
        when(actuatorRepository.ofIdentity(actuatorID)).thenReturn(Optional.of(mockActuator));

        //Act
        Optional<IActuator> actuator = actuatorService.getActuatorByID(actuatorID);

        //Assert
        assertTrue(actuator.isPresent());
    }


    /**
     * Getting an actuator by its ID should return an empty Optional when the actuator is not found.
     */
    @Test
    void shouldReturnEmptyOptional_WhenActuatorIsNotFound() {
        //Arrange
        ActuatorID actuatorID = mock(ActuatorID.class);

        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        ActuatorService actuatorService = new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);

        when(actuatorRepository.ofIdentity(actuatorID)).thenReturn(Optional.empty());

        //Act
        Optional<IActuator> actuator = actuatorService.getActuatorByID(actuatorID);

        //Assert
        assertTrue(actuator.isEmpty());
    }

    /**
     * Tests getting all actuators when actuators are found.
     */
    @Test
    void shouldGetAllActuators_WhenActuatorsAreFound() {
        //Arrange
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        ActuatorID actuatorID = mock(ActuatorID.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ModelPath modelPath = mock(ModelPath.class);
        DeviceID deviceID = mock(DeviceID.class);

        ActuatorService actuatorService = new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);

        IActuator mockActuator = mock(IActuator.class);
        IActuator mockActuator2 = mock(IActuator.class);
        when(actuatorRepository.findAll()).thenReturn(List.of(mockActuator, mockActuator2));

        int expectedActuators = 2;

        //Act
        List<IActuator> actuators = actuatorService.getAllActuators();

        //Assert
        assertEquals(expectedActuators, actuators.size());
    }

    /**
     * Tests returning an empty list when no actuators are found.
     */
    @Test
    void shouldReturnEmptyList_WhenNoActuatorsAreFound() {
        //Arrange
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        IActuatorFactory actuatorFactory = mock(IActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        ActuatorService actuatorService = new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);

        when(actuatorRepository.findAll()).thenReturn(List.of());

        //Act
        List<IActuator> actuators = actuatorService.getAllActuators();

        //Assert
        assertTrue(actuators.isEmpty());
    }
}
