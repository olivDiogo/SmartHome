package SmartHome.domain;

/**
 * Class responsible for creating devices.
 */
public class DeviceFactory {

    /**
     * Creates a new device with a given name.
     *
     * @param name The name of the device.
     * @return A new device with the given name.
     */
    public Device createDevice(String name) {
        return new Device(name);
    }
}
