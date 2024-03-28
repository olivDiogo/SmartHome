package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.ddd.AggregateRoot;
import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;

public interface Sensor extends AggregateRoot<SensorID> {

    /**
     * Gets the sensor ID.
     *
     * @return The sensor ID.
     */
    public SensorID getID();


    /**
     * Gets the sensor name.
     *
     * @return The sensor name.
     */
    public SensorName getName();

    /**
     * Gets the model path.
     *
     * @return The model path.
     */
    public ModelPath getModelPath();

    /**
     * Gets the sensor type ID.
     *
     * @return The sensor type ID.
     */
    public SensorTypeID getSensorTypeID();

    /**
     * Returns the sensor attributes in a string format.
     *
     * @return The sensor attributes in a string format.
     */
    public String toString();

    /**
     * Method to get the value object of the sensor.
     * @return the value.
     */
    public ValueObject getValue ();

    /**
     * Gets the device ID.
     * @return The device ID.
     */
    public DeviceID getDeviceID();


}
