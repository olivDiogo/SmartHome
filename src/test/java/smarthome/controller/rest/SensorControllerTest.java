package smarthome.controller.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
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
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.sensor_type.SensorTypeFactoryImpl;
import smarthome.domain.unit.Unit;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceName;
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
import smarthome.utils.dto.data_dto.sensor_data_dto.ISensorDataDTO;
import smarthome.utils.dto.data_dto.sensor_data_dto.SensorDataGenericDTOImp;
import smarthome.utils.dto.data_dto.sensor_data_dto.SensorDataWithDateDTOImp;
import smarthome.utils.dto.data_dto.sensor_data_dto.SensorDataWithGPSDTOImp;


@SpringBootTest
@AutoConfigureMockMvc
class SensorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private IHouseFactory houseFactory;

  @MockBean
  private IDeviceRepository deviceRepository;

  @MockBean
  private ISensorTypeRepository sensorTypeRepository;

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

    Dimension dimension = new Dimension(10, 10, 10);

    int floor = 1;
    RoomFloor roomFloor = new RoomFloor(floor);

    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    return roomFactory.createRoom(houseID, roomName, dimension, roomFloor);
  }

  Device setupDevice() {
    // Arrange
    Room room = setupRoom();
    RoomID roomID = room.getID();

    String strDeviceName = "Light";
    DeviceName deviceName = new DeviceName(strDeviceName);


    String strDeviceType = "LightBulb";
    TypeDescription typeDescription = new TypeDescription(strDeviceType);
    DeviceTypeFactoryImpl deviceTypeFactory = new DeviceTypeFactoryImpl();
    DeviceType deviceType = deviceTypeFactory.createDeviceType(typeDescription);
    DeviceTypeID deviceTypeID = deviceType.getID();

    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    return deviceFactory.createDevice(roomID, deviceName, deviceTypeID);
  }


  /**
   * Test to add a generic sensor to a device
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnSensorDTO_whenGenericSensorIsAddedToDevice() throws Exception {
    //Arrange
    Device device = setupDevice();

    String deviceIDStr = device.getID().toString();
    String sensorModelPath = "smarthome.domain.sensor.dew_point_sensor.DewPointSensor";

    /* create unit */
    UnitDescription unit = new UnitDescription("Celsius");
    UnitSymbol strUnitSymbol = new UnitSymbol("C");
    Unit sensorUnit = new UnitFactoryImpl().createUnit(unit, strUnitSymbol);

    /* create sensor type */
    String strSensorType = "DewPoint";
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();

    SensorType sensorType = sensorTypeFactory.createSensorType(new TypeDescription(strSensorType), sensorUnit.getID());

    String sensorTypeIDStr = sensorType.getID().toString();

    /* create dataDTO */
    ISensorDataDTO sensorDataDTO = new SensorDataGenericDTOImp(deviceIDStr, sensorModelPath,
        "DewPoint", sensorTypeIDStr);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    //Act + Assert
    mockMvc.perform(post("/sensor/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isCreated());
  }


  /**
   * Test to add a sensor with GPS to a device
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnSensorDTO_WhenSensorWithGPSIsAddedToDevice() throws Exception {
    //Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().toString();

    String strSensorType = "SunsetTime";
    TypeDescription typeDescription = new TypeDescription(strSensorType);

    String unitDescription = "Celsius";
    UnitDescription unit = new UnitDescription(unitDescription);

    String unitSymbol = "C";
    UnitSymbol strUnitSymbol = new UnitSymbol(unitSymbol);

    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit sensorUnit = unitFactory.createUnit(unit, strUnitSymbol);

    String sensorModelPath = "smarthome.domain.sensor.sunset_time_sensor.SunsetTimeSensor";
    String sensorName = "SunSet";

    String latitude = "38.7143";
    String longitude = "-9.1459";

    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(typeDescription, sensorUnit.getID());

    ISensorDataDTO sensorDataDTO = new SensorDataWithGPSDTOImp(deviceIDStr, sensorModelPath,
        sensorName, sensorType.getID().toString(), latitude, longitude);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act & Assert
    mockMvc.perform(post("/sensor/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isCreated());
  }

  /**
   * Test to add a sensor with date to a device
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnSensorDTO_WhenSensorWithDateIsAddedToDevice() throws Exception {
    //Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().toString();

    String strSensorType = "ElectricConsumptionWh";
    TypeDescription typeDescription = new TypeDescription(strSensorType);

    String unitDescription = "Wh";
    UnitDescription unit = new UnitDescription(unitDescription);

    String unitSymbol = "Wh";
    UnitSymbol strUnitSymbol = new UnitSymbol(unitSymbol);

    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit sensorUnit = unitFactory.createUnit(unit, strUnitSymbol);

    String sensorModelPath = "smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor";
    String sensorName = "ElectricConsumptionWh";

    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);

    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(typeDescription, sensorUnit.getID());

    ISensorDataDTO sensorDataDTO = new SensorDataWithDateDTOImp(deviceIDStr, sensorModelPath,
        sensorName, sensorType.getID().toString(), datePeriod.getStartDate().toString(),
        datePeriod.getEndDate().toString());

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act & Assert
    mockMvc.perform(post("/sensor/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isCreated());
  }

  @Test
  void shouldReturnBadRequest_WhenDeviceIDIsNull() throws Exception {
    //Arrange
    String deviceID = null;

    String sensorModelPath = "smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor";
    String sensorName = "ElectricConsumptionWh";

    String strUnitDescription = "Wh";
    UnitDescription unitDescription = new UnitDescription(strUnitDescription);
    String strUnitSymbol = "Wh";
    UnitSymbol unitSymbol = new UnitSymbol(strUnitSymbol);
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);

    String strSensorType = "ElectricConsumptionWh";
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(new TypeDescription(strSensorType),
        unit.getID());
    String sensorTypeID = sensorType.getID().getID();

    ISensorDataDTO sensorDataDTO = new SensorDataGenericDTOImp(deviceID, sensorModelPath,
        sensorName, sensorTypeID);

    // Act & Assert
    mockMvc.perform(post("/sensor/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void shouldReturnBadRequest_WhenSensorModelPathIsNull() throws Exception {
    //Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().getID();

    String sensorModelPath = null;
    String sensorName = "ElectricConsumptionWh";

    String strUnitDescription = "Wh";
    UnitDescription unitDescription = new UnitDescription(strUnitDescription);
    String strUnitSymbol = "Wh";
    UnitSymbol unitSymbol = new UnitSymbol(strUnitSymbol);
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);

    String strSensorType = "ElectricConsumptionWh";
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(new TypeDescription(strSensorType),
        unit.getID());
    String sensorTypeID = sensorType.getID().getID();

    ISensorDataDTO sensorDataDTO = new SensorDataGenericDTOImp(deviceIDStr, sensorModelPath,
        sensorName, sensorTypeID);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/sensor/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isBadRequest());

  }

  @Test
  void shouldReturnBadRequest_WhenSensorTypeIDIsNull() throws Exception {
    //Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().getID();

    String sensorModelPath = "smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor";
    String sensorName = "ElectricConsumptionWh";

    String strUnitDescription = "Wh";
    UnitDescription unitDescription = new UnitDescription(strUnitDescription);
    String strUnitSymbol = "Wh";
    UnitSymbol unitSymbol = new UnitSymbol(strUnitSymbol);
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);

    String sensorTypeID = null;

    ISensorDataDTO sensorDataDTO = new SensorDataGenericDTOImp(deviceIDStr, sensorModelPath,
        sensorName, sensorTypeID);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/sensor/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void shouldReturnBadRequest_WhenSensorTypeIDIsInvalid() throws Exception {
    //Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().getID();

    String sensorModelPath = "smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor";
    String sensorName = "ElectricConsumptionWh";

    String strUnitDescription = "Wh";
    UnitDescription unitDescription = new UnitDescription(strUnitDescription);
    String strUnitSymbol = "Wh";
    UnitSymbol unitSymbol = new UnitSymbol(strUnitSymbol);
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);

    String strSensorType = "Electric";
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(new TypeDescription(strSensorType),
        unit.getID());
    String sensorTypeID = sensorType.getID().getID();

    ISensorDataDTO sensorDataDTO = new SensorDataGenericDTOImp(deviceIDStr, sensorModelPath,
        sensorName, sensorTypeID);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act & Assert
    mockMvc.perform(post("/sensor/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isBadRequest());
  }
}