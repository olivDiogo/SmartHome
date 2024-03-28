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
    public ActuatorID getID();

    /**
     * Gets the actuator name.
     *
     * @return The actuator name.
     */
    public ActuatorName getName();

    /**
     * Gets the model path.
     *
     * @return The model path.
     */
    public ModelPath getModelPath();

    /**
     * Gets the actuator type ID.
     *
     * @return The actuator type ID.
     */
    public ActuatorTypeID getActuatorTypeID();

    /**
     * Gets the device ID.
     */
    public DeviceID getDeviceID();

    /**     * Returns the actuator attributes in a string format.
     *
     * @return The actuator attributes in a string format.
     */
    public String toString();

    /**
     * Method to get the value object of the actuator.
     * @return the value.
     */
    public ValueObject setValue (ValueObject value);


}
