package smartHome.domain.actuator;

import smartHome.ddd.IAggregateRoot;
import smartHome.ddd.IValueObject;
import smartHome.valueObject.*;

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


}
