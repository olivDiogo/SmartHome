package SmartHome.controller;

import SmartHome.domain.*;
import SmartHome.dto.DeviceDTO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

class DeactivateDeviceControllerTest {

    @Test
    void shouldInstantiateDeactivateDeviceController_WhenConstructorIsCalledWithValidArguments() {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        //Act
        new DeactivateDeviceController(house);
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullHouse() {
        //Arrange
        House house = null;
        String expectedMessage = "Invalid arguments";
        //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DeactivateDeviceController(house));
        assertEquals(expectedMessage, exception.getMessage());
        }

    @Test
    void shouldReturnDeviceListWithOneDevice_WhenGetDeviceListIsCalledAfterAddingDeviceToRoom() {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String roomName = "Sala";
        int floor = 2;
        int width = 2;
        int length = 2;
        int height = 2;
        String deviceName = "Luz";
        Room room = house.addRoom(roomName, floor, width, length, height);
        room.addDevice(deviceName,new DeviceFactory() );
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(house);
        //Act
        List<DeviceDTO> deviceDTOList = deactivateDeviceController.getDeviceList();
        //Assert
        assertEquals(1, deviceDTOList.size());
    }

    @Test
    void shouldReturnEmptyDeviceList_WhenGetDeviceListIsCalledWithoutAddingAnyDevice() {
        //Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(house);
        //Act
        List<DeviceDTO> deviceList = deactivateDeviceController.getDeviceList();
        //Assert
        assertEquals(0, deviceList.size());
    }

    @Test
    void shouldReturnEmptyOptional_WhenDeactivateDeviceIsCalledWithDeviceNotInList() {
        //Arrange
        String name = "Luz";
        House house = new House(new LocationFactory(), new RoomFactory());
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(house);
        DeviceFactory deviceFactory = new DeviceFactory();
        Device device = deviceFactory.createDevice(name);
        DeviceDTO deviceDTO = new DeviceDTO(device);
        deactivateDeviceController.getDeviceList();

        //Act
        Optional<DeviceDTO> optionalDeviceDTO1 = deactivateDeviceController.deactivateDevice(deviceDTO);

        //Assert
        assertTrue(optionalDeviceDTO1.isEmpty());
    }

    @Test
    void shouldReturnUpdatedDeviceDTO_WhenDeactivateDeviceIsCalledWithDeviceInList() {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String roomName = "Sala";
        int floor = 2;
        int width = 2;
        int length = 2;
        int height = 2;
        String deviceName = "Luz";
        Room room = house.addRoom(roomName, floor, width, length, height);
        room.addDevice(deviceName, new DeviceFactory());
        DeactivateDeviceController deactivateDeviceController = new DeactivateDeviceController(house);
        List<DeviceDTO> deviceDTOList = deactivateDeviceController.getDeviceList();
        DeviceDTO deviceDTO = deviceDTOList.stream().findFirst().orElseThrow(() -> new AssertionError("Device not found"));

        // Act
        Optional<DeviceDTO> result = deactivateDeviceController.deactivateDevice(deviceDTO);

        // Assert
        assertTrue(result.isPresent(), "deactivateDevice should return an optional that contains a DeviceDTO");
        DeviceDTO updatedDeviceDTO = result.get();
        assertFalse(updatedDeviceDTO._status, "Device status should be false to indicate it was deactivated.");

        // Also verify that the device matches the expected one to avoid false positives
        assertEquals(deviceDTO._deviceID, updatedDeviceDTO._deviceID, "The returned DeviceDTO should correspond to the device that was deactivated.");
    }

}