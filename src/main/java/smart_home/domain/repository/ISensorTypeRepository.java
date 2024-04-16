package smart_home.domain.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.sensor_type.SensorType;
import smart_home.value_object.SensorTypeID;

public interface ISensorTypeRepository extends IRepository<SensorTypeID, SensorType> {
}
