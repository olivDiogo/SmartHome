package smarthome.persistence.spring_data.actuator;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.ActuatorDataModel;


public interface IActuatorSpringDataRepository extends JpaRepository<ActuatorDataModel, String> {

}
