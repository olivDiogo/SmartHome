package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImpSensorModelFactoryTest {
    @Test
    void shouldCreateSensorModelWhenGivenValidSensorModelNameAndModelPath() {
        // Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorModelFactory sensorModelFactory = new ImpSensorModelFactory();
        // Act
        try(MockedConstruction<SensorModel> sensorModelMockedConstruction = mockConstruction(SensorModel.class, (mock, context) -> {
            SensorModelName actualSensorModelName = (SensorModelName) context.arguments().get(0);
            ModelPath actualModelPath = (ModelPath) context.arguments().get(1);

        })) {
            SensorModel sensorModel = sensorModelFactory.createSensorModel(sensorModelName, modelPath);
            // Assert
            assertTrue(sensorModelMockedConstruction.constructed().size() == 1);
            assertTrue(sensorModelMockedConstruction.constructed().get(0) == sensorModel);
        }
    }


}