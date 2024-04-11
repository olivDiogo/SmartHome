package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.ddd.AggregateRoot;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import SmartHomeDDD.valueObject.SensorTypeID;

public class SensorModel implements AggregateRoot<ModelPath> {
  private SensorModelName _sensorModelName;
  private ModelPath _modelPath;
  private SensorTypeID _sensorTypeID;

  /**
   * Creates a new sensor model with the given sensor model name, model path, and sensor type ID.
   *
   * @param sensorModelName The name of the sensor model.
   * @param modelPath The path to the model.
   * @param sensorTypeID The ID of the sensor type.
   */
  public SensorModel(
      SensorModelName sensorModelName, ModelPath modelPath, SensorTypeID sensorTypeID) {
    validateSensorModelName(sensorModelName);
    validateModelPath(modelPath);
    validateSensorTypeID(sensorTypeID);
  }

  /**
   * Validates the sensor type ID.
   *
   * @param sensorTypeID The sensor type ID to validate.
   */
  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    if (sensorTypeID == null) {
      throw new IllegalArgumentException("Please enter a valid sensor type ID.");
    } else {
      this._sensorTypeID = sensorTypeID;
    }
  }

  /**
   * Validates the sensor model name.
   *
   * @param sensorModelName The sensor model name to validate.
   */
  private void validateSensorModelName(SensorModelName sensorModelName) {
    if (sensorModelName == null) {
      throw new IllegalArgumentException("Please enter a valid sensor model name.");
    } else {
      this._sensorModelName = sensorModelName;
    }
  }

  /**
   * Validates the model path.
   *
   * @param modelPath The model path to validate.
   */
  private void validateModelPath(ModelPath modelPath) {
    if (modelPath == null) {
      throw new IllegalArgumentException("Please enter a valid model path.");
    } else {
      this._modelPath = modelPath;
    }
  }

  /**
   * Returns the sensor type ID.
   *
   * @return The sensor type ID.
   */
  public SensorTypeID getSensorTypeID() {
    return _sensorTypeID;
  }

  /**
   * Returns the sensor model name.
   *
   * @return The sensor model name.
   */
  public SensorModelName getSensorModelName() {
    return _sensorModelName;
  }

  /**
   * Returns the model path.
   *
   * @return The model path.
   */
  public ModelPath getModelPath() {
    return _modelPath;
  }

  /**
   * Returns the unique identifier of the SensorModel instance.
   *
   * @return The ModelPath that uniquely identifies the sensor model.
   */
  @Override
  public ModelPath getID() {
    return _modelPath;
  }

  /**
   * Compares the sensor model to another object.
   *
   * @param object The object to compare.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof SensorModel sensorModel) {
      return _modelPath.equals(sensorModel._modelPath);
    }
    return false;
  }

  /**
   * Returns the hash code of the sensor model.
   *
   * @return The hash code of the sensor model.
   */
  @Override
  public int hashCode() {
    return _modelPath.hashCode();
  }

  /**
   * Returns a string representation of the sensor model.
   *
   * @return A string representation of the sensor model.
   */
  @Override
  public String toString() {
    return "SensorModel: sensorModelName="
        + _sensorModelName
        + ", modelPath="
        + _modelPath
        + ", sensorTypeID="
        + _sensorTypeID;
  }
}
