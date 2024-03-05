package SmartHome.controller;

import SmartHome.domain.*;
import SmartHome.dto.ActuatorDTO;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddActuatorToDeviceControllerTest {


    /**
     * See if the constructor works.
     * @throws InstantiationException
     */
    @Test
    void seeIfConstructorWorks() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        // Act & Assert
        new AddActuatorToDeviceController(house, catalogue);
    }

    /**
     * See if the constructor throws exception.
     * @throws InstantiationException
     */
    @Test
    void seeIfConstructorThrowsException() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        House house = null;
        CatalogueActuator catalogue = null;

        // Act & Assert
        assertThrows(InstantiationException.class, () -> new AddActuatorToDeviceController(house, catalogue));
    }

    /**
     * See if the constructor throws exception when catalogue is null.
     * @throws InstantiationException
     */
    @Test
    void seeIfConstructorThrowsExceptionWhenCatalogueNull() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = null;

        // Act & Assert
        assertThrows(InstantiationException.class, () -> new AddActuatorToDeviceController(house, catalogue));
    }

    /**
     * See if the constructor throws exception when house is null.
     * @throws InstantiationException
     */
    @Test
    void seeIfConstructorThrowsExceptionWhenHouseNull() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        House house = null;
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        // Act & Assert
        assertThrows(InstantiationException.class, () -> new AddActuatorToDeviceController(house, catalogue));
    }

    /**
     * Test for getRooms method.
     * @throws InstantiationException
     */
    @Test
    void testForGetRooms() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);
        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;
        house.addRoom(roomName, floor, width, length, height);
        int expected = 1;

        // Act
        List<RoomDTO> rooms = controller.getRooms();

        // Assert
        assertEquals(expected, rooms.size());
    }


    /**
     * Test to see if getDevicesFromRoom returns the correct number of devices.
     */
    @Test
    void getDevicesFromRoomShouldReturnOne() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);
        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;
        Room room = house.addRoom(roomName, floor, width, length, height);

        String deviceName = "Lamp";
        room.addDevice(deviceName, new DeviceFactory());

        RoomDTO roomDTO = controller.getRooms().get(0);

        // Act
        List<DeviceDTO> devices = controller.getDevicesFromRoom(roomDTO);

        // Assert
        assertEquals(1, devices.size());
    }

    /**
     * Test to see if getDevicesFromRoom returns empty list.
     * @throws InstantiationException
     */
    @Test
    void getDevicesFromRoomShouldReturnZero() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);
        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;
        house.addRoom(roomName, floor, width, length, height);

        RoomDTO roomDTO = controller.getRooms().get(0);

        // Act
        List<DeviceDTO> devices = controller.getDevicesFromRoom(roomDTO);

        // Assert
        assertEquals(0, devices.size());
    }

    /**
     * Test to see if getActuatorModels returns the correct number of actuator models.
     * @throws InstantiationException
     */
    @Test
    void seeIfGetActuatorModelsWorks() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);
        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        // Act
        List<String> result = controller.getActuatorModels();

        // Assert
        assertEquals(3, result.size());
    }

    /**
     * Test to see if addActuatorToDevice returns the correct actuator.
     * @throws InstantiationException
     */
    @Test
    void seeIfAddActuatorToDeviceWorks() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);
        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;
        Room room = house.addRoom(roomName, floor, width, length, height);

        String deviceName = "Lamp";
        String strDescription = "SetInteger";
        String actuatorModel = "SmartHome.actuators.SetIntegerActuator";
        room.addDevice(deviceName, new DeviceFactory());

        catalogue.addActuatorType(strDescription, new ActuatorTypeFactory());


        List<RoomDTO> roomDTOList = controller.getRooms();
        RoomDTO roomDTO = roomDTOList.get(0);

        List<DeviceDTO> deviceDTOList = controller.getDevicesFromRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOList.get(0);

        // Act
        ActuatorDTO actuatorDTO = controller.addActuatorToDevice(deviceDTO,actuatorModel);

        // Assert
        assertNotNull(actuatorDTO);
    }

    /**
     * Test to see if addActuatorToDevice returns null.
     * @throws InstantiationException
     */

    @Test
    void addInvalidActuatorToDevice() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);
        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;
        Room room = house.addRoom(roomName, floor, width, length, height);

        String deviceName = "Lamp";
        room.addDevice(deviceName, new DeviceFactory());

        List<RoomDTO> roomDTOList = controller.getRooms();
        RoomDTO roomDTO = roomDTOList.get(0);

        List<DeviceDTO> deviceDTOList = controller.getDevicesFromRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOList.get(0);

        // Act
        ActuatorDTO actuatorDTO = controller.addActuatorToDevice(deviceDTO, "InvalidActuator");

        // Assert
        assertNull(actuatorDTO);
    }
}
