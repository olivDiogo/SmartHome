package smart_home.domain.actuator_model;

import org.junit.jupiter.api.Test;
import smart_home.value_object.ActuatorModelID;
import smart_home.value_object.ActuatorModelName;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.ModelPath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ImpActuatorModelFactoryTest {

  /**
   * Test to ensure that an ActuatorModel can be created successfully
   */
  @Test
  void shouldCreateActuatorModelWhenGivenValidActuatorModelNameAndModelPath() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    // Act
    ActuatorModel actuatorModel =
        actuatorModelFactory.createActuatorModel(actuatorModelName, modelPath, actuatorTypeID);
    // Assert
    assertNotNull(actuatorModel);
    assertEquals(actuatorModelName, actuatorModel.getActuatorModelName());
  }

  /**
   * Test to ensure that an ActuatorModel can be created successfully with an ActuatorModelID
   */
  @Test
  void shouldCreateActuatorModel_WhenGivenValidActuatorModelID_AndActuatorModelNameAndModelPath() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorModelID actuatorModelID = mock(ActuatorModelID.class);
    // Act
    ActuatorModel actuatorModel =
        actuatorModelFactory.createActuatorModel(
            actuatorModelID, actuatorModelName, modelPath, actuatorTypeID);
    // Assert
    assertNotNull(actuatorModel);
    assertEquals(actuatorModelName, actuatorModel.getActuatorModelName());
  }
}
