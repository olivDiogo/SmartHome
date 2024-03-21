package SmartHomeDDD.repository;

import org.junit.jupiter.api.Test;
import SmartHomeDDD.ValueObject.TypeDescription;
import SmartHomeDDD.domain.SensorType.SensorType;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SensorTypeRepositoryTest {

    @Test
    void shouldInstantiateSensorTypeRepository() {
        new SensorTypeRepository();
    }

    @Test
    void shouldSaveSensorType_whenGivenValidSensorType() {
        //Arrange
        SensorType sensorType = mock(SensorType.class);
        TypeDescription typeDescription = mock(TypeDescription.class);
        when(sensorType.getID()).thenReturn(typeDescription);
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

        //Act
        SensorType savedSensorType = sensorTypeRepository.save(sensorType);

        //Assert
        assertEquals(sensorType, savedSensorType);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenGivenNullSensorType() {
        //Arrange
        SensorType sensorType = null;
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        String expectedMessage = "SensorType cannot be null.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sensorTypeRepository.save(sensorType));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldThrowException_whenSensorTypeAlreadyExists() {
        //Arrange
        SensorType sensorType = mock(SensorType.class);
        TypeDescription TypeDescription = mock(TypeDescription.class);
        when(sensorType.getID()).thenReturn(TypeDescription);

        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

        sensorTypeRepository.save(sensorType);
        String expectedMessage = "SensorType already exists.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sensorTypeRepository.save(sensorType));

        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void shouldRetunrAllSensorTypes_whenFindAllIsCalled() {
        //Arrange
        SensorType firstSensorType = mock(SensorType.class);
        TypeDescription firstTypeDescription = mock(TypeDescription.class);
        when(firstSensorType.getID()).thenReturn(firstTypeDescription);

        SensorType secondSensorType = mock(SensorType.class);
        TypeDescription secondTypeDescription = mock(TypeDescription.class);
        when(secondSensorType.getID()).thenReturn(secondTypeDescription);

        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

        sensorTypeRepository.save(firstSensorType);
        sensorTypeRepository.save(secondSensorType);
        List<SensorType> expectedList = List.of(firstSensorType, secondSensorType);

        //Act
        List<SensorType> allSensorTypes = sensorTypeRepository.findAll();

        //Assert
        assertEquals(expectedList, allSensorTypes);
    }

    @Test
    void shouldReturnEmptyList_whenNoSensorTypesAreSaved() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        //Act
        List<SensorType> allSensorTypes = sensorTypeRepository.findAll();
        //Assert
        assertTrue(allSensorTypes.isEmpty());
    }

    @Test
    void shouldReturnSensorType_whenGivenValidTypeDescription() {
        //Arrange
        SensorType sensorType = mock(SensorType.class);
        TypeDescription typeDescription = mock(TypeDescription.class);
        when(sensorType.getID()).thenReturn(typeDescription);

        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        sensorTypeRepository.save(sensorType);

        //Act
        SensorType returnedSensorType = sensorTypeRepository.ofIdentity(typeDescription).get();

        //Assert
        assertEquals(sensorType, returnedSensorType);
    }

    @Test
    void shouldReturnOptinalEmpty_whenGivenInvalidTypeDescription() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

        SensorType sensorType = mock(SensorType.class);
        TypeDescription typeDescription = mock(TypeDescription.class);
        when(sensorType.getID()).thenReturn(typeDescription);

        sensorTypeRepository.save(sensorType);

        TypeDescription nonExistentTypeDescription = mock(TypeDescription.class);

        //Act
        Optional<SensorType> returnedSensorType = sensorTypeRepository.ofIdentity(nonExistentTypeDescription);

        //Assert
        assertTrue(returnedSensorType.isEmpty());
    }

    @Test
    void shouldReturnTrue_whenGivenValidTypeDescription() {
        //Arrange
        SensorType sensorType = mock(SensorType.class);
        TypeDescription typeDescription = mock(TypeDescription.class);
        when(sensorType.getID()).thenReturn(typeDescription);

        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        sensorTypeRepository.save(sensorType);

        //Act
        boolean containsSensorType = sensorTypeRepository.containsOfIdentity(typeDescription);

        //Assert
        assertTrue(containsSensorType);
    }

    @Test
    void shouldReturnFalse_whenGivenInvalidTypeDescription() {
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

        SensorType sensorType = mock(SensorType.class);
        TypeDescription typeDescription = mock(TypeDescription.class);
        when(sensorType.getID()).thenReturn(typeDescription);

        sensorTypeRepository.save(sensorType);

        TypeDescription nonExistentTypeDescription = mock(TypeDescription.class);

        //Act
        boolean containsSensorType = sensorTypeRepository.containsOfIdentity(nonExistentTypeDescription);

        //Assert
        assertFalse(containsSensorType);
    }

}
