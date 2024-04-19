package smart_home.persistence.spring_data.room;

import org.springframework.data.jpa.repository.JpaRepository;
import smart_home.persistence.jpa.data_model.RoomDataModel;

public interface IRoomSpringDataRepository extends JpaRepository<RoomDataModel, String>{
}
