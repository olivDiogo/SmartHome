package smart_home.controller;

import org.junit.jupiter.api.Test;
import smart_home.ddd.IAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.device.DeviceFactoryImpl;
import smart_home.domain.device.IDeviceFactory;
import smart_home.domain.device_type.DeviceType;
import smart_home.domain.device_type.DeviceTypeFactoryImpl;
import smart_home.domain.device_type.IDeviceTypeFactory;
import smart_home.domain.house.House;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.domain.house.IHouseFactory;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.domain.repository.IDeviceTypeRepository;
import smart_home.domain.repository.IHouseRepository;
import smart_home.domain.repository.IRoomRepository;
import smart_home.domain.room.IRoomFactory;
import smart_home.domain.room.Room;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.IDeviceTypeService;
import smart_home.domain.service.IHouseService;
import smart_home.domain.service.IRoomService;
import smart_home.dto.DeviceDTO;
import smart_home.dto.RoomDTO;
import smart_home.mapper.DeviceAssembler;
import smart_home.mapper.RoomAssembler;
import smart_home.persistence.mem.DeviceRepository;
import smart_home.persistence.mem.DeviceTypeRepository;
import smart_home.persistence.mem.HouseRepository;
import smart_home.persistence.mem.RoomRepository;
import smart_home.service.DeviceServiceImpl;
import smart_home.service.DeviceTypeServiceImpl;
import smart_home.service.HouseServiceImpl;
import smart_home.service.RoomServiceImpl;
import smart_home.value_object.Address;
import smart_home.value_object.DeviceName;
import smart_home.value_object.DeviceStatus;
import smart_home.value_object.DeviceTypeID;
import smart_home.value_object.Dimension;
import smart_home.value_object.GPS;
import smart_home.value_object.HouseID;
import smart_home.value_object.IPostalCodeFactory;
import smart_home.value_object.PostalCodeFactory;
import smart_home.value_object.RoomFloor;
import smart_home.value_object.RoomID;
import smart_home.value_object.RoomName;
import smart_home.value_object.TypeDescription;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetDevicesByRoomAndTemperatureFunctionalityControllerTest {

  /**
   * Test to get devices with temperature functionality from a room
   */
  @Test
  void shouldReturnDevicesWithTemperatureFunctionalityFromARoom_WhenGetDevicesByRoomAndTypeDescriptionIsCalled() {
    //Arrange
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = new RoomRepository();
    IDeviceService deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    IDeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    IDeviceTypeService deviceTypeService = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

    IHouseRepository houseRepository = new HouseRepository();

    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IRoomService roomService = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceTypeFactory impDeviceTypeFactory = new DeviceTypeFactoryImpl();
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IHouseService houseService = new HouseServiceImpl (houseFactory, houseRepository);
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();

    GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomService, roomAssembler);
    GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(deviceService, deviceAssembler, deviceTypeService);
    GetDevicesByRoomAndTemperatureFunctionalityController getDevicesByRoomAndTemperatureFunctionalityController = new GetDevicesByRoomAndTemperatureFunctionalityController();

    /* Create a house */
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    /* Create a room */
    String strRoomName = "Bedroom";
    RoomName roomName = new RoomName(strRoomName);
    Dimension dimension = new Dimension(2, 2, 2);
    RoomFloor roomFloor = new RoomFloor(1);
    HouseID houseID = house.getID();

    Room room = roomService.addRoom(houseID, roomName, dimension, roomFloor);

    /* Create and save devices */
    RoomID roomID = room.getID();

    String name1 = "Light1";
    String name2 = "Light2";
    DeviceName deviceName1 = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(name2);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    String strDeviceTypeID = "Bedroom Light";
    TypeDescription deviceTypeDescription = new TypeDescription(strDeviceTypeID);

    String strDeviceTypeID2 = "Temperature";
    TypeDescription deviceTypeDescription2 = new TypeDescription(strDeviceTypeID2);

    DeviceType deviceType = impDeviceTypeFactory.createDeviceType(deviceTypeDescription);
    DeviceType deviceType2 = impDeviceTypeFactory.createDeviceType(deviceTypeDescription2);

    deviceTypeRepository.save(deviceType);
    deviceTypeRepository.save(deviceType2);

    deviceService.addDevice(roomID, deviceName1, deviceStatus, deviceType.getID());
    Device device1 = deviceService.addDevice(roomID, deviceName2, deviceStatus, deviceType2.getID());
    Device device2 = deviceService.addDevice(roomID, deviceName2, deviceStatus, deviceType2.getID());

    /* Get list of Rooms */
    List<RoomDTO> rooms = getListOfRoomsController.getRooms();

    /* Get map of devices grouped by functionality */
    Map<DeviceType, List<DeviceDTO>> map = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();

    /* get list from a room */
    RoomDTO roomDTO = rooms.get(0);


    DeviceDTO deviceDTO1 = deviceAssembler.domainToDTO(device1);
    DeviceDTO deviceDTO2 = deviceAssembler.domainToDTO(device2);
    List<DeviceDTO> expected = new ArrayList<>();
    expected.add(deviceDTO1);
    expected.add(deviceDTO2);

    //Act
    List<DeviceDTO> devicesTemperature = getDevicesByRoomAndTemperatureFunctionalityController.getDevicesByRoomAndTypeDescription(map, roomDTO, "Temperature");

    //Assert
    assertEquals(expected.toString(), devicesTemperature.toString());
  }

  /**
   * Test to get devices without temperature functionality from a room
   */
  @Test
  void shouldReturnEmptyDeviceListByTypeDescription_WhenGetDevicesByTypeDescriptionIsCalledWithZeroTempDevices() {
    //Arrange
    IDeviceRepository deviceRepository = new DeviceRepository();
    IDeviceFactory deviceFactory = new DeviceFactoryImpl();
    IRoomRepository roomRepository = new RoomRepository();
    IDeviceService deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

    IDeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
    IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
    IDeviceTypeService deviceTypeService = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

    IHouseRepository houseRepository = new HouseRepository();

    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IRoomService roomService = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    IDeviceTypeFactory impDeviceTypeFactory = new DeviceTypeFactoryImpl();
    IHouseFactory houseFactory = new HouseFactoryImpl();
    IHouseService houseService = new HouseServiceImpl (houseFactory, houseRepository);
    IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();

    GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(deviceService, deviceAssembler, deviceTypeService);
    GetDevicesByRoomAndTemperatureFunctionalityController getDevicesByRoomAndTemperatureController = new GetDevicesByRoomAndTemperatureFunctionalityController();

    /* Create a house */
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";

    Address newAddress = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);

    House house = houseService.addHouse(newAddress, newGPS);

    /* Create a room */
    String strRoomName = "Bedroom";
    RoomName roomName = new RoomName(strRoomName);
    Dimension dimension = new Dimension(2, 2, 2);
    RoomFloor roomFloor = new RoomFloor(1);
    HouseID houseID = house.getID();

    Room room = roomService.addRoom(houseID, roomName, dimension, roomFloor);

    RoomDTO roomDTO = roomAssembler.domainToDTO(room);

    /* Create and save devices */
    RoomID roomID = room.getID();

    String name1 = "Light1";
    String name2 = "Light2";
    DeviceName deviceName1 = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(name2);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    String strDeviceTypeID = "Bedroom Light";
    TypeDescription deviceTypeDescription = new TypeDescription(strDeviceTypeID);

    String strDeviceTypeID2 = "Humidty";
    TypeDescription deviceTypeDescription2 = new TypeDescription(strDeviceTypeID2);

    DeviceType deviceType = impDeviceTypeFactory.createDeviceType(deviceTypeDescription);
    DeviceType deviceType2 = impDeviceTypeFactory.createDeviceType(deviceTypeDescription2);

    deviceTypeRepository.save(deviceType);
    deviceTypeRepository.save(deviceType2);

    deviceService.addDevice(roomID, deviceName1, deviceStatus, deviceType.getID());
    deviceService.addDevice(roomID, deviceName2, deviceStatus, deviceType2.getID());


    /* Get map of devices grouped by functionality */
    Map<DeviceType, List<DeviceDTO>> map = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();

    int expectedListSize = 0;

    //Act
    List<DeviceDTO> devicesTemperature = getDevicesByRoomAndTemperatureController.getDevicesByRoomAndTypeDescription(map, roomDTO, "Temperature");

    //
    assertEquals(expectedListSize, devicesTemperature.size());

  }


}
