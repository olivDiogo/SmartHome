package SmartHome.domain;

public class DeviceFactory {
    public Device createDevice(String name) {
        return new Device(name);
    }
}
