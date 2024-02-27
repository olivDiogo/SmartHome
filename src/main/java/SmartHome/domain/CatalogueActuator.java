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

    private boolean validateConstructorParameters(String strDescription) {
        return strDescription != null && !strDescription.trim().isEmpty();
    }

    public List<ActuatorType> getActuatorTypes() {
        return _listActuatorTypes;
    }




}
