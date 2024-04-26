package smart_home.domain.sensor_model;

import smart_home.ddd.IAggregateRoot;
import smart_home.utils.Validator;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorModelName;
import smart_home.value_object.SensorTypeID;

public class SensorModel implements IAggregateRoot<ModelPath> {
    private SensorModelName sensorModelName;
    private ModelPath modelPath;
    private SensorTypeID sensorTypeID;

    /**
     * Creates a new sensor model with the given sensor model name, model path, and sensor type ID.
     *
     * @param sensorModelName The name of the sensor model.
     * @param modelPath       The path to the model.
     * @param sensorTypeID    The ID of the sensor type.
     */
    SensorModel(
            SensorModelName sensorModelName, ModelPath modelPath, SensorTypeID sensorTypeID) {
      Validator.validateNotNull(sensorModelName, "SensorModelName");
      Validator.validateNotNull(modelPath, "ModelPath");
      Validator.validateNotNull(sensorTypeID, "SensorTypeID");
        this.sensorModelName = sensorModelName;
        this.modelPath = modelPath;
        this.sensorTypeID = sensorTypeID;
    }



    /**
     * Returns the sensor type ID.
     *
     * @return The sensor type ID.
     */
    public SensorTypeID getSensorTypeID() {
        return sensorTypeID;
    }

    /**
     * Returns the sensor model name.
     *
     * @return The sensor model name.
     */
    public SensorModelName getSensorModelName() {
        return sensorModelName;
    }

    /**
     * Returns the model path.
     *
     * @return The model path.
     */
    public ModelPath getModelPath() {
        return modelPath;
    }

    /**
     * Returns the unique identifier of the SensorModel instance.
     *
     * @return The ModelPath that uniquely identifies the sensor model.
     */
    @Override
    public ModelPath getID() {
        return modelPath;
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
            return modelPath.equals(sensorModel.modelPath);
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
        return modelPath.hashCode();
    }

    /**
     * Returns a string representation of the sensor model.
     *
     * @return A string representation of the sensor model.
     */
    @Override
    public String toString() {
        return "SensorModel: sensorModelName="
                + sensorModelName
                + ", modelPath="
                + modelPath
                + ", sensorTypeID="
                + sensorTypeID;
    }
}
