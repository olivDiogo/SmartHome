package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorTypeID;

import java.util.List;

public interface ISensorModelRepository extends IRepository<ModelPath, SensorModel> {

    List<SensorModel> findBySensorTypeId(SensorTypeID sensorTypeID);
}
