package smarthome.persistence.spring_data.unit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smarthome.persistence.jpa.data_model.UnitDataModel;

@Repository
public interface IUnitSpringDataRepository extends JpaRepository<UnitDataModel, String> {

}
