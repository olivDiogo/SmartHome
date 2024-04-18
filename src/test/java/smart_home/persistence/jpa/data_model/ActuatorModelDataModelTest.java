package smart_home.persistence.jpa.data_model;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.value_object.ActuatorModelID;
import smart_home.value_object.ActuatorModelName;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.ModelPath;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
 class ActuatorModelDataModelTest {

  /** Test to ensure that an ActuatorModelDataModel can be instantiated successfully */
  @Test
  void shouldInstantiateActuatorModelDataModelWithDefaultConstructor() {
    // Act
    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel();
    // Assert
    assertNotNull(actuatorModelDataModel);
  }

  /**
   * Test to ensure that an ActuatorModelDataModel can be instantiated successfully when given an
   * ActuatorModel
   */
  @Test
  void shouldInstantiateActuatorModelDataModel() {
    // Arrange
    ActuatorModelID actuatorModelID = mock(ActuatorModelID.class);
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    ActuatorModel actuatorModel =
        new ActuatorModel(actuatorModelID, actuatorModelName, modelPath, actuatorTypeID);
    // Act
    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModel);
    // Assert
    assertNotNull(actuatorModelDataModel);
  }

  /**
   * Test to ensure that an IllegalArgumentException is thrown when an ActuatorModelDataModel is
   * instantiated with a null ActuatorModel
   */
  @Test
  void shouldThrowExceptionWhenGivenNullActuatorModel() {
    // Arrange
    ActuatorModel actuatorModel = null;
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> new ActuatorModelDataModel(actuatorModel));
    // Assert
    assertEquals("ActuatorModel cannot be null.", exception.getMessage());
  }

  /**
   * Test to return the actuator model ID
   */
  @Test
  void shouldReturnActuatorModelIDWhenGetActuatorModelID() {
    // Arrange
    String strActuatorModelName = "actuatorModelName";
    String strModelPath = "modelPath";
    String strActuatorTypeID = "actuatorTypeID";

    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorModel actuatorModelDouble = mock(ActuatorModel.class);

    when(actuatorModelName.getActuatorModelName()).thenReturn(strActuatorModelName);
    when(modelPath.getID()).thenReturn(strModelPath);
    when(actuatorTypeID.getID()).thenReturn(strActuatorTypeID);

    when(actuatorModelDouble.getActuatorModelName()).thenReturn(actuatorModelName);
    when(actuatorModelDouble.getID()).thenReturn(modelPath);
    when(actuatorModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);

    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModelDouble);
    // Act
    String result = actuatorModelDataModel.get_actuatorModelID();
    // Assert
    assertEquals(strModelPath, result);
  }

  /**
   * Test to return the actuator model name
   */
  @Test
  void shouldReturnActuatorModelNameWhenGetActuatorModelName() {
    // Arrange
    String strActuatorModelName = "actuatorModelName";
    String strModelPath = "modelPath";
    String strActuatorTypeID = "actuatorTypeID";

    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorModel actuatorModelDouble = mock(ActuatorModel.class);

    when(actuatorModelName.getActuatorModelName()).thenReturn(strActuatorModelName);
    when(modelPath.getID()).thenReturn(strModelPath);
    when(actuatorTypeID.getID()).thenReturn(strActuatorTypeID);

    when(actuatorModelDouble.getActuatorModelName()).thenReturn(actuatorModelName);
    when(actuatorModelDouble.getID()).thenReturn(modelPath);
    when(actuatorModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);

    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModelDouble);
    // Act
    String result = actuatorModelDataModel.get_actuatorModelName();
    // Assert
    assertEquals(strActuatorModelName, result);
  }

  /**
   * Test to return the actuator type ID
   */
  @Test
  void shouldReturnActuatorTypeIDWhenGetActuatorTypeID() {
    // Arrange
    String strActuatorModelName = "actuatorModelName";
    String strModelPath = "modelPath";
    String strActuatorTypeID = "actuatorTypeID";

    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorModel actuatorModelDouble = mock(ActuatorModel.class);

    when(actuatorModelName.getActuatorModelName()).thenReturn(strActuatorModelName);
    when(modelPath.getID()).thenReturn(strModelPath);
    when(actuatorTypeID.getID()).thenReturn(strActuatorTypeID);

    when(actuatorModelDouble.getActuatorModelName()).thenReturn(actuatorModelName);
    when(actuatorModelDouble.getID()).thenReturn(modelPath);
    when(actuatorModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);

    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModelDouble);
    // Act
    String result = actuatorModelDataModel.get_actuatorTypeID();
    // Assert
    assertEquals(strActuatorTypeID, result);
  }

  /**
   * Test to return the model path
   */
  @Test
  void shouldReturnActuatorModelPathWhenGetActuatorModelPath() {
    // Arrange
    String strActuatorModelName = "actuatorModelName";
    String strModelPath = "modelPath";
    String strActuatorTypeID = "actuatorTypeID";

    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorModel actuatorModelDouble = mock(ActuatorModel.class);

    when(actuatorModelName.getActuatorModelName()).thenReturn(strActuatorModelName);
    when(modelPath.getID()).thenReturn(strModelPath);
    when(actuatorTypeID.getID()).thenReturn(strActuatorTypeID);

    when(actuatorModelDouble.getActuatorModelName()).thenReturn(actuatorModelName);
    when(actuatorModelDouble.getID()).thenReturn(modelPath);
    when(actuatorModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);

    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModelDouble);
    // Act
    String result = actuatorModelDataModel.get_modelPath();
    // Assert
    assertEquals(strModelPath, result);
  }
}
