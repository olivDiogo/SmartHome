package SmartHome.domain;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogueActuator {
    private List<ActuatorType> _listActuatorTypes;
    private List<String> _listStringClassesActuator;

    /**
     * Constructor for the CatalogueActuator class.
     *
     * @param filePathname the file pathname
     * @throws InstantiationException if the filePathname is null
     */
    public CatalogueActuator(String filePathname) throws InstantiationException {
        Configurations configs = new Configurations();
        if (!validateConstructorParameters(filePathname)) {
            throw new InstantiationException("Invalid parameters");
        } else {
            try {
                Configuration config = configs.properties(new File(filePathname)); // e.g. filePathname = "config.properties"

                // access configuration properties
                String[] arrayStringClassesSensors = config.getStringArray("actuator");
                _listActuatorTypes = new ArrayList<>();
                this._listStringClassesActuator = List.of(arrayStringClassesSensors);
            } catch (ConfigurationException exception) {
                // Something went wrong
                throw new InstantiationException("something went wrong in reading the configuration: " + exception.getMessage());
            }
        }
    }

    /**
     * Validates the parameters of the constructor.
     * @param strDescription
     * @return
     */
    private boolean validateConstructorParameters(String strDescription) {
        return strDescription != null && !strDescription.trim().isEmpty();
    }

    /**
     * Instantiates an actuator type to the list of actuator types.
     *
     * @param strDescription the description of the actuator type
     * @param actuatorTypeFactory the actuator type factory
     * @return the created actuator type
     * @throws InstantiationException if the actuator type cannot be created
     */
    public ActuatorType addActuatorType(String strDescription, ActuatorTypeFactory actuatorTypeFactory) throws InstantiationException {
        ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(strDescription);

        addActuatorTypeToList(actuatorType);

        return actuatorType;
    }

    /**
     * Adds an actuator type to the list of actuator types.
     *
     * @param actuatorType the actuator type to be added
     * @return the actuator type added to the list
     */
    protected ActuatorType addActuatorTypeToList(ActuatorType actuatorType) {
        this._listActuatorTypes.add(actuatorType);
        return actuatorType;
    }

    /**
     * Gets the list of actuator types.
     * @return a copy of the list of actuator types
     */
    public List<ActuatorType> getActuatorTypes() {
        return List.copyOf(_listActuatorTypes);
    }

    /**
     * Gets the actuator type with the specified description.
     *
     * @param strDescription the description of the actuator type
     * @return the actuator type with the specified description
     */
    public ActuatorType getActuatorType(String strDescription) {

        Optional<ActuatorType> optActuatorType = this._listActuatorTypes.stream().filter(s -> s.getDescription().equals(strDescription)).findFirst();

        return optActuatorType.orElse(null);
    }

    /**
     * Gets the list of actuator models.
     * @return a copy of the list of actuator models
     */
    public List<String> getActuatorModels(){
        return List.copyOf(_listStringClassesActuator);
    }

    /**
     * Instantiates an actuator if the actuator model required exists.
     *
     * @param strModel the model of the actuator
     * @param actuatorFactory the actuator factory that instantiates the actuator
     * @return the actuator instantiated
     * @throws InstantiationException if the actuator cannot be created
     */
    public Actuator getActuator(String strModel, ActuatorFactory actuatorFactory) throws InstantiationException {
        Optional<String> optActuatorType = this.getActuatorModels().stream().filter(s -> s.equals(strModel)).findFirst();

        if(optActuatorType.isPresent())
        {
            Actuator actuator = actuatorFactory.createActuator(strModel, this);
            return actuator;
        }
        else
            return null;
    }

}
