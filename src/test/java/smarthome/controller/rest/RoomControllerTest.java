package smarthome.controller.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.house.House;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.utils.dto.RoomDataDTO;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private IHouseRepository houseRepository;

  @MockBean
  private IRoomRepository roomRepository;

  @Autowired
  private RoomFactoryImpl roomFactory;

  House setupHouse() {
    // Arrange
    String street = "Rua de Sao Bento";
    String doorNumber = "123";
    String postalCode = "1200-109";
    String countryCode = "PT";
    double latitude = 38.7143;
    double longitude = -9.1459;
    Address address = new Address(street, doorNumber, postalCode, countryCode,
        new PostalCodeFactory());
    GPS gps = new GPS(latitude, longitude);
    return new House(address, gps);
  }

  RoomDataDTO setupRoomDataDTO(House house) {
    String houseIDStr = house.getID().toString();
    String name = "Living Room";
    int floor = 1;
    int width = 10;
    int length = 10;
    int height = 3;
    return new RoomDataDTO(houseIDStr, name, floor, width, length, height);
  }

  // given a RoomDataDTO create a Room object using RoomFactory
  Room setupRoom(RoomDataDTO roomDataDTO) {
    HouseID houseID = new HouseID(roomDataDTO.houseID);
    RoomName name = new RoomName(roomDataDTO.name);
    RoomFloor floor = new RoomFloor(roomDataDTO.floor);
    Dimension dimension = new Dimension(roomDataDTO.width, roomDataDTO.length, roomDataDTO.height);
    return roomFactory.createRoom(houseID, name, dimension, floor);
  }

  /**
   * Verify that a Room is correctly added to the House
   */
  @Test
  void shouldReturnRoomDTO_whenRoomIsAddedToHouse() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));

    // Act & Assert
    mockMvc.perform(post("/room/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(roomDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.roomName").value("Living Room"));
  }

  /**
   * Verify that a Room is not added to the House when the House does not exist
   */
  @Test
  void shouldReturnBadRequest_whenHouseDoesNotExist() throws Exception {
    // Arrange
    String houseIDStr = "1";
    HouseID houseID = new HouseID(houseIDStr);
    String name = "Living Room";
    int floor = 1;
    int width = 10;
    int length = 10;
    int height = 3;
    RoomDataDTO roomDataDTO = new RoomDataDTO(houseIDStr, name, floor, width, length, height);
    when(houseRepository.ofIdentity(houseID)).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(post("/room/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(roomDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test getAllRooms method in RoomController other test method
   */
  @Test
  void shouldReturnAllRooms_whenGetAllRoomsIsCalled() throws Exception {
    //Arrange
    House house = setupHouse();
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));

    RoomDataDTO dto1 = setupRoomDataDTO(house);
    RoomDataDTO dto2 = setupRoomDataDTO(house);

    Room room = setupRoom(dto1);
    Room room2 = setupRoom(dto2);

    when(roomRepository.findAll()).thenReturn(List.of(room, room2));

    //Act & Assert
    mockMvc.perform(get("/room/all"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].roomName").value("Living Room"))
        .andExpect(jsonPath("$[1].roomName").value("Living Room"));
  }

  //TODO: test getAllRooms with no rooms. Should return an empty list or an error?

  /**
   * Test getRoomById method in RoomController
   */
  @Test
  void shouldReturnRoom_whenGetRoomById() throws Exception {
    // Arrange
    House house = setupHouse();
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));

    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));

    // Act & Assert
    mockMvc.perform(get("/room/" + room.getID()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.roomName").value("Living Room"));
  }

  /**
   * Test getRoomById method in RoomController when room does not exist
   */
  @Test
  void shouldReturnNotFound_whenRoomDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));

    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(get("/room/" + room.getID()))
        .andExpect(status().isNotFound());
  }
}