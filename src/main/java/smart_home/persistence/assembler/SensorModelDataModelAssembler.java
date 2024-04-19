package smart_home.persistence.assembler;

import smart_home.domain.sensor_model.ISensorModelFactory;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.persistence.jpa.data_model.SensorModelDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorModelName;
import smart_home.value_object.SensorTypeID;

import java.util.ArrayList;
import java.util.List;

public class SensorModelDataModelAssembler implements IDataModelAssembler<SensorModelDataModel, SensorModel> {

    private ISensorModelFactory _sensorModelFactory;

    /**
     * SensorModelDataModelConverter constructor to initialize the sensorModelFactory
     *
     * @param sensorModelFactory ISensorModelFactory
     */
    public SensorModelDataModelAssembler(ISensorModelFactory sensorModelFactory) {
        Validator.validateNotNull(sensorModelFactory, "Sensor Model Factory");
        this._sensorModelFactory = sensorModelFactory;
    }

    /**
     * Method to convert SensorModel to SensorModelDataModel
     *
     * @param sensorModelDataModel SensorModelDataModel object
     * @return SensorModelDataModel object
     */
    @Override
    public SensorModel toDomain(SensorModelDataModel sensorModelDataModel) {
        Validator.validateNotNull(sensorModelDataModel, "Sensor Model Data Model");

        ModelPath modelPath = new ModelPath(sensorModelDataModel.getModelPath());
        SensorModelName sensorModelName = new SensorModelName(sensorModelDataModel.getSensorModelName());
        SensorTypeID sensorTypeID = new SensorTypeID(sensorModelDataModel.getSensorTypeID());

        SensorModel sensorModel = _sensorModelFactory.createSensorModel(sensorModelName, modelPath, sensorTypeID);

        return sensorModel;
    }

    /**
     * Method to convert SensorModelDataModel to SensorModel
     *
     * @param sensorModelDataModels List of SensorModelDataModel objects
     * @return SensorModelDataModel object
     */
    @Override
    public List<SensorModel> toDomain(List<SensorModelDataModel> sensorModelDataModels) {
        if (sensorModelDataModels == null || sensorModelDataModels.isEmpty())
            throw new IllegalArgumentException("The list of sensor models cannot be null or empty.");

        List<SensorModel> sensorModels = new ArrayList<>();

        for (SensorModelDataModel sensorModelDataModel : sensorModelDataModels) {
            SensorModel sensorModel = toDomain(sensorModelDataModel);
            sensorModels.add(sensorModel);
        }

        return sensorModels;
    }
}
