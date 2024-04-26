package smarthome.persistence.spring_data.actuator_model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.ActuatorModelDataModel;

public interface IRepositoryActuatorModelSpringData extends
    JpaRepository<ActuatorModelDataModel, String> {


  List<ActuatorModelDataModel> findBy_actuatorTypeID(String id);
}
