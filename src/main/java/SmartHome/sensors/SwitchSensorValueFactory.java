package SmartHome.sensors;

public class SwitchSensorValueFactory {
    public SwitchSensorValue create(boolean bValue) {
        return new SwitchSensorValue(bValue);
    }
}
