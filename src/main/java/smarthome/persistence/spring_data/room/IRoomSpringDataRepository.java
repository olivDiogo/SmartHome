package smarthome.persistence.spring_data.room;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.data_model.RoomDataModel;

public interface IRoomSpringDataRepository extends JpaRepository<RoomDataModel, String>{
}
