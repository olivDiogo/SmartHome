package smart_home.service;

import org.junit.jupiter.api.Test;
import smart_home.domain.device.Device;
import smart_home.domain.device.IDeviceFactory;
import smart_home.domain.room.Room;
import smart_home.persistence.mem.DeviceRepository;
import smart_home.persistence.mem.RoomRepository;
import smart_home.value_object.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceServiceImplTest {

    /**
     * Test the constructor of the DeviceService class.
     */
    @Test
    void shouldInstantiateDeviceService_WhenGivenValidParameters() {
        // Arrange
        DeviceServiceImpl deviceServiceImpl;
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        // Act
        DeviceServiceImpl result = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        // Assert
        assertNotNull(result);
    }

    /**
     * Test the constructor of the DeviceService class with a null DeviceRepository.
     */
    @Test
    void shouldThrowException_WhenGivenNullDeviceRepository() {
        // Arrange
        DeviceRepository deviceRepository = null;
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        String expectedMessage = "DeviceRepository cannot be null.";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        });

        String actualMessage = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test the constructor of the DeviceService class with a null DeviceFactory.
     */
    @Test
    void shouldThrowException_WhenGivenNullDeviceFactory() {
        // Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        IDeviceFactory deviceFactory = null;
        RoomRepository roomRepository = mock(RoomRepository.class);

        String expectedMessage = "DeviceFactory cannot be null.";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        });

        String actualMessage = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test the constructor of the DeviceService class with a null RoomRepository.
     */
    @Test
    void shouldThrowException_WhenGivenNullRoomRepository() {
        // Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = null;

        String expectedMessage = "RoomRepository cannot be null.";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
        });

        String actualMessage = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test the addDevice method of the DeviceService class with a valid roomID, deviceName and deviceStatus.
     */
    @Test
    void shouldAddADevice_WhenGivenValidParameters() {
        // Arrange
        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);


        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        Room mockRoom = mock(Room.class);
        Device mockDevice = mock(Device.class);

        when(roomRepository.ofIdentity(roomID)).thenReturn(Optional.of(mockRoom));
        when(deviceFactory.createDevice(any(RoomID.class), any(DeviceName.class), any(DeviceStatus.class), any(DeviceTypeID.class))).thenReturn(mockDevice);

        //Act
        Device device = deviceServiceImpl.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);

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
        DeviceTypeID deviceTypeID = mock(DeviceTypeID.class);

        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        when(roomRepository.ofIdentity(roomID)).thenReturn(Optional.empty());

        String expectedMessage = "Room with ID " + roomID + " not found.";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            //Act
            deviceServiceImpl.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);
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
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        RoomID roomID = mock(RoomID.class);
        DeviceName deviceName = mock(DeviceName.class);
        DeviceStatus deviceStatus = mock(DeviceStatus.class);

        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        Device mockDevice = mock(Device.class);
        Device mockDevice2 = mock(Device.class);
        when(deviceRepository.findAll()).thenReturn(List.of(mockDevice, mockDevice2));

        int expectedSize = 2;

        // Act
        List<Device> deviceList = deviceServiceImpl.getAllDevices();
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
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        when(deviceRepository.findAll()).thenReturn(List.of());

        int expectedSize = 0;

        // Act
        List<Device> deviceList = deviceServiceImpl.getAllDevices();
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
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        DeviceID deviceID = mock(DeviceID.class);
        Device mockDevice = mock(Device.class);

        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.of(mockDevice));

        // Act
        Optional<Device> device = deviceServiceImpl.getDeviceByID(deviceID);

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
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        DeviceID deviceID = mock(DeviceID.class);

        when(deviceRepository.ofIdentity(deviceID)).thenReturn(Optional.empty());

        // Act
        Optional<Device> device = deviceServiceImpl.getDeviceByID(deviceID);

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
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        Room room = mock(Room.class);
        RoomID roomID = room.getID();

        Device mockDevice = mock(Device.class);
        Device mockDevice2 = mock(Device.class);

        when(deviceRepository.getDevicesByRoomId(roomID)).thenReturn(List.of(mockDevice, mockDevice2));

        // Act
        List<Device> deviceList = deviceServiceImpl.getDevicesByRoomId(roomID);

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
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        DeviceID deviceIdDouble = mock(DeviceID.class);
        Device deviceDouble = mock(Device.class);

        when(deviceRepository.ofIdentity(deviceIdDouble)).thenReturn(Optional.of(deviceDouble));

        // Act
        Device device = deviceServiceImpl.deactivateDeviceByID(deviceIdDouble);

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
        IDeviceFactory deviceFactory = mock(IDeviceFactory.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

        DeviceID deviceIdDouble = mock(DeviceID.class);

        when(deviceRepository.ofIdentity(deviceIdDouble)).thenReturn(Optional.empty());

        String expectedMessage = "Device with ID " + deviceIdDouble + " not found.";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {

            deviceServiceImpl.deactivateDeviceByID(deviceIdDouble);
        });

        String actualMessage = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, actualMessage);
    }
}