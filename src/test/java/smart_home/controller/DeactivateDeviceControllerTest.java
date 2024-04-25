package smart_home.controller;

import org.junit.jupiter.api.Test;
import smart_home.ddd.IAssembler;
import smart_home.domain.device.IDeviceFactory;
import smart_home.domain.house.IHouseFactory;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.domain.repository.IHouseRepository;
import smart_home.domain.repository.IRoomRepository;
import smart_home.domain.room.IRoomFactory;
import smart_home.domain.service.IDeviceService;
import smart_home.domain.service.IRoomService;
import smart_home.dto.RoomDTO;
import smart_home.mapper.DeviceAssembler;
import smart_home.mapper.RoomAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.device.DeviceFactoryImpl;
import smart_home.domain.house.House;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.domain.room.Room;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.dto.DeviceDTO;
import smart_home.service.DeviceServiceImpl;
import smart_home.service.RoomServiceImpl;
import smart_home.value_object.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeactivateDeviceControllerTest {

    /**
     * Test to verify that the US08DeactivateDevice constructor returns a non-null object.
     */
    @Test
    void shouldReturnNotNull_WhenUS08DeactivateDeviceIsConstructed() {
        // Arrange
        IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
        IDeviceFactory deviceFactory = new DeviceFactoryImpl();
        IRoomRepository roomRepository = mock(IRoomRepository.class);
        IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

        // Act
        DeactivateDeviceController result = new DeactivateDeviceController(deviceServiceImpl, deviceAssembler);

        // Assert
        assertNotEquals(null, result);
    }

    /**
     * Test to verify that the US08DeactivateDevice constructor throws an exception when the DeviceService is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenUS08DeactivateDeviceIsConstructedAndDeviceServiceIsNull() {
        // Arrange
      IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();

        // Act
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeactivateDeviceController(null, deviceAssembler);
        });

        // Assert
        assertEquals("DeviceService cannot be null.", exception.getMessage());
    }

/**
     * Test to verify that the US08DeactivateDevice constructor throws an exception when the DeviceAssembler is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenUS08DeactivateDeviceIsConstructedAndDeviceAssemblerIsNull() {
        // Arrange
      IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
      IDeviceFactory deviceFactory = new DeviceFactoryImpl();
      IRoomRepository roomRepository = mock(IRoomRepository.class);
      IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        // Act
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new DeactivateDeviceController(deviceServiceImpl, null);
        });

        // Assert
        assertEquals("DeviceAssembler cannot be null.", exception.getMessage());
    }

    /**
     * Test to verify that the requestAllDevices method returns all devices.
     */
    @Test
    void shouldReturnAllDevices_WhenRequestAllDevicesIsCalled() {
        // Arrange
        IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
        IDeviceFactory deviceFactory = new DeviceFactoryImpl();
        IRoomRepository roomRepository = mock(IRoomRepository.class);
        IRoomFactory roomFactory = new RoomFactoryImpl();
        IHouseRepository houseRepository = mock(IHouseRepository.class);
        IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
        IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
        IHouseFactory houseFactory = new HouseFactoryImpl();
        IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceServiceImpl, deviceAssembler);

        // Add a house
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";
        Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, gps);
        when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
        houseRepository.save(house);


        // Add a room
        HouseID houseID = house.getID();
        RoomName roomName = new RoomName("Living Room");
        Dimension dimension = new Dimension(10, 10, 10);
        RoomFloor roomFloor = new RoomFloor(1);
        Room room = roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor);
        when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));


        // Add a device
        DeviceName deviceName = new DeviceName("Light bulb");
        DeviceStatus deviceStatus = new DeviceStatus(false);
        DeviceTypeID deviceTypeID = new DeviceTypeID("1");
        Device device = deviceServiceImpl.addDevice(room.getID(), deviceName, deviceStatus, deviceTypeID);
        when(deviceRepository.findAll()).thenReturn(List.of(device));

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
      IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
      IDeviceFactory deviceFactory = new DeviceFactoryImpl();
      IRoomRepository roomRepository = mock(IRoomRepository.class);
      IHouseRepository houseRepository = mock(IHouseRepository.class);
      IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
      IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
      IHouseFactory houseFactory = new HouseFactoryImpl();
      IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
      DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceServiceImpl, deviceAssembler);

        // Add a house
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";
        Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, gps);
        when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
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
      IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
      IDeviceFactory deviceFactory = new DeviceFactoryImpl();
      IRoomRepository roomRepository = mock(IRoomRepository.class);
      IRoomFactory roomFactory = new RoomFactoryImpl();
      IHouseRepository houseRepository = mock(IHouseRepository.class);
      IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
      IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
      IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
      IHouseFactory houseFactory = new HouseFactoryImpl();
      IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
      DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceServiceImpl, deviceAssembler);

        // Add a house
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";
        Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, gps);
        when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
        houseRepository.save(house);

        // Add a room
        HouseID houseID = house.getID();
        RoomName roomName = new RoomName("Living Room");
        Dimension dimension = new Dimension(10, 10, 10);
        RoomFloor roomFloor = new RoomFloor(1);
        Room room = roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor);
        when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));

        // Add a device
        DeviceName deviceName = new DeviceName("Lightbulb");
        DeviceStatus deviceStatus = new DeviceStatus(false);
        DeviceTypeID deviceTypeID = new DeviceTypeID("1");
        Device device = deviceServiceImpl.addDevice(room.getID(), deviceName, deviceStatus, deviceTypeID);
        DeviceID deviceId = device.getID();

        when(deviceRepository.ofIdentity(deviceId)).thenReturn(Optional.of(device));

        DeviceDTO deviceDTO = new DeviceDTO(device.getID().toString(), room.getID().toString(), deviceName.toString(), deviceStatus.toString());
        // Act
        DeviceDTO deactivatedDevice = deactivateDeviceController.requestDeactivateDevice(deviceDTO);

        // Assert
        assertEquals("OFF", deactivatedDevice.deviceStatus);
    }

    /**
     * Test to verify that the requestDeactivateDevice method returns the device not found message.
     */
    @Test
    void shouldReturnDeviceNotFound_WhenRequestDeactivateDeviceIsCalledAndDeviceIsNotFound() {
        // Arrange
      IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
      IDeviceFactory deviceFactory = new DeviceFactoryImpl();
      IRoomRepository roomRepository = mock(IRoomRepository.class);
      IRoomFactory roomFactory = new RoomFactoryImpl();
      IHouseRepository houseRepository = mock(IHouseRepository.class);
      IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
      IDeviceService deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
      IAssembler<Device, DeviceDTO> deviceAssembler = new DeviceAssembler();
      IHouseFactory houseFactory = new HouseFactoryImpl();
      IPostalCodeFactory postalCodeFactory = new PostalCodeFactory();
      DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(deviceServiceImpl, deviceAssembler);

        // Add a house
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";
        Address address = new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
        GPS gps = new GPS(41.5514, -8.4221);
        House house = houseFactory.createHouse(address, gps);
        when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
        houseRepository.save(house);

        // Add a room
        HouseID houseID = house.getID();
        RoomName roomName = new RoomName("Living Room");
        Dimension dimension = new Dimension(10, 10, 10);
        RoomFloor roomFloor = new RoomFloor(1);
        Room room = roomServiceImpl.addRoom(houseID, roomName, dimension, roomFloor);
        when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));


        DeviceDTO deviceDTO = new DeviceDTO("does_not_exist", room.getID().toString(), "Lightbulb", "OFF");
        // Act
        DeviceDTO deactivatedDevice = deactivateDeviceController.requestDeactivateDevice(deviceDTO);

        // Assert
        assertEquals(deactivatedDevice.deviceID, "Device not found.");
    }
}