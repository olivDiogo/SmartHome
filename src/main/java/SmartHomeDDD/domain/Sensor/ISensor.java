package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.AggregateRoot;
import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;

public interface ISensor extends AggregateRoot<SensorID> {

    /**
     * Gets the sensor ID.
     *
     * @return The sensor ID.
     */
    SensorID getID();


    /**
     * Gets the sensor name.
     *
     * @return The sensor name.
     */
    SensorName getName();

    /**
     * Gets the model path.
     *
     * @return The model path.
     */
    ModelPath getModelPath();

    /**
     * Gets the sensor type ID.
     *
     * @return The sensor type ID.
     */
    SensorTypeID getSensorTypeID();

    /**
     * Returns the sensor attributes in a string format.
     *
     * @return The sensor attributes in a string format.
     */
    String toString();

    /**
     * Method to get the value object of the sensor.
     *
     * @return the value.
     */
    ValueObject getValue();

    /**
     * Gets the device ID.
     *
     * @return The device ID.
     */
    DeviceID getDeviceID();


}
