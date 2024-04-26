package smart_home.controller;

import org.junit.jupiter.api.Test;
import smart_home.ddd.IAssembler;
import smart_home.domain.actuator.IActuator;
import smart_home.domain.actuator.IActuatorFactory;
import smart_home.domain.actuator_model.IActuatorModelFactory;
import smart_home.domain.actuator_type.IActuatorTypeFactory;
import smart_home.domain.device.IDeviceFactory;
import smart_home.domain.house.IHouseFactory;
import smart_home.domain.repository.IActuatorModelRepository;
import smart_home.domain.repository.IActuatorRepository;
import smart_home.domain.repository.IActuatorTypeRepository;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.domain.repository.IHouseRepository;
import smart_home.domain.repository.IRoomRepository;
import smart_home.domain.repository.ISensorModelRepository;
import smart_home.domain.repository.IUnitRepository;
import smart_home.domain.room.IRoomFactory;
import smart_home.domain.sensor_model.ISensorModelFactory;
import smart_home.domain.sensor_model.SensorModelFactoryImpl;
import smart_home.domain.service.IActuatorModelService;
import smart_home.domain.service.IActuatorTypeService;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.IHouseService;
import smart_home.domain.service.IRoomService;
import smart_home.domain.unit.IUnitFactory;
import smart_home.dto.actuator_data_dto.ActuatorDataGenericDTOImp;
import smart_home.dto.actuator_data_dto.ActuatorDataWithDecimalLimitsDTOImp;
import smart_home.dto.actuator_data_dto.ActuatorDataWithIntegerLimitsDTOImp;
import smart_home.dto.actuator_data_dto.IActuatorDataDTO;
import smart_home.mapper.*;
import smart_home.domain.actuator.ActuatorFactoryImpl;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.domain.actuator_model.ActuatorModelFactoryImpl;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.domain.actuator_type.ActuatorTypeFactoryImpl;
import smart_home.domain.device.Device;
import smart_home.domain.device.DeviceFactoryImpl;
import smart_home.domain.house.House;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.domain.room.Room;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.domain.service.IActuatorService;
import smart_home.domain.unit.UnitFactoryImpl;
import smart_home.dto.*;
import smart_home.persistence.mem.*;
import smart_home.service.*;
import smart_home.utils.LoadModelsAndUnit;
import smart_home.value_object.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddActuatorToDeviceControllerTest {
  ISensorModelRepository sensorModelRepository = new SensorModelRepository();
  ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();
  IRoomRepository roomRepository = new RoomRepository();
  IRoomFactory roomFactory = new RoomFactoryImpl();
  IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
  IHouseRepository houseRepository = new HouseRepository();
  IUnitRepository unitRepository = new UnitRepository();
  IUnitFactory unitFactory = new UnitFactoryImpl();
  IActuatorRepository actuatorRepository = new ActuatorRepository();
  IActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
  IActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
  IActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
  IActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
  IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();
  IDeviceRepository deviceRepository = new DeviceRepository();
  IDeviceFactory deviceFactory = new DeviceFactoryImpl();
  IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler = new ActuatorModelAssembler();
  IRoomService roomServiceImpl =
      new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
  IActuatorModelService actuatorModelServiceImpl =
      new ActuatorModelServiceImpl(actuatorModelRepository, actuatorModelFactory);
  IActuatorTypeService ActuatorTypeServiceImpl =
      new ActuatorTypeServiceImpl(actuatorTypeRepository, actuatorTypeFactory, unitRepository);
  IAssembler<ActuatorType, ActuatorTypeDTO> actuatorTypeAssembler = new ActuatorTypeAssembler();
  IAssembler<IActuator, ActuatorDTO> actuatorAssembler = new ActuatorAssembler();
  IActuatorService actuatorService =
      new ActuatorServiceImpl(actuatorRepository, actuatorFactory, deviceRepository);
  IDeviceService deviceServiceImpl =
      new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
  IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
  IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
  IHouseFactory houseFactory = new HouseFactoryImpl();
  IHouseService houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

  private void loadHouseAndRoom() {
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";
    double latitude = 41.178;
    double longitude = -8.608;
    String name = "Quarto da Maria";
    int width = 10;
    int length = 10;
    int height = 10;
    int floor = 2;
    RoomName roomName1 = new RoomName(name);
    GPS newGPS = new GPS(latitude, longitude);
    Address newAddress =
        new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
    House house = houseServiceImpl.addHouse(newAddress, newGPS);
    HouseID houseID = house.getID();
    Dimension dimension = new Dimension(width, length, height);
    RoomFloor roomFloor = new RoomFloor(floor);
    roomServiceImpl.addRoom(houseID, roomName1, dimension, roomFloor);
  }

  private Device loadDevice(RoomID roomID) {
    String name1 = "Lampada";
    String nameDevice = "Ar Condicionado";
    DeviceName deviceName = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(nameDevice);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");
    deviceServiceImpl.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);
    deviceServiceImpl.addDevice(roomID, deviceName2, deviceStatus, deviceTypeID);
    List<Device> devices = deviceServiceImpl.getDevicesByRoomId(roomID);
    return devices.get(0);
  }

  /** Test to check if the AddActuatorToDeviceController is being created correctly. */
  @Test
  void shouldThrowExceptionWhenRoomServiceIsNull() throws InstantiationException {
    // Assert
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    String expectedMessage = "Room service is required";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    null,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when room assembler is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenRoomAssemblerIsNull() throws InstantiationException {
    // Assert
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    String expectedMessage = "Room assembler is required";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    null,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when device service is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenDeviceServiceIsNull() throws InstantiationException {
    // Assert
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    String expectedMessage = "Device service is required";
    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    null,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when device assembler is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenDeviceAssemblerIsNull() throws InstantiationException {
    // Assert
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    String expectedMessage = "Device assembler is required";
    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    null,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when actuator model service is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorModelServiceIsNull() throws InstantiationException {
    // Assert
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    String expectedMessage = "Actuator model service is required";
     //Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    null,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when actuator model assembler is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorModelAssemblerIsNull() throws InstantiationException {
    // Assert
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    String expectedMessage = "Actuator model assembler is required";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    null,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }


  /**
   * Should throw exception when actuator type service is null.
   */
  @Test
  void shouldThrowException_whenConfigurationServiceIsNull() {
    // Act
    try {
      new AddActuatorToDeviceController(
          roomServiceImpl,
          roomAssembler,
          deviceServiceImpl,
          deviceAssembler,
          actuatorModelServiceImpl,
          actuatorModelAssembler,
          ActuatorTypeServiceImpl,
          actuatorTypeAssembler,
          actuatorAssembler,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid actuator type service.", e.getMessage());
    }
  }

  /**
   * Should throw exception when actuator type service is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorTypeServiceIsNull() throws InstantiationException {
    // Assert
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    String expectedMessage = "Actuator type service is required";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    null,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when actuator type assembler is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorTypeAssemblerIsNull() throws InstantiationException {
    // Assert
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    String expectedMessage = "Actuator type assembler is required";
    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    null,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when actuator assembler is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorAssemblerIsNull() throws InstantiationException {
    // Assert
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    String expectedMessage = "Actuator assembler is required";
    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    null,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when actuator service is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorServiceIsNull() throws InstantiationException {
    // Assert
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    String expectedMessage = "Actuator service is required";
    // Act + Assert
   Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    null));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when room service is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldReturnEmptyList_whenThereAreNoRooms() throws InstantiationException {
    // Arrange
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    // Act
    List<RoomDTO> result = controller.getRooms();

    // Assert
    assertTrue(result.isEmpty());
  }

  /**
   * Should get rooms from house when given valid house ID.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldGetRoomsFromHouse_WhenGivenValidHouseID() throws InstantiationException {
    // Arrange
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);
    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    List<RoomDTO> expectedRoomsDTOList = roomAssembler.domainToDTO(rooms);
    String expectedRoomDTOName = expectedRoomsDTOList.get(0).roomName;
    String expectedRoomDTOID = expectedRoomsDTOList.get(0).roomId;
    List<String> expectedList = List.of(expectedRoomDTOName, expectedRoomDTOID);

    // Act
    List<RoomDTO> roomsDTOList = controller.getRooms();
    String actualRoomDTOName = roomsDTOList.get(0).roomName;
    String actualRoomDTOID = roomsDTOList.get(0).roomId;
    List<String> actualList = List.of(actualRoomDTOName, actualRoomDTOID);

    // Assert
    assertEquals(expectedList, actualList);
  }

  /**
   * Should throw exception when room ID does not exist in repository.
   *
   */
  @Test
  void shouldThrowException_WhenRoomIDDoesNotExistInRepository() throws InstantiationException {
    // Arrange
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    String roomID = "123";
    RoomID nonExistentRoomID = new RoomID(roomID);

    String roomName = "Quarto da Maria";
    String dimension = "10x10x10";
    String floor = "2";
    RoomDTO roomDTO = new RoomDTO(roomName, dimension, floor, nonExistentRoomID.toString());

    String expectedMessage = "Room with ID " + nonExistentRoomID + " not found.";

    // Act & Assert
    Exception exception =
        assertThrows(IllegalArgumentException.class, () -> controller.getDevicesFromRoom(roomDTO));

    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should get devices from room when given valid room ID.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldGetDevicesFromRoom_WhenParametersAreValid() throws InstantiationException {
    // Arrange
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);
    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    loadDevice(roomID);
    List<Device> devices = deviceServiceImpl.getDevicesByRoomId(roomID);
    List<DeviceDTO> deviceDTOListExpected = deviceAssembler.domainToDTO(devices);
    List<RoomDTO> roomsDTOList = controller.getRooms();
    RoomDTO roomDTO = roomsDTOList.get(0);
    String expectedDeviceDTOID = deviceDTOListExpected.get(0).deviceID;
    String expectedDeviceDTOName = deviceDTOListExpected.get(0).deviceName;
    List<String> expectedDeviceDTOList = List.of(expectedDeviceDTOID, expectedDeviceDTOName);
    // Act
    List<DeviceDTO> devicesDTOList = controller.getDevicesFromRoom(roomDTO);

    String actualDeviceDTOID = devicesDTOList.get(0).deviceID;
    String actualDeviceDTOName = devicesDTOList.get(0).deviceName;
    List<String> actualDeviceDTOList = List.of(actualDeviceDTOID, actualDeviceDTOName);
    // Assert
    assertEquals(expectedDeviceDTOList, actualDeviceDTOList);
  }

  /**
   * Should get available actuator types list.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldGetAvailableActuatorTypesList() throws InstantiationException {
    // Arrange
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    TypeDescription typeDescription = new TypeDescription("BlindRoller");
    UnitID unit = new UnitID("Percent");
    ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
    ActuatorTypeServiceImpl.addActuatorType(actuatorType);

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    List<ActuatorTypeDTO> ActuatorTypeDTOList = controller.getActuatorTypes();

    List<ActuatorType> actuatorTypes = actuatorTypeRepository.findAll();
    List<ActuatorTypeDTO> expectedActuatorTypeDTOList =
        actuatorTypeAssembler.domainToDTO(actuatorTypes);
    // Assert

    assertEquals(
        expectedActuatorTypeDTOList.get(0).actuatorTypeID,
        ActuatorTypeDTOList.get(0).actuatorTypeID);
  }

  /**
   * Should get available actuator models list.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldGetAvailableActuatorModelsList() throws InstantiationException {
    // Arrange
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    TypeDescription typeDescription = new TypeDescription("BlindRoller");
    UnitID unit = new UnitID("Percent");
    ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
    ActuatorTypeServiceImpl.addActuatorType(actuatorType);

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    ActuatorTypeDTO dto = actuatorTypeAssembler.domainToDTO(actuatorType);
    List<ActuatorModelDTO> ActuatorModelDTOList = controller.getActuatorModels(dto);

    List<ActuatorModel> actuatorModels = actuatorModelRepository.findAll();
    List<ActuatorModelDTO> expectedActuatorModelDTOList =
        actuatorModelAssembler.domainToDTO(actuatorModels);
    // Assert

    assertEquals(
        expectedActuatorModelDTOList.get(0).actuatorModelID,
        ActuatorModelDTOList.get(0).actuatorModelID);
  }

  /**
   * Should throw exception when actuator model repository is empty.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_WhenActuatorModelRepositoryIsEmpty() throws InstantiationException {
    // Arrange
    TypeDescription typeDescription = new TypeDescription("BlindRoller");
    UnitDescription unitDescription = new UnitDescription("Celsius");
    UnitID unitID = new UnitID("Celsius");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
    unitServiceImpl.addMeasurementType(unitDescription, unitSymbol);
    ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unitID);
    ActuatorTypeServiceImpl.addActuatorType(actuatorType);

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    ActuatorTypeDTO dto = actuatorTypeAssembler.domainToDTO(actuatorType);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> controller.getActuatorModels(dto));

    // Assert
    assertEquals("No actuator models found.", exception.getMessage());
  }

  /**
   * Should throw exception when actuator type repository is empty.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_WhenActuatorTypeRepositoryIsEmpty() throws InstantiationException {

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> controller.getActuatorTypes());

    // Assert
    assertEquals("No actuator types found.", exception.getMessage());
  }

  /**
   * Should return empty list when there are no actuator types.
   *
   * @throws InstantiationException
   */
  @Test
  void shouldReturnEmptyList_whenThereAreNoActuatorTypes() throws InstantiationException {
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unit = new UnitID("Celsius");
    ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
    ActuatorTypeServiceImpl.addActuatorType(actuatorType);

    TypeDescription nonExistintTypeDescription = new TypeDescription("Humidity");
    UnitID unitID = new UnitID("Percent");
    ActuatorType nonExistintActuatorType =
        ActuatorTypeServiceImpl.createActuatorType(nonExistintTypeDescription, unitID);

    ActuatorTypeDTO actuatorTypeDTO = actuatorTypeAssembler.domainToDTO(nonExistintActuatorType);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> controller.getActuatorModels(actuatorTypeDTO));

    // Assert
    assertEquals(
        "Actuator type with ID " + nonExistintTypeDescription + " not found.",
        exception.getMessage());
  }

  /**
   * should add blind roller actuator to device when parameters are valid.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldAddActuatorToDevice_whenParametersAreValidBlindRoller() throws InstantiationException {
    // Arrange
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    Device device = loadDevice(roomID);

    TypeDescription typeDescription = new TypeDescription("BlindRoller");
    UnitID unit = new UnitID("Percent");
    ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
    ActuatorTypeServiceImpl.addActuatorType(actuatorType);

    String modelPath = "smart_home.domain.actuator.blind_roller_actuator.BlindRollerActuator";
    String actuatorName = "Actuator";

    int expected = 1;

    actuatorTypeAssembler.domainToDTO(actuatorType);

    IActuatorDataDTO actuatorDataDTO =
        new ActuatorDataGenericDTOImp(
            device.getID().toString(), modelPath, actuatorName, actuatorType.getID().getID());

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);
    // Act
    ActuatorDTO actuatorDTO = controller.addActuatorToDevice(actuatorDataDTO);
    int result = actuatorRepository.findAll().size();

    // Assert
    assertNotNull(actuatorDTO);
    assertEquals(expected, result);
  }

  /**
   * Should add set integer actuator to device when parameters are valid.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldAddActuatorToDevice_whenParametersAreValidSetIntegerActuator()
      throws InstantiationException {
    // Arrange
    String lowerLimit = "1";
    String upperLimit = "100";
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    Device device = loadDevice(roomID);

    TypeDescription typeDescription = new TypeDescription("SetInteger");
    UnitID unit = new UnitID("Percent");
    ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
    ActuatorTypeServiceImpl.addActuatorType(actuatorType);

    String modelPath = "smart_home.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorName = "Actuator";


    int expected = 1;

    actuatorTypeAssembler.domainToDTO(actuatorType);

    IActuatorDataDTO actuatorDataDTO =
        new ActuatorDataWithIntegerLimitsDTOImp(
            device.getID().toString(), modelPath, actuatorName, actuatorType.getID().getID(), lowerLimit, upperLimit);
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);
    // Act
    ActuatorDTO actuatorDTO = controller.addActuatorToDevice(actuatorDataDTO);
    int result = actuatorRepository.findAll().size();

    // Assert
    assertNotNull(actuatorDTO);
    assertEquals(expected, result);
  }

  /**
   * Should add set decimal actuator to device when parameters are valid.
   *
   * @throws InstantiationException exception
   */

    @Test
    void shouldAddActuatorToDevice_whenParametersAreValidSetDecimalActuator()
        throws InstantiationException {
      // Arrange
      String lowerLimit = "1.1";
      String upperLimit = "99.8";
      LoadModelsAndUnit loadModelsAndUnit =
          new LoadModelsAndUnit(
              sensorModelRepository,
              actuatorModelRepository,
              unitRepository,
              sensorModelFactory,
              actuatorModelFactory,
              unitFactory);

      loadHouseAndRoom();
      List<Room> rooms = roomRepository.findAll();
      RoomID roomID = rooms.get(0).getID();
      Device device = loadDevice(roomID);

      TypeDescription typeDescription = new TypeDescription("SetDecimal");
      UnitID unit = new UnitID("Percent");
      ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
      ActuatorTypeServiceImpl.addActuatorType(actuatorType);

      String modelPath = "smart_home.domain.actuator.set_decimal_actuator.SetDecimalActuator";
      String actuatorName = "Actuator";

      int expected = 1;

      actuatorTypeAssembler.domainToDTO(actuatorType);

      IActuatorDataDTO actuatorDataDTO =
          new ActuatorDataWithDecimalLimitsDTOImp(
              device.getID().toString(), modelPath, actuatorName, actuatorType.getID().getID(), lowerLimit, upperLimit);
      AddActuatorToDeviceController controller =
          new AddActuatorToDeviceController(
              roomServiceImpl,
              roomAssembler,
              deviceServiceImpl,
              deviceAssembler,
              actuatorModelServiceImpl,
              actuatorModelAssembler,
              ActuatorTypeServiceImpl,
              actuatorTypeAssembler,
              actuatorAssembler,
              actuatorService);
      // Act
      ActuatorDTO actuatorDTO = controller.addActuatorToDevice(actuatorDataDTO);
      int result = actuatorRepository.findAll().size();

      // Assert
      assertNotNull(actuatorDTO);
      assertEquals(expected, result);
    }

  /**
   * Should add set integer actuator to device when parameters are valid.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldAddActuatorToDevice_whenParametersAreValidSwitchActuator()
      throws InstantiationException {
    // Arrange
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    Device device = loadDevice(roomID);

    TypeDescription typeDescription = new TypeDescription("Switch");
    UnitID unit = new UnitID("Percent");
    ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
    ActuatorTypeServiceImpl.addActuatorType(actuatorType);

    String modelPath = "smart_home.domain.actuator.switch_actuator.SwitchActuator";
    String actuatorName = "Actuator";

    int expected = 1;

    actuatorTypeAssembler.domainToDTO(actuatorType);

    IActuatorDataDTO actuatorDataDTO =
        new ActuatorDataGenericDTOImp(
            device.getID().toString(), modelPath, actuatorName, actuatorType.getID().getID());
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);
    // Act
    ActuatorDTO actuatorDTO = controller.addActuatorToDevice(actuatorDataDTO);
    int result = actuatorRepository.findAll().size();

    // Assert
    assertNotNull(actuatorDTO);
    assertEquals(expected, result);
  }

  /**
   * Should throw exception when actuator data DTO is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorDataDTOIsNull()
      throws InstantiationException {
    // Arrange
    LoadModelsAndUnit loadModelsAndUnit =
        new LoadModelsAndUnit(
            sensorModelRepository,
            actuatorModelRepository,
            unitRepository,
            sensorModelFactory,
            actuatorModelFactory,
            unitFactory);

    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    loadDevice(roomID);

    TypeDescription typeDescription = new TypeDescription("Switch");
    UnitID unit = new UnitID("Percent");
    ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
    ActuatorTypeServiceImpl.addActuatorType(actuatorType);

    actuatorTypeAssembler.domainToDTO(actuatorType);

    IActuatorDataDTO actuatorDataDTO = null;
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            ActuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    String expectedMessage = "Actuator data DTO is required";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> controller.addActuatorToDevice(actuatorDataDTO));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }
}
