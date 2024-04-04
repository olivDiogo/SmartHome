package SmartHomeDDD.service;

import SmartHomeDDD.domain.ActuatorModel.ActuatorModel;
import SmartHomeDDD.domain.ActuatorModel.ActuatorModelFactory;
import SmartHomeDDD.domain.Unit.Unit;
import SmartHomeDDD.domain.Unit.UnitFactory;
import SmartHomeDDD.repository.ActuatorModelRepository;
import SmartHomeDDD.repository.UnitRepository;
import SmartHomeDDD.valueObject.*;
import java.io.File;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ActuatorConfigurationService {

  private ActuatorModelRepository _ActuatorModelRepository;
  private UnitRepository _unitRepository;
  private ActuatorModelFactory _ActuatorModelFactory;
  private UnitFactory _unitFactory;

  public ActuatorConfigurationService(
      ActuatorModelRepository ActuatorModelRepository,
      UnitRepository unitRepository,
      ActuatorModelFactory ActuatorModelFactory,
      UnitFactory unitFactory)
      throws InstantiationException {
    validateActuatorModelRepository(ActuatorModelRepository);
    validateMeasurementTypeRepository(unitRepository);
    validateActuatorModelFactory(ActuatorModelFactory);
    validateUnitFactory(unitFactory);
    loadDefaultActuatorModels();
    loadDefaultMeasurementTypes();
  }

  private void validateActuatorModelRepository(ActuatorModelRepository ActuatorModelRepository) {
    if (ActuatorModelRepository == null) {
      throw new IllegalArgumentException("Please enter a valid Actuator model repository.");
    } else {
      this._ActuatorModelRepository = ActuatorModelRepository;
    }
  }

  private void validateMeasurementTypeRepository(UnitRepository unitRepository) {
    if (unitRepository == null) {
      throw new IllegalArgumentException("Please enter a valid measurement type repository.");
    } else {
      this._unitRepository = unitRepository;
    }
  }

  private void validateActuatorModelFactory(ActuatorModelFactory ActuatorModelFactory) {
    if (ActuatorModelFactory == null) {
      throw new IllegalArgumentException("Please enter a valid Actuator model factory.");
    } else {
      this._ActuatorModelFactory = ActuatorModelFactory;
    }
  }

  private void validateUnitFactory(UnitFactory unitFactory) {
    if (unitFactory == null) {
      throw new IllegalArgumentException("Please enter a valid unit factory.");
    } else {
      this._unitFactory = unitFactory;
    }
  }

  private void loadDefaultActuatorModels() throws InstantiationException {
    Configurations configs = new Configurations();
    try {
      Configuration config =
          configs.properties(
              new File("configDDD.properties")); // e.g. filePathname = "config.properties"

      // access configuration properties
      String[] arrayStringClassesActuators = config.getStringArray("actuator");
      for (String Actuator : arrayStringClassesActuators) {
        String ActuatorPathStr =
            Actuator.split(";")[0]; // String containing the path of the Actuator
        String ActuatorModelName =
            ActuatorPathStr.substring(
                ActuatorPathStr.lastIndexOf('.') + 1); // String containing the Actuator model name
        String ActuatorTypeIDstr = Actuator.split(";")[1]; // String containing the Actuator type ID

        ModelPath ActuatorPath = new ModelPath(ActuatorPathStr);
        ActuatorModelName ActuatorName = new ActuatorModelName(ActuatorModelName);
        ActuatorTypeID ActuatorTypeID = new ActuatorTypeID(ActuatorTypeIDstr);

        ActuatorModel ActuatorModel =
            _ActuatorModelFactory.createActuatorModel(ActuatorName, ActuatorPath, ActuatorTypeID);
        _ActuatorModelRepository.save(ActuatorModel);
      }
    } catch (ConfigurationException exception) {
      // Something went wrong
      throw new InstantiationException(
          "something went wrong in reading the configuration: " + exception.getMessage());
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
            _unitFactory.createMeasurement(measurementDescription, measurementTUnit);
        _unitRepository.save(measurementType);
      }
    } catch (ConfigurationException exception) {
      // Something went wrong
      throw new InstantiationException(
          "something went wrong in reading the configuration: " + exception.getMessage());
    }
  }
}
