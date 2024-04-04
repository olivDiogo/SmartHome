package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.ddd.AggregateRoot;
import SmartHomeDDD.ddd.ValueObject;
import SmartHomeDDD.valueObject.*;

public interface Actuator extends AggregateRoot<ActuatorID> {

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
    ValueObject setValue(ValueObject value);


}
