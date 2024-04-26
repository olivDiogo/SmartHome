package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.SensorID;

public interface ISensorRepository extends IRepository<SensorID, ISensor> {

}
