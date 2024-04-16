package smart_home.domain.device_type;

import smart_home.value_object.DeviceTypeID;
import smart_home.value_object.TypeDescription;


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
    @Override
    public DeviceType createDeviceType(DeviceTypeID id, TypeDescription description) throws IllegalArgumentException{
        return new DeviceType(id, description);
    }
}

