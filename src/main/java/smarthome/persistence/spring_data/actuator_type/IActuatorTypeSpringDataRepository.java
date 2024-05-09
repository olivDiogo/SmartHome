package smarthome.persistence.spring_data.actuator_type;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.ActuatorTypeDataModel;


public interface IActuatorTypeSpringDataRepository extends
    JpaRepository<ActuatorTypeDataModel, String> {

}
