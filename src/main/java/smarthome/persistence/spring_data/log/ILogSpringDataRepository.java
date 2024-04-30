package smarthome.persistence.spring_data.log;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.jpa.data_model.LogDataModel;

public interface ILogSpringDataRepository extends JpaRepository<LogDataModel, String> {

  List<LogDataModel> findByDeviceIDAndTimestampBetween(
      DeviceID deviceID, LocalDateTime start, LocalDateTime end);

  List<LogDataModel> findByDeviceIDAndSensorIDAndTimestampBetween(
      DeviceID deviceID, SensorTypeID sensorTypeID, LocalDateTime start, LocalDateTime end);
}
