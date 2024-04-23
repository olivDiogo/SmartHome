package smart_home.persistence.assembler;

import java.util.ArrayList;
import java.util.List;
import smart_home.domain.actuator.IActuator;
import smart_home.domain.actuator.IActuatorFactory;
import smart_home.persistence.jpa.data_model.ActuatorDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.ActuatorID;
import smart_home.value_object.ActuatorName;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.DecimalLimits;
import smart_home.value_object.DeviceID;
import smart_home.value_object.IntegerLimits;
import smart_home.value_object.ModelPath;

public class ActuatorDataModelAssembler implements
    IDataModelAssembler<ActuatorDataModel, IActuator> {

  private final List<Object> _parameters = new ArrayList<>();
  private final IActuatorFactory _actuatorFactory;

  public ActuatorDataModelAssembler(IActuatorFactory actuatorFactory) {
    Validator.validateNotNull(actuatorFactory, "Actuator factory");
    this._actuatorFactory = actuatorFactory;
  }

  @Override
  public IActuator toDomain(ActuatorDataModel domainEntity) {
    Validator.validateNotNull(domainEntity, "Actuator data model");
    _parameters.clear();
    getDeviceID(domainEntity);
    getModelPath(domainEntity);
    getActuatorTypeID(domainEntity);
    getActuatorName(domainEntity);
    getIntegerLimits(domainEntity);
    getDecimalLimits(domainEntity);
    getActuatorID(domainEntity);

    return _actuatorFactory.createActuator(_parameters.toArray());
  }

  @Override
  public List<IActuator> toDomain(List<ActuatorDataModel> domainEntities) {
    return List.of();
  }

  private boolean getDeviceID(ActuatorDataModel actuatorDataModel) {
    DeviceID deviceID = new DeviceID(actuatorDataModel.getDeviceID());
    _parameters.add(deviceID);
    return true;
  }

  private boolean getModelPath(ActuatorDataModel actuatorDataModel) {
    ModelPath modelPath = new ModelPath(actuatorDataModel.getModelPath());
    _parameters.add(modelPath);
    return true;
  }

  private boolean getActuatorTypeID(ActuatorDataModel actuatorDataModel) {
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataModel.getActuatorTypeID());
    _parameters.add(actuatorTypeID);
    return true;
  }

  private boolean getActuatorName(ActuatorDataModel actuatorDataModel) {
    ActuatorName actuatorName = new ActuatorName(actuatorDataModel.getActuatorName());
    _parameters.add(actuatorName);
    return true;
  }

  private boolean getIntegerLimits(ActuatorDataModel actuatorDataModel) {
    if (actuatorDataModel.getIntegerLowerBond() != null
        || actuatorDataModel.getIntegerUpperBond() != null) {
      int integerLowerBond = Integer.parseInt(actuatorDataModel.getIntegerLowerBond());
      int integerUpperBond = Integer.parseInt(actuatorDataModel.getIntegerUpperBond());
      IntegerLimits integerLimits = new IntegerLimits(integerLowerBond, integerUpperBond);
      _parameters.add(integerLimits);
      return true;
    }
    return false;
  }

  private boolean getDecimalLimits(ActuatorDataModel actuatorDataModel) {
    if (actuatorDataModel.getDecimalLowerBond() != null
        || actuatorDataModel.getDecimalUpperBond() != null) {
      double decimalLowerBond = Double.parseDouble(actuatorDataModel.getDecimalLowerBond());
      double decimalUpperBond = Double.parseDouble(actuatorDataModel.getDecimalUpperBond());
      DecimalLimits decimalLimits = new DecimalLimits(decimalLowerBond, decimalUpperBond);
      _parameters.add(decimalLimits);
      return true;
    }
    return false;
  }

  private boolean getActuatorID(ActuatorDataModel actuatorDataModel) {
    ActuatorID actuatorID = new ActuatorID(actuatorDataModel.getActuatorID());
    _parameters.add(actuatorID);
    return true;
  }
}
