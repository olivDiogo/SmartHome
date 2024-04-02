package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.DeviceDTO;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.domain.Device.ImpDeviceFactory;
import SmartHomeDDD.assembler.DeviceAssembler;
import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.domain.House.ImpHouseFactory;
import SmartHomeDDD.domain.Room.ImpRoomFactory;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.service.DeviceService;
import SmartHomeDDD.service.RoomService;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeactivateDeviceControllerTest {

    /**
     * Test to verify that the US08DeactivateDevice constructor returns a non-null object.
     */
    @Test
    void shouldReturnNotNull_WhenUS08DeactivateDeviceIsConstructed() {
        // Arrange
        DeactivateDeviceController deactivateDeviceController;
        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        RoomRepository roomRepository = new RoomRepository();
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        // Act & Assert
        new DeactivateDeviceController(deviceService, deviceAssembler);
    }

    /**
     * Test to verify that the requestAllDevices method returns all devices.
     */
    @Test
    void shouldReturnAllDevices_WhenRequestAllDevicesIsCalled() {
        // Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        RoomRepository roomRepository = new RoomRepository();
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        ImpHouseFactory houseFactory = new ImpHouseFactory();
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceService, deviceAssembler);

        // Add a house
        Address address = new Address("street","1");
        ZipCode zipCode = new ZipCode(1234,567);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, zipCode, gps);
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
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        RoomRepository roomRepository = new RoomRepository();
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        ImpHouseFactory houseFactory = new ImpHouseFactory();
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceService, deviceAssembler);

        // Add a house
        Address address = new Address("street","1");
        ZipCode zipCode = new ZipCode(1234,567);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, zipCode, gps);
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
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        RoomRepository roomRepository = new RoomRepository();
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        ImpHouseFactory houseFactory = new ImpHouseFactory();
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceService, deviceAssembler);

        // Add a house
        Address address = new Address("street","1");
        ZipCode zipCode = new ZipCode(1234,567);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, zipCode, gps);
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
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        RoomRepository roomRepository = new RoomRepository();
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        ImpHouseFactory houseFactory = new ImpHouseFactory();
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceService, deviceAssembler);

        // Add a house
        Address address = new Address("street","1");
        ZipCode zipCode = new ZipCode(1234,567);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, zipCode, gps);
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