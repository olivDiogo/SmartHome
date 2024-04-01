package SmartHomeDDD.repository;

import SmartHomeDDD.domain.Actuator.Actuator;
import SmartHomeDDD.valueObject.ActuatorID;
import SmartHomeDDD.valueObject.DeviceID;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorRepositoryTest {
    /**
     * Test the constructor of the ActuatorRepository class.
     */
    @Test
    void shouldInstantiateActuatorRepository() {
        new ActuatorRepository();
    }

    /**
     * Test the save method of the ActuatorRepository class with a valid Actuator.
     */
    @Test
    void shouldSaveActuator_WhenGivenValidActuator() {
        //Arrange
        Actuator actuator = mock(Actuator.class);
        ActuatorID actuatorID = mock(ActuatorID.class);

        when(actuator.getID()).thenReturn(actuatorID);

        ActuatorRepository actuatorRepository = new ActuatorRepository();

        //Act
        Actuator savedActuator = actuatorRepository.save(actuator);

        //Assert
        assertEquals(actuator, savedActuator);
    }

    /**
     * Test the save method of the ActuatorRepository class with a null Actuator.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullActuator() {
        //Arrange
        Actuator actuator = null;
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        String expectedMessage = "Actuator cannot be null.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> actuatorRepository.save(actuator));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test the save method of the ActuatorRepository class with an already existing Actuator.
     */
    @Test
    void shouldThrowException_WhenActuatorAlreadyExists() {
        //Arrange
        Actuator actuator = mock(Actuator.class);
        ActuatorID actuatorID = mock(ActuatorID.class);

        when(actuator.getID()).thenReturn(actuatorID);

        ActuatorRepository actuatorRepository = new ActuatorRepository();
        String expectedMessage = "Actuator already exists.";

        //Act
        actuatorRepository.save(actuator);

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> actuatorRepository.save(actuator));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test the findAll method of the ActuatorRepository class.
     */
    @Test
    void shouldFindAllActuators_WhenFindAllIsCalled() {
        //Arrange
        Actuator actuator = mock(Actuator.class);
        ActuatorID actuatorID = mock(ActuatorID.class);

        when(actuator.getID()).thenReturn(actuatorID);

        ActuatorRepository actuatorRepository = new ActuatorRepository();
        actuatorRepository.save(actuator);

        int expectedSize = List.of(actuator).size();

        //Act
        List<Actuator> actuators = actuatorRepository.findAll();

        //Assert
        assertEquals(expectedSize, actuators.size());
    }

    /**
     * Test the ofIdentity method of the ActuatorRepository with a valid ActuatorID.
     */
    @Test
    void shouldReturnActuator_WhenGivenValidActuatorID() {
        //Arrange
        Actuator actuator = mock(Actuator.class);
        ActuatorID actuatorID = mock(ActuatorID.class);

        when(actuator.getID()).thenReturn(actuatorID);

        ActuatorRepository actuatorRepository = new ActuatorRepository();
        actuatorRepository.save(actuator);

        //Act
        Optional<Actuator> foundActuator = actuatorRepository.ofIdentity(actuatorID);

        //Assert
        assertTrue(foundActuator.isPresent());
    }

    /**
     * Test the ofIdentity method of the ActuatorRepository with an invalid ActuatorID.
     */
    @Test
    void shouldReturnEmptyOptional_WhenGivenInvalidActuatorID() {
        //Arrange
        Actuator actuator = mock(Actuator.class);
        ActuatorID actuatorID = mock(ActuatorID.class);
        ActuatorID invalidActuatorID = mock(ActuatorID.class);

        when(actuator.getID()).thenReturn(actuatorID);

        ActuatorRepository actuatorRepository = new ActuatorRepository();
        actuatorRepository.save(actuator);

        //Act
        Optional<Actuator> foundActuator = actuatorRepository.ofIdentity(invalidActuatorID);

        //Assert
        assertTrue(foundActuator.isEmpty());
    }

    /**
     * Test the containsOfIdentity method of the ActuatorRepository with a valid ActuatorID.
     */
    @Test
    void shouldReturnTrue_WhenGivenValidActuatorID() {
        //Arrange
        Actuator actuator = mock(Actuator.class);
        ActuatorID actuatorID = mock(ActuatorID.class);

        when(actuator.getID()).thenReturn(actuatorID);

        ActuatorRepository actuatorRepository = new ActuatorRepository();
        actuatorRepository.save(actuator);

        //Act
        boolean containsActuator = actuatorRepository.containsOfIdentity(actuatorID);

        //Assert
        assertTrue(containsActuator);
    }

    /**
     * Test the containsOfIdentity method of the ActuatorRepository with an invalid ActuatorID.
     */
    @Test
    void shouldReturnFalse_WhenGivenInvalidActuatorID() {
        //Arrange
        Actuator actuator = mock(Actuator.class);
        ActuatorID actuatorID = mock(ActuatorID.class);
        ActuatorID invalidActuatorID = mock(ActuatorID.class);

        when(actuator.getID()).thenReturn(actuatorID);

        ActuatorRepository actuatorRepository = new ActuatorRepository();
        actuatorRepository.save(actuator);

        //Act
        boolean containsActuator = actuatorRepository.containsOfIdentity(invalidActuatorID);

        //Assert
        assertFalse(containsActuator);
    }

    /**
     * Test the findByDeviceId method of the ActuatorRepository with a valid DeviceID.
     */
    @Test
    void shouldReturnActuatorsList_WhenGivenValidDeviceID() {
        //Arrange
        Actuator actuator = mock(Actuator.class);
        ActuatorID actuatorID = mock(ActuatorID.class);
        DeviceID deviceID = mock(DeviceID.class);

        when(actuator.getID()).thenReturn(actuatorID);
        when(actuator.getDeviceID()).thenReturn(deviceID);

        ActuatorRepository actuatorRepository = new ActuatorRepository();
        actuatorRepository.save(actuator);

        int expectedSize = List.of(actuator).size();

        //Act
        List<Actuator> actuators = actuatorRepository.findByDeviceID(deviceID);

        //Assert
        assertEquals(expectedSize, actuators.size());
    }
}
