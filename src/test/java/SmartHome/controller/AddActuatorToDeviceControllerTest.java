package SmartHome.controller;

import SmartHome.domain.*;
import SmartHome.dto.ActuatorDTO;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AddActuatorToDeviceControllerTest {


    /**
     * See if the constructor works.
     *
     * @throws InstantiationException if the house is null.
     */
    @Test
    void shouldInstantiateController_WhenHouseAndCatalogueAreValid() throws InstantiationException {
        // Arrange
        String filePathName = "config.properties";

        House house = new House(new LocationFactory(), new RoomFactory());

        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        // Act & Assert
        new AddActuatorToDeviceController(house, catalogue);
    }

    /**
     * See if the constructor throws exception when house is null.
     *
     * @throws InstantiationException if the house is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenHouseIsNull() throws IllegalArgumentException, InstantiationException {
        // Arrange
        String filePathName = "config.properties";
        House house = null;
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        String expected = "Invalid arguments";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AddActuatorToDeviceController(house, catalogue));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expected));
    }

    /**
     * See if the constructor throws exception when catalogue is null.
     *
     * @throws InstantiationException if the catalogueActuator is null.
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenCatalogueIsNull() throws IllegalArgumentException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = null;

        String expected = "Invalid arguments";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AddActuatorToDeviceController(house, catalogue));

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expected));
    }

    /**
     * Test for getRooms method. Verify if the list of roomsDTO is in the same order as the list of rooms.
     *
     * @throws InstantiationException if the house is null.
     */
    @Test
    void shouldReturnListOfRoomsDTO_WhenGetRoomsIsCalled() throws IllegalArgumentException, InstantiationException {
        // Arrange
        String filePathName = "config.properties";

        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;

        String roomName2 = "Bedroom";
        int floor2 = 1;
        double width2 = 13.0;
        double length2 = 42.0;
        double height2 = 22.5;

        String roomName3 = "Kitchen";
        int floor3 = 1;
        double width3 = 3.0;
        double length3 = 4.0;
        double height3 = 2.5;

        House house = new House(new LocationFactory(), new RoomFactory());

        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        AddActuatorToDeviceController addActuatorToDeviceController = new AddActuatorToDeviceController(house, catalogue);

        house.addRoom(roomName, floor, width, length, height);
        house.addRoom(roomName2, floor2, width2, length2, height2);
        house.addRoom(roomName3, floor3, width3, length3, height3);

        String expected = house.getRooms().toString();

        // Act
        List<RoomDTO> roomDTOList = addActuatorToDeviceController.getRooms();
        String result = roomDTOList.toString();

        // Assert
        assertEquals(expected, result);
        assertTrue(result.contains(expected));
    }


    /**
     * Test to see if getDevicesFromRoom returns the correct number of devices.
     * @throws InstantiationException
     */
    @Test
    void shouldReturnListOfDevicesDTO_WhenGetDevicesFromRoomWithDevicesIsCalled() throws IllegalArgumentException, InstantiationException {
        // Arrange
        String filePathName = "config.properties";

        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        AddActuatorToDeviceController addActuatorToDeviceController = new AddActuatorToDeviceController(house, catalogue);

        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;

        String roomName2 = "kitchen";
        int floor2 = 1;
        double width2 = 31.0;
        double length2 = 41.0;
        double height2 = 21.5;

        String deviceName = "Lamp";
        String deviceName2 = "Lamp2";

        Room room = house.addRoom(roomName, floor, width, length, height);
        house.addRoom(roomName2, floor2, width2, length2, height2);

        room.addDevice(deviceName, new DeviceFactory());
        room.addDevice(deviceName2, new DeviceFactory());

        RoomDTO roomDTO = addActuatorToDeviceController.getRooms().get(0);

        String expected = room.getDevices().toString();

        // Act
        List<DeviceDTO> devices = addActuatorToDeviceController.getDevicesFromRoom(roomDTO);

        String result = devices.toString();

        // Assert
        assertEquals(expected, result);
        assertTrue(result.contains(expected));
    }

    /**
     * Test to see if getDevicesFromRoom returns empty list.
     * @throws InstantiationException
     */
    @Test
    void shouldReturnEmptyList_WhenGetDevicesFromRoomWithoutDevicesIsCalled() throws IllegalArgumentException, InstantiationException {
        // Arrange
        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;

        String filePathName = "config.properties";
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        house.addRoom(roomName, floor, width, length, height);

        RoomDTO roomDTO = controller.getRooms().get(0);

        int expectedDevices = 0;

        // Act
        List<DeviceDTO> devices = controller.getDevicesFromRoom(roomDTO);

        // Assert
        assertEquals(expectedDevices, devices.size());
    }

    /**
     * Test to see if getActuatorModels returns the correct number of actuator models.
     * @throws InstantiationException
     */
/*    @Test
    void shouldReturnListOfModels_WhenGetActuatorModelsIsCalled() throws IllegalArgumentException, InstantiationException {
        // Arrange
        String filePathName = "config.properties";

        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        int expectedModels = 3;

        // Act
        List<String> result = controller.getActuatorModels();

        // Assert
        assertEquals(expectedModels, result.size());
    }*/

    /**
     * Test to see if addActuatorToDevice returns a valid actuatorDTO.
     * @throws InstantiationException
     */
/*    @Test
    void shouldReturnValidActuatorDTO_WhenAddActuatorToDeviceWithValidModelIsCalled() throws IllegalArgumentException, InstantiationException {
        // Arrange
        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;

        String filePathName = "config.properties";

        String deviceName = "Lamp";
        String strDescription = "SetInteger";
        String actuatorModel = "SmartHome.actuators.SetIntegerActuator";

        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        Room room = house.addRoom(roomName, floor, width, length, height);
        room.addDevice(deviceName, new DeviceFactory());

        catalogue.addActuatorType(strDescription, new ActuatorTypeFactory());

        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        List<RoomDTO> roomDTOList = controller.getRooms();
        RoomDTO roomDTO = roomDTOList.get(0);

        List<DeviceDTO> deviceDTOList = controller.getDevicesFromRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOList.get(0);

        // Act
        ActuatorDTO actuatorDTO = controller.addActuatorToDevice(deviceDTO, actuatorModel);

        // Assert
        assertNotNull(actuatorDTO);
    }*/

    /**
     * Test to see if addActuatorToDevice returns null when the actuator model is invalid.
     * @throws InstantiationException
     */

    @Test
    void shouldReturnNull_WhenAddInvalidActuatorModelToDeviceIsCalled() throws IllegalArgumentException, InstantiationException {
        // Arrange
        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;

        String deviceName = "Lamp";
        String model = "InvalidActuator";

        String filePathName = "config.properties";

        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        Room room = house.addRoom(roomName, floor, width, length, height);

        room.addDevice(deviceName, new DeviceFactory());

        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        List<RoomDTO> roomDTOList = controller.getRooms();
        RoomDTO roomDTO = roomDTOList.get(0);

        List<DeviceDTO> deviceDTOList = controller.getDevicesFromRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOList.get(0);

        // Act
        ActuatorDTO actuatorDTO = controller.addActuatorToDevice(deviceDTO, model);

        // Assert
        assertNull(actuatorDTO);
    }

    /**
     * Test to see if addActuatorToDevice returns null when the device is null.
     * @throws InstantiationException
     */
    @Test
    void shouldReturnNull_WhenAddActuatorToNonExistingDeviceIsCalled() throws InstantiationException, NoSuchFieldException, IllegalAccessException {
        // Arrange
        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;

        String filePathName = "config.properties";

        String deviceName = "Lamp";
        String strDescription = "SetInteger";
        String actuatorModel = "SmartHome.actuators.SetIntegerActuator";

        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        Room room = house.addRoom(roomName, floor, width, length, height);
        room.addDevice(deviceName, new DeviceFactory());

        catalogue.addActuatorType(strDescription, new ActuatorTypeFactory());

        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        Field statusField = DeviceDTO.class.getDeclaredField("_status");
        statusField.set(deviceDTO, true);

        // Act
        ActuatorDTO actuatorDTO = controller.addActuatorToDevice(deviceDTO, actuatorModel);

        // Assert
        assertNull(actuatorDTO);
    }

    /**
     * Test to see if addActuatorToDevice returns null when the device is deactivated.
     * @throws InstantiationException
     */
    @Test
    void shouldReturnNull_WhenAddActuatorToDeactivatedDeviceIsCalled() throws InstantiationException {
        // Arrange
        String roomName = "Living Room";
        int floor = 1;
        double width = 3.0;
        double length = 4.0;
        double height = 2.5;

        String filePathName = "config.properties";

        String deviceName = "Lamp";
        String strDescription = "SetInteger";
        String actuatorModel = "SmartHome.actuators.SetIntegerActuator";

        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueActuator catalogue = new CatalogueActuator(filePathName);

        Room room = house.addRoom(roomName, floor, width, length, height);
        room.addDevice(deviceName, new DeviceFactory());

        catalogue.addActuatorType(strDescription, new ActuatorTypeFactory());

        AddActuatorToDeviceController controller = new AddActuatorToDeviceController(house, catalogue);

        List<RoomDTO> roomDTOList = controller.getRooms();
        RoomDTO roomDTO = roomDTOList.get(0);

        List<DeviceDTO> deviceDTOList = controller.getDevicesFromRoom(roomDTO);
        DeviceDTO deviceDTO = deviceDTOList.get(0);
        deviceDTO._status = false;

        // Act
        ActuatorDTO actuatorDTO = controller.addActuatorToDevice(deviceDTO, actuatorModel);

        // Assert
        assertNull(actuatorDTO);
    }
}
