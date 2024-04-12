package smartHome.domain.actuatorModel;

import smartHome.valueObject.ActuatorModelName;
import smartHome.valueObject.ActuatorTypeID;
import smartHome.valueObject.ModelPath;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

public class ImpActuatorModelFactoryTest {

  @Test
  void shouldCreateActuatorModelWhenGivenValidActuatorModelNameAndModelPath() {
    // Arrange
    ActuatorModelName actuatorModelName = mock(ActuatorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    // Act
    try (MockedConstruction<ActuatorModel> actuatorModelMockedConstruction =
        mockConstruction(
            ActuatorModel.class,
            (mock, context) -> {
              ActuatorModelName actualActuatorModelName =
                  (ActuatorModelName) context.arguments().get(0);
              ModelPath actualModelPath = (ModelPath) context.arguments().get(1);
            })) {
      ActuatorModel actuatorModel =
          actuatorModelFactory.createActuatorModel(actuatorModelName, modelPath, actuatorTypeID);
      // Assert
      assertTrue(actuatorModelMockedConstruction.constructed().get(0) == actuatorModel);
    }
  }
}
