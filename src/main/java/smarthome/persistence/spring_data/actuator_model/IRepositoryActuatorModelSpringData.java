package smarthome.persistence.spring_data.actuator_model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smarthome.persistence.jpa.data_model.ActuatorModelDataModel;

//@Repository
public interface IRepositoryActuatorModelSpringData extends
    JpaRepository<ActuatorModelDataModel, String> {


  List<ActuatorModelDataModel> findByActuatorTypeID(String id);
}
