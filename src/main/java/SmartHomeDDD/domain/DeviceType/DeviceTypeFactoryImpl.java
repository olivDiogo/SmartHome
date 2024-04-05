package SmartHomeDDD.domain.DeviceType;

import SmartHomeDDD.valueObject.TypeDescription;

/**
 * Factory implementation for creating device types.
 * This class provides a concrete implementation for the DeviceTypeFactory interface,
 * allowing for the creation of DeviceType instances based on provided descriptions.
 */
public class DeviceTypeFactoryImpl implements IDeviceTypeFactory {

    /**
     * Creates a new DeviceType instance based on the provided type description.
     *
     * @param deviceTypeDescription The description of the device type to be created.
     * @return A new instance of DeviceType initialized with the given description.
     */
    @Override
    public DeviceType createDeviceType(TypeDescription deviceTypeDescription) throws IllegalArgumentException {
        return new DeviceType(deviceTypeDescription);
    }
}

