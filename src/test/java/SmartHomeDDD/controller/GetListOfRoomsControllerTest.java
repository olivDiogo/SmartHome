package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.RoomDTO;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.Room.ImpRoomFactory;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.service.RoomService;
import SmartHomeDDD.valueObject.Dimension;
import SmartHomeDDD.valueObject.HouseID;
import SmartHomeDDD.valueObject.RoomFloor;
import SmartHomeDDD.valueObject.RoomName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetListOfRoomsControllerTest {

    /**
     * Test to check if the GetListOfRoomsController is being created correctly.
     */
    @Test
    void shouldCreateGetListOfRoomsController() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        //Act
        GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomService, roomAssembler);

    }

    /**
     * Test to check if the GetListOfRoomsController is returning an empty list when there are no rooms.
     */
    @Test
    void shouldReturnEmptyList_whenThereAreNoRooms() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomService, roomAssembler);

        //Act
        List<RoomDTO> roomDTOList = getListOfRoomsController.getRooms();

        //Assert
        assertTrue(roomDTOList.isEmpty());

    }


    /**
     * Test to check if the GetListOfRoomsController is returning a list of rooms when there are rooms.
     */
    @Test
    void shouldReturnListOfRooms_WhenGetRoomsIsCalled() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomService, roomAssembler);

        HouseID houseID = new HouseID("1");
        RoomName roomName = new RoomName("Living Room");
        Dimension dimension = new Dimension(10, 10, 10);
        RoomFloor roomFloor = new RoomFloor(1);

        Room room = roomFactory.createRoom(houseID, roomName, dimension, roomFloor);

        HouseID houseID2 = new HouseID("1");
        RoomName roomName2 = new RoomName("Bedroom");
        Dimension dimension2 = new Dimension(10, 10, 10);
        RoomFloor roomFloor2 = new RoomFloor(1);

        Room room2 = roomFactory.createRoom(houseID2, roomName2, dimension2, roomFloor2);
        roomRepository.save(room);
        roomRepository.save(room2);

        List<RoomDTO> expectedRoomDTOList = new ArrayList<>();
        expectedRoomDTOList.add(roomAssembler.domainToDTO(room));
        expectedRoomDTOList.add(roomAssembler.domainToDTO(room2));

        //Act
        List<RoomDTO> roomDTOList = getListOfRoomsController.getRooms();

        //Assert
        assertEquals(expectedRoomDTOList.get(0).roomId, roomDTOList.get(0).roomId);
        assertEquals(expectedRoomDTOList.get(0).roomName, roomDTOList.get(0).roomName);
        assertEquals(expectedRoomDTOList.get(0).dimensions, roomDTOList.get(0).dimensions);
        assertEquals(expectedRoomDTOList.get(0).floor, roomDTOList.get(0).floor);
        assertEquals(expectedRoomDTOList.get(1).roomId, roomDTOList.get(1).roomId);
        assertEquals(expectedRoomDTOList.get(1).roomName, roomDTOList.get(1).roomName);
        assertEquals(expectedRoomDTOList.get(1).dimensions, roomDTOList.get(1).dimensions);
        assertEquals(expectedRoomDTOList.get(1).floor, roomDTOList.get(1).floor);

    }

}
