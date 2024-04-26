package smarthome.persistence.jpa.data_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.utils.Validator;

@Entity
@Table(name = "sensor_model")
public class SensorModelDataModel {

    @Id
    @Column (name = "model_path")
    private String _modelPath;
    @Column (name = "sensor_model_name")
    private String _sensorModelName;
    @Column (name = "sensor_type_id")
    private String _sensorTypeID;
    @Version
    private long version;


    /**
     * SensorModelDataModel constructor
     */
    public SensorModelDataModel() {
    }

    /**
     * SensorModelDataModel constructor
     * @param sensorModel SensorModel object
     */
    public SensorModelDataModel(SensorModel sensorModel) {

        Validator.validateNotNull(sensorModel, "Sensor Model");
        this._modelPath = sensorModel.getModelPath().getID();
        this._sensorModelName = sensorModel.getSensorModelName().getSensorModelName();
        this._sensorTypeID = sensorModel.getSensorTypeID().getID();
    }


    /**
     * Method to get model path
     * @return String
     */
    public String getModelPath() {
        return this._modelPath;
    }

    /**
     * Method to get sensor model name
     * @return String
     */
    public String getSensorModelName() {
        return this._sensorModelName;
    }

    /**
     * Method to get sensor type ID
     * @return String
     */
    public String getSensorTypeID() {
        return this._sensorTypeID;
    }


}

