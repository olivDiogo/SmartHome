package smartHome.domain.sensorModel;

import smartHome.valueObject.ModelPath;
import smartHome.valueObject.SensorModelName;
import smartHome.valueObject.SensorTypeID;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
/**
 * Tests for the {@link SensorModelFactoryImpl} class, ensuring that sensor models are created correctly.
 */

class SensorModelFactoryImpTest {
  /**
   * Test to ensure that a sensor model can be created successfully when given valid parameters.
   */
  @Test
  void shouldCreateSensorModelWhenGivenValidSensorModelNameAndModelPath() {
    // Arrange
    SensorModelName sensorModelName = mock(SensorModelName.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();
    // Act

    SensorModel sensorModel =
        sensorModelFactory.createSensorModel(sensorModelName, modelPath, sensorTypeID);
    // Assert
    assertNotNull(sensorModel);
  }
}
