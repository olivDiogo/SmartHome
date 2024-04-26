package smarthome.domain.repository;

import java.util.List;
import smarthome.ddd.IRepository;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorTypeID;

public interface ISensorModelRepository extends IRepository<ModelPath, SensorModel> {

  List<SensorModel> findBySensorTypeId(SensorTypeID sensorTypeID);
}
