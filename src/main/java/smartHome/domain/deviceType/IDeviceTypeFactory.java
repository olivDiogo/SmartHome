package smartHome.domain.deviceType;

import smartHome.valueObject.DeviceTypeID;
import smartHome.valueObject.TypeDescription;


/**
 * Represents a factory interface for creating {@link DeviceType} instances.
 * This interface defines a method for creating device types based on provided descriptions.
 */
public interface IDeviceTypeFactory {

    /**
     * Creates a new {@link DeviceType} instance based on the provided type description.
     *
     * @param deviceTypeDescription The description of the device type to be created.
     *                              It contains information such as the type name and attributes.
     * @return A new instance of {@link DeviceType} configured according to the provided description.
     */
    DeviceType createDeviceType (TypeDescription deviceTypeDescription);

    DeviceType createDeviceType (DeviceTypeID id,TypeDescription description);

}
