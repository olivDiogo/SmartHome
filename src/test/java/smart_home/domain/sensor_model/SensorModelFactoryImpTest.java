package smart_home.domain.sensor_model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorModelName;
import smart_home.value_object.SensorTypeID;

import static org.mockito.Mockito.mock;
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
