package SmartHome.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Device {
    private String _name;
    private boolean _status;
    private List<Sensor> _sensors;
    private UUID _deviceId;
    private List<Actuator> _actuators;

    protected Device(String name) throws IllegalArgumentException {
        setName(name);
        this._sensors = new ArrayList<>();
        this._status = false;
        this._deviceId = UUID.randomUUID();
        this._actuators = new ArrayList<>();
    }
    private void setName(String name) throws IllegalArgumentException{
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter a valid name for the device type.");
        }
        this._name = name;
    }
    public boolean deactivateDevice() {
        this._status = false;
        return true;
    }
    public String getName() {
        return this._name;
    }
    public boolean getStatus() {
        return _status;
    }
    public UUID getDeviceId() {
        return this._deviceId;
    }

    public List<String> getDeviceFunctionalities(){
        List<String> functionalities = new ArrayList<>();
        for (Sensor sensor : _sensors) {
            functionalities.add(sensor.getSensorType().getDescription());
        }
        return functionalities;
    }

    public List<String> getSensorList() {
        List<String> sensorList = new ArrayList<>();
        for (Sensor sensor : _sensors) {
            sensorList.add(sensor.getSensorType().getDescription());
        }
        return List.copyOf(sensorList);

    }

    public List<String> getActuatorList() {
        List<String> actuatorList = new ArrayList<>();
        for (Actuator actuator : _actuators) {
            actuatorList.add(actuator.getActuatorType().getDescription());
        }
        return List.copyOf(actuatorList);
    }

    public Sensor addSensor(String strModel, CatalogueSensor catalogue, SensorFactory sensorFactory) throws InstantiationException {
        Sensor sensor = catalogue.getSensor( strModel, sensorFactory );
        sensor = addSensorToDevice(sensor);
        return sensor;
    }

    protected Sensor addSensorToDevice(Sensor sensor) {
        if( sensor == null )
            return null;
        this._sensors.add(sensor);
        return sensor;
    }

    @Override
    public String toString() {
        return "Device{" +
                "_name='" + _name + '\'' +
                ", _status=" + _status +
                ", _sensors=" + _sensors +
                ", _deviceId=" + _deviceId +
                '}';
    }



}
