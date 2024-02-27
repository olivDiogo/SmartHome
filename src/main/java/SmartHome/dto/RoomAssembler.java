package SmartHome.dto;

import SmartHome.domain.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomAssembler
{
    static public RoomDTO domain2DTO(Room room )
    {
        return new RoomDTO( room );
    }
    static public List<RoomDTO> domain2DTO(List<Room> rooms)
    {
        return rooms.stream().map(RoomDTO::new).toList();
    }

    static public Map<RoomDTO, Room> domain2DTOMap(List<Room> rooms) {
        Map<RoomDTO, Room> roomsDTOAndRooms = new HashMap<>();

        rooms.forEach(room -> {
            RoomDTO roomDTO = RoomAssembler.domain2DTO(room);
            roomsDTOAndRooms.put(roomDTO, room);
        });

        return roomsDTOAndRooms;
    }

}
