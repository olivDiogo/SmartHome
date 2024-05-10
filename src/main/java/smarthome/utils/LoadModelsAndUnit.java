package smarthome.utils;

import java.io.File;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_model.IActuatorModelFactory;
import smarthome.domain.repository.IActuatorModelRepository;
import smarthome.domain.repository.ISensorModelRepository;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.sensor_model.ISensorModelFactory;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitSymbol;

@Component
public class LoadModelsAndUnit {

  private final IUnitRepository unitRepository;
  private final IUnitFactory unitFactory;

  @Autowired
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

  private void loadDefaults(String type, Object factory, Object repository)
      throws InstantiationException {
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
          SensorModel model = ((ISensorModelFactory) factory).createSensorModel(
              new SensorModelName(modelName), path, new SensorTypeID(typeIdStr));
          ((ISensorModelRepository) repository).save(model);
        } else if (type.equals("actuator")) {
          ActuatorModel model = ((IActuatorModelFactory) factory).createActuatorModel(
              path, new ActuatorModelName(modelName), new ActuatorTypeID(typeIdStr));
          ((IActuatorModelRepository) repository).save(model);
        }
      }
    } catch (ConfigurationException e) {
      throw new InstantiationException(
          "something went wrong in reading the configuration: " + e.getMessage());
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

//  @Override
//  public void run(String... args) throws Exception {
//    Configurations configs = new Configurations();
//    try {
//      Configuration config = configs.properties(new File("configDDD.properties"));
//      String[] arrayStringMeasurementTypes = config.getStringArray("measurement");
//      for (String measurement : arrayStringMeasurementTypes) {
//        String[] measurementTypes = measurement.split(";");
//        String measurementTypeDescription = measurementTypes[0].split(":")[1];
//        String measurementTypeUnit = measurementTypes[1].split(":")[1];
//
//        UnitDescription measurementDescription = new UnitDescription(measurementTypeDescription);
//        UnitSymbol measurementTUnit = new UnitSymbol(measurementTypeUnit);
//        Unit measurementType = unitFactory.createUnit(measurementDescription, measurementTUnit);
//
//        // Check if the measurement type already exists
//        Optional<Unit> existing = unitRepository.ofIdentity(measurementType.getID());
//        if (existing.isEmpty()) {
//          unitRepository.save(measurementType);
//        } else {
//          System.out.println("Measurement type already exists: " + measurementDescription);
//        }
//      }
//    } catch (ConfigurationException e) {
//      throw new InstantiationException("Error in configuration: " + e.getMessage());
//    }
//  }
}
