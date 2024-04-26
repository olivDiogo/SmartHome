package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.RoomID;

public interface IRoomRepository extends IRepository<RoomID, Room> {

  Room update(Room room);
}
