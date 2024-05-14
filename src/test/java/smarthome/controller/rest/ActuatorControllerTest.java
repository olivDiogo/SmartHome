package smarthome.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.actuator_type.ActuatorTypeFactoryImpl;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.DeviceTypeFactoryImpl;
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.unit.Unit;
import smarthome.domain.unit.UnitFactoryImpl;
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
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.utils.dto.data_dto.actuator_data_dto.ActuatorDataGenericDTOImp;
import smarthome.utils.dto.data_dto.actuator_data_dto.ActuatorDataWithIntegerLimitsDTOImp;
import smarthome.utils.dto.data_dto.actuator_data_dto.IActuatorDataDTO;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ActuatorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private IHouseFactory houseFactory;

  @MockBean
  private IDeviceRepository deviceRepository;


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

  Room setupRoom() {
    // Arrange
    House house = setupHouse();

    HouseID houseID = house.getID();
    String strRoomName = "Living Room";
    RoomName roomName = new RoomName(strRoomName);
    int floor = 1;
    RoomFloor roomFloor = new RoomFloor(floor);
    Dimension dimension = new Dimension(10, 10, 10);

    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    return roomFactory.createRoom(houseID, roomName, dimension, roomFloor);
  }

  Device setupDevice() {
    // Arrange
    Room room = setupRoom();

    RoomID roomID = room.getID();
    String strDeviceName = "LivingRoomLight1";
    DeviceName deviceName = new DeviceName(strDeviceName);

    String strTypeDescription = "Light";
    TypeDescription typeDescription = new TypeDescription(strTypeDescription);
    DeviceTypeFactoryImpl deviceTypeFactory = new DeviceTypeFactoryImpl();
    DeviceType deviceType = deviceTypeFactory.createDeviceType(typeDescription);
    DeviceTypeID deviceTypeID = deviceType.getID();

    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    return deviceFactory.createDevice(roomID, deviceName, deviceTypeID);
  }

  /**
   * Verify that a generic Actuator is correctly added to the Device
   */
  @Test
  void shouldReturnActuatorDTO_whenGenericActuatorIsAddedToDevice() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.switch_actuator.SwitchActuator";
    String actuatorName = "Light";

    /* Create Unit */
    String unit = "On/Off";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("I/O");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "Switch";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorDataDTO actuatorDataDTO = new ActuatorDataGenericDTOImp(deviceID, actuatorModelPath,
        actuatorName, actuatorTypeID);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isCreated());
  }

  /**
   * Verify that a SetIntegerActuator is correctly added to the Device
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnActuatorDTO_whenSetIntegerActuatorIsAddedToDevice() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorName = "SetInteger";
    String minLimit = "0";
    String maxLimit = "100";

    /* Create Unit */
    String unit = "Integer";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("-");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "SetInteger";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorDataDTO actuatorDataDTO = new ActuatorDataWithIntegerLimitsDTOImp(deviceID,
        actuatorModelPath, actuatorName, actuatorTypeID, minLimit, maxLimit);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isCreated());
  }

  /**
   * Verify that an Actuator is not added to the Device when the DeviceID is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenDeviceIDIsNull() throws Exception {
    // Arrange
    String actuatorModelPath = "smarthome.domain.actuator.switch_actuator.SwitchActuator";
    String actuatorName = "Light";

    /* Create Unit */
    String unit = "On/Off";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("I/O");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "Switch";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorDataDTO actuatorDataDTO = new ActuatorDataGenericDTOImp(null, actuatorModelPath,
        actuatorName, actuatorTypeID);

    // Act & Assert
    mockMvc.perform(post("/actuator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that an Actuator is not added to the Device when the ActuatorModelPath is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenActuatorModelPathIsNull() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorName = "Light";

    /* Create Unit */
    String unit = "On/Off";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("I/O");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "Switch";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorDataDTO actuatorDataDTO = new ActuatorDataGenericDTOImp(deviceID, null, actuatorName,
        actuatorTypeID);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that an Actuator is not added to the Device when the ActuatorName is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenActuatorNameIsNull() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.switch_actuator.SwitchActuator";

    /* Create Unit */
    String unit = "On/Off";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("I/O");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "Switch";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorDataDTO actuatorDataDTO = new ActuatorDataGenericDTOImp(deviceID, actuatorModelPath,
        null, actuatorTypeID);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that an Actuator is not added to the Device when the ActuatorTypeID is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenActuatorTypeIDIsNull() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.switch_actuator.SwitchActuator";
    String actuatorName = "Light";

    /* Create Unit */
    String unit = "On/Off";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("I/O");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "Switch";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());

    /* Create ActuatorDataDTO */
    IActuatorDataDTO actuatorDataDTO = new ActuatorDataGenericDTOImp(deviceID, actuatorModelPath,
        actuatorName, null);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that an Actuator is not added to the Device when the ActuatorTypeID is invalid
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenActuatorTypeIDIsInvalid() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.switch_actuator.SwitchActuator";
    String actuatorName = "Light";

    /* Create Unit */
    String unit = "On/Off";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("I/O");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "Switch";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorDataDTO actuatorDataDTO = new ActuatorDataGenericDTOImp(deviceID, actuatorModelPath,
        actuatorName, "InvalidActuatorTypeID");
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Set Integer Actuator is not added to the Device when the lower limit is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenMinLimitIsNull() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorName = "SetInteger";
    String maxLimit = "100";

    /* Create Unit */
    String unit = "Integer";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("-");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "SetInteger";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorDataDTO actuatorDataDTO = new ActuatorDataWithIntegerLimitsDTOImp(deviceID,
        actuatorModelPath, actuatorName, actuatorTypeID, null, maxLimit);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Set Integer Actuator is not added to the Device when the upper limit is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenMaxLimitIsNull() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorName = "SetInteger";
    String minLimit = "0";

    /* Create Unit */
    String unit = "Integer";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("-");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "SetInteger";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorDataDTO actuatorDataDTO = new ActuatorDataWithIntegerLimitsDTOImp(deviceID,
        actuatorModelPath, actuatorName, actuatorTypeID, minLimit, null);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Set Integer Actuator is not added to the Device when the lower limit is invalid
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenMinLimitIsInvalid() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorName = "SetInteger";
    String minLimit = "Invalid";
    String maxLimit = "100";

    /* Create Unit */
    String unit = "Integer";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("-");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "SetInteger";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorDataDTO actuatorDataDTO = new ActuatorDataWithIntegerLimitsDTOImp(deviceID,
        actuatorModelPath, actuatorName, actuatorTypeID, minLimit, maxLimit);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Set Integer Actuator is not added to the Device when the upper limit is invalid
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenMaxLimitIsInvalid() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorName = "SetInteger";
    String minLimit = "0";
    String maxLimit = "Invalid";

    /* Create Unit */
    String unit = "Integer";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("-");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "SetInteger";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorDataDTO actuatorDataDTO = new ActuatorDataWithIntegerLimitsDTOImp(deviceID,
        actuatorModelPath, actuatorName, actuatorTypeID, minLimit, maxLimit);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuator/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }
}
