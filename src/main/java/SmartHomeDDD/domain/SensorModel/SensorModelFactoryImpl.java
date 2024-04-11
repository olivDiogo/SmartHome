package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelName;
import SmartHomeDDD.valueObject.SensorTypeID;
/**
 * Implementation of the {@link ISensorModelFactory} interface, responsible for creating
 * {@link SensorModel} instances. This factory encapsulates the logic for sensor model creation,
 * ensuring that all necessary validations and initializations are performed before
 * a {@link SensorModel} object is returned to the caller.
 */
public class SensorModelFactoryImpl implements ISensorModelFactory {
    /**
     * Creates a new {@link SensorModel} instance using the provided sensor model name, model path, and sensor type ID.
     * This method ensures that a {@link SensorModel} object is instantiated with valid and non-null
     * parameters, leveraging the SensorModel constructor for validation and initialization.
     *
     * @param sensorModelName the name of the sensor model, must not be null
     * @param modelPath the path of the sensor model, must not be null
     * @param sensorTypeID the type ID of the sensor model, must not be null
     * @return a fully initialized {@link SensorModel} instance
     * @throws IllegalArgumentException if any of the parameters are null, handled by the {@link SensorModel} constructor
     */
    @Override
    public SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath modelPath, SensorTypeID sensorTypeID) {
        return new SensorModel(sensorModelName, modelPath, sensorTypeID);
    }
}
