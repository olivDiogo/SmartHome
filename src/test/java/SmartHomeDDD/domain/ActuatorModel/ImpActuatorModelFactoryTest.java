package SmartHomeDDD.domain.ActuatorModel;

import SmartHomeDDD.valueObject.ActuatorModelName;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.ModelPath;
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
        ActuatorModelFactory actuatorModelFactory = new ImpActuatorModelFactory();
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        // Act
        try(MockedConstruction<ActuatorModel> actuatorModelMockedConstruction = mockConstruction(ActuatorModel.class, (mock, context) -> {
            ActuatorModelName actualActuatorModelName = (ActuatorModelName) context.arguments().get(0);
            ModelPath actualModelPath = (ModelPath) context.arguments().get(1);

        })) {
            ActuatorModel actuatorModel = actuatorModelFactory.createActuatorModel(actuatorModelName, modelPath, actuatorTypeID);
            // Assert
            assertTrue(actuatorModelMockedConstruction.constructed().size() == 1);
            assertTrue(actuatorModelMockedConstruction.constructed().get(0) == actuatorModel);
        }

    }
}
