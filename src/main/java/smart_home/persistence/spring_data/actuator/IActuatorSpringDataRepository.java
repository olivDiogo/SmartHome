package smart_home.persistence.spring_data.actuator;

import org.springframework.data.jpa.repository.JpaRepository;
import smart_home.persistence.jpa.data_model.ActuatorDataModel;

public interface IActuatorSpringDataRepository extends JpaRepository<ActuatorDataModel, String> {
}
