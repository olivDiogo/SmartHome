package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.SensorTypeID;

public interface ISensorTypeRepository extends IRepository<SensorTypeID, SensorType> {

}
