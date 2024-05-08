package smarthome.persistence.spring_data.device;

import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smarthome.domain.device.Device;
import smarthome.persistence.jpa.data_model.DeviceDataModel;
@Repository
public interface IDeviceSpringDataRepository extends JpaRepository<DeviceDataModel, String> {

  List<DeviceDataModel> findByRoomID(String id);

  List<DeviceDataModel> findByDeviceTypeID(String string);

}
