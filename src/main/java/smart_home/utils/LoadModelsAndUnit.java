package smart_home.utils;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import smart_home.domain.repository.IActuatorModelRepository;
import smart_home.domain.repository.ISensorModelRepository;
import smart_home.domain.repository.IUnitRepository;
import smart_home.domain.sensor_model.ISensorModelFactory;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.domain.actuator_model.IActuatorModelFactory;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.domain.unit.IUnitFactory;
import smart_home.domain.unit.Unit;
import smart_home.persistence.mem.SensorModelRepository;
import smart_home.persistence.mem.ActuatorModelRepository;
import smart_home.persistence.mem.UnitRepository;
import smart_home.value_object.*;

import java.io.File;

public class LoadModelsAndUnit {

    private IUnitRepository unitRepository;
    private IUnitFactory unitFactory;

    public LoadModelsAndUnit(
            ISensorModelRepository sensorModelRepository,
            IActuatorModelRepository actuatorModelRepository,
            IUnitRepository unitRepository,
            ISensorModelFactory sensorModelFactory,
            IActuatorModelFactory actuatorModelFactory,
            IUnitFactory unitFactory) throws InstantiationException {
        validateRepository(sensorModelRepository, "sensor model");
        validateRepository(actuatorModelRepository, "actuator model");
        validateRepository(unitRepository, "unit");
        validateFactory(sensorModelFactory, "sensor model");
        validateFactory(actuatorModelFactory, "actuator model");
        validateFactory(unitFactory, "unit");
        this.unitRepository = unitRepository;
        this.unitFactory = unitFactory;
        loadDefaults("sensor", sensorModelFactory, sensorModelRepository);
        loadDefaults("actuator", actuatorModelFactory, actuatorModelRepository);
        loadDefaultMeasurementTypes();
    }

    private void validateRepository(Object repository, String type) {
        if (repository == null) {
            throw new IllegalArgumentException("Please enter a valid " + type + " repository.");
        }
    }

    private void validateFactory(Object factory, String type) {
        if (factory == null) {
            throw new IllegalArgumentException("Please enter a valid " + type + " factory.");
        }
    }

    private void loadDefaults(String type, Object factory, Object repository) throws InstantiationException {
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties(new File("configDDD.properties"));
            String[] deviceData = config.getStringArray(type);

            for (String deviceInfo : deviceData) {
                String pathStr = deviceInfo.split(";")[0];
                String modelName = pathStr.substring(pathStr.lastIndexOf('.') + 1);
                String typeIdStr = deviceInfo.split(";")[1];

                ModelPath path = new ModelPath(pathStr);
                if (type.equals("sensor")) {
                    SensorModel model = ((ISensorModelFactory) factory).createSensorModel(new SensorModelName(modelName), path, new SensorTypeID(typeIdStr));
                    ((SensorModelRepository) repository).save(model);
                } else if (type.equals("actuator")) {
                    ActuatorModel model = ((IActuatorModelFactory) factory).createActuatorModel(new ActuatorModelName(modelName), path, new ActuatorTypeID(typeIdStr));
                    ((ActuatorModelRepository) repository).save(model);
                }
            }
        } catch (ConfigurationException e) {
            throw new InstantiationException("something went wrong in reading the configuration: " + e.getMessage());
        }
    }

    private void loadDefaultMeasurementTypes() throws InstantiationException {
        Configurations configs = new Configurations();
        try {
            Configuration config =
                    configs.properties(
                            new File("configDDD.properties")); // e.g. filePathname = "config.properties"

            // access configuration properties
            String[] arrayStringMeasurementTypes = config.getStringArray("measurement");
            for (String measurement : arrayStringMeasurementTypes) {
                String[] measurementTypes = measurement.split(";");

                String measurementTypeDescription = measurementTypes[0].split(":")[1];
                UnitDescription measurementDescription = new UnitDescription(measurementTypeDescription);

                String measurementTypeUnit = measurementTypes[1].split(":")[1];
                UnitSymbol measurementTUnit = new UnitSymbol(measurementTypeUnit);

                Unit measurementType =
                        unitFactory.createUnit(measurementDescription, measurementTUnit);
                unitRepository.save(measurementType);
            }
        } catch (ConfigurationException exception) {
            // Something went wrong
            throw new InstantiationException(
                    "something went wrong in reading the configuration: " + exception.getMessage());
        }
    }
}
