package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;

public class SwitchSensor {
    private final SensorType _sensorType; // This needs to be initialized in the constructor
    private boolean status;

    // Modified to use the passed 'catalogue' parameter correctly
    public SwitchSensor(CatalogueSensor catalogue) throws InstantiationException {
        this._sensorType = setSensorType(catalogue); // Initialize _sensorType in the constructor
        status = false;
    }

    // Modified to return SensorType and use the passed 'catalogue' object
    private SensorType setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        // Assume CatalogueSensors is properly defined elsewhere
        SensorType sensorType = catalogue.getSensorType("Switch");
        if (sensorType == null)
            throw new InstantiationException("SensorType with description 'Switch' does not exist.");
        else {
            return sensorType; // Return the sensorType instead of setting it directly
        }
    }

    public String getStatus() {
        return status ? "On" : "Off";
    }
}
