package smarthome.persistence.assembler;

import smarthome.domain.sensor_model.ISensorModelFactory;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.persistence.jpa.data_model.SensorModelDataModel;
import smarthome.utils.Validator;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;

import java.util.ArrayList;
import java.util.List;

public class SensorModelDataModelAssembler implements IDataModelAssembler<SensorModelDataModel, SensorModel> {

    private ISensorModelFactory sensorModelFactory;

    /**
     * SensorModelDataModelConverter constructor to initialize the sensorModelFactory
     *
     * @param sensorModelFactory ISensorModelFactory
     */
    public SensorModelDataModelAssembler(ISensorModelFactory sensorModelFactory) {
        Validator.validateNotNull(sensorModelFactory, "Sensor Model Factory");
        this.sensorModelFactory = sensorModelFactory;
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

        SensorModel sensorModel = sensorModelFactory.createSensorModel(sensorModelName, modelPath, sensorTypeID);

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
        if (sensorModelDataModels == null || sensorModelDataModels.isEmpty()){
            throw new IllegalArgumentException("The list of sensor models cannot be null or empty.");
        }

        List<SensorModel> sensorModels = new ArrayList<>();

        for (SensorModelDataModel sensorModelDataModel : sensorModelDataModels) {
            SensorModel sensorModel = toDomain(sensorModelDataModel);
            sensorModels.add(sensorModel);
        }

        return sensorModels;
    }
}
