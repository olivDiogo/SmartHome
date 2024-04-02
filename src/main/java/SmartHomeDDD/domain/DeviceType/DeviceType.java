package SmartHomeDDD.domain.DeviceType;

import SmartHomeDDD.ddd.AggregateRoot;
import SmartHomeDDD.valueObject.DeviceTypeID;
import SmartHomeDDD.valueObject.TypeDescription;

import java.util.UUID;

public class DeviceType implements AggregateRoot<DeviceTypeID> {
    private TypeDescription _deviceTypeDescription;
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
     * @param deviceTypeDescription
     */
    private void validateDeviceTypeDescription(TypeDescription deviceTypeDescription){
        if (deviceTypeDescription == null)
            throw new IllegalArgumentException("Device type description cannot be null.");
    }

    /**
     * Generates a new device type ID.
     */
    private void generateDeviceTypeID(){
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
     * @param o is the object to be compared.
     * @return true if the instances are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceType)) return false;
        DeviceType deviceType = (DeviceType) o;
        return _deviceTypeID.toString().equals(deviceType._deviceTypeID.toString());
    }

    public TypeDescription getDescription() {
        return getDescription();

    }
}
