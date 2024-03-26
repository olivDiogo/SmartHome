package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelID;
import SmartHomeDDD.valueObject.SensorModelName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SensorModelTest {
    @Test
    void shouldReturnValidSensorModel_WhenGivenValidParameters() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        //Act
        new SensorModel(sensorModelName, modelPath);
    }
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullSensorModelName() {
        //Arrange
        SensorModelName sensorModelName = null;
        ModelPath modelPath = mock(ModelPath.class);
        String expectedMessage = "Please enter a valid sensor model name.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorModel(sensorModelName, modelPath));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullModelPath() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = null;
        String expectedMessage = "Please enter a valid model path.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorModel(sensorModelName, modelPath));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldReturnTrue_WhenGivenSameObject() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
        //Act
        boolean result = sensorModel.equals(sensorModel);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalse_WhenGivenDifferentObject() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
        SensorModel sensorModel2 = new SensorModel(sensorModelName, modelPath);
        //Act
        boolean result = sensorModel.equals(sensorModel2);
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalse_WhenGivenNull() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
        //Act
        boolean result = sensorModel.equals(null);
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnSensorModelID_WhenGetIDIsCalled() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
        //Act
        SensorModelID sensorModelID = sensorModel.getID();
        //Assert
        assertTrue(sensorModel.toString().contains(sensorModelID.toString()));
    }
    @Test
    void shouldReturnObjectInStringFormat_WhenToStringIsCalled() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
        //Act
        String result = sensorModel.toString();
        //Assert
        assertTrue(result.contains(sensorModelName.toString()));
        assertTrue(result.contains(modelPath.toString()));
    }

}