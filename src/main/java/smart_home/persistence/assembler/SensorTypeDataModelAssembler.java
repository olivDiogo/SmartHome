package smart_home.persistence.assembler;

import smart_home.domain.sensor_type.ISensorTypeFactory;
import smart_home.domain.sensor_type.SensorType;
import smart_home.persistence.jpa.data_model.SensorTypeDataModel;
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
        validateSensorTypeFactory (sensorTypeFactory);
        this._sensorTypeFactory = sensorTypeFactory;
    }

    /**
     * Validate the sensorTypeFactory
     * @param sensorTypeFactory the sensorTypeFactory to be validated
     */
    private void validateSensorTypeFactory (ISensorTypeFactory sensorTypeFactory) {
        if (sensorTypeFactory == null)
            throw new IllegalArgumentException("Sensor Type Factory cannot be null");
    }
    @Override
    public SensorType toDomain(SensorTypeDataModel sensorTypeDataModel) {
        if (sensorTypeDataModel == null)
            throw new IllegalArgumentException("Sensor Type Data Model cannot be null");

        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeDataModel.getSensorTypeID());
        TypeDescription typeDescription = new TypeDescription(sensorTypeDataModel.getTypeDescription());
        UnitID unitID = new UnitID(sensorTypeDataModel.getUnitID());

        return _sensorTypeFactory.createSensorType(sensorTypeID, typeDescription, unitID);
    }

    @Override
    public List<SensorType> toDomain(List<SensorTypeDataModel> sensorTypeDataModels) {
        if (sensorTypeDataModels == null || sensorTypeDataModels.isEmpty())
            throw new IllegalArgumentException("The list of sensor types cannot be null or empty.");

        List <SensorType> sensorTypes = new ArrayList<>();

        for (SensorTypeDataModel sensorTypeDataModel : sensorTypeDataModels){
            SensorType sensorType = toDomain(sensorTypeDataModel);
            sensorTypes.add(sensorType);
        }

        return sensorTypes;
    }
}
