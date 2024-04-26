package smart_home.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smart_home.ddd.IAssembler;
import smart_home.domain.actuator_model.ActuatorModelFactoryImpl;
import smart_home.domain.actuator_model.IActuatorModelFactory;
import smart_home.domain.device.IDeviceFactory;
import smart_home.domain.house.IHouseFactory;
import smart_home.domain.repository.IActuatorModelRepository;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.domain.repository.IHouseRepository;
import smart_home.domain.repository.IRoomRepository;
import smart_home.domain.repository.ISensorModelRepository;
import smart_home.domain.repository.ISensorRepository;
import smart_home.domain.repository.ISensorTypeRepository;
import smart_home.domain.repository.IUnitRepository;
import smart_home.domain.room.IRoomFactory;
import smart_home.domain.sensor.ISensor;
import smart_home.domain.sensor.ISensorFactory;
import smart_home.domain.sensor_model.ISensorModelFactory;
import smart_home.domain.sensor_type.ISensorTypeFactory;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.IHouseService;
import smart_home.domain.service.IRoomService;
import smart_home.domain.service.ISensorModelService;
import smart_home.domain.service.ISensorService;
import smart_home.domain.service.ISensorTypeService;
import smart_home.domain.unit.IUnitFactory;
import smart_home.mapper.*;
import smart_home.domain.device.Device;
import smart_home.domain.device.DeviceFactoryImpl;
import smart_home.domain.house.House;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.domain.room.Room;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.domain.sensor.SensorFactoryImpl;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.domain.sensor_model.SensorModelFactoryImpl;
import smart_home.domain.sensor_type.SensorType;
import smart_home.domain.sensor_type.SensorTypeFactoryImpl;
import smart_home.domain.unit.UnitFactoryImpl;
import smart_home.dto.*;
import smart_home.dto.sensor_data_dto.SensorDataGenericDTOImp;
import smart_home.service.*;
import smart_home.utils.LoadModelsAndUnit;
import smart_home.value_object.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddSensorToDeviceControllerTest {
  private IHouseRepository houseRepository;
  private IHouseService houseServiceImpl;
  private IHouseFactory houseFactory;
  private IPostalCodeFactory postalCodeFactory;
  private IRoomRepository roomRepository;
  private IRoomFactory roomFactory;
  private IRoomService roomServiceImpl;
  private IDeviceRepository deviceRepository;
  private IDeviceFactory deviceFactory;
  private IDeviceService deviceServiceImpl;
  private IUnitRepository unitRepository;
  private IUnitFactory unitFactory;
  private ISensorRepository sensorRepository;
  private ISensorFactory sensorFactory;
  private IAssembler<ISensor, SensorDTO> sensorAssembler;
  private ISensorService sensorServiceImpl;
  private ISensorTypeRepository sensorTypeRepository;
  private ISensorTypeFactory sensorTypeFactory;
  private ISensorTypeService sensorTypeServiceImpl;
  private IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler;
  private ISensorModelRepository sensorModelRepository;
  private ISensorModelFactory sensorModelFactory;
  private IAssembler<SensorModel, SensorModelDTO> sensorModelAssembler;
  private ISensorModelService sensorModelServiceImpl;
  private IActuatorModelRepository actuatorModelRepository;
  private IActuatorModelFactory actuatorModelFactory;

  @BeforeEach
  void setUp() {
    houseRepository = mock(IHouseRepository.class);
    houseFactory = new HouseFactoryImpl();
    houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
    postalCodeFactory = new PostalCodeFactory();

    roomRepository = mock(IRoomRepository.class);
    roomFactory = new RoomFactoryImpl();
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    deviceRepository = mock(IDeviceRepository.class);
    deviceFactory = new DeviceFactoryImpl();
    deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    unitRepository = mock(IUnitRepository.class);
    unitFactory = new UnitFactoryImpl();

    sensorRepository = mock(ISensorRepository.class);
    sensorFactory = new SensorFactoryImpl();
    sensorAssembler = new SensorAssembler();
    sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository);

    sensorTypeRepository = mock(ISensorTypeRepository.class);
    sensorTypeFactory = new SensorTypeFactoryImpl();
    sensorTypeAssembler = new SensorTypeAssembler();
    sensorTypeServiceImpl =
        new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository);

    sensorModelRepository = mock(ISensorModelRepository.class);
    sensorModelFactory = new SensorModelFactoryImpl();
    sensorModelAssembler = new SensorModelAssembler();
    sensorModelServiceImpl = new SensorModelServiceImpl(sensorModelRepository, sensorModelFactory);

    actuatorModelRepository = mock(IActuatorModelRepository.class);
    actuatorModelFactory = new ActuatorModelFactoryImpl();
  }

  private House loadHouse() {
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";
    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);
    Address newAddress =
        new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
    House house = houseServiceImpl.addHouse(newAddress, newGPS);
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    return house;
  }

  private Room loadRoom(HouseID houseID) {
    String name = "Quarto da Maria";
    int width = 10;
    int length = 10;
    int height = 10;
    int floor = 2;
    RoomName roomName1 = new RoomName(name);
    Dimension dimension = new Dimension(width, length, height);
    RoomFloor roomFloor = new RoomFloor(floor);
    Room room = roomServiceImpl.addRoom(houseID, roomName1, dimension, roomFloor);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    return room;
  }

  private Device loadDevice(RoomID roomID) {
    String name1 = "Lampada";
    String nameDevice = "Ar Condicionado";
    DeviceName deviceName = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(nameDevice);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");
    Device device1 = deviceServiceImpl.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);
    Device device2 = deviceServiceImpl.addDevice(roomID, deviceName2, deviceStatus, deviceTypeID);
    when(deviceRepository.ofIdentity(device1.getID())).thenReturn(Optional.of(device1));
    when(deviceRepository.findBy_roomID(roomID)).thenReturn(List.of(device1));
    List<Device> devices = deviceServiceImpl.getDevicesByRoomId(roomID);


    return devices.get(0);
  }

  private void loadModelsAndUnit() throws InstantiationException {
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);
  }

  /** Test to check if the AddSensorToDeviceController is being created correctly. */
  @Test
  void shouldCreateAddSensorToDeviceController() throws InstantiationException {
    // Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Assert
    assertNotNull(addSensorToDeviceController);
  }

