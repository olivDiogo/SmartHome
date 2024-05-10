package smarthome.IT;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.service.IDeviceService;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.data_dto.RoomDataDTO;

@SpringBootTest
@AutoConfigureMockMvc
class RoomIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private IHouseRepository houseRepository;

  @MockBean
  private IRoomRepository roomRepository;

  @MockBean
  private IDeviceService deviceService;

  @MockBean
  private IAssembler<Device, DeviceDTO> deviceAssembler;

  @Autowired
  private IRoomFactory roomFactory;

  @Autowired
  private IHouseFactory houseFactory;

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
    House house = houseFactory.createHouse(address, gps);
    return house;
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

  /**
   * Test getAllRooms method in RoomController when no rooms are found
   */
  @Test
  void shouldReturnNotFound_whenNoRoomsFound() throws Exception {
    //Arrange
    when(roomRepository.findAll()).thenReturn(List.of());

    //Act & Assert
    mockMvc.perform(get("/room/all"))
        .andExpect(status().isNoContent());
  }

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

  /**
   * test getDeviceByRoomId method in RoomController
   */
  @Test
  void shouldReturnDevices_whenGetDevicesByRoomId() throws Exception {
    // Arrange
    House house = setupHouse();
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));

    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));

    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    DeviceName deviceName = new DeviceName("device1");
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("device1");
    Device device1 = deviceFactory.createDevice(room.getID(), deviceName, deviceStatus,
        deviceTypeID);
    Device device2 = deviceFactory.createDevice(room.getID(), deviceName, deviceStatus,
        deviceTypeID);
    List<Device> devices = List.of(device1, device2);
    when(deviceService.getDevicesByRoomId(room.getID())).thenReturn(devices);

    DeviceDTO deviceDTO1 = new DeviceDTO(device1.getID().toString(), room.getID().toString(),
        deviceName.toString(), deviceStatus.toString());
    DeviceDTO deviceDTO2 = new DeviceDTO(device2.getID().toString(), room.getID().toString(),
        deviceName.toString(), deviceStatus.toString());
    when(deviceAssembler.domainToDTO(device1)).thenReturn(deviceDTO1);
    when(deviceAssembler.domainToDTO(device2)).thenReturn(deviceDTO2);

    // Act & Assert
    mockMvc.perform(get("/room/" + room.getID() + "/devices")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self").exists());
  }
}