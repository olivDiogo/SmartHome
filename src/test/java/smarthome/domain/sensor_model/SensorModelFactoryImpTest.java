package smarthome.domain.sensor_model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;

/**
 * Tests for the {@link SensorModelFactoryImpl} class, ensuring that sensor models are created
 * correctly.
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
