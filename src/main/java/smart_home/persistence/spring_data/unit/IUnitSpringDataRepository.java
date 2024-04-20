package smart_home.persistence.spring_data.unit;

import org.springframework.data.jpa.repository.JpaRepository;
import smart_home.persistence.jpa.data_model.UnitDataModel;

public interface IUnitSpringDataRepository extends JpaRepository<UnitDataModel, String>{
}
