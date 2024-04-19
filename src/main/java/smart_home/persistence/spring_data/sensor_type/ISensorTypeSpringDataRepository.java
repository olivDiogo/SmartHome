package smart_home.persistence.spring_data.sensor_type;

import org.springframework.data.jpa.repository.JpaRepository;
import smart_home.persistence.jpa.data_model.SensorTypeDataModel;

public interface ISensorTypeSpringDataRepository extends JpaRepository<SensorTypeDataModel, String>{
}
