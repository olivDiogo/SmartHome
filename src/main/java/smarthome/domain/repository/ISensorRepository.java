package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorID;
import java.util.List;

public interface ISensorRepository extends IRepository<SensorID, ISensor> {
  List<ISensor> ofDeviceID(DeviceID deviceID);
}
