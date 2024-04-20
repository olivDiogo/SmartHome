package smart_home.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import smart_home.mapper.DeviceAssembler;
import smart_home.mapper.RoomAssembler;
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
import smart_home.persistence.mem.DeviceRepository;
import smart_home.persistence.mem.DeviceTypeRepository;
import smart_home.persistence.mem.HouseRepository;
import smart_home.persistence.mem.RoomRepository;
import smart_home.service.DeviceServiceImpl;
import smart_home.service.DeviceTypeServiceImpl;
import smart_home.service.HouseServiceImpl;
import smart_home.service.RoomServiceImpl;
import smart_home.value_object.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

 class GetListOfAllDevicesGroupedByFunctionalityControllerTest {
    /**
     * Test to ensure that GetListOfAllDevicesGroupedByFunctionality can be instantiated successfully.
     */
    @Test
    void shouldInstantiateGetListOfAllDevicesGroupedByFunctionality_whenConstructorArgumentsAreValid() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
        IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        //Act
        GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(deviceServiceImpl, deviceAssembler, deviceTypeServiceImpl);

        //Assert
        assertNotNull(getListOfAllDevicesGroupedByFunctionality);
    }

    /**
     * Test to ensure that GetListOfAllDevicesGroupedByFunctionality throws an IllegalArgumentException when the DeviceService is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGetListOfAllDevicesGroupedByFunctionalityIsCalledWithNullDeviceService() {
        //Arrange
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
        IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new GetListOfAllDevicesGroupedByFunctionalityController(null, deviceAssembler, deviceTypeServiceImpl), "DeviceService cannot be null.");
    }

    /**
     * Test to ensure that GetListOfAllDevicesGroupedByFunctionality throws an IllegalArgumentException when the DeviceAssembler is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGetListOfAllDevicesGroupedByFunctionalityIsCalledWithNullDeviceAssembler() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

        IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new GetListOfAllDevicesGroupedByFunctionalityController(deviceServiceImpl, null, deviceTypeServiceImpl), "DeviceAssembler cannot be null.");
    }

    /**
     * Test to ensure that GetListOfAllDevicesGroupedByFunctionality throws an IllegalArgumentException when the DeviceTypeRepository is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGetListOfAllDevicesGroupedByFunctionalityIsCalledWithNullDeviceTypeRepository() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new GetListOfAllDevicesGroupedByFunctionalityController(deviceServiceImpl, deviceAssembler, null), "DeviceTypeService cannot be null.");
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenGetDevicesDTOGroupedByFunctionalityIsCalledWithEmptyDevicesList() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

        IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(deviceServiceImpl, deviceAssembler, deviceTypeServiceImpl);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality(), "No devices found.");
    }

    /**
     * Test to ensure that GetDevicesDTOGroupedByFunctionality returns a map of devices grouped by functionality, when devices have different type.
     */
    @Test
    void shouldReturnMapOfDevicesGroupedByFunctionality_WhenGetDevicesDTOGroupedByFunctionalityIsCalledWithValidDevicesListAndDevicesHaveDifferentType() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
        IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        DeviceTypeFactoryImpl impDeviceTypeFactory = new DeviceTypeFactoryImpl();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseRepository houseRepository = new HouseRepository();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(deviceServiceImpl, deviceAssembler, deviceTypeServiceImpl);

        /* Create a house */
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";

        Address newAddress = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

        double latitude = 41.178;
        double longitude = -8.608;
        GPS newGPS = new GPS(latitude, longitude);

        House house = houseServiceImpl.addHouse(newAddress, newGPS);

        /* Create a room */
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);

        String strRoomName = "Bedroom";
        RoomName roomName = new RoomName(strRoomName);
        Dimension dimension = new Dimension(2, 2, 2);
        RoomFloor roomFloor = new RoomFloor(1);
        HouseID houseID = house.getID();

        Room room = roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor);

        /* Create and save devices */
        RoomID roomID = room.getID();

        String name = "Light";
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(true);
        String strDeviceTypeID1 = "Bedroom Light";
        String strDeviceTypeID2 = "Kitchen Light";
        TypeDescription deviceTypeDescription1 = new TypeDescription(strDeviceTypeID1);
        TypeDescription deviceTypeDescription2 = new TypeDescription(strDeviceTypeID2);

        DeviceType deviceType1 = impDeviceTypeFactory.createDeviceType(deviceTypeDescription1);
        DeviceType deviceType2 = impDeviceTypeFactory.createDeviceType(deviceTypeDescription2);

        deviceTypeRepository.save(deviceType1);
        deviceTypeRepository.save(deviceType2);

        Device device1 = deviceServiceImpl.addDevice(roomID, deviceName, deviceStatus, deviceType1.getID());
        Device device2 = deviceServiceImpl.addDevice(roomID, deviceName, deviceStatus, deviceType2.getID());

        //Act
        Map<DeviceType, List<DeviceDTO>> result = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();

        //Assert
        assertEquals(result.get(deviceType1).get(0).deviceID.toString(), device1.getID().getID());
        assertEquals(result.get(deviceType2).get(0).deviceID.toString(), device2.getID().getID());
    }

    /**
     * Test to ensure that GetDevicesDTOGroupedByFunctionality returns a map of devices grouped by functionality, when devices have the same type.
     */
    @Test
    void shouldReturnMapOfDevicesGroupedByFunctionality_WhenGetDevicesDTOGroupedByFunctionalityIsCalledWithValidDevicesListAndDevicesHaveSameType() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
        IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        DeviceTypeFactoryImpl impDeviceTypeFactory = new DeviceTypeFactoryImpl();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseRepository houseRepository = new HouseRepository();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(deviceServiceImpl, deviceAssembler, deviceTypeServiceImpl);

        /* Create a house */
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";

        Address newAddress = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

        double latitude = 41.178;
        double longitude = -8.608;
        GPS newGPS = new GPS(latitude, longitude);

        House house = houseServiceImpl.addHouse(newAddress, newGPS);

        /* Create a room */
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);

        String strRoomName = "Bedroom";
        RoomName roomName = new RoomName(strRoomName);
        Dimension dimension = new Dimension(2, 2, 2);
        RoomFloor roomFloor = new RoomFloor(1);
        HouseID houseID = house.getID();

        Room room = roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor);

        /* Create and save devices */
        RoomID roomID = room.getID();

        String name1 = "Light1";
        String name2 = "Light2";
        DeviceName deviceName1 = new DeviceName(name1);
        DeviceName deviceName2 = new DeviceName(name2);
        DeviceStatus deviceStatus = new DeviceStatus(true);
        String strDeviceTypeID = "Bedroom Light";
        TypeDescription deviceTypeDescription = new TypeDescription(strDeviceTypeID);

        DeviceType deviceType = impDeviceTypeFactory.createDeviceType(deviceTypeDescription);

        deviceTypeRepository.save(deviceType);

        Device device1 = deviceServiceImpl.addDevice(roomID, deviceName1, deviceStatus, deviceType.getID());
        Device device2 = deviceServiceImpl.addDevice(roomID, deviceName2, deviceStatus, deviceType.getID());

        //Act
        Map<DeviceType, List<DeviceDTO>> result = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();

        //Assert
        assertEquals(result.get(deviceType).get(0).deviceID.toString(), device1.getID().getID());
        assertEquals(result.get(deviceType).get(1).deviceID.toString(), device2.getID().getID());

    }

    /**
     * Test to verify if an exception is thrown if the device type is not found.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenGetDevicesDTOGroupedByFunctionalityIsCalledWithInvalidDeviceType() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
        IDeviceTypeFactory deviceTypeFactory = new DeviceTypeFactoryImpl();
        DeviceTypeServiceImpl deviceTypeServiceImpl = new DeviceTypeServiceImpl(deviceTypeRepository, deviceTypeFactory);

        DeviceTypeFactoryImpl impDeviceTypeFactory = new DeviceTypeFactoryImpl();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        HouseRepository houseRepository = new HouseRepository();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        GetListOfAllDevicesGroupedByFunctionalityController getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionalityController(deviceServiceImpl, deviceAssembler, deviceTypeServiceImpl);

        /* Create a house */
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";

        Address newAddress = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);

        double latitude = 41.178;
        double longitude = -8.608;
        GPS newGPS = new GPS(latitude, longitude);

        House house = houseServiceImpl.addHouse(newAddress, newGPS);

        /* Create a room */
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);

        String strRoomName = "Bedroom";
        RoomName roomName = new RoomName(strRoomName);
        Dimension dimension = new Dimension(2, 2, 2);
        RoomFloor roomFloor = new RoomFloor(1);
        HouseID houseID = house.getID();

        Room room = roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor);

        /* Create and save devices */
        RoomID roomID = room.getID();

        String name = "Light";
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(true);

        String strDeviceTypeID = "Wrong Type Description";
        TypeDescription deviceTypeDescription = new TypeDescription(strDeviceTypeID);

        DeviceType deviceType = mock(DeviceType.class);
        when(deviceType.getID()).thenReturn(new DeviceTypeID("Wrong Type ID"));
        when(deviceType.getDescription()).thenReturn(deviceTypeDescription);

        Device device = deviceServiceImpl.addDevice(roomID, deviceName, deviceStatus, deviceType.getID());

        String expectedMessage = "DeviceType not found.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality());

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
