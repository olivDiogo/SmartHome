package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.ValueObject.Dimension;
import SmartHomeDDD.ValueObject.RoomFloor;
import SmartHomeDDD.ValueObject.RoomName;
import SmartHomeDDD.ddd.Assembler;
import SmartHomeDDD.domain.Room.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomAssembler implements Assembler <Room, RoomDTO> {

    public RoomAssembler() {
    }

    public RoomDTO domainToDTO(Room room) {

        String roomName = roomNameToString(room.getRoomName());
        String dimension = dimensionToString(room.getDimension());
        String roomFloor = roomFloorToString(room.getRoomFloor());
        String roomID = roomIDToString(room.getID().toString());

        RoomDTO roomDTO = new RoomDTO(roomName, dimension, roomFloor, roomID);
        return roomDTO;

    }

    public List<RoomDTO> domainToDTO(List<Room> rooms) {

        List<RoomDTO> roomsDTO = new ArrayList<>();

        for (Room room : rooms) {
            String roomName = roomNameToString(room.getRoomName());
            String dimension = dimensionToString(room.getDimension());
            String roomFloor = roomFloorToString(room.getRoomFloor());
            String roomID = roomIDToString(room.getID().toString());
            RoomDTO roomDTO = new RoomDTO(roomName, dimension, roomFloor, roomID);
            roomsDTO.add(roomDTO);
        }
        return roomsDTO;
    }


    public String roomNameToString(RoomName roomName){
        return roomName.toString();
    }

    public String dimensionToString(Dimension dimension){
        return dimension.toString();
    }

    public String roomFloorToString(RoomFloor roomFloor){
        return roomFloor.toString();
    }

    public String roomIDToString(String roomID){
        return roomID;
    }











}
