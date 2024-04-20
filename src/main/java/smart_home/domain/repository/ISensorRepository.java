package smart_home.domain.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.sensor.ISensor;
import smart_home.value_object.SensorID;

public interface ISensorRepository extends IRepository<SensorID, ISensor> {
}
