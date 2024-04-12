package smartHome.controller;

import smartHome.dto.RoomDTO;
import smartHome.assembler.RoomAssembler;
import smartHome.domain.room.RoomFactoryImpl;
import smartHome.domain.room.Room;
import smartHome.persistence.mem.HouseRepository;
import smartHome.persistence.mem.RoomRepository;
import smartHome.service.RoomService;
import smartHome.valueObject.Dimension;
import smartHome.valueObject.HouseID;
import smartHome.valueObject.RoomFloor;
import smartHome.valueObject.RoomName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GetListOfRoomsControllerTest {

    /**
     * Test to check if the GetListOfRoomsController is being created correctly.
     */
    @Test
    void shouldCreateGetListOfRoomsController() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        //Act
        GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomService, roomAssembler);

        //Assert
        assertNotNull(getListOfRoomsController);
    }


    /**
     * Test to check if the GetListOfRoomsController is returning null when the RoomService is null.
     */
    @Test
    void shouldReturnNull_whenRoomServiceIsNull() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = null;

        //Act
        try {
            GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomService, roomAssembler);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid room service.", e.getMessage());
        }
    }

    /**
     * Test to check if the GetListOfRoomsController is returning null when the RoomAssembler is null.
     */
    @Test
    void shouldReturnNull_whenRoomAssemblerIsNull() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = null;
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        //Act
        try {
            GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomService, roomAssembler);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid room assembler.", e.getMessage());
        }
    }

    /**
     * Test to check if the GetListOfRoomsController is returning an empty list when there are no rooms.
     */
    @Test
    void shouldReturnEmptyList_whenThereAreNoRooms() {
        //Arrange
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
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
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomService, roomAssembler);

        HouseID houseID = new HouseID("1");
        RoomName roomName = new RoomName("Living Room");
        Dimension dimension = new Dimension(10, 10, 10);
        RoomFloor roomFloor = new RoomFloor(1);

        Room room = roomFactory.createRoom(houseID, roomName, dimension, roomFloor);
        roomRepository.save(room);

        List<RoomDTO> expectedRoomDTOList = new ArrayList<>();
        expectedRoomDTOList.add(roomAssembler.domainToDTO(room));
        
        //Act
        List<RoomDTO> roomDTOList = getListOfRoomsController.getRooms();

        //Assert
        assertEquals(expectedRoomDTOList.get(0).roomId, roomDTOList.get(0).roomId);

    }

}
