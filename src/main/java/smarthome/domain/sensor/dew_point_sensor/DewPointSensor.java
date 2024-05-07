package smarthome.domain.sensor.dew_point_sensor;

import java.util.Objects;
import java.util.UUID;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.Validator;
import smarthome.utils.ValueSimulator;
import smarthome.utils.visitor_pattern.ISensorVisitor;

public class DewPointSensor implements ISensor {

  private final ModelPath modelPath;
  private final SensorName sensorName;
  private SensorID sensorID;
  private final SensorTypeID sensorTypeID;
  private DewPointValue dewPointValue;
  private final DeviceID deviceID;

  /**
   * Constructor of the class.
   *
   * @param deviceID     The device ID.
   * @param modelPath    The model path.
   * @param sensorName   The sensor name.
   * @param sensorTypeID The sensor type ID.
   */
  public DewPointSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName) {
    Validator.validateNotNull(deviceID, "DeviceID");
    this.deviceID = deviceID;
    Validator.validateNotNull(modelPath, "ModelPath");
    this.modelPath = modelPath;
    Validator.validateNotNull(sensorName, "SensorName");
    this.sensorName = sensorName;
    validateSensorTypeID(sensorTypeID);
    this.sensorTypeID = sensorTypeID;
    generateDewPointID();
  }

  public DewPointSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName, SensorID sensorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    this.deviceID = deviceID;
    Validator.validateNotNull(modelPath, "ModelPath");
    this.modelPath = modelPath;
    Validator.validateNotNull(sensorName, "SensorName");
    this.sensorName = sensorName;
    validateSensorTypeID(sensorTypeID);
    this.sensorTypeID = sensorTypeID;
    Validator.validateNotNull(sensorID, "SensorID");
    this.sensorID = sensorID;
  }


  /**
   * Validates the sensor type ID for a DewPoint.
   *
   * @param sensorTypeID The sensor type ID.
   */
  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");

    if (!Objects.equals(sensorTypeID.getID(), "DewPoint")) {
      throw new IllegalArgumentException("SensorTypeID must be 'DewPoint'");
    }
  }

  /**
   * Generates a new DewPointID.
   */
  private void generateDewPointID() {
    this.sensorID = new SensorID(UUID.randomUUID().toString());
  }

  /**
   * Gets the sensor name of the Dew Point Sensor.
   *
   * @return The sensor name.
   */
  @Override
  public SensorName getName() {
    return this.sensorName;
  }

  /**
   * Gets the model path for the Dew Point Sensor.
   *
   * @return The model path.
   */
  @Override
  public ModelPath getModelPath() {
    return this.modelPath;
  }

  /**
   * Gets the sensor ID of the DewPointSensor.
   *
   * @return The sensor ID.
   */
  @Override
  public SensorID getID() {
    return this.sensorID;
  }

  /**
   * Method to get the dew point value object of the sensor.
   *
   * @return the value.
   */

  @Override
  public DewPointValue getValue() {
    int dewPointValue = ValueSimulator.generateRandomValue(-70, 70);
    this.dewPointValue = new DewPointValue(dewPointValue);

    return this.dewPointValue;
  }

  /**
   * Gets the sensor type ID for the Dew Point Sensor.
   *
   * @return The sensor type ID.
   */
  @Override
  public SensorTypeID getSensorTypeID() {
    return this.sensorTypeID;
  }



  /**
   * Gets the device ID.
   *
   * @return The device ID.
   */
  @Override
  public DeviceID getDeviceID() {
    return this.deviceID;
  }

  /**
   * Method to compare the sensor with another object.
   *
   * @return the result of the comparison.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof DewPointSensor dewPointSensor) {

      return this.sensorID.equals(dewPointSensor.sensorID);
    }
    return false;
  }


  /**
   * Method to get the hash code of the sensor.
   *
   * @return the hash code.
   */
  @Override
  public int hashCode() {
    return this.sensorID.hashCode();
  }

  /**
   * Method to get the string representation of the sensor.
   *
   * @return the string representation.
   */
  @Override
  public String toString() {
    return "DewPointSensor:" +
        " modelPath=" + modelPath +
        ", sensorName=" + sensorName +
        ", sensorID=" + sensorID +
        ", sensorTypeID=" + sensorTypeID +
        ", dewPointValue=" + dewPointValue +
        ", deviceID=" + deviceID;
  }

  /**
   * Method to accept the visitor.
   *
   * @param visitor The visitor.
   * @return the result of the visit.
   */
  public String accept(ISensorVisitor visitor) {
    visitor.visitDewPointSensor(this);
    return this.toString();
  }

}
