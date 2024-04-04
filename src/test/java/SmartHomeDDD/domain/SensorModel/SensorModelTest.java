package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import SmartHomeDDD.valueObject.SensorTypeID;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorModelTest {

    /**
     * The method should return a valid sensor model when given valid parameters.
     */

    @Test
    void shouldReturnValidSensorModel_WhenGivenValidParameters() {
        //Arrange
        SensorModelName sensorModelName = new SensorModelName("sensorModelName");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);

        //Assert
        assertNotNull(sensorModel);

    }

    /**
     * The method should throw an IllegalArgumentException when given a null sensor model name.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullSensorModelName() {
        //Arrange
        SensorModelName sensorModelName = null;
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");

        String expectedMessage = "Please enter a valid sensor model name.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorModel(sensorModelName, modelPath, sensorTypeID));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * The method should throw an IllegalArgumentException when given a null model path.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGivenNullModelPath() {
        //Arrange
        SensorModelName sensorModelName = new SensorModelName("sensorModelName");
        ModelPath modelPath = null;
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");

        String expectedMessage = "Please enter a valid model path.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorModel(sensorModelName, modelPath, sensorTypeID));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * The method should return true when given the same object.
     */
    @Test
    void shouldReturnTrue_WhenGivenSameObject() {
        //Arrange
        SensorModelName sensorModelName = new SensorModelName("sensorModelName");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");

        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);

        //Act
        boolean result = sensorModel.equals(sensorModel);
        //Assert
        assertTrue(result);

    }

    /**
     * The method should return false when given null.
     */
    @Test
    void shouldReturnFalse_WhenGivenNull() {
        //Arrange
        SensorModelName sensorModelName = new SensorModelName("sensorModelName");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");

        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);
        //Act
        boolean result = sensorModel.equals(null);
        //Assert
        assertFalse(result);
    }

    /**
     * The method should sensor model id when get id is called.
     */
    @Test
    void shouldReturnSensorModelID_WhenGetIDIsCalled() {
        //Arrange
        SensorModelName sensorModelName = new SensorModelName("sensorModelName");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");

        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);
        //Act
        ModelPath sensorModelID = sensorModel.getID();
        //Assert
        assertTrue(sensorModel.toString().contains(sensorModelID.toString()));

    }

    /**
     * The method should return object in string format when to string is called.
     */
    @Test
    void shouldReturnObjectInStringFormat_WhenToStringIsCalled() {
        //Arrange
        SensorModelName sensorModelName = new SensorModelName("sensorModelName");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");

        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);
        String expected = "SensorModel{" +
                ", _sensorModelName=" + sensorModelName +
                ", _modelPath=" + modelPath +
                '}';
        //Act
        String result = sensorModel.toString();

        //Assert
        assertTrue(result.contains(expected));

    }

    /**
     * The method should return sensor model name when get sensor model name is called.
     */
    @Test
    void shouldReturnSensorModelName_WhenGetSensorModelNameIsCalled() {
        //Arrange
        SensorModelName sensorModelName = new SensorModelName("sensorModelName");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");

        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);
        //Act
        SensorModelName result = sensorModel.getSensorModelName();
        //Assert
        assertEquals(sensorModelName, result);
    }

    /**
     * The method should return model path when get model path is called.
     */
    @Test
    void shouldReturnModelPath_WhenGetModelPathIsCalled() {
        //Arrange
        SensorModelName sensorModelName = new SensorModelName("sensorModelName");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID =  new SensorTypeID("sensorTypeID");

        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);

        //Act
        ModelPath result = sensorModel.getModelPath();
        //Assert
        assertEquals(modelPath, result);

    }

    /**
     * should throw an IllegalArgumentException when given a null sensor type ID.
     */

    @Test
    void shouldThrownIllegalArgumentException_WhenGivenNullSensorTypeID() {
        //Arrange
        SensorModelName sensorModelName = new SensorModelName("sensorModelName");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = null;
        String expectedMessage = "Please enter a valid sensor type ID.";

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new SensorModel(sensorModelName, modelPath, sensorTypeID));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * should return sensor type id when get sensor type id is called.
     */
    @Test
    void shouldReturnSensorTypeID_WhenGetSensorTypeIDIsCalled() {
        //Arrange
        SensorModelName sensorModelName = new SensorModelName("sensorModelName");
        ModelPath modelPath = new ModelPath("modelPath");
        SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");
        SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);

        //Act
        SensorTypeID result = sensorModel.getSensorTypeID();
        //Assert
        assertEquals(sensorTypeID, result);
    }
}

