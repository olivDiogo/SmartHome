package smarthome.persistence.spring_data.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.ActuatorDataModel;
import smarthome.persistence.jpa.data_model.SensorDataModel;
import java.util.List;

public interface ISensorSpringDataRepository extends JpaRepository<SensorDataModel, String> {
  List<SensorDataModel> findByDeviceID (String deviceID);

}
