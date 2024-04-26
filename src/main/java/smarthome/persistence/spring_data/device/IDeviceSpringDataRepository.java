package smarthome.persistence.spring_data.device;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.DeviceDataModel;


import java.util.List;

public interface IDeviceSpringDataRepository extends JpaRepository<DeviceDataModel, String>{

    List<DeviceDataModel> findBy_roomID(String id);
}
