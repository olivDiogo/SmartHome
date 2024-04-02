package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.ddd.Assembler;
import SmartHomeDDD.domain.Room.Room;

import java.util.List;

public class RoomAssembler implements Assembler<Room, RoomDTO> {

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
    @Override
    public RoomDTO domainToDTO(Room room) {

        if (room == null) {
            throw new IllegalArgumentException("The Room cannot be null.");
        }

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
    @Override
    public List<RoomDTO> domainToDTO(List<Room> rooms) {
        if (rooms == null || rooms.isEmpty() || rooms.contains(null)) {
            throw new IllegalArgumentException("The list of Rooms cannot be null.");
        }

        List<RoomDTO> roomsDTO = rooms.stream().map(this::domainToDTO).toList();
        return roomsDTO;
    }

}
