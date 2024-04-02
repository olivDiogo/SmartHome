package SmartHome.dto;

import SmartHome.domain.Room;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RoomAssembler {

    /**
     * Converts a Room domain object to a RoomDTO.
     *
     * @param room The Room domain object to be converted.
     * @return A RoomDTO object that represents the given Room.
     */
    static public RoomDTO domain2DTO(Room room) {
        return new RoomDTO(room);
    }

    /**
     * Converts a list of Room domain objects to a list of RoomDTOs.
     *
     * @param rooms The list of Room domain objects to be converted.
     * @return A list of RoomDTO objects that represent the given Rooms.
     */
    static public List<RoomDTO> domain2DTO(List<Room> rooms) {
        return rooms.stream().map(RoomDTO::new).toList();
    }

    /**
     * Converts a list of Room domain objects to a map of RoomDTOs and Rooms.
     *
     * @param rooms The list of Room domain objects to be converted.
     * @return A map where the keys are RoomDTO objects that represent the given Rooms, and the values are the original Room objects.
     */
    static public Map<RoomDTO, Room> domain2DTOMap(List<Room> rooms) {
        Map<RoomDTO, Room> roomsDTOAndRooms = new LinkedHashMap<>();

        rooms.forEach(room -> {
            RoomDTO roomDTO = RoomAssembler.domain2DTO(room);
            roomsDTOAndRooms.put(roomDTO, room);
        });

        return roomsDTOAndRooms;
    }

}
