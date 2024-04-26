package smarthome.persistence.spring_data.unit;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.UnitDataModel;

public interface IUnitSpringDataRepository extends JpaRepository<UnitDataModel, String>{
}
