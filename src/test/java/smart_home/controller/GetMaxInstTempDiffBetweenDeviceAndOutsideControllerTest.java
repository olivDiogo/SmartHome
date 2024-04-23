package smart_home.controller;

import org.junit.jupiter.api.Test;
import smart_home.domain.device.Device;
import smart_home.domain.device.DeviceFactoryImpl;
import smart_home.domain.device_type.DeviceType;
import smart_home.domain.device_type.DeviceTypeFactoryImpl;
import smart_home.domain.device_type.IDeviceTypeFactory;
import smart_home.domain.house.House;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.domain.room.Room;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.dto.DeviceDTO;
import smart_home.dto.RoomDTO;
import smart_home.mapper.DeviceAssembler;
import smart_home.mapper.RoomAssembler;
import smart_home.persistence.mem.*;
import smart_home.service.*;
import smart_home.value_object.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GetMaxInstTempDiffBetweenDeviceAndOutsideControllerTest {

    /**
     * Test to check if the constructor of the GetMaxInstTempDiffBetweenDeviceAndOutsideController class is instantiated correctly.
     */
    @Test
    void shouldInstantiateGetMaxInstTempDiffBetweenDeviceAndOutsideController() {
        //Arrange
        LogRepository logRepository = new LogRepository();
        LogServiceImpl logService = new LogServiceImpl(logRepository);

        //Act
        GetMaxInstTempDiffBetweenDeviceAndOutsideController getMaxInstTempDiffBetweenDeviceAndOutsideController = new GetMaxInstTempDiffBetweenDeviceAndOutsideController(logService, logRepository);

        //Assert
        assertNotNull(getMaxInstTempDiffBetweenDeviceAndOutsideController);
    }

    /**
     * Test to check if the constructor of the GetMaxInstTempDiffBetweenDeviceAndOutsideController class throws an exception when the log service is null.
     */
    @Test
    void shouldThrowException_WhenLogServiceIsNull () {
        //Arrange
        LogServiceImpl logService = null;
        LogRepository logRepository = new LogRepository();

        String expectedMessage = "Log Service is required";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetMaxInstTempDiffBetweenDeviceAndOutsideController(logService, logRepository));

        String result = exception.getMessage();

        assertEquals(expectedMessage, result);
    }


