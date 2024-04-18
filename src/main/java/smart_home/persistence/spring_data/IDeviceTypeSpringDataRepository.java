package smart_home.persistence.spring_data;

import org.springframework.data.jpa.repository.JpaRepository;
import smart_home.persistence.jpa.data_model.DeviceTypeDataModel;

public interface IDeviceTypeSpringDataRepository extends JpaRepository<DeviceTypeDataModel, String>{
}