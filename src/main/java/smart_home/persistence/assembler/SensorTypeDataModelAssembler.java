package smart_home.persistence.assembler;

import smart_home.domain.sensor_type.ISensorTypeFactory;
import smart_home.domain.sensor_type.SensorType;
import smart_home.persistence.jpa.data_model.SensorTypeDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.SensorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import java.util.ArrayList;
import java.util.List;

public class SensorTypeDataModelAssembler implements IDataModelAssembler<SensorTypeDataModel, SensorType> {
    private ISensorTypeFactory _sensorTypeFactory;

    /**
     * Constructor of SensorTypeDataModelConverter
     * @param sensorTypeFactory the sensorTypeFactory to be used
     */
    public SensorTypeDataModelAssembler(ISensorTypeFactory sensorTypeFactory) {
        Validator.validateNotNull(sensorTypeFactory, "Sensor Type Factory");
        this._sensorTypeFactory = sensorTypeFactory;
    }

    @Override
    public SensorType toDomain(SensorTypeDataModel sensorTypeDataModel) {
        Validator.validateNotNull(sensorTypeDataModel, "Sensor Type Data Model");

        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeDataModel.getSensorTypeID());
        TypeDescription typeDescription = new TypeDescription(sensorTypeDataModel.getTypeDescription());
        UnitID unitID = new UnitID(sensorTypeDataModel.getUnitID());

        return _sensorTypeFactory.createSensorType(sensorTypeID, typeDescription, unitID);
    }

    @Override
    public List<SensorType> toDomain(List<SensorTypeDataModel> sensorTypeDataModels) {
        if (sensorTypeDataModels == null || sensorTypeDataModels.isEmpty()){
            throw new IllegalArgumentException("The list of sensor types cannot be null or empty.");
        }

        List <SensorType> sensorTypes = new ArrayList<>();

        for (SensorTypeDataModel sensorTypeDataModel : sensorTypeDataModels){
            SensorType sensorType = toDomain(sensorTypeDataModel);
            sensorTypes.add(sensorType);
        }

        return sensorTypes;
    }
}
