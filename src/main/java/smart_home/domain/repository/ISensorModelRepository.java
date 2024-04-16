package smart_home.domain.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorTypeID;

import java.util.List;

public interface ISensorModelRepository extends IRepository<ModelPath, SensorModel> {

    List<SensorModel> findBySensorTypeId(SensorTypeID sensorTypeID);
}
