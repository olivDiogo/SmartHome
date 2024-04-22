package smart_home.persistence.spring_data.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import smart_home.persistence.jpa.data_model.SensorDataModel;

public interface ISensorSpringDataRepository extends JpaRepository<SensorDataModel, String>{
}
