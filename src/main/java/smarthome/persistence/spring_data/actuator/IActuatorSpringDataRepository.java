package smarthome.persistence.spring_data.actuator;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.domain.actuator.IActuator;
import smarthome.persistence.jpa.data_model.ActuatorDataModel;
import java.util.List;
import java.util.Optional;


public interface IActuatorSpringDataRepository extends JpaRepository<ActuatorDataModel, String> {

  List<ActuatorDataModel> findByDeviceID (String deviceID);
}
