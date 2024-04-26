package smarthome.persistence.spring_data.device_type;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.DeviceTypeDataModel;

public interface IDeviceTypeSpringDataRepository extends
    JpaRepository<DeviceTypeDataModel, String> {

}