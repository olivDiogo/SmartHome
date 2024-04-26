package smarthome.domain.actuator;

import smarthome.ddd.IAggregateRoot;
import smarthome.ddd.IValueObject;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.*;
import smarthome.utils.visitor_pattern.IActuatorVisitor;

public interface IActuator extends IAggregateRoot<ActuatorID> {

    /**
     * Gets the actuator ID.
     *
     * @return The actuator ID.
     */
    ActuatorID getID();

    /**
     * Gets the actuator name.
     *
     * @return The actuator name.
     */
    ActuatorName getName();

    /**
     * Gets the model path.
     *
     * @return The model path.
     */
    ModelPath getModelPath();

    /**
     * Gets the actuator type ID.
     *
     * @return The actuator type ID.
     */
    ActuatorTypeID getActuatorTypeID();

    /**
     * Gets the device ID.
     */
    DeviceID getDeviceID();

    /**
     * Returns the actuator attributes in a string format.
     *
     * @return The actuator attributes in a string format.
     */
    String toString();

    /**
     * Method to get the value object of the actuator.
     *
     * @return the value.
     */
    IValueObject setValue(IValueObject value);


    /**
     * Method to accept the visitor.
     *
     * @param visitor The visitor.
     */
    String accept(IActuatorVisitor visitor);
}
