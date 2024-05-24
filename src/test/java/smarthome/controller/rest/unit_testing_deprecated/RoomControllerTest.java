package smarthome.controller.rest.unit_testing_deprecated;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import smarthome.controller.rest.RoomController;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.mapper.RoomAssembler;
import smarthome.service.IRoomService;
import smarthome.utils.dto.RoomDTO;
import smarthome.utils.dto.data_dto.RoomDataDTO;

@SpringBootTest
class RoomControllerTest {

  @MockBean IRoomService roomService;
  @MockBean IRoomFactory roomFactory;
  @MockBean RoomAssembler roomAssembler;


  private RoomController roomController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    this.roomController =
        new RoomController(roomService, roomAssembler);
  }

  /** Unit test to add a new room to the house */
  @Test
  void shouldCreateRoom_WhenParametersAreValid() {
    // Arrange
    String houseID = "123";
    String name = "Living Room";
    int floor = 1;
    int width = 10;
    int length = 10;
    int height = 3;
    RoomDataDTO roomDataDTO = new RoomDataDTO(name, floor, width, length, height);

    Room mockRoom = mock(Room.class);
    RoomName mockName = mock(RoomName.class);
    RoomFloor mockFloor = mock(RoomFloor.class);
    Dimension mockDimension = mock(Dimension.class);
    RoomID mockId = mock(RoomID.class);
    when(mockRoom.getName()).thenReturn(mockName);
    when(mockRoom.getFloor()).thenReturn(mockFloor);
    when(mockRoom.getDimension()).thenReturn(mockDimension);
    when(mockRoom.getID()).thenReturn(mockId);
    RoomDTO roomDTO = new RoomDTO("123", "1", 1, "1");
    when(roomService.addRoom(
        any(RoomName.class), any(Dimension.class), any(RoomFloor.class)))
        .thenReturn(mockRoom);
    when(roomAssembler.domainToDTO(mockRoom)).thenReturn(roomDTO);

    // Act
    ResponseEntity<?> response = roomController.createRoom(roomDataDTO);

    // Assert
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  /** Unit test to add a new Room to the House with DTO mock */
  @Test
  void shouldCreateRoom_WhenParametersAreValidWithDTOMock() {
    // Arrange
    // Arrange
    String houseID = "123";
    String name = "Living Room";
    int floor = 1;
    int width = 10;
    int length = 10;
    int height = 3;
    RoomDataDTO roomDataDTO = new RoomDataDTO(name, floor, width, length, height);
    RoomDTO mockRoomDTO = mock(RoomDTO.class);
    Room mockRoom = mock(Room.class);

    when(roomService.addRoom(
        any(RoomName.class), any(Dimension.class), any(RoomFloor.class)))
        .thenReturn(mockRoom);
    when(roomAssembler.domainToDTO(mockRoom)).thenReturn(mockRoomDTO);

    // Act
    ResponseEntity<?> response = roomController.createRoom(roomDataDTO);

    // Assert
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }

  /** Unit Test getAllRooms method */
  @Test
  void shouldReturnAllRooms_whenGetAllRoomsIsCalled()  {
    // Arrange
    Room mockRoom = mock(Room.class);
    RoomDTO mockRoomDTO = mock(RoomDTO.class);
    when(roomService.getAllRooms()).thenReturn(List.of(mockRoom));
    when(roomAssembler.domainToDTO(List.of(mockRoom))).thenReturn(List.of(mockRoomDTO));

    // Act
    ResponseEntity<?> response = roomController.getAllRooms();

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  /** Unit Test get room by ID method */
  @Test
  void shouldReturnRoom_whenGetRoomByIdIsCalled() {
    // Arrange
    String id = "1";
    Room mockRoom = mock(Room.class);
    RoomDTO mockRoomDTO = mock(RoomDTO.class);
    when(roomService.getRoomById(any(RoomID.class))).thenReturn(java.util.Optional.of(mockRoom));
    when(roomAssembler.domainToDTO(mockRoom)).thenReturn(mockRoomDTO);

    // Act
    ResponseEntity<?> response = roomController.getRoomById(id);

    // Assert
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}
