package SmartHomeDDD.service;

import SmartHomeDDD.domain.Actuator.Actuator;
import SmartHomeDDD.domain.Actuator.ActuatorFactory;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.repository.ActuatorRepository;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.valueObject.*;
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
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        //Act
        new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);
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
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        ActuatorService actuatorService = new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);

        Device mockDevice = mock(Device.class);
        Actuator mockActuator = mock(Actuator.class);

        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));
        when(actuatorFactory.createActuator(deviceID, modelPath, actuatorTypeID, actuatorName)).thenReturn(mockActuator);

        //Act
        Actuator actuator = actuatorService.addActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

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
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        ActuatorService actuatorService = new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);

        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> actuatorService.addActuator(deviceID, modelPath, actuatorTypeID, actuatorName));
    }

    /**
     * Tests getting an actuator by its ID when the actuator is found.
     */
    @Test
    void shouldGetActuatorByID_WhenActuatorIsFound() {
        //Arrange
        ActuatorID actuatorID = mock(ActuatorID.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        ActuatorService actuatorService = new ActuatorService(actuatorRepository, null, null);
        Actuator mockActuator = mock(Actuator.class);

        when(actuatorRepository.ofIdentity(actuatorID)).thenReturn(Optional.of(mockActuator));

        //Act
        Optional<Actuator> actuator = actuatorService.getActuatorByID(actuatorID);

        //Assert
        assertTrue(actuator.isPresent());
    }

    /**
     * Tests returning an empty Optional when the actuator is not found.
     */
    @Test
    void shouldReturnEmptyOptional_WhenActuatorIsNotFound() {
        //Arrange
        ActuatorID actuatorID = mock(ActuatorID.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        ActuatorService actuatorService = new ActuatorService(actuatorRepository, null, null);

        when(actuatorRepository.ofIdentity(actuatorID)).thenReturn(Optional.empty());

        //Act
        Optional<Actuator> actuator = actuatorService.getActuatorByID(actuatorID);

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
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        ActuatorID actuatorID = mock(ActuatorID.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        ActuatorName actuatorName = mock(ActuatorName.class);
        ModelPath modelPath = mock(ModelPath.class);
        DeviceID deviceID = mock(DeviceID.class);

        ActuatorService actuatorService = new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);

        Actuator mockActuator = mock(Actuator.class);
        Actuator mockActuator2 = mock(Actuator.class);
        when(actuatorRepository.findAll()).thenReturn(List.of(mockActuator, mockActuator2));

        int expectedActuators = 2;

        //Act
        List<Actuator> actuators = actuatorService.getAllActuators();

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
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        ActuatorService actuatorService = new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);

        when(actuatorRepository.findAll()).thenReturn(List.of());

        //Act
        List<Actuator> actuators = actuatorService.getAllActuators();

        //Assert
        assertTrue(actuators.isEmpty());
    }
}
