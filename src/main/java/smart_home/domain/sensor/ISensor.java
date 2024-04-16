package smart_home.domain.sensor;

import smart_home.ddd.IAggregateRoot;
import smart_home.ddd.IValueObject;
import smart_home.value_object.*;

public interface ISensor extends IAggregateRoot<SensorID> {

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
    IValueObject getValue();

    /**
     * Gets the device ID.
     *
     * @return The device ID.
     */
    DeviceID getDeviceID();


}
