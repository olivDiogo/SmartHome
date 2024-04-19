package smart_home.persistence.spring_data;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import smart_home.persistence.jpa.data_model.ActuatorModelDataModel;

public interface IRepositoryActuatorModelSpringData extends JpaRepository<ActuatorModelDataModel, String>{


    List<ActuatorModelDataModel> findBy_actuatorTypeID(String id);
}
