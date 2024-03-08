package SmartHome.controller;

import SmartHome.domain.*;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.SensorDTO;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddSensorToDeviceTest {

    @Test
    public void constructor_WithValidArguments_ShouldInstantiate() throws IllegalArgumentException, InstantiationException {
        //Arrange
        House validHouse = new House(new LocationFactory(), new RoomFactory());
        CatalogueSensor validCatalogue = new CatalogueSensor("config.properties");
        //Act & Assert
        try {
            AddSensorToDeviceController controller = new AddSensorToDeviceController(validHouse, validCatalogue);
            assertNotNull(controller);
        } catch (IllegalArgumentException e){
            fail();
        }
    }
    @Test
    public void constructor_WithNullArguments_ShouldThrowInstantiationException() {
        // Arrange
        House invalidHouse = null;
        CatalogueSensor invalidCatalogue = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new AddSensorToDeviceController(invalidHouse, invalidCatalogue);
        });

        // Assert
        String expectedMessage = "Invalid arguments";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Exception message should contain the expected text");
    }
    @Test
    void shouldInstantiateValidConstructor() throws InstantiationException {
        // arrange

        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());

        // act
        new AddSensorToDeviceController(house, catalogue);

        // assert
        assertNotNull(house);
    }

/*    @Test
    void NewAddSensorToDeviceControllerWithNullHouse() throws InstantiationException {
        // arrange
        Configuration config = new PropertyListConfiguration();
        CatalogueSensors catalogue = new CatalogueSensors(config);
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
                new AddSensorToDeviceController(null, catalogue)
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }*/

    @Test
    void NewAddSensorToDeviceControllerWithNullCatalogue() {
        // arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new AddSensorToDeviceController(house, null)
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void getNonEmptyRooms() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(house, catalogue);

        String roomName1 = "Living Room";
        int floor = 1;
        double width = 5;
        double length = 5;
        double height = 3;
        house.addRoom(roomName1, floor, width, length, height);

        // act
        List<RoomDTO> roomsDTO = addSensorToDeviceController.getRooms();

        // assert
        assertEquals(1, roomsDTO.size());
    }

    @Test
    void getEmptyRooms() throws InstantiationException {
        // assert
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(house, catalogue);


        // act
        List<RoomDTO> roomsDTO = addSensorToDeviceController.getRooms();

        // assert
        assertEquals(0, roomsDTO.size());
    }

    @Test
    void getSizeOf2Rooms() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(house, catalogue);


        String roomName1 = "Living Room";
        String roomName2 = "Dining Room";
        int floor = 1;
        double width = 5;
        double length = 5;
        double height = 3;
        house.addRoom(roomName1, floor, width, length, height);
        house.addRoom(roomName2, floor, width, length, height);

        // act
        List<RoomDTO> roomsDTO = addSensorToDeviceController.getRooms();

        // assert
        assertEquals(2, roomsDTO.size());
    }

    @Test
    void getDuplicatedEmptyRooms() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(house, catalogue);

        // act
        List<RoomDTO> roomsDTO = addSensorToDeviceController.getRooms();

        // assert
        assertEquals(0, roomsDTO.size());
    }

    @Test
    void getDevicesFromExistingRoomWithoutDevices() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(house, catalogue);

        String roomName1 = "Living Room";
        int floor = 1;
        double width = 5;
        double length = 5;
        double height = 3;

        house.addRoom(roomName1, floor, width, length, height);
        RoomDTO livingRoomDTO = addSensorToDeviceController.getRooms().get(0);

        // act
        List<DeviceDTO> devicesDTO = addSensorToDeviceController.getDevicesFromRoom(livingRoomDTO);

        // assert
        assertEquals(devicesDTO.size(), 0);
    }

    @Test
    void getDevicesFromExistingRoomWithDevice() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(house, catalogue);

        String roomName1 = "Living Room";
        int floor = 1;
        double width = 5;
        double length = 5;
        double height = 3;

        Room livingRoom = house.addRoom(roomName1, floor, width, length, height);
        livingRoom.addDevice("device1", new DeviceFactory());


        List<RoomDTO> roomDTOS = addSensorToDeviceController.getRooms();
        RoomDTO livingRoomDTO = roomDTOS.get(0);

        // act
        List<DeviceDTO> devices = addSensorToDeviceController.getDevicesFromRoom(livingRoomDTO);

        // assert
        assertEquals(devices.size(), 1);
    }

    @Test
    void getDevicesFromExistingRoomWith2Devices() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(house, catalogue);

        String roomName1 = "Living Room";
        int floor = 1;
        double width = 5;
        double length = 5;
        double height = 3;

        Room livingRoom = house.addRoom(roomName1, floor, width, length, height);
        livingRoom.addDevice("device1", new DeviceFactory());
        livingRoom.addDevice("device2", new DeviceFactory());

        List<RoomDTO> roomDTOS = addSensorToDeviceController.getRooms();
        RoomDTO livingRoomDTO = roomDTOS.get(0);

        // act
        List<DeviceDTO> devicesDTO = addSensorToDeviceController.getDevicesFromRoom(livingRoomDTO);

        // assert
        assertEquals(devicesDTO.size(), 2);
    }

    @Test
    void GetEmptySensorModels() throws InstantiationException, ConfigurationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(house, catalogue);

        Configurations configs = new Configurations();
        Configuration config = configs.properties("config.properties");
        int supportedSensors = config.getStringArray("sensor").length;

        // act
        List<String> sensorModels = addSensorToDeviceController.getSensorsModels();

        // assert
        assertEquals(supportedSensors, sensorModels.size());
    }

    @Test
    void AddNonExistingSensorModelToDevice() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(house, catalogue);

        String roomName1 = "Living Room";
        int floor = 1;
        double width = 5;
        double length = 5;
        double height = 3;

        String deviceName = "device1";
        String sensorModel = "SmartHome.sensors.GA100K";

        Room livingRoom = house.addRoom(roomName1, floor, width, length, height);

        livingRoom.addDevice(deviceName, new DeviceFactory());

        List<RoomDTO> roomDTOS = addSensorToDeviceController.getRooms();
        RoomDTO roomDTO = roomDTOS.get(0);

        List<DeviceDTO> devicesDTO = addSensorToDeviceController.getDevicesFromRoom(roomDTO);
        DeviceDTO deviceDTO = devicesDTO.get(0);


        // act
        SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(deviceDTO, sensorModel);

        // assert
        assertNull(sensorDTO);
    }

    private DeviceDTO setUp() throws InstantiationException {
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue);
        String roomName1 = "Living Room";
        int floor = 1;
        double width = 5;
        double length = 5;
        double height = 3;
        String deviceName = "device1";
        catalogue.addSensorType("Temperature", Unit.TEMPERATURE, new SensorTypeFactory());
        Room defaultRoom = house.addRoom(roomName1, floor, width, length, height);
        defaultRoom.addDevice(deviceName, new DeviceFactory());
        List<RoomDTO> roomDTOS = controller.getRooms();
        RoomDTO roomDTO = roomDTOS.get(0);
        List<DeviceDTO> devicesDTO = controller.getDevicesFromRoom(roomDTO);
        DeviceDTO deviceDTO = devicesDTO.get(0);
        return deviceDTO;
    }

    @Test
    void AddExistingSensorModelToDevice() throws InstantiationException {
        // arrange
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");
        House house = new House(new LocationFactory(), new RoomFactory());
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(house, catalogue);

        String roomName1 = "Living Room";
        int floor = 1;
        double width = 5;
        double length = 5;
        double height = 3;

        String deviceName = "device1";
        String sensorModel = "SmartHome.sensors.TemperatureSensor";

        catalogue.addSensorType("Temperature", Unit.TEMPERATURE, new SensorTypeFactory());

        Room livingRoom = house.addRoom(roomName1, floor, width, length, height);

        livingRoom.addDevice(deviceName, new DeviceFactory());

        List<RoomDTO> roomDTOS = addSensorToDeviceController.getRooms();
        RoomDTO roomDTO = roomDTOS.get(0);
        ;
        List<DeviceDTO> devicesDTO = addSensorToDeviceController.getDevicesFromRoom(roomDTO);
        DeviceDTO deviceDTO = devicesDTO.get(0);

        // act
        SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(deviceDTO, sensorModel);

        // assert
        assertNotNull(sensorDTO);
    }

}