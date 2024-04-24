package smart_home.domain.sensor.switch_sensor;

import smart_home.ddd.IValueObject;

public class SwitchSensorValue implements IValueObject {
    private final boolean bValue;

    /**
     * Constructs a SwitchSensorValue with a specified state.
     *
     * @param bValue The state of the switch sensor.
     */
    public SwitchSensorValue(boolean bValue) {
        this.bValue = bValue;
    }

    /**
     * Returns a string representation of the switch sensor value.
     *
     * @return The switch sensor value as a string.
     */
    public String toString() {
        return this.bValue ? "On" : "Off";
    }

    /**
     * Method to check if the switch sensor value is equal to another switch sensor value.
     */
    public boolean equals(Object o) {
        if (o instanceof SwitchSensorValue switchSensorValue) {
            return this.bValue == switchSensorValue.bValue;
        }
        return false;
    }

    /**
     * Method to generate a hash code for the switch sensor value.
     */
    public int hashCode() {
        return Boolean.hashCode(this.bValue);
    }
}
