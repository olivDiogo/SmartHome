package SmartHome.sensors;

public class SolarIrradianceValueFactory {
    public SolarIrradianceValue createSolarIrradianceValue(double value) {
        return new SolarIrradianceValue(value);
    }
}
