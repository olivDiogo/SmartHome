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
     * Gets the list of actuator types.
     * @return a copy of the list of actuator types
     */
    public List<ActuatorType> getActuatorTypes() {
        return List.copyOf(_listActuatorTypes);
    }

    /**
     * Gets the list of actuator models.
     * @return a copy of the list of actuator models
     */
    public List<String> getActuatorModels(){
        return List.copyOf(_listStringClassesActuator);
    }


}
