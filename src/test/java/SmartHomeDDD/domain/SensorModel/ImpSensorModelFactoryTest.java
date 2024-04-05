package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import SmartHomeDDD.valueObject.SensorTypeID;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class ImpSensorModelFactoryTest {
    @Test
    void shouldCreateSensorModelWhenGivenValidSensorModelNameAndModelPath() {
        // Arrange
        SensorModelName sensorModelName = mock(SensorModelName.class);
        ModelPath modelPath = mock(ModelPath.class);
        SensorTypeID sensorTypeID = mock(SensorTypeID.class);
        ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();
        int expectedSize = 1;
        // Act
        try(MockedConstruction<SensorModel> sensorModelMockedConstruction = mockConstruction(SensorModel.class, (mock, context) -> {
            SensorModelName actualSensorModelName = (SensorModelName) context.arguments().get(0);
            ModelPath actualModelPath = (ModelPath) context.arguments().get(1);

        })) {
            SensorModel sensorModel = sensorModelFactory.createSensorModel(sensorModelName, modelPath, sensorTypeID);
            // Assert
            assertEquals(sensorModelMockedConstruction.constructed().size(), expectedSize);
            assertSame(sensorModelMockedConstruction.constructed().get(0) , sensorModel);
        }
    }


}