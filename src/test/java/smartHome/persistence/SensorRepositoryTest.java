package smartHome.persistence;

import smartHome.domain.sensor.ISensor;
import smartHome.persistence.mem.SensorRepository;
import smartHome.valueObject.DeviceID;
import smartHome.valueObject.SensorID;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorRepositoryTest {

    /**
     * Test the save method of the SensorRepository class with a valid Sensor.
     */
    @Test
    void shouldSaveSensor_WhenGivenValidSensor() {
        //Arrange
        ISensor Sensor = mock(ISensor.class);
        SensorID SensorID = mock(SensorID.class);
        when(Sensor.getID()).thenReturn(SensorID);
        SensorRepository SensorRepository = new SensorRepository();
        //Act
        ISensor savedSensor = SensorRepository.save(Sensor);
        //Assert
        assertEquals(Sensor, savedSensor);
    }

    /**
     * Test the save method of the SensorRepository class with a null Sensor.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullSensor() {
        //Arrange
        ISensor Sensor = null;
        SensorRepository SensorRepository = new SensorRepository();
        String expectedMessage = "Sensor cannot be null.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> SensorRepository.save(Sensor));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test the save method of the SensorRepository class with an already existing Sensor.
     */
    @Test
    void shouldThrowException_WhenSensorAlreadyExists() {
        //Arrange
        ISensor Sensor = mock(ISensor.class);
        SensorID SensorID = mock(SensorID.class);
        when(Sensor.getID()).thenReturn(SensorID);
        SensorRepository SensorRepository = new SensorRepository();

        SensorRepository.save(Sensor);
        String expectedMessage = "Sensor already exists.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> SensorRepository.save(Sensor));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test the findAll method of the SensorRepository class.
     */
    @Test
    void shouldReturnAllSensors_WhenFindAllIsCalled() {
        //Arrange
        ISensor firstSensor = mock(ISensor.class);
        SensorID firstSensorID = mock(SensorID.class);
        when(firstSensor.getID()).thenReturn(firstSensorID);
        ISensor secondSensor = mock(ISensor.class);
        SensorID secondSensorID = mock(SensorID.class);
        when(secondSensor.getID()).thenReturn(secondSensorID);

        SensorRepository SensorRepository = new SensorRepository();

        SensorRepository.save(firstSensor);
        SensorRepository.save(secondSensor);
        List<ISensor> expectedList = List.of(firstSensor, secondSensor);
        //Act
        List<ISensor> allSensors = SensorRepository.findAll();
        //Assert
        assertEquals(expectedList, allSensors);
    }

    /**
     * Test the findAll method of the SensorRepository class when no Sensors are saved.
     */
    @Test
    void shouldReturnEmptyList_WhenNoSensorsAreSaved() {
        //Arrange
        SensorRepository SensorRepository = new SensorRepository();
        //Act
        List<ISensor> allSensors = SensorRepository.findAll();
        //Assert
        assertTrue(allSensors.isEmpty());
    }

    /**
     * Test the ofIdentity method of the SensorRepository class with a valid SensorID.
     */
    @Test
    void shoudReturnSensor_WhenGivenValidSensorID() {
        //Arrange
        ISensor Sensor = mock(ISensor.class);
        SensorID SensorID = mock(SensorID.class);
        when(Sensor.getID()).thenReturn(SensorID);
        SensorRepository SensorRepository = new SensorRepository();
        SensorRepository.save(Sensor);
        //Act
        ISensor returnedSensor = SensorRepository.ofIdentity(SensorID).get();
        //Assert
        assertEquals(Sensor, returnedSensor);
    }

    /**
     * Test the ofIdentity method of the SensorRepository class with an invalid SensorID.
     */
    @Test
    void shouldReturnOptinalEmpty_WhenGivenInvalidSensorID() {
        //Arrange
        SensorRepository SensorRepository = new SensorRepository();

        ISensor Sensor = mock(ISensor.class);
        SensorID SensorID = mock(SensorID.class);
        when(Sensor.getID()).thenReturn(SensorID);
        SensorRepository.save(Sensor);

        SensorID nonExistentSensorID = mock(SensorID.class);
        //Act
        Optional<ISensor> returnedSensor = SensorRepository.ofIdentity(nonExistentSensorID);
        //Assert
        assertTrue(returnedSensor.isEmpty());
    }

    /**
     * Test the containsOfIdentity method of the SensorRepository class with a valid SensorID.
     */
    @Test
    void shouldReturnTrue_WhenGivenValidSensorID() {
        //Arrange
        ISensor Sensor = mock(ISensor.class);
        SensorID SensorID = mock(SensorID.class);
        when(Sensor.getID()).thenReturn(SensorID);
        SensorRepository SensorRepository = new SensorRepository();
        SensorRepository.save(Sensor);
        //Act
        boolean containsSensor = SensorRepository.containsOfIdentity(SensorID);
        //Assert
        assertTrue(containsSensor);
    }

    /**
     * Test the containsOfIdentity method of the SensorRepository class with an invalid SensorID.
     */
    @Test
    void shouldReturnFalse_WhenGivenInvalidSensorID() {
        //Arrange
        SensorRepository SensorRepository = new SensorRepository();

        ISensor Sensor = mock(ISensor.class);
        SensorID SensorID = mock(SensorID.class);
        when(Sensor.getID()).thenReturn(SensorID);
        SensorRepository.save(Sensor);

        SensorID nonExistentSensorID = mock(SensorID.class);
        //Act
        boolean containsSensor = SensorRepository.containsOfIdentity(nonExistentSensorID);
        //Assert
        assertFalse(containsSensor);
    }

    /**
     * Test the findByDeviceId method of the SensorRepository class with a valid DeviceID.
     */
    @Test
    void shouldReturnSensorList_WhenGivenValidDeviceID() {
        //Arrange
        SensorRepository SensorRepository = new SensorRepository();

        ISensor Sensor = mock(ISensor.class);
        SensorID SensorID = mock(SensorID.class);
        when(Sensor.getID()).thenReturn(SensorID);

        ISensor Sensor2 = mock(ISensor.class);
        SensorID SensorID2 = mock(SensorID.class);
        when(Sensor2.getID()).thenReturn(SensorID2);

        ISensor Sensor3 = mock(ISensor.class);
        SensorID SensorID3 = mock(SensorID.class);
        when(Sensor3.getID()).thenReturn(SensorID3);

        DeviceID DeviceID = mock(DeviceID.class);
        when(Sensor.getDeviceID()).thenReturn(DeviceID);
        when(Sensor2.getDeviceID()).thenReturn(DeviceID);
        when(Sensor3.getDeviceID()).thenReturn(DeviceID);

        SensorRepository.save(Sensor);
        SensorRepository.save(Sensor2);
        SensorRepository.save(Sensor3);

        List<ISensor> expectedSensorList = List.of(Sensor, Sensor2, Sensor3);

        //Act
        List<ISensor> returnedSensorList = SensorRepository.findByDeviceId(DeviceID);

        //Assert
        assertEquals(expectedSensorList, returnedSensorList);
    }

}