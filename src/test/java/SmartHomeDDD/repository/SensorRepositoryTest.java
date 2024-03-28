package SmartHomeDDD.repository;

import SmartHomeDDD.domain.Sensor.Sensor;
import SmartHomeDDD.valueObject.SensorID;
import SmartHomeDDD.valueObject.DeviceID;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorRepositoryTest {

    /**
     * Test the constructor of the SensorRepository class.
     */
    @Test
    void shouldInstantiateSensorRepository() {
        new SensorRepository();
    }

    /**
     * Test the save method of the SensorRepository class with a valid Sensor.
     */
    @Test
    void shouldSaveSensor_WhenGivenValidSensor() {
        //Arrange
        Sensor Sensor = mock(Sensor.class);
        SensorID SensorID = mock(SensorID.class);
        when(Sensor.getID()).thenReturn(SensorID);
        SensorRepository SensorRepository = new SensorRepository();
        //Act
        Sensor savedSensor = SensorRepository.save(Sensor);
        //Assert
        assertEquals(Sensor, savedSensor);
    }

    /**
     * Test the save method of the SensorRepository class with a null Sensor.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullSensor() {
        //Arrange
        Sensor Sensor = null;
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
        Sensor Sensor = mock(Sensor.class);
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
        Sensor firstSensor = mock(Sensor.class);
        SensorID firstSensorID = mock(SensorID.class);
        when(firstSensor.getID()).thenReturn(firstSensorID);
        Sensor secondSensor = mock(Sensor.class);
        SensorID secondSensorID = mock(SensorID.class);
        when(secondSensor.getID()).thenReturn(secondSensorID);

        SensorRepository SensorRepository = new SensorRepository();

        SensorRepository.save(firstSensor);
        SensorRepository.save(secondSensor);
        List<Sensor> expectedList = List.of(firstSensor, secondSensor);
        //Act
        List<Sensor> allSensors = SensorRepository.findAll();
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
        List<Sensor> allSensors = SensorRepository.findAll();
        //Assert
        assertTrue(allSensors.isEmpty());
    }

    /**
     * Test the ofIdentity method of the SensorRepository class with a valid SensorID.
     */
    @Test
    void shoudReturnSensor_WhenGivenValidSensorID() {
        //Arrange
        Sensor Sensor = mock(Sensor.class);
        SensorID SensorID = mock(SensorID.class);
        when(Sensor.getID()).thenReturn(SensorID);
        SensorRepository SensorRepository = new SensorRepository();
        SensorRepository.save(Sensor);
        //Act
        Sensor returnedSensor = SensorRepository.ofIdentity(SensorID).get();
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

        Sensor Sensor = mock(Sensor.class);
        SensorID SensorID = mock(SensorID.class);
        when(Sensor.getID()).thenReturn(SensorID);
        SensorRepository.save(Sensor);

        SensorID nonExistentSensorID = mock(SensorID.class);
        //Act
        Optional<Sensor> returnedSensor = SensorRepository.ofIdentity(nonExistentSensorID);
        //Assert
        assertTrue(returnedSensor.isEmpty());
    }

    /**
     * Test the containsOfIdentity method of the SensorRepository class with a valid SensorID.
     */
    @Test
    void shouldReturnTrue_WhenGivenValidSensorID() {
        //Arrange
        Sensor Sensor = mock(Sensor.class);
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

        Sensor Sensor = mock(Sensor.class);
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

        Sensor Sensor = mock(Sensor.class);
        SensorID SensorID = mock(SensorID.class);
        when(Sensor.getID()).thenReturn(SensorID);

        Sensor Sensor2 = mock(Sensor.class);
        SensorID SensorID2 = mock(SensorID.class);
        when(Sensor2.getID()).thenReturn(SensorID2);

        Sensor Sensor3 = mock(Sensor.class);
        SensorID SensorID3 = mock(SensorID.class);
        when(Sensor3.getID()).thenReturn(SensorID3);

        DeviceID DeviceID = mock(DeviceID.class);
        when(Sensor.getDeviceID()).thenReturn(DeviceID);
        when(Sensor2.getDeviceID()).thenReturn(DeviceID);
        when(Sensor3.getDeviceID()).thenReturn(DeviceID);

        SensorRepository.save(Sensor);
        SensorRepository.save(Sensor2);
        SensorRepository.save(Sensor3);

        List<Sensor> expectedSensorList = List.of(Sensor, Sensor2, Sensor3);

        //Act
        List<Sensor> returnedSensorList = SensorRepository.findByDeviceId(DeviceID);

        //Assert
        assertEquals(expectedSensorList, returnedSensorList);
    }

}