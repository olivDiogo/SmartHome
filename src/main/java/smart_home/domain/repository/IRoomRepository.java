package smart_home.domain.repository;

import smart_home.ddd.IRepository;
import smart_home.domain.room.Room;
import smart_home.value_object.RoomID;

public interface IRoomRepository extends IRepository<RoomID, Room> {
    Room update (Room room);
}
