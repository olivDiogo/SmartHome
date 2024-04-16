package smartHome.domain.repository;

import smartHome.ddd.IRepository;
import smartHome.domain.room.Room;
import smartHome.valueObject.RoomID;

public interface IRoomRepository extends IRepository<RoomID, Room> {
}
