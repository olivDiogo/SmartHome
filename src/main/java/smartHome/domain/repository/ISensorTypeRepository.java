package smartHome.domain.repository;

import smartHome.ddd.IRepository;
import smartHome.domain.sensorType.SensorType;
import smartHome.valueObject.SensorTypeID;

public interface ISensorTypeRepository extends IRepository<SensorTypeID, SensorType> {
}
