package smartHome.domain.deviceType;

import smartHome.ddd.IAggregateRoot;
import smartHome.valueObject.DeviceTypeID;
import smartHome.valueObject.TypeDescription;

import java.util.UUID;

public class DeviceType implements IAggregateRoot<DeviceTypeID> {
    private final TypeDescription _deviceTypeDescription;
    private DeviceTypeID _deviceTypeID;

    /**
     * Creates a new instance of the {@link DeviceType} class.
     *
     * @param deviceTypeDescription
     */
    DeviceType(TypeDescription deviceTypeDescription) {
        generateDeviceTypeID();
        validateDeviceTypeDescription(deviceTypeDescription);
        this._deviceTypeDescription = deviceTypeDescription;
    }

    /**
     * Validates the device type description.
     * If the device type description is null, an exception is thrown.
     *
     * @param deviceTypeDescription is the device type description.
     */
    private void validateDeviceTypeDescription(TypeDescription deviceTypeDescription) {
        if (deviceTypeDescription == null)
            throw new IllegalArgumentException("Device type description cannot be null.");
    }

    /**
     * Generates a new device type ID.
     */
    private void generateDeviceTypeID() {
        this._deviceTypeID = new DeviceTypeID(UUID.randomUUID().toString());
    }

    /**
     * Gets the device type ID.
     *
     * @return The device type ID.
     */
    @Override
    public DeviceTypeID getID() {
        return _deviceTypeID;
    }

    /**
     * Compares this instance with another instance.
     *
     * @param object is the object to be compared.
     * @return true if the instances are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof DeviceType deviceType) {
            return this._deviceTypeID.equals(deviceType.getID());
        }
        return false;
    }

    /**
     * Generates a hash code for the device type.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return this._deviceTypeID.hashCode();
    }

    /**
     * Gets the description of the device type.
     *
     * @return The description.
     */
    public TypeDescription getDescription() {
        return _deviceTypeDescription;

    }

    /**
     * Returns a string representation of the device type.
     *
     * @return The string representation.
     */
    public String toString() {
        return "Device Type:  Device Description= " + _deviceTypeDescription.getDescription() + " ID= " + _deviceTypeID.getID();
    }


}
