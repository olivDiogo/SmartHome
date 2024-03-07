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

    /**
     * Constructor of the class Device.
     *
     * @param name the name of the device.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    protected Device(String name) throws IllegalArgumentException {
        setName(name);
        this._sensors = new ArrayList<>();
        this._status = true;
        this._deviceId = UUID.randomUUID();
        this._actuators = new ArrayList<>();
    }

    /**
     * Method to set the name of the device.
     *
     * @param name the name of the device.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    private void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter a valid name for the device type.");
        }
        this._name = name;
    }

    /**
     * Method to deactivate the device.
     *
     * @return true if the device is activated, false otherwise.
     */
    public boolean deactivateDevice() {
        this._status = false;
        return true;
    }


    /**
     * Method to get the name of the device.
     *
     * @return the name of the device.
     */
    public String getName() {
        return this._name;
    }

    /**
     * Method to get the status of the device.
     *
     * @return the status of the device.
     */
    public boolean getStatus() {
        return _status;
    }

    /**
     * Method to get the device ID.
     *
     * @return the device ID.
     */
    public UUID getDeviceId() {
        return this._deviceId;
    }

    /**
     * Method to get the functionalities of the device.
     *
     * @return the functionalities of the device.
     */
    public List<String> getDeviceFunctionalities() {
        List<String> functionalities = new ArrayList<>();
        for (Sensor sensor : _sensors) {
            functionalities.add(sensor.getSensorType().getDescription());
        }
        return functionalities;
    }

    /**
     * Method to get the list of sensors of the device.
     *
     * @return the list of sensors of the device.
     */
    public List<String> getSensorList() {
        List<String> sensorList = new ArrayList<>();
        for (Sensor sensor : _sensors) {
            sensorList.add(sensor.getSensorType().getDescription());
        }
        return List.copyOf(sensorList);

    }

    /**
     * Method to get the list of actuators of the device.
     *
     * @return the list of actuators of the device.
     */
    public List<String> getActuatorList() {
        List<String> actuatorList = new ArrayList<>();
        for (Actuator actuator : _actuators) {
            actuatorList.add(actuator.getActuatorType().getDescription());
        }
        return List.copyOf(actuatorList);
    }

    /**
     * Method to add a sensor to the device.
     *
     * @param strModel      the model of the sensor.
     * @param catalogue     the catalogue of sensors.
     * @param sensorFactory the factory of the sensor.
     * @return the sensor added to the device.
     * @throws InstantiationException if the sensor could not be created.
     */
    public Sensor addSensor(String strModel, CatalogueSensor catalogue, SensorFactory sensorFactory) throws InstantiationException {
        Sensor sensor = catalogue.getSensor(strModel, sensorFactory);
        sensor = addSensorToDevice(sensor);
        return sensor;
    }

    /**
     * Method to add the sensor to the device's sensor list.
     *
     * @param sensor the sensor to add.
     * @return the sensor added to the device.
     */
    protected Sensor addSensorToDevice(Sensor sensor) {
        if (sensor == null)
            return null;
        this._sensors.add(sensor);
        return sensor;
    }

    /**
     * Method to add an actuator to the device.
     *
     * @param strModel        the model of the actuator.
     * @param catalogue       the catalogue of actuators.
     * @param actuatorFactory the factory of the actuator.
     * @return the actuator added to the device.
     * @throws InstantiationException if the actuator could not be created.
     */
    public Actuator addActuator(String strModel, CatalogueActuator catalogue, ActuatorFactory actuatorFactory) throws InstantiationException {
        Actuator actuator = catalogue.getActuator(strModel, actuatorFactory);
        actuator = addActuatorToDevice(actuator);
        return actuator;
    }

    /**
     * Method to add the actuator to device's actuator list.
     *
     * @param actuator the actuator to add.
     * @return the actuator added to the device.
     */
    protected Actuator addActuatorToDevice(Actuator actuator) {
        if (actuator == null)
            return null;
        this._actuators.add(actuator);
        return actuator;
    }

    /**
     * Overrides the toString method from the Object class.
     * Provides a string representation of the Device object, including its name, status, sensors and device ID.
     *
     * @return a string representation of the Device object.
     */
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
