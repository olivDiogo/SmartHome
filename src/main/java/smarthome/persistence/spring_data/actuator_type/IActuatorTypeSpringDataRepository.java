package smarthome.persistence.spring_data.actuator_type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smarthome.persistence.jpa.data_model.ActuatorTypeDataModel;

@Repository
public interface IActuatorTypeSpringDataRepository extends
    JpaRepository<ActuatorTypeDataModel, String> {

}
