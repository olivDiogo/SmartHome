package smarthome.controller.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.DeviceTypeFactoryImpl;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.persistence.mem.DeviceTypeRepository;
import smarthome.persistence.mem.HouseRepository;
import smarthome.persistence.mem.RoomRepository;
import smarthome.utils.dto.DeviceDataDTO;
import smarthome.utils.dto.RoomDataDTO;

@SpringBootTest
@AutoConfigureMockMvc
class DeviceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private HouseFactoryImpl houseFactory;

  @Autowired
  private RoomFactoryImpl roomFactory;

  @Autowired
  private DeviceFactoryImpl deviceFactory;

  @Autowired
  private DeviceTypeFactoryImpl deviceTypeFactory;

  @MockBean
  private HouseRepository houseRepository;

  @MockBean
  private RoomRepository roomRepository;

  @MockBean
  private DeviceTypeRepository deviceTypeRepository;


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

  DeviceDataDTO setupDeviceDataDTO(Room room, DeviceType deviceType) {
    String deviceTypeStr = deviceType.getID().toString();
    String deviceName = "Light";
    boolean deviceStatus = true;
    String roomIDStr = room.getID().toString();
    return new DeviceDataDTO(deviceTypeStr, deviceName, deviceStatus,roomIDStr);
  }

  Device setupDevice(DeviceDataDTO deviceDataDTO) {
    RoomID roomID = new RoomID(deviceDataDTO.roomID);
    DeviceName deviceName = new DeviceName(deviceDataDTO.deviceName);
    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceDataDTO.deviceTypeID);
    DeviceStatus deviceStatus = new DeviceStatus(deviceDataDTO.deviceStatus);
    return deviceFactory.createDevice(roomID, deviceName, deviceStatus, deviceTypeID);
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
    DeviceDataDTO deviceDataDTO = setupDeviceDataDTO(room, deviceType);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));

    // Act & Assert
    mockMvc.perform(post("/device/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.deviceName").value("Light"));
  }

}




