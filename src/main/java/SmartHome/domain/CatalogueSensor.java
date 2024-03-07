package SmartHome.domain;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CatalogueSensor {
    private List<SensorType> _listSensorTypes;
    private List<String> _listStringClassesSensors;

    /**
     * Constructor of the class CatalogueSensor.
     *
     * @param filePathname the path of the file that contains the configuration of the sensors.
     * @throws InstantiationException if something went wrong in reading the configuration.
     */
    public CatalogueSensor(String filePathname) throws InstantiationException {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File(filePathname)); // e.g. filePathname = "config.properties"

            // access configuration properties
            String[] arrayStringClassesSensors = config.getStringArray("sensor");
            setListSensorTypes();
            setListStringClassesSensors(arrayStringClassesSensors);
        } catch (ConfigurationException exception) {
            // Something went wrong
            throw new InstantiationException("something went wrong in reading the configuration: " + exception.getMessage());
        }
    }

    /**
     * Constructor of the class CatalogueSensor.
     */
    private void setListSensorTypes() {
        this._listSensorTypes = new ArrayList<>();
    }

    /**
     * Constructor of the class CatalogueSensor.
     *
     * @param arrayStringClassesSensors the array of the classes of the sensors.
     */
    private void setListStringClassesSensors(String[] arrayStringClassesSensors) {
        this._listStringClassesSensors = List.of(arrayStringClassesSensors);
    }


    /**
     * Add a sensor type to the list of sensor types.
     *
     * @param strDescription    the description of the sensor type.
     * @param unit              the unit of the sensor type.
     * @param sensorTypeFactory the factory of the sensor type.
     * @return the sensor type added to the list of sensor types.
     * @throws InstantiationException if the sensor could not be added.
     */
    public SensorType addSensorType(String strDescription, Unit unit, SensorTypeFactory sensorTypeFactory) throws InstantiationException {
        SensorType _sensorType = sensorTypeFactory.createSensorType(strDescription, unit);

        addSensorTypeToList(_sensorType);

        return _sensorType;
    }

    /**
     * Add a sensor type to the list of sensor types.
     *
     * @param sensorType the sensor type to add.
     * @return the sensor type added to the list of sensor types.
     */
    protected SensorType addSensorTypeToList(SensorType sensorType) {
        this._listSensorTypes.add(sensorType);
        return sensorType;
    }

    /**
     * Get the sensor type from the list of sensor types.
     *
     * @param strDescription the description of the sensor type.
     * @return the sensor type from the list of sensor types.
     */
    public SensorType getSensorType(String strDescription) {
        Optional<SensorType> optSensorType = this._listSensorTypes.stream().filter(s -> s.getDescription().equals(strDescription)).findFirst();

        return optSensorType.orElse(null);
    }

    /**
     * Get the list of sensor types.
     *
     * @return the list of sensor types.
     */
    public List<SensorType> getSensorTypes() {
        return List.copyOf(this._listSensorTypes);
    }

    /**
     * Get the list of sensor models.
     *
     * @return the list of sensor models.
     */
    public List<String> getSensorModels() {
        return List.copyOf(this._listStringClassesSensors);
    }

    /**
     * Get the sensor from the list of sensor models.
     *
     * @param strModel      the model of the sensor.
     * @param sensorFactory the factory of the sensor.
     * @return the sensor from the list of sensor models.
     * @throws InstantiationException if the sensor could not be created.
     */
    public Sensor getSensor(String strModel, SensorFactory sensorFactory) throws InstantiationException {
        Optional<String> optSensorType = this.getSensorModels().stream().filter(s -> s.equals(strModel)).findFirst();

        if (optSensorType.isPresent()) {
            Sensor sensor = sensorFactory.createSensor(strModel, this);
            return sensor;
        } else
            return null;
    }
}
