package smart_home.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import smart_home.mapper.RoomAssembler;
import smart_home.domain.room.Room;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.dto.RoomDTO;
import smart_home.persistence.mem.HouseRepository;
import smart_home.persistence.mem.RoomRepository;
import smart_home.service.RoomServiceImpl;
import smart_home.value_object.Dimension;
import smart_home.value_object.HouseID;
import smart_home.value_object.RoomFloor;
import smart_home.value_object.RoomName;

import java.util.ArrayList;
import java.util.List;

class GetListOfRoomsControllerTest {

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

        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);

        //Act
        GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomServiceImpl, roomAssembler);

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

        RoomServiceImpl roomServiceImpl = null;

        //Act
        try {
            GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomServiceImpl, roomAssembler);
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

        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);

        //Act
        try {
            GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomServiceImpl, roomAssembler);
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

        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);

        GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomServiceImpl, roomAssembler);

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

        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);

        GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomServiceImpl, roomAssembler);

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
