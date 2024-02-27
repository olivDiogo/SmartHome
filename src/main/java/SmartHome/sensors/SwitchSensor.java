package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;

public class SwitchSensor {
    private final SensorType _sensorType;
    private boolean status;

    public SwitchSensor(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("Switch");
        if( sensorType == null )
            throw new InstantiationException("SensorType with description 'Switch' does not exist.");
        else{
            this._sensorType = sensorType;
            status = false;
        }
    }

    public String getStatus() {
        if (status == true) return "On";
        else return "Off";
    }
}
