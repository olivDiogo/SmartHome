package smarthome.persistence.spring_data.house;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.HouseDataModel;

public interface IHouseSpringDataRepository extends JpaRepository<HouseDataModel, String> {

}
