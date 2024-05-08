package smarthome.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.ddd.IAssembler;
import smarthome.domain.exceptions.EmptyReturnException;
import smarthome.domain.room.Room;
import smarthome.utils.Validator;
import smarthome.utils.dto.RoomDTO;

@Component
public class RoomAssembler implements IAssembler<Room, RoomDTO> {


  /**
   * Method to convert a Room into a RoomDTO.
   *
   * @param room is the Room to be converted.
   * @return the RoomDTO.
   */
  @Override
  public RoomDTO domainToDTO(Room room) {

    Validator.validateNotNull(room, "Room");

    String roomName = room.getName().toString();
    String dimension = room.getDimension().toString();
    String roomFloor = room.getFloor().toString();
    String roomID = room.getID().toString();

    return new RoomDTO(roomName, dimension, roomFloor, roomID);
  }

  /**
   * Method to convert a list of Rooms into a list of RoomDTOs.
   *
   * @param rooms is the list of Rooms to be converted.
   * @return the list of RoomDTOs.
   */
  @Override
  public List<RoomDTO> domainToDTO(List<Room> rooms) throws EmptyReturnException {
    if (rooms == null) {
      throw new IllegalArgumentException("The list of Rooms cannot be null.");
    }
    if (rooms.isEmpty()) {
      throw new EmptyReturnException("The list of Rooms is empty.");
    }

    return rooms.stream().map(this::domainToDTO).toList();
  }

}
