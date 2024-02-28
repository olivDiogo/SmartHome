package SmartHome.sensors;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;

public class WindSensor {

    private final SensorType _sensorType;

    /**
     * Constructor for WindSensor
     * @param catalogue
     * @throws InstantiationException
     */
    public WindSensor(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("WindSpeedAndDirection");
        if( sensorType == null){
            throw new InstantiationException("\"SensorType with description 'WindSpeedAndDirection' does not exist.\"");
        }
        else {
            this._sensorType = sensorType;
        }
    }

}
