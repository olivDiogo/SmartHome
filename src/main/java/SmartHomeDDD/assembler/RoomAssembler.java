package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.ddd.Assembler;
import SmartHomeDDD.domain.Room.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomAssembler implements Assembler <Room, RoomDTO> {

    /**
     * Constructor for the RoomAssembler class.
     */

    public RoomAssembler() {
    }

    /**
     * Method to convert a Room into a RoomDTO.
     *
     * @param room is the Room to be converted.
     * @return the RoomDTO.
     */
    public RoomDTO domainToDTO(Room room) {

        String roomName = room.getRoomName().toString();
        String dimension = room.getDimension().toString();
        String roomFloor = room.getRoomFloor().toString();
        String roomID = room.getID().toString();
        RoomDTO roomDTO = new RoomDTO(roomName, dimension, roomFloor, roomID);

        return roomDTO;

    }

    /**
     * Method to convert a list of Rooms into a list of RoomDTOs.
     *
     * @param rooms is the list of Rooms to be converted.
     * @return the list of RoomDTOs.
     */
    public List<RoomDTO> domainToDTO(List<Room> rooms) {

        List<RoomDTO> roomsDTO = new ArrayList<>();

        for (Room room : rooms) {
            String roomName = room.getRoomName().toString();
            String dimension = room.getDimension().toString();
            String roomFloor = room.getRoomFloor().toString();
            String roomID = room.getID().toString();
            RoomDTO roomDTO = new RoomDTO(roomName, dimension, roomFloor, roomID);
            roomsDTO.add(roomDTO);
        }
        return roomsDTO;
    }

}
