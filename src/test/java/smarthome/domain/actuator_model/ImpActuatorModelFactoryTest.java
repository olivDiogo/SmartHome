package smarthome.domain.actuator_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

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
        actuatorModelFactory.createActuatorModel(modelPath, actuatorModelName, actuatorTypeID);
    // Assert
    assertNotNull(actuatorModel);
    assertEquals(actuatorModelName, actuatorModel.getName());
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
    // Act
    ActuatorModel actuatorModel =
        actuatorModelFactory.createActuatorModel(modelPath, actuatorModelName, actuatorTypeID);
    // Assert
    assertNotNull(actuatorModel);
    assertEquals(actuatorModelName, actuatorModel.getName());
  }
}
