package smarthome.persistence.spring_data.log;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.jpa.data_model.LogDataModel;

public interface ILogSpringDataRepository extends JpaRepository<LogDataModel, String> {

  List<LogDataModel> findByDeviceIDAndTimestampBetween(
      String deviceID, LocalDateTime start, LocalDateTime end);

  List<LogDataModel> findByDeviceIDAndDescriptionAndTimestampBetween(
      String deviceID, String sensorTypeID, LocalDateTime start, LocalDateTime end);

  List<LogDataModel> findByDeviceIDAndDescription(String deviceID, String sensorTypeID);
}
