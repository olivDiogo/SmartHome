package SmartHomeDDD.repository;

import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.valueObject.SensorModelID;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorModelRepositoryTest {
    @Test
    void shouldAddSensorModelToRepositoryWhenGivenValidSensorModel() {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModel sensorModel = mock(SensorModel.class);
        //Act
        SensorModel actualSensorModel = sensorModelRepository.save(sensorModel);
        //Assert
        assertEquals(sensorModel, actualSensorModel);
    }
    @Test
    void shouldThrowExceptionWhenGivenNullSensorModel() {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModel sensorModel = null;
        String expectedMessage = "SensorModel cannot be null.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sensorModelRepository.save(sensorModel));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenSensorModelAlreadyExists() {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModel sensorModel = mock(SensorModel.class);
        sensorModelRepository.save(sensorModel);
        String expectedMessage = "SensorModel already exists.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sensorModelRepository.save(sensorModel));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldReturnListSensorModelsWhenGetAllSensorModelsIsCalled() {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModel sensorModel = mock(SensorModel.class);
        SensorModel secondSensorModel = mock(SensorModel.class);

        SensorModelID sensorModelID = mock(SensorModelID.class);
        when(sensorModel.getID()).thenReturn(sensorModelID);

        sensorModelRepository.save(sensorModel);
        sensorModelRepository.save(secondSensorModel);

        List<SensorModel> expectedSensorModels = List.of(sensorModel, secondSensorModel);
        //Act
        List<SensorModel> sensorModels = sensorModelRepository.findAll();
        //Assert
        assertEquals(expectedSensorModels, sensorModels);
    }
    @Test
    void shouldReturnEmptyListWhenNoSensorModelsAreAdded() {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        //Act
        List<SensorModel> sensorModels = sensorModelRepository.findAll();
        //Assert
        assertTrue(sensorModels.isEmpty());
    }
    @Test
    void shouldReturnSensorModelWhenGivenValidSensorModelID() {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModel sensorModel = mock(SensorModel.class);
        SensorModelID sensorModelID = mock(SensorModelID.class);
        when(sensorModel.getID()).thenReturn(sensorModelID);

        sensorModelRepository.save(sensorModel);
        //Act
        SensorModel actualSensorModel = sensorModelRepository.ofIdentity(sensorModelID).get();
        //Assert
        assertEquals(sensorModel, actualSensorModel);
    }
    @Test
    void shouldReturnOptionalEmptyWhenGivenInvalidSensorModelID() {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModel sensorModel = mock(SensorModel.class);
        SensorModelID sensorModelID = mock(SensorModelID.class);
        SensorModelID invalidSensorModelID = mock(SensorModelID.class);
        when(sensorModel.getID()).thenReturn(sensorModelID);

        sensorModelRepository.save(sensorModel);
        //Act
        boolean result = sensorModelRepository.ofIdentity(invalidSensorModelID).isEmpty();
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnTrueWhenGivenValidSensorModelID() {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModel sensorModel = mock(SensorModel.class);
        SensorModelID sensorModelID = mock(SensorModelID.class);
        when(sensorModel.getID()).thenReturn(sensorModelID);

        sensorModelRepository.save(sensorModel);
        //Act
        boolean result = sensorModelRepository.containsOfIdentity(sensorModelID);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseWhenGivenInvalidSensorModelID() {
        //Arrange
        SensorModelRepository sensorModelRepository = new SensorModelRepository();
        SensorModel sensorModel = mock(SensorModel.class);
        SensorModelID sensorModelID = mock(SensorModelID.class);
        SensorModelID invalidSensorModelID = mock(SensorModelID.class);
        when(sensorModel.getID()).thenReturn(sensorModelID);

        sensorModelRepository.save(sensorModel);
        //Act
        boolean result = sensorModelRepository.containsOfIdentity(invalidSensorModelID);
        //Assert
        assertFalse(result);
    }
}