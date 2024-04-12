package smartHome.domain.sensorModel;

import smartHome.valueObject.ModelPath;
import smartHome.valueObject.SensorModelName;
import smartHome.valueObject.SensorTypeID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorModelTest {

  /** Test of class SensorModel constructor with valid parameters */
  @Test
  void shouldReturnValidSensorModel_WhenGivenValidParameters() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    // Act
    SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);

    // Assert
    assertNotNull(sensorModel);
  }

  /** Test of class SensorModel constructor with null SensorModelName */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullSensorModelName() {
    // Arrange
    SensorModelName sensorModelName = null;
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    String expectedMessage = "Please enter a valid sensor model name.";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new SensorModel(sensorModelName, modelPath, sensorTypeID));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Test of class SensorModel constructor with null ModelPath */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullModelPath() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = null;
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    String expectedMessage = "Please enter a valid model path.";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new SensorModel(sensorModelName, modelPath, sensorTypeID));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Test of class SensorModel constructor with null SensorTypeID */
  @Test
  void shouldReturnTrue_WhenGivenSameObject() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);
    // Act
    boolean result = sensorModel.equals(sensorModel);
    // Assert
    assertTrue(result);
  }

  /** Test of class SensorModel equals method with null */
  @Test
  void shouldReturnFalse_WhenGivenNull() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);
    // Act
    boolean result = sensorModel.equals(null);
    // Assert
    assertFalse(result);
  }

  /** Test of class SensorModel to get the expected ID */
  @Test
  void shouldReturnSensorModelID_WhenGetIDIsCalled() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);
    // Act
    ModelPath sensorModelID = sensorModel.getID();
    // Assert
    assertTrue(sensorModel.toString().contains(sensorModelID.toString()));
  }

  /** Test of class SensorModel to get it in string format. */
  @Test
  void shouldReturnObjectInStringFormat_WhenToStringIsCalled() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);
    String expected =
        "SensorModel: sensorModelName="
            + sensorModelName
            + ", modelPath="
            + modelPath
            + ", sensorTypeID="
            + sensorTypeID;
    // Act
    String result = sensorModel.toString();

    // Assert
    assertTrue(result.contains(expected));
  }

  /** Test of class SensorModel to get sensor model name. */
  @Test
  void shouldReturnSensorModelName_WhenGetSensorModelNameIsCalled() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);
    // Act
    SensorModelName result = sensorModel.getSensorModelName();
    // Assert
    assertEquals(sensorModelName, result);
  }

  /** Test of class SensorModel to get model path. */
  @Test
  void shouldReturnModelPath_WhenGetModelPathIsCalled() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);
    // Act
    ModelPath result = sensorModel.getModelPath();
    // Assert
    assertEquals(modelPath, result);
  }

  /** Test of class SensorModel throws exception when given null sensor type ID. */
  @Test
  void shouldThrownIllegalArgumentException_WhenGivenNullSensorTypeID() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = null;
    String expectedMessage = "Please enter a valid sensor type ID.";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> new SensorModel(sensorModelName, modelPath, sensorTypeID));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /** Test of class SensorModel to get sensor type ID. */
  @Test
  void shouldReturnSensorTypeID_WhenGetSensorTypeIDIsCalled() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    SensorModel sensorModel = new SensorModel(sensorModelName, modelPath, sensorTypeID);
    // Act
    SensorTypeID result = sensorModel.getSensorTypeID();
    // Assert
    assertEquals(sensorTypeID, result);
  }

  /** Test of class SensorModel to check if two objects have the same hashCode. */
  @Test
  void shouldReturnTheHashCode_WhenHashCodeIsCalled() {
    // Arrange
    SensorModelName sensorModelName1 = mock(SensorModelName.class);
    ModelPath modelPath1 = mock(ModelPath.class);
    SensorTypeID sensorTypeID1 = mock(SensorTypeID.class);
    SensorModel sensorModel1 = new SensorModel(sensorModelName1, modelPath1, sensorTypeID1);

    SensorModelName sensorModelName2 = mock(SensorModelName.class);
    SensorTypeID sensorTypeID2 = mock(SensorTypeID.class);
    SensorModel sensorModel2 = new SensorModel(sensorModelName2, modelPath1, sensorTypeID2);

    // Act

    int expectedHashCode1 = sensorModel1.hashCode();
    int expectedHashCode2 = sensorModel2.hashCode();

    // Assert
    assertEquals(expectedHashCode1, expectedHashCode2);
  }
}