//  /**
//   * Test to check if the AddSensorToDeviceController is returning null when the RoomService is
//   * null.
//   */
//  @Test
//  void shouldThrowException_whenRoomServiceIsNull() throws InstantiationException {
//    // Arrange
//    RoomServiceImpl roomServiceImpl = null;
//    String message = "Room service is required";
//    // Act
//    IllegalArgumentException exception =
//        assertThrows(
//            IllegalArgumentException.class,
//            () ->
//                new AddSensorToDeviceController(
//
//                    sensorModelServiceImpl,
//                    sensorModelAssembler,
//                    sensorTypeServiceImpl,
//                    sensorTypeAssembler,
//                    sensorAssembler,
//                    sensorServiceImpl));
//    // Assert
//    assertEquals(message, exception.getMessage());
//  }
//
//  /**
//   * Test to check if the AddSensorToDeviceController is returning null when the RoomAssembler is
//   * null.
//   */
//  @Test
//  void shouldThrowException_whenRoomAssemblerIsNull() throws InstantiationException {
//    // Arrange
//    RoomAssembler roomAssembler = null;
//    String message = "Room assembler is required";
//
//    // Act
//    IllegalArgumentException exception =
//        assertThrows(
//            IllegalArgumentException.class,
//            () ->
//                new AddSensorToDeviceController(
//
//                    sensorModelServiceImpl,
//                    sensorModelAssembler,
//                    sensorTypeServiceImpl,
//                    sensorTypeAssembler,
//                    sensorAssembler,
//                    sensorServiceImpl));
//    // Assert
//    assertEquals(message, exception.getMessage());
//  }
//
//  /**
//   * Test to check if the AddSensorToDeviceController is returning null when the DeviceService is
//   * null.
//   */
//  @Test
//  void shouldThrowException_whenDeviceServiceIsNull() throws InstantiationException {
//    // Arrange
//    DeviceServiceImpl deviceServiceImpl = null;
//    String message = "Device service is required";
//
//    // Act
//    IllegalArgumentException exception =
//        assertThrows(
//            IllegalArgumentException.class,
//            () ->
//                new AddSensorToDeviceController(
//
//                    sensorModelServiceImpl,
//                    sensorModelAssembler,
//                    sensorTypeServiceImpl,
//                    sensorTypeAssembler,
//                    sensorAssembler,
//                    sensorServiceImpl));
//    // Assert
//    assertEquals(message, exception.getMessage());
//  }
//
//  /**
//   * Test to check if the AddSensorToDeviceController is returning null when the DeviceAssembler is
//   * null.
//   */
//  @Test
//  void shouldThrowException_whenDeviceAssemblerIsNull() throws InstantiationException {
//    // Arrange
//    DeviceAssembler deviceAssembler = null;
//    String message = "Device assembler is required";
//    // Act
//    IllegalArgumentException exception =
//        assertThrows(
//            IllegalArgumentException.class,
//            () ->
//                new AddSensorToDeviceController(
//
//                    sensorModelServiceImpl,
//                    sensorModelAssembler,
//                    sensorTypeServiceImpl,
//                    sensorTypeAssembler,
//                    sensorAssembler,
//                    sensorServiceImpl));
//    // Assert
//    assertEquals(message, exception.getMessage());
//  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorModelService
   * is null.
   */
  @Test
  void shouldThrowException_whenSensorModelServiceIsNull() {
    // Act
    String expectedMessage = "Sensor model service is required";

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(

                    null,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the
   * SensorModelAssembler is null.
   */
  @Test
  void shouldThrowException_whenSensorModelAssemblerIsNull() throws InstantiationException {
    // Arrange
    SensorModelAssembler sensorModelAssembler = null;
    String message = "Sensor model assembler is required";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(

                    sensorModelServiceImpl,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorTypeService
   * is null.
   */
  @Test
  void shouldThrowException_whenSensorTypeServiceIsNull() throws InstantiationException {
    // Arrange
    SensorTypeServiceImpl sensorTypeServiceImpl = null;

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(

                    sensorModelServiceImpl,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    // Assert
    assertEquals("Sensor type service is required", exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorTypeAssembler
   * is null.
   */
  @Test
  void shouldThrowException_whenSensorTypeAssemblerIsNull() throws InstantiationException {
    // Arrange
    SensorTypeAssembler sensorTypeAssembler = null;
    String message = "Sensor type assembler is required";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(

                    sensorModelServiceImpl,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorAssembler is
   * null.
   */
  @Test
  void shouldThrowException_whenSensorAssemblerIsNull() throws InstantiationException {
    // Arrange
    SensorAssembler sensorAssembler = null;
    String message = "Sensor assembler is required";

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(

                    sensorModelServiceImpl,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorService is
   * null.
   */
  @Test
  void shouldThrowException_whenSensorServiceIsNull() throws InstantiationException {
    // Arrange
    SensorServiceImpl sensorServiceImpl = null;
    String message = "Sensor service is required";

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(

                    sensorModelServiceImpl,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * Test to get available sensor models list when the sensor model repository is not empty.
   */
  @Test
  void shouldGetAvailableSensorModelsList() throws InstantiationException {
    // Arrange
    loadModelsAndUnit();
    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);    
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    SensorModel sensorModel =
        sensorModelServiceImpl.createSensorModel(
            new SensorModelName("TemperatureSensor"),
            new ModelPath("smart_home.domain.sensor.temperature_sensor.TemperatureSensor"),
            sensorType.getID());
    when(sensorModelRepository.findBySensorTypeId(sensorType.getID())).thenReturn(List.of(sensorModel));
    when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));

    // Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

    List<SensorModelDTO> sensorModelDTOList =
        addSensorToDeviceController.getSensorModels(sensorTypeDTO);

    List<SensorModel> sensorModels = sensorModelRepository.findAll();
    List<SensorModelDTO> expectedSensorModelDTOList =
        sensorModelAssembler.domainToDTO(sensorModels);

    // Assert
    assertEquals(
        expectedSensorModelDTOList.get(0).sensorModelID, sensorModelDTOList.get(0).sensorModelID);
  }

  @Test
  void shouldThrowExceptionWhenSensorModelRepositoryIsEmpty() throws InstantiationException {
    // Arrange
    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitDescription unitDescription = new UnitDescription("Celsius");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);    
    UnitSymbol unitSymbol = new UnitSymbol("C");
    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
    unitServiceImpl.addMeasurementType(unitDescription, unitSymbol);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));


    // Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);
    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> addSensorToDeviceController.getSensorModels(sensorTypeDTO));

    // Assert
    assertEquals("No sensor models found.", exception.getMessage());
  }

  /**
   * Test if exception is thrown when there are no sensor types in the repository.
   *
   * @throws InstantiationException
   */
  @Test
  void shouldThrowExceptionWhenSensorTypeRepositoryIsEmpty() throws InstantiationException {
    // Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> addSensorToDeviceController.getSensorTypes());

    // Assert
    assertEquals("No sensor types found.", exception.getMessage());
  }

  /** Test to check if the AddSensorToDeviceController is returning a list of sensor types. */
  @Test
  void shouldReturnListOfSensorTypes_whenSensorTypeRepositoryIsNotEmpty()
      throws InstantiationException {
    // Arrange
    loadModelsAndUnit();

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));
    when(sensorTypeRepository.findAll()).thenReturn(List.of(sensorType));

    //Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    List<SensorTypeDTO> sensorTypeDTOList = addSensorToDeviceController.getSensorTypes();
    List<SensorTypeDTO> sensorTypeDTO =
        sensorTypeAssembler.domainToDTO(sensorTypeRepository.findAll());

    // Assert
    assertEquals(sensorTypeDTO.get(0).sensorTypeID, sensorTypeDTOList.get(0).sensorTypeID);
  }

  /** Test if exception is thrown when the sensor type doesn't exist in the repository. */
  @Test
  void shouldReturnEmptyList_whenThereAreNoSensorTypes() throws InstantiationException {
   loadModelsAndUnit();
    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    TypeDescription nonExistintTypeDescription = new TypeDescription("Humidity");
    UnitID unitID2 = new UnitID("Percent");
    when(unitRepository.containsOfIdentity(unitID2)).thenReturn(true);

    SensorType nonExistintSensorType =
        sensorTypeServiceImpl.createSensorType(nonExistintTypeDescription, unitID2);

    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(nonExistintSensorType);

    // Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> addSensorToDeviceController.getSensorModels(sensorTypeDTO));

    // Assert
    assertEquals(
        "Sensor type with ID " + nonExistintTypeDescription + " not found.",
        exception.getMessage());
  }

  /** Test addSensorToDevice method with valid parameters. Adding temperature sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForTemperatureSensor()
      throws InstantiationException {
    // Arrange
    loadModelsAndUnit();
    House house = loadHouse();
    Room room = loadRoom(house.getID());
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath = "smart_home.domain.sensor.temperature_sensor.TemperatureSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
        new SensorDataGenericDTOImp(
            device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding HumiditySensor sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForHumiditySensor()
      throws InstantiationException {
    // Arrange
    loadModelsAndUnit();
    House house = loadHouse();
    Room room = loadRoom(house.getID());
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("Humidity");
    UnitID unitID = new UnitID("Percent");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath = "smart_home.domain.sensor.humidity_sensor.HumiditySensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
        new SensorDataGenericDTOImp(
            device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding AveragePowerConsumptionSensor
   * sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForAveragePowerConsumptionSensor()
      throws InstantiationException {
    // Arrange
     loadModelsAndUnit();
    House house = loadHouse();
    Room room = loadRoom(house.getID());
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("AveragePowerConsumption");
    UnitID unitID = new UnitID("Watt");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath =
        "smart_home.domain.sensor.average_power_consumption_sensor.AveragePowerConsumptionSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
        new SensorDataGenericDTOImp(
            device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding Switch sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForSwitchSensor()
      throws InstantiationException {
    // Arrange
     loadModelsAndUnit();
    House house = loadHouse();
    Room room = loadRoom(house.getID());
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("Switch");
    UnitID unitID = new UnitID("Watt");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath = "smart_home.domain.sensor.switch_sensor.SwitchSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
        new SensorDataGenericDTOImp(
            device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding DewPointSensor sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForDewPointSensor()
      throws InstantiationException {
    // Arrange
     loadModelsAndUnit();
    House house = loadHouse();
    Room room = loadRoom(house.getID());
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("DewPoint");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath = "smart_home.domain.sensor.dew_point_sensor.DewPointSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
        new SensorDataGenericDTOImp(
            device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding Solar Irradiance sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForSolarIrradianceSensor()
      throws InstantiationException {
    // Arrange
     loadModelsAndUnit();
    House house = loadHouse();
    Room room = loadRoom(house.getID());
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("SolarIrradiance");
    UnitID unitID = new UnitID("WattBySquareMeter");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath = "smart_home.domain.sensor.solar_irradiance_sensor.SolarIrradianceSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
        new SensorDataGenericDTOImp(
            device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding PercentagePositionSensor sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForPercentagePositionSensor()
      throws InstantiationException {
    // Arrange
     loadModelsAndUnit();
    House house = loadHouse();
    Room room = loadRoom(house.getID());
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("PercentagePosition");
    UnitID unitID = new UnitID("Percent");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath =
        "smart_home.domain.sensor.percentage_position_sensor.PercentagePositionSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
        new SensorDataGenericDTOImp(
            device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding Instance Power Consumption sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForInstantPowerConsumptionSensor()
      throws InstantiationException {
    // Arrange
     loadModelsAndUnit();
    House house = loadHouse();
    Room room = loadRoom(house.getID());
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("InstantPowerConsumption");
    UnitID unitID = new UnitID("Watt");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath =
        "smart_home.domain.sensor.instant_power_consumption_sensor.InstantPowerConsumptionSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
        new SensorDataGenericDTOImp(
            device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with valid parameters. Adding Percentage Position Sensor. */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForInstantElectricConsumptionSensor()
      throws InstantiationException {
    // Arrange
     loadModelsAndUnit();
    House house = loadHouse();
    Room room = loadRoom(house.getID());
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("PercentagePosition");
    UnitID unitID = new UnitID("Percent");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath =
        "smart_home.domain.sensor.percentage_position_sensor.PercentagePositionSensor";
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp =
        new SensorDataGenericDTOImp(
            device.getID().getID(), modelPath, sensorName, sensorType.getID().getID());

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /** Test addSensorToDevice method with invalid parameters. */
  @Test
  void shouldThrowException_whenParametersAreInvalid() throws InstantiationException {
    // Arrange
     loadModelsAndUnit();
    House house = loadHouse();
    Room room = loadRoom(house.getID());
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorDataGenericDTOImp sensorDataGenericDTOImp = null;

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(

            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp));

    // Assert
    assertEquals(exception.getMessage(), "Sensor data DTO is required");
  }
}
