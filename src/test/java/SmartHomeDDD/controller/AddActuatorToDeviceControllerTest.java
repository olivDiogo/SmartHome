package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.*;
import SmartHomeDDD.assembler.*;
import SmartHomeDDD.domain.Actuator.ImpActuatorFactory;
import SmartHomeDDD.domain.ActuatorModel.ActuatorModel;
import SmartHomeDDD.domain.ActuatorModel.ImpActuatorModelFactory;
import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import SmartHomeDDD.domain.ActuatorType.ImpActuatorTypeFactory;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.domain.Device.ImpDeviceFactory;
import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.domain.House.ImpHouseFactory;
import SmartHomeDDD.domain.Room.ImpRoomFactory;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.domain.Unit.ImpUnitFactory;
import SmartHomeDDD.repository.*;
import SmartHomeDDD.service.*;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddActuatorToDeviceControllerTest {
  RoomRepository roomRepository = new RoomRepository();
  ImpRoomFactory roomFactory = new ImpRoomFactory();
  RoomAssembler roomAssembler = new RoomAssembler();
  HouseRepository houseRepository = new HouseRepository();
  UnitRepository unitRepository = new UnitRepository();
  ImpUnitFactory unitFactory = new ImpUnitFactory();
  ActuatorRepository actuatorRepository = new ActuatorRepository();
  ImpActuatorFactory actuatorFactory = new ImpActuatorFactory();
  ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
  ImpActuatorTypeFactory actuatorTypeFactory = new ImpActuatorTypeFactory();
  ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
  ImpActuatorModelFactory actuatorModelFactory = new ImpActuatorModelFactory();
  DeviceRepository deviceRepository = new DeviceRepository();
  ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
  ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();
  RoomService roomService =
      new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
  ActuatorModelService actuatorModelService =
      new ActuatorModelService(actuatorModelRepository, actuatorModelFactory);
  ActuatorTypeService actuatorTypeService =
      new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, unitRepository);
  ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();
  ActuatorAssembler actuatorAssembler = new ActuatorAssembler();
  ActuatorService actuatorService =
      new ActuatorService(actuatorRepository, actuatorFactory, deviceRepository);
  DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
  DeviceAssembler deviceAssembler = new DeviceAssembler();
  PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
  ImpHouseFactory houseFactory = new ImpHouseFactory();
  HouseService houseService = new HouseService(houseFactory, houseRepository);

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
    House house = houseService.addHouse(newAddress, newGPS);
    HouseID houseID = house.getID();
    Dimension dimension = new Dimension(width, length, height);
    RoomFloor roomFloor = new RoomFloor(floor);
    roomService.addRoom(houseID, roomName1, dimension, roomFloor);
  }

  private Device loadDevice(RoomID roomID) {
    String name1 = "Lampada";
    String nameDevice = "Ar Condicionado";
    DeviceName deviceName = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(nameDevice);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");
    deviceService.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);
    deviceService.addDevice(roomID, deviceName2, deviceStatus, deviceTypeID);
    List<Device> devices = deviceService.getDevicesByRoomId(roomID);
    return devices.get(0);
  }

  /** Test to check if the AddActuatorToDeviceController is being created correctly. */
  @Test
  void shouldCreateAddActuatorToDeviceController() throws InstantiationException {
    // Assert
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

    // Act
    try {
      new AddActuatorToDeviceController(
          null,
          roomAssembler,
          deviceService,
          deviceAssembler,
          actuatorModelService,
          actuatorModelAssembler,
          actuatorTypeService,
          actuatorTypeAssembler,
          actuatorAssembler,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid room service.", e.getMessage());
    }
  }

  @Test
  void shouldThrowException_whenRoomAssemblerIsNull() throws InstantiationException {
    // Assert
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
    // Act
    try {
      new AddActuatorToDeviceController(
          roomService,
          null,
          deviceService,
          deviceAssembler,
          actuatorModelService,
          actuatorModelAssembler,
          actuatorTypeService,
          actuatorTypeAssembler,
          actuatorAssembler,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid room assembler.", e.getMessage());
    }
  }

  @Test
  void shouldThrowException_whenDeviceServiceIsNull() throws InstantiationException {
    // Assert
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
    // Act
    try {
      new AddActuatorToDeviceController(
          roomService,
          roomAssembler,
          null,
          deviceAssembler,
          actuatorModelService,
          actuatorModelAssembler,
          actuatorTypeService,
          actuatorTypeAssembler,
          actuatorAssembler,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid device service.", e.getMessage());
    }
  }

  @Test
  void shouldThrowException_whenDeviceAssemblerIsNull() throws InstantiationException {
    // Assert
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
    // Act
    try {
      new AddActuatorToDeviceController(
          roomService,
          roomAssembler,
          deviceService,
          null,
          actuatorModelService,
          actuatorModelAssembler,
          actuatorTypeService,
          actuatorTypeAssembler,
          actuatorAssembler,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid device assembler.", e.getMessage());
    }
  }

  @Test
  void shouldThrowException_whenSensorModelServiceIsNull() throws InstantiationException {
    // Assert
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
    // Act
    try {
      new AddActuatorToDeviceController(
          roomService,
          roomAssembler,
          deviceService,
          deviceAssembler,
          null,
          actuatorModelAssembler,
          actuatorTypeService,
          actuatorTypeAssembler,
          actuatorAssembler,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid actuator model service.", e.getMessage());
    }
  }

  @Test
  void shouldThrowException_whenSensorModelAssemblerIsNull() throws InstantiationException {
    // Assert
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
    // Act
    try {
      new AddActuatorToDeviceController(
          roomService,
          roomAssembler,
          deviceService,
          deviceAssembler,
          actuatorModelService,
          null,
          actuatorTypeService,
          actuatorTypeAssembler,
          actuatorAssembler,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid actuator model assembler.", e.getMessage());
    }
  }

  @Test
  void shouldThrowException_whenConfigurationServiceIsNull() {
    // Act
    try {
      new AddActuatorToDeviceController(
          roomService,
          roomAssembler,
          deviceService,
          deviceAssembler,
          actuatorModelService,
          actuatorModelAssembler,
          actuatorTypeService,
          actuatorTypeAssembler,
          actuatorAssembler,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid actuator type service.", e.getMessage());
    }
  }

  @Test
  void shouldThrowException_whenSensorTypeServiceIsNull() throws InstantiationException {
    // Assert
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
    // Act
    try {
      new AddActuatorToDeviceController(
          roomService,
          roomAssembler,
          deviceService,
          deviceAssembler,
          actuatorModelService,
          actuatorModelAssembler,
          null,
          actuatorTypeAssembler,
          actuatorAssembler,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid actuator type service.", e.getMessage());
    }
  }

  @Test
  void shouldThrowException_whenSensorTypeAssemblerIsNull() throws InstantiationException {
    // Assert
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
    // Act
    try {
      new AddActuatorToDeviceController(
          roomService,
          roomAssembler,
          deviceService,
          deviceAssembler,
          actuatorModelService,
          actuatorModelAssembler,
          actuatorTypeService,
          null,
          actuatorAssembler,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid actuator type assembler.", e.getMessage());
    }
  }

  @Test
  void shouldThrowException_whenSensorAssemblerIsNull() throws InstantiationException {
    // Assert
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
    // Act
    try {
      new AddActuatorToDeviceController(
          roomService,
          roomAssembler,
          deviceService,
          deviceAssembler,
          actuatorModelService,
          actuatorModelAssembler,
          actuatorTypeService,
          actuatorTypeAssembler,
          null,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid actuator assembler.", e.getMessage());
    }
  }

  @Test
  void shouldThrowException_whenSensorServiceIsNull() throws InstantiationException {
    // Assert
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
    // Act
    try {
      new AddActuatorToDeviceController(
          roomService,
          roomAssembler,
          deviceService,
          deviceAssembler,
          actuatorModelService,
          actuatorModelAssembler,
          actuatorTypeService,
          actuatorTypeAssembler,
          actuatorAssembler,
          null);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid actuator service.", e.getMessage());
    }
  }

  @Test
  void shouldReturnEmptyList_whenThereAreNoRooms() throws InstantiationException {
    // Arrange
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomService,
            roomAssembler,
            deviceService,
            deviceAssembler,
            actuatorModelService,
            actuatorModelAssembler,
            actuatorTypeService,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    // Act
    List<RoomDTO> result = controller.getRooms();

    // Assert
    assertTrue(result.isEmpty());
  }

  @Test
  void shouldGetRoomsFromHouse_WhenGivenValidHouseID() throws InstantiationException {
    // Arrange
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomService,
            roomAssembler,
            deviceService,
            deviceAssembler,
            actuatorModelService,
            actuatorModelAssembler,
            actuatorTypeService,
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

  @Test
  void shouldThrowException_WhenRoomIDDoesNotExistInRepository()
      throws InstantiationException {
    // Arrange
    // TODO:
  }

  @Test
  void shouldGetDevicesFromRoom_WhenParametersAreValid() throws InstantiationException {
    // Arrange
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomService,
            roomAssembler,
            deviceService,
            deviceAssembler,
            actuatorModelService,
            actuatorModelAssembler,
            actuatorTypeService,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);
    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    loadDevice(roomID);
    List<Device> devices = deviceService.getDevicesByRoomId(roomID);
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

  @Test
  void shouldGetAvailableActuatorTypesList() throws InstantiationException{
    // Arrange
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

    TypeDescription typeDescription = new TypeDescription("BlindRoller");
    UnitID unit = new UnitID("Percent");
    ActuatorType actuatorType = actuatorTypeService.createActuatorType(typeDescription, unit);
    actuatorTypeService.saveActuatorType(actuatorType);

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomService,
            roomAssembler,
            deviceService,
            deviceAssembler,
            actuatorModelService,
            actuatorModelAssembler,
            actuatorTypeService,
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

  @Test
  void shouldGetAvailableActuatorModelsList() throws InstantiationException {
    // Arrange
    ActuatorConfigurationService configurationService =
        new ActuatorConfigurationService(
            actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

    TypeDescription typeDescription = new TypeDescription("BlindRoller");
    UnitID unit = new UnitID("Percent");
    ActuatorType actuatorType = actuatorTypeService.createActuatorType(typeDescription, unit);
    actuatorTypeService.saveActuatorType(actuatorType);

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomService,
            roomAssembler,
            deviceService,
            deviceAssembler,
            actuatorModelService,
            actuatorModelAssembler,
            actuatorTypeService,
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

  @Test
  void shouldThrowExceptionWhenSensorModelRepositoryIsEmpty() throws InstantiationException {}

  @Test
  void shouldThrowExceptionWhenSensorTypeRepositoryIsEmpty() throws InstantiationException {}

  @Test
  void shouldReturnListOfSensorTypes_whenSensorTypeRepositoryIsNotEmpty()
          throws InstantiationException{}
  @Test
  void shouldReturnEmptyList_whenThereAreNoSensorTypes() throws InstantiationException {}

  @Test
  void shouldAddSensorToDevice_whenParametersAreValid() throws InstantiationException {
    // Arrange
    ActuatorConfigurationService configurationService =
            new ActuatorConfigurationService(
                    actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    Device device = loadDevice(roomID);

    TypeDescription typeDescription = new TypeDescription("Switch");
    UnitID unit = new UnitID("Percent");
    ActuatorType actuatorType = actuatorTypeService.createActuatorType(typeDescription, unit);
    actuatorTypeService.saveActuatorType(actuatorType);

    String modelPath = "SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuator";
    String actuatorName = "Actuator";

    actuatorTypeAssembler.domainToDTO(actuatorType);

    ActuatorDataDTO actuatorDataDTO =
            new ActuatorDataDTO(
                    device.getID().getId(), modelPath, actuatorName, actuatorType.getID().getId());
    AddActuatorToDeviceController controller =
            new AddActuatorToDeviceController(
                    roomService,
                    roomAssembler,
                    deviceService,
                    deviceAssembler,
                    actuatorModelService,
                    actuatorModelAssembler,
                    actuatorTypeService,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService);
    // Act
    ActuatorDTO actuatorDTO = controller.addActuatorToDevice(actuatorDataDTO);

    // Assert
    assertEquals(actuatorDTO.deviceID, actuatorDTO.deviceID);
  }



}
