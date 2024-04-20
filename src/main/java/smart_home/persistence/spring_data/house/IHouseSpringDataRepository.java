package smart_home.persistence.spring_data.house;

import org.springframework.data.jpa.repository.JpaRepository;
import smart_home.persistence.jpa.data_model.HouseDataModel;

public interface IHouseSpringDataRepository extends JpaRepository<HouseDataModel, String> {

}
