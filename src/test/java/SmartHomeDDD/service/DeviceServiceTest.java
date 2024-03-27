package SmartHomeDDD.service;

import SmartHomeDDD.valueObject.DeviceID;
import SmartHomeDDD.valueObject.DeviceName;
import SmartHomeDDD.valueObject.DeviceStatus;
import SmartHomeDDD.valueObject.RoomID;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.domain.Device.DeviceFactory;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.repository.RoomRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceServiceTest {

    /**
     * Test the constructor of the DeviceService class.
     */
    @Test
    public void shouldInstantiateDeviceService_WhenGivenValidParameters() {
        // Arrange
        DeviceService deviceService;
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        // Act
        new DeviceService(deviceRepository, deviceFactory, roomRepository);
    }

    /**
     * Test the addDevice method of the DeviceService class with a valid roomID, deviceName and deviceStatus.
     */
    @Test
    public void shouldAddADevice_WhenGivenValidParameters() {
        // Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);


        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        Room mockRoom = mock(Room.class);
        Device mockDevice = mock(Device.class);

        when(roomRepository.ofIdentity(roomID)).thenReturn(Optional.of(mockRoom));
        when(deviceFactory.createDevice(any(RoomID.class), any(DeviceName.class), any(DeviceStatus.class))).thenReturn(mockDevice);

        //Act
        Device device = deviceService.addDevice(roomID, deviceName, deviceStatus);

        //Assert
        assertNotNull(device);
    }

    /**
     * Test the addDevice method of the DeviceService class with an invalid roomID.
     */
    @Test
    public void shouldThrowException_WhenGivenInvalidRoomID() {
        // Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);

        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        when(roomRepository.ofIdentity(roomID)).thenReturn(Optional.empty());

        String expectedMessage = "Room with ID " + roomID + " not found.";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            //Act
            deviceService.addDevice(roomID, deviceName, deviceStatus);
        });

        String actualMessage = exception.getMessage();

        //Assert
        assertEquals(expectedMessage, actualMessage);

    }

    /**
     * Test the getAllDevices method of the DeviceService class with a valid
     */
    @Test
    public void shouldReturnAllDevices_WhenDevicesExist() {
        // Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        Device mockDevice = mock(Device.class);
        Device mockDevice2 = mock(Device.class);
        when(deviceRepository.findAll()).thenReturn(List.of(mockDevice, mockDevice2));

        int expectedSize = 2;

        // Act
        List<Device> deviceList = deviceService.getAllDevices();
        int result = deviceList.size();

        // Assert
        assertEquals(expectedSize, result);

    }

    /**
     * Test the getDevices method when there are no devices.
     */
    @Test
    public void shouldReturnEmptyList_WhenNoDevicesExist() {
        // Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        when(deviceRepository.findAll()).thenReturn(List.of());

        int expectedSize = 0;

        // Act
        List<Device> deviceList = deviceService.getAllDevices();
        int result = deviceList.size();

        // Assert
        assertEquals(expectedSize, result);
    }

/**
     * Test the getDeviceById method of the DeviceService class with a valid deviceID.
     */
    @Test
    public void shouldReturnDevice_WhenGetDeviceByIdIsCalledWithValidDeviceID() {
        // Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceID deviceID = mock(DeviceID.class);
        Device mockDevice = mock(Device.class);

        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));

        // Act
        Optional<Device> device = deviceService.getDeviceByID(deviceID);

        // Assert
        assertNotNull(device);
        assertTrue(device.isPresent());
        assertEquals(mockDevice, device.get());
    }

    /**
     * Test the getDeviceById method of the DeviceService class with an invalid deviceID.
     */
    @Test
    public void shouldReturnEmptyOptional_WhenGetDeviceByIdIsCalledWithInvalidDeviceID() {
        // Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceID deviceID = mock(DeviceID.class);

        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.empty());

        // Act
        Optional<Device> device = deviceService.getDeviceByID(deviceID);

        // Assert
        assertNotNull(device);
        assertTrue(device.isEmpty());
    }

    /**
     * Test the getDeviceListByRoomId method of the DeviceService class with a valid roomID.
     */
    @Test
    public void shouldGetDeviceListByRoomId_WhenGivenValidRoomId() {
        // Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        Room room = mock(Room.class);
        RoomID roomID = room.getID();

        Device mockDevice = mock(Device.class);
        Device mockDevice2 = mock(Device.class);

        when(deviceRepository.findByRoomId(roomID)).thenReturn(List.of(mockDevice, mockDevice2));

        // Act
        List<Device> deviceList = deviceService.getDevicesByRoomId(roomID);

        // Assert
        assertNotNull(deviceList);
        assertEquals(2, deviceList.size());
    }

    /**
     * Tests deactivateDeviceByID method of the DeviceService class with a valid deviceID.
     */
    @Test
    public void shouldDeactivateDevice_WhenGivenValidDeviceID() {
        // Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceID deviceIdDouble = mock(DeviceID.class);
        Device deviceDouble = mock(Device.class);

        when(deviceRepository.ofIdentity(deviceIdDouble)).thenReturn(Optional.of(deviceDouble));

        // Act
        Device device = deviceService.deactivateDeviceByID(deviceIdDouble);

        // Assert
        assertEquals(deviceDouble, device);
    }

    /**
     * Tests deactivateDeviceByID method of the DeviceService class with an invalid deviceID.
     */
    @Test
    public void shouldThrowException_WhenGivenInvalidDeviceID() {
        // Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceID deviceIdDouble = mock(DeviceID.class);

        when(deviceRepository.ofIdentity(deviceIdDouble)).thenReturn(Optional.empty());

        String expectedMessage = "Device with ID " + deviceIdDouble + " not found.";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {

            deviceService.deactivateDeviceByID(deviceIdDouble);
        });

        String actualMessage = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }
}