package smarthome.persistence.spring_data.sensor_model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.SensorModelDataModel;

public interface ISensorModelSpringDataRepository extends
    JpaRepository<SensorModelDataModel, String> {

  List<SensorModelDataModel> findBy_sensorTypeID(String sensorTypeID);
}