//    @Test
//    void shouldReturnEmptyDeviceListByTypeDescription_WhenGetDevicesByTypeDescriptionIsCalledWithZeroTempDevices() {
//        //Arrange
//        DeviceRepository deviceRepository = new DeviceRepository();
//        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
//        RoomRepository roomRepository = new RoomRepository();
//        DeviceServiceImpl deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
//        DeviceAssembler deviceAssembler = new DeviceAssembler();
//
//        LogRepository logRepository = new LogRepository();
//        LogServiceImpl logService = new LogServiceImpl(logRepository);
//
//        HouseRepository houseRepository = new HouseRepository();
//        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
//        RoomAssembler roomAssembler = new RoomAssembler();
//
//        RoomServiceImpl roomService = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);
//
//        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
//        IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
//        DeviceTypeServiceImpl deviceTypeService = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);
//
//        DeviceTypeFactoryImpl impDeviceTypeFactory = new DeviceTypeFactoryImpl();
//        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
//        HouseServiceImpl houseService = new HouseServiceImpl (houseFactory, houseRepository);
//        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
//
//        GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(deviceService, deviceAssembler, deviceTypeService);
//        GetMaxInstTempDiffBetweenDeviceAndOutsideController getMaxInstTempDiffBetweenDeviceAndOutsideController = new GetMaxInstTempDiffBetweenDeviceAndOutsideController(logService, logRepository);
//
//        /* Create a house */
//        String street = "Rua Do Isep";
//        String doorNumber = "122A";
//        String countryCode = "PT";
//        String postalCode = "4000-007";
//
//        Address newAddress = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
//
//        double latitude = 41.178;
//        double longitude = -8.608;
//        GPS newGPS = new GPS(latitude, longitude);
//
//        House house = houseService.addHouse(newAddress, newGPS);
//
//        /* Create a room */
//        String strRoomName = "Bedroom";
//        RoomName roomName = new RoomName(strRoomName);
//        Dimension dimension = new Dimension(2, 2, 2);
//        RoomFloor roomFloor = new RoomFloor(1);
//        HouseID houseID = house.getID();
//
//        Room room = roomService.addRoom(houseID, roomName, dimension, roomFloor);
//
//        /* Create and save devices */
//        RoomID roomID = room.getID();
//
//        String name1 = "Light1";
//        String name2 = "Light2";
//        DeviceName deviceName1 = new DeviceName(name1);
//        DeviceName deviceName2 = new DeviceName(name2);
//        DeviceStatus deviceStatus = new DeviceStatus(true);
//        String strDeviceTypeID = "Bedroom Light";
//        TypeDescription deviceTypeDescription = new TypeDescription(strDeviceTypeID);
//
//        String strDeviceTypeID2 = "Humidty";
//        TypeDescription deviceTypeDescription2 = new TypeDescription(strDeviceTypeID2);
//
//        DeviceType deviceType = impDeviceTypeFactory.createDeviceType(deviceTypeDescription);
//        DeviceType deviceType2 = impDeviceTypeFactory.createDeviceType(deviceTypeDescription2);
//
//        deviceTypeRepository.save(deviceType);
//        deviceTypeRepository.save(deviceType2);
//
//        Device device1 = deviceService.addDevice(roomID, deviceName1, deviceStatus, deviceType.getID());
//        Device device2 = deviceService.addDevice(roomID, deviceName2, deviceStatus, deviceType2.getID());
//
//
//
//        /* Get map of devices grouped by functionality */
//        Map<DeviceType, List<DeviceDTO>> map = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();
//
//        /*Get list of devices of temperature */
//        List<DeviceDTO> devicesTemperature = getMaxInstTempDiffBetweenDeviceAndOutsideController.getDevicesByTypeDescription(map);
//
//        int expectedListSize = 0;
//
//        //Act
//        int result = devicesTemperature.size();
//
//        //
//        assertEquals(expectedListSize,result);
//
//    }

    @Test
    void shouldReturnSpecificTemperatureDevicesFromARoom_WhenGetDevicesByTypeDescriptionIsCalled () {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        DeviceServiceImpl deviceService = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        LogRepository logRepository = new LogRepository();
        LogServiceImpl logService = new LogServiceImpl(logRepository);

        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
        IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
        DeviceTypeServiceImpl deviceTypeService = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        HouseRepository houseRepository = new HouseRepository();

        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        RoomServiceImpl roomService = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);

        DeviceTypeFactoryImpl impDeviceTypeFactory = new DeviceTypeFactoryImpl();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseServiceImpl houseService = new HouseServiceImpl (houseFactory, houseRepository);
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();

        GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(roomService, roomAssembler);
        GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(deviceService, deviceAssembler, deviceTypeService);
        GetMaxInstTempDiffBetweenDeviceAndOutsideController getMaxInstTempDiffBetweenDeviceAndOutsideController = new GetMaxInstTempDiffBetweenDeviceAndOutsideController(logService, logRepository);

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

        Device device1 = deviceService.addDevice(roomID, deviceName1, deviceStatus, deviceType.getID());
        Device device2 = deviceService.addDevice(roomID, deviceName2, deviceStatus, deviceType2.getID());
        Device device3 = deviceService.addDevice(roomID, deviceName2, deviceStatus, deviceType2.getID());

        /* Get list of Rooms */
        List<RoomDTO> rooms = getListOfRoomsController.getRooms();

        /* Get map of devices grouped by functionality */
        Map<DeviceType, List<DeviceDTO>> map = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();

        int expectedListSize = 2;

        /* get list from a room */
        RoomDTO roomDTO = rooms.get(0);

        List<DeviceDTO> devicesTemperature = getMaxInstTempDiffBetweenDeviceAndOutsideController.getDevicesByTypeDescription(map, roomDTO);

        //Act
        int result = devicesTemperature.size();

        //Assert
        assertEquals(expectedListSize,result);

    }

}