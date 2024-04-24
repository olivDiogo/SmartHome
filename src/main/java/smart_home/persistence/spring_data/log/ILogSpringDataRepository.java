package smart_home.persistence.spring_data.log;

import org.springframework.data.jpa.repository.JpaRepository;
import smart_home.persistence.jpa.data_model.LogDataModel;
import smart_home.value_object.DeviceID;
import smart_home.value_object.SensorTypeID;
import java.time.LocalDateTime;
import java.util.List;

public interface ILogSpringDataRepository extends JpaRepository<LogDataModel, String> {
  List<LogDataModel> findByDeviceIDAndTimestampBetween(
      DeviceID deviceID, LocalDateTime start, LocalDateTime end);

  List<LogDataModel> findByDeviceIDAndSensorTypeIDAndTimestampBetween(
      DeviceID deviceID, SensorTypeID sensorTypeID, LocalDateTime start, LocalDateTime end);
}
