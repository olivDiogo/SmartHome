package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.DeviceID;
import java.util.List;

public interface IActuatorRepository extends IRepository<ActuatorID, IActuator> {

  /**
   * Method to find all actuators of a device.
   * @param deviceID is the unique identifier of the device.
   * @return a list of actuators of the device.
   */
  List<IActuator> ofDeviceID(DeviceID deviceID);
}
