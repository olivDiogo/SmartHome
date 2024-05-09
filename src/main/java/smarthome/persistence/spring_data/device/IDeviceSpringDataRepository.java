package smarthome.persistence.spring_data.device;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.DeviceDataModel;

public interface IDeviceSpringDataRepository extends JpaRepository<DeviceDataModel, String> {

  List<DeviceDataModel> findByRoomID(String id);

  List<DeviceDataModel> findByDeviceTypeID(String string);

}
