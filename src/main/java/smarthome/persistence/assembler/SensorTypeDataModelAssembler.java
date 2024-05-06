package smarthome.persistence.assembler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.domain.sensor_type.ISensorTypeFactory;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.jpa.data_model.SensorTypeDataModel;
import smarthome.utils.Validator;

@Component
public class SensorTypeDataModelAssembler implements
    IDataModelAssembler<SensorTypeDataModel, SensorType> {

  private final ISensorTypeFactory sensorTypeFactory;

  /**
   * Constructor of SensorTypeDataModelConverter
   *
   * @param sensorTypeFactory the sensorTypeFactory to be used
   */
  public SensorTypeDataModelAssembler(ISensorTypeFactory sensorTypeFactory) {
    Validator.validateNotNull(sensorTypeFactory, "Sensor Type Factory");
    this.sensorTypeFactory = sensorTypeFactory;
  }

  @Override
  public SensorType toDomain(SensorTypeDataModel sensorTypeDataModel) {
    Validator.validateNotNull(sensorTypeDataModel, "Sensor Type Data Model");

    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeDataModel.getSensorTypeID());
    TypeDescription typeDescription = new TypeDescription(sensorTypeDataModel.getTypeDescription());
    UnitID unitID = new UnitID(sensorTypeDataModel.getUnitID());

    return sensorTypeFactory.createSensorType(sensorTypeID, typeDescription, unitID);
  }

  @Override
  public List<SensorType> toDomain(List<SensorTypeDataModel> sensorTypeDataModels) {
    if (sensorTypeDataModels == null || sensorTypeDataModels.isEmpty()) {
      throw new IllegalArgumentException("The list of sensor types cannot be null or empty.");
    }

    List<SensorType> sensorTypes = new ArrayList<>();

    for (SensorTypeDataModel sensorTypeDataModel : sensorTypeDataModels) {
      SensorType sensorType = toDomain(sensorTypeDataModel);
      sensorTypes.add(sensorType);
    }

    return sensorTypes;
  }
}
