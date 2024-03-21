package SmartHomeDDD.repository;

import org.junit.jupiter.api.Test;
import SmartHomeDDD.ValueObject.TypeDescription;
import SmartHomeDDD.domain.SensorType.SensorType;

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
}
