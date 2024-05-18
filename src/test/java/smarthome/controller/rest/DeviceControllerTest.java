package smarthome.controller.rest;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


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
import smarthome.domain.device.Device;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.IDeviceTypeFactory;
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.IDeviceTypeRepository;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.utils.dto.data_dto.DeviceDataDTO;
import smarthome.utils.dto.data_dto.RoomDataDTO;

@SpringBootTest
@AutoConfigureMockMvc
class DeviceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private IHouseFactory houseFactory;

  @Autowired
  private IRoomFactory roomFactory;

  @Autowired
  private IDeviceFactory deviceFactory;

  @Autowired
  private IDeviceTypeFactory deviceTypeFactory;

  @MockBean
  private IDeviceRepository deviceRepository;

  @MockBean
  private IHouseRepository houseRepository;

  @MockBean
  private IRoomRepository roomRepository;

  @MockBean
  private IDeviceTypeRepository deviceTypeRepository;


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
    return houseFactory.createHouse(address, gps);
  }

  Room setupRoom(RoomDataDTO roomDataDTO) {
    HouseID houseID = new HouseID(roomDataDTO.houseID);
    RoomName name = new RoomName(roomDataDTO.name);
    RoomFloor floor = new RoomFloor(roomDataDTO.floor);
    Dimension dimension = new Dimension(roomDataDTO.width, roomDataDTO.length, roomDataDTO.height);
    return roomFactory.createRoom(houseID, name, dimension, floor);
  }

  DeviceType setupDeviceType() {
    TypeDescription typeDescription = new TypeDescription("Bulb");
    return deviceTypeFactory.createDeviceType(typeDescription);
  }

  DeviceType setupDeviceTypeTwo() {
    TypeDescription typeDescription = new TypeDescription("Fan");
    return deviceTypeFactory.createDeviceType(typeDescription);
  }

  DeviceDataDTO setupDeviceDataDTO(Room room, String deviceTypeDescription) {
    String deviceName = "Light";
    String roomIDStr = room.getID().toString();
    return new DeviceDataDTO(deviceTypeDescription, deviceName,roomIDStr);
  }

  Device setupDevice(DeviceDataDTO deviceDataDTO) {
    RoomID roomID = new RoomID(deviceDataDTO.roomID);
    DeviceName deviceName = new DeviceName(deviceDataDTO.deviceName);
    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceDataDTO.deviceTypeDescription);
    return deviceFactory.createDevice(roomID, deviceName, deviceTypeID);
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


  /**
   * Verify that a Device is correctly added to the Room
   */
  @Test
  void shouldReturnDeviceDTO_whenDeviceIsAddedToRoom() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(room, deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.save(device)).thenReturn(device);

    // Act & Assert
    mockMvc.perform(post("/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.deviceName").value("Light"))
        .andExpect(jsonPath("$._links.self").exists());
  }

  /**
   * Verify that a Device is correctly retrieved by its ID
   */
  @Test
  void shouldReturnDeviceDTO_whenGetDeviceById() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(room, deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(get("/devices/" + device.getID())
         .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.deviceID").value(device.getID().toString()))
        .andExpect(jsonPath("$._links.self").exists());
  }

  /**
   * Verify that a Device is not added to the Room when the Room does not exist
   */
  @Test
  void shouldReturnBadRequest_whenRoomDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(new RoomID(deviceDataDTO.roomID))).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(post("/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Device is not added to the Room when the DeviceType does not exist
   */
  @Test
  void shouldReturnBadRequest_whenDeviceTypeDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(new DeviceTypeID(deviceDataDTO.deviceTypeDescription)))
        .thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(post("/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Device is not added to the Room when the House does not exist
   */
  @Test
  void shouldReturnBadRequest_whenHouseDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);

    when(houseRepository.ofIdentity(new HouseID(roomDataDTO.houseID))).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(post("/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test getDeviceID method when the device does not exist
   */
  @Test
  void shouldReturnNotFound_whenDeviceDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(setupRoom(roomDataDTO).getID())).thenReturn(Optional.of(setupRoom(roomDataDTO)));
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(get("/devices" + device.getID())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  /**
   * Test getDeviceID when deviceType does not exist
   */
  @Test
  void shouldReturnNotFound_whenDeviceTypeDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(get("/devices" + device.getID())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  /**
   * Test getAllDevice when devices are available
   */
  @Test
  void shouldReturnAllDevices_whenGetAllDevicesIsCalled() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);

    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";

    DeviceDataDTO deviceDataDTO1 = setupDeviceDataDTO(room, deviceTypeDescription);
    DeviceDataDTO deviceDataDTO2 = setupDeviceDataDTO(room, deviceTypeDescription);

    Device device = setupDevice(deviceDataDTO1);
    Device device2 = setupDevice(deviceDataDTO2);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(deviceRepository.findAll()).thenReturn(List.of(device, device2));

    int expectedSize = List.of(device, device2).size();

    // Act & Assert
    mockMvc.perform(get("/devices")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.deviceDTOList", hasSize(expectedSize)))
        .andExpect(jsonPath("$._links.self").exists());

  }

  /**
   * Test getAllDevices when no devices are available
   */
  @Test
  void shouldReturnNotFound_whenNoDevicesAvailable() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.findAll()).thenReturn(List.of());

    // Act & Assert
    mockMvc.perform(get("/devicesall")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  /**
   * Test deactivateDevice when the device exists
   */
  @Test
  void shouldReturnDeviceDTO_whenDeactivateDevice() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    Room room = setupRoom(roomDataDTO);
    String deviceTypeDescription = "Bulb";
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(room, deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(put("/devices/deactivate/" + device.getID())
         .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.deviceID").value(device.getID().toString()))
        .andExpect(jsonPath("$.deviceStatus").value("OFF"))
        .andExpect(jsonPath("$._links.self").exists());
  }


  /**
   * Test deactivateDevice when the device does not exist
   */
  @Test
  void shouldReturnNotFound_whenDeactivateDeviceDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(setupRoom(roomDataDTO).getID())).thenReturn(Optional.of(setupRoom(roomDataDTO)));
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(put("/devicesdeactivate/" + device.getID())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }


  /**
   * Test getAllDevicesGroupedByFunctionality when there is a device with no device type
   */
  @Test
  void shouldReturnNotFound_whenThereIsNoDeviceType() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    DeviceType deviceType = setupDeviceType();
    Room room = setupRoom(roomDataDTO);
    String deviceTypeDescription = "Bulb";
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(room, deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.empty());
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.findAll()).thenReturn(List.of(device));

    // Act & Assert
    mockMvc.perform(get("/devicesgrouped")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }


  /**
   * Test getAllDevicesGroupedByFunctionality returns a map of devices grouped by
   * functionality, when devices have different type.
   */
  @Test
  void shouldReturnDevicesGroupedByFunctionality_whenDevicesHaveDifferentType() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    DeviceType deviceType2 = setupDeviceTypeTwo();
    String deviceTypeDescription = "Bulb";
    String deviceTypeDescription2 = "Fan";

    Device device = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription));
    Device deviceTwo = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription2));
    Device deviceThree = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription2));

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(deviceTypeRepository.ofIdentity(deviceType2.getID())).thenReturn(Optional.of(deviceType2));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.findAll()).thenReturn(List.of(device, deviceTwo, deviceThree));

    // Act & Assert
    mockMvc.perform(get("/devices/grouped")
            .param("typeDescription",deviceTypeDescription)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.linkedHashMapList[0]['" + deviceTypeDescription + "']", hasSize(1)))
        .andExpect(jsonPath("$._embedded.linkedHashMapList[0]['" + deviceTypeDescription + "'][0].deviceID").value(device.getID().getID()))
        .andExpect(jsonPath("$._embedded.linkedHashMapList[0]['" + deviceTypeDescription2 + "']", hasSize(2)))
        .andExpect(jsonPath("$._embedded.linkedHashMapList[0]['" + deviceTypeDescription2 + "'][0].deviceID").value(deviceTwo.getID().getID()))
        .andExpect(jsonPath("$._embedded.linkedHashMapList[0]['" + deviceTypeDescription2 + "'][1].deviceID").value(deviceThree.getID().getID()));
  }

  /**
   * test getDeviceByRoomId method in RoomController
   */
  @Test
  void shouldReturnDevices_whenGetDevicesByRoomId() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomDataDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    DeviceType deviceType2 = setupDeviceTypeTwo();
    String deviceTypeDescription = "Bulb";
    String deviceTypeDescription2 = "Fan";

    Device device = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription));
    Device deviceTwo = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription2));
    Device deviceThree = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription2));

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(deviceTypeRepository.ofIdentity(deviceType2.getID())).thenReturn(Optional.of(deviceType2));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.findByRoomID(room.getID())).thenReturn(List.of(device, deviceTwo, deviceThree));

    // Act & Assert
    mockMvc.perform(get("/devices/" + room.getID() + "/room")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self").exists());
  }

}
