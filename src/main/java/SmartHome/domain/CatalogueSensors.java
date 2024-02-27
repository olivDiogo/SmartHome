package SmartHome.domain;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CatalogueSensors
{
    private List<SensorType> _listSensorTypes;
    private List<String> _listStringClassesSensors;

   /* public CatalogueSensors(Configuration config )
    {
        // access configuration properties
        String [] arrayStringClassesSensors = config.getStringArray("sensor");
        this._listStringClassesSensors = List.of(arrayStringClassesSensors);
    }*/

    public CatalogueSensors(String filePathname ) throws InstantiationException
    {
        Configurations configs = new Configurations();
        try
        {
            Configuration config = configs.properties(new File(filePathname)); // e.g. filePathname = "config.properties"

            // access configuration properties
            String [] arrayStringClassesSensors = config.getStringArray("sensor");
            _listSensorTypes = new ArrayList<>();
            this._listStringClassesSensors = List.of(arrayStringClassesSensors);
        }
        catch (ConfigurationException exception)
        {
            // Something went wrong
            throw new InstantiationException("something went wrong in reading the configuration: " + exception.getMessage());
        }
    }

    //Validate that the sensor type is not already in the list
    public SensorType addSensorType(String strDescription, Unit unit, SensorTypeFactory sensorTypeFactory) throws InstantiationException
    {
        SensorType _sensorType = sensorTypeFactory.createSensorType(strDescription, unit);

        addSensorTypeToList(_sensorType);

        return _sensorType;
    }

    protected SensorType addSensorTypeToList(SensorType sensorType)
    {
        this._listSensorTypes.add(sensorType);
        return sensorType;
    }

    public SensorType getSensorType( String strDescription )
    {
        Optional<SensorType> optSensorType = this._listSensorTypes.stream().filter(s -> s.getDescription().equals(strDescription) ).findFirst();

        return optSensorType.orElse(null);
    }

    public List<SensorType> getSensorTypes()
    {
        return List.copyOf(this._listSensorTypes);
    }

    public List<String> getSensorModels()
    {
        return List.copyOf(this._listStringClassesSensors);
    }

    public Sensor getSensor(String strModel, SensorFactory sensorFactory) throws InstantiationException {
        Optional<String> optSensorType = this.getSensorModels().stream().filter(s -> s.equals(strModel)).findFirst();

        if(optSensorType.isPresent())
        {
                Sensor sensor = sensorFactory.createSensor(strModel, this);
                return sensor;
        }
        else
            return null;
    }
}
