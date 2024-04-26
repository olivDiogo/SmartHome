package smarthome.domain.sensor;

import smarthome.ddd.IAggregateRoot;
import smarthome.ddd.IValueObject;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.*;
import smarthome.utils.visitor_pattern.ISensorVisitor;

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

    /**
     * Accepts the visitor.
     *
     * @param visitor The visitor.
     */
    String accept(ISensorVisitor visitor);
}
