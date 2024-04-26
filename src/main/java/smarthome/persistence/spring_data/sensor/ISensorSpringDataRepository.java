package smarthome.persistence.spring_data.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.SensorDataModel;

public interface ISensorSpringDataRepository extends JpaRepository<SensorDataModel, String> {

}
