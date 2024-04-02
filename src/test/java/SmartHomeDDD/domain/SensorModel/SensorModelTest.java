package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelID;
import SmartHomeDDD.valueObject.SensorModelName;
import SmartHomeDDD.valueObject.SensorTypeID;
import net.bytebuddy.implementation.StubMethod;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.concurrent.locks.StampedLock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorModelTest {
    @Test
    void shouldReturnValidSensorModel_WhenGivenValidParameters() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        try(MockedConstruction sensorModelID = mockConstruction(SensorModelID.class)) {
            //Act
            new SensorModel(sensorModelName, modelPath);
        }
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
        try(MockedConstruction sensorModelID = mockConstruction(SensorModelID.class)) {

            SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
            //Act
            boolean result = sensorModel.equals(sensorModel);
            //Assert
            assertTrue(result);
        }
    }

    @Test
    void shouldReturnFalse_WhenGivenNull() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        try(MockedConstruction sensorModelID = mockConstruction(SensorModelID.class)) {

            SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
            //Act
            boolean result = sensorModel.equals(null);
            //Assert
            assertFalse(result);
        }
    }
    @Test
    void shouldReturnSensorModelID_WhenGetIDIsCalled() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        try(MockedConstruction mockedSensorModelID = mockConstruction(SensorModelID.class)) {
            SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
            //Act
            SensorModelID sensorModelID = sensorModel.getID();
            //Assert
            assertTrue(sensorModel.toString().contains(sensorModelID.toString()));
        }
    }
    @Test
    void shouldReturnObjectInStringFormat_WhenToStringIsCalled() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        try(MockedConstruction<SensorModelID> mockedSensorModelID = mockConstruction(SensorModelID.class)) {
            SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
            SensorModelID sensorModelID = mockedSensorModelID.constructed().get(0);
            String expected = "SensorModel{" +
                    "_sensorModelID=" + sensorModelID +
                    ", _sensorModelName=" + sensorModelName +
                    ", _modelPath=" + modelPath +
                    '}';
            //Act
            String result = sensorModel.toString();

            //Assert
            assertTrue(result.contains(expected));
        }
    }
    @Test
    void shouldReturnSensorModelName_WhenGetSensorModelNameIsCalled() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        try(MockedConstruction<SensorModelID> mockedSensorModelID = mockConstruction(SensorModelID.class)) {

            SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
            //Act
            SensorModelName result = sensorModel.getSensorModelName();
            //Assert
            assertEquals(sensorModelName, result);
        }
    }
    @Test
    void shouldReturnModelPath_WhenGetModelPathIsCalled() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        try(MockedConstruction<SensorModelID> mockedSensorModelID = mockConstruction(SensorModelID.class)) {

            SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
            //Act
            ModelPath result = sensorModel.getModelPath();
            //Assert
            assertEquals(modelPath, result);
        }
    }
    @Test
    void sensorModelIDShouldBeGeneratedFromModelPath_WhenGenerateIDIsCalled() {
        //Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        try(MockedConstruction<SensorModelID> mockedSensorModelID = mockConstruction(SensorModelID.class, (mock, context) -> {
            String modelPathString = (String) context.arguments().get(0);
            when(mock.toString()).thenReturn(modelPathString);})) {

                SensorModel sensorModel = new SensorModel(sensorModelName, modelPath);
                //Act
                SensorModelID sensorModelID = sensorModel.getID();
                //Assert
                assertTrue(sensorModelID.toString().contains(modelPath.toString()));
            }
        }

}