package smart_home.persistence.spring_data.sensor_model;

import org.springframework.data.jpa.repository.JpaRepository;
import smart_home.persistence.jpa.data_model.SensorModelDataModel;

import java.util.List;

public interface ISensorModelSpringDataRepository extends JpaRepository<SensorModelDataModel, String> {

    List<SensorModelDataModel> findBy_sensorTypeID(String sensorTypeID);
}
