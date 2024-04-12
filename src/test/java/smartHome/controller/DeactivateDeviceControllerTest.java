package smartHome.controller;

import smartHome.dto.DeviceDTO;
import smartHome.assembler.DeviceAssembler;
import smartHome.assembler.RoomAssembler;
import smartHome.domain.device.Device;
import smartHome.domain.device.DeviceFactoryImpl;
import smartHome.domain.house.House;
import smartHome.domain.house.HouseFactoryImpl;
import smartHome.domain.room.RoomFactoryImpl;
import smartHome.domain.room.Room;
import smartHome.persistence.mem.DeviceRepository;
import smartHome.persistence.mem.HouseRepository;
import smartHome.persistence.mem.RoomRepository;
import smartHome.service.DeviceService;
import smartHome.service.RoomService;
import smartHome.valueObject.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeactivateDeviceControllerTest {

    /**
     * Test to verify that the US08DeactivateDevice constructor returns a non-null object.
     */
    @Test
    void shouldReturnNotNull_WhenUS08DeactivateDeviceIsConstructed() {
        // Arrange
        DeactivateDeviceController deactivateDeviceController;
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        // Act
        DeactivateDeviceController result = new DeactivateDeviceController(deviceService, deviceAssembler);

        // Assert
        assertNotEquals(null, result);
    }

    /**
     * Test to verify that the requestAllDevices method returns all devices.
     */
    @Test
    void shouldReturnAllDevices_WhenRequestAllDevicesIsCalled() {
        // Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceService, deviceAssembler);

        // Add a house
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";
        Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, gps);
        houseRepository.save(house);


        // Add a room
        HouseID houseID = house.getID();
        RoomName roomName = new RoomName("Living Room");
        Dimension dimension = new Dimension(10, 10, 10);
        RoomFloor roomFloor = new RoomFloor(1);
        Room room = roomService.addRoom(houseID, roomName, dimension, roomFloor);

        // Add a device
        DeviceName deviceName = new DeviceName("Light bulb");
        DeviceStatus deviceStatus = new DeviceStatus(false);
        DeviceTypeID deviceTypeID = new DeviceTypeID("1");
        Device device = deviceService.addDevice(room.getID(), deviceName, deviceStatus, deviceTypeID);

        // Act
        List<DeviceDTO> devices = deactivateDeviceController.requestAllDevices();

        // Assert
        assertEquals(devices.get(0).deviceID, device.getID().toString());
    }

    /**
     * Test to verify that the requestAllDevices method returns no devices.
     */
    @Test
    void shouldReturnNoDevices_WhenRequestAllDevicesIsCalled() {
        // Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceService, deviceAssembler);

        // Add a house
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";
        Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, gps);
        houseRepository.save(house);

        // Act
        List<DeviceDTO> devices = deactivateDeviceController.requestAllDevices();

        // Assert
        assertTrue(devices.isEmpty());
    }

    /**
     * Test to verify that the requestDeactivateDevice method returns the deactivated device.
     */
    @Test
    void shouldReturnDeactivatedDevice_WhenRequestDeactivateDeviceIsCalled() {
        // Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceService, deviceAssembler);

        // Add a house
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";
        Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, gps);
        houseRepository.save(house);

        // Add a room
        HouseID houseID = house.getID();
        RoomName roomName = new RoomName("Living Room");
        Dimension dimension = new Dimension(10, 10, 10);
        RoomFloor roomFloor = new RoomFloor(1);
        Room room = roomService.addRoom(houseID, roomName, dimension, roomFloor);

        // Add a device
        DeviceName deviceName = new DeviceName("Lightbulb");
        DeviceStatus deviceStatus = new DeviceStatus(false);
        DeviceTypeID deviceTypeID = new DeviceTypeID("1");
        Device device = deviceService.addDevice(room.getID(), deviceName, deviceStatus, deviceTypeID);

        DeviceDTO deviceDTO = new DeviceDTO(device.getID().toString(), room.getID().toString(), deviceName.toString(), deviceStatus.toString());
        // Act
        DeviceDTO deactivatedDevice = deactivateDeviceController.requestDeactivateDevice(deviceDTO);

        // Assert
        assertEquals(deactivatedDevice.deviceStatus.toString(), "OFF");
    }

    /**
     * Test to verify that the requestDeactivateDevice method returns the device not found message.
     */
    @Test
    void shouldReturnDeviceNotFound_WhenRequestDeactivateDeviceIsCalledAndDeviceIsNotFound() {
        // Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        RoomRepository roomRepository = new RoomRepository();
        RoomFactoryImpl roomFactory = new RoomFactoryImpl();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        HouseFactoryImpl houseFactory = new HouseFactoryImpl();
        PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceService, deviceAssembler);

        // Add a house
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";
        Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, gps);
        houseRepository.save(house);

        // Add a room
        HouseID houseID = house.getID();
        RoomName roomName = new RoomName("Living Room");
        Dimension dimension = new Dimension(10, 10, 10);
        RoomFloor roomFloor = new RoomFloor(1);
        Room room = roomService.addRoom(houseID, roomName, dimension, roomFloor);


        DeviceDTO deviceDTO = new DeviceDTO("does_not_exist", room.getID().toString(), "Lightbulb", "OFF");
        // Act
        DeviceDTO deactivatedDevice = deactivateDeviceController.requestDeactivateDevice(deviceDTO);

        // Assert
        assertEquals(deactivatedDevice.deviceID, "Device not found.");
    }
}