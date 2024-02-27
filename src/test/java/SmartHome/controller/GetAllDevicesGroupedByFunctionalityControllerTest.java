package SmartHome.controller;

import SmartHome.domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GetAllDevicesGroupedByFunctionalityControllerTest {

    /**
     * Tests the instantiation of the GetAllDevicesGroupedByFunctionalityController
     * when the house and catalogue are valid.
     *
     * @throws InstantiationException if either the house or catalogue are null
     */
    @Test
    public void whenHouseAndCatalogueAreValid_thenInstantiateGetAllDevicesGroupedByFunctionalityController() throws InstantiationException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");

        // Act
        new GetAllDevicesGroupedByFunctionalityController(house, catalogue);

        // Assert
    }

    /**
     * Tests the instantiation of the GetAllDevicesGroupedByFunctionalityController
     * when the house is null.
     *
     * @throws InstantiationException if either the house or catalogue are null
     */
    @Test
    public void whenHouseIsNull_thenThrowsException() throws InstantiationException {
        // Arrange
        House house = null;
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");

        String expectedMessage = "Arguments cannot be null!";

        // Act + Assert
        Exception exception = assertThrows(InstantiationException.class, () -> new GetAllDevicesGroupedByFunctionalityController(house, catalogue));

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the instantiation of the GetAllDevicesGroupedByFunctionalityController
     * when the catalogue is null.
     *
     * @throws InstantiationException if either the house or catalogue are null
     */
    @Test
    public void whenCatalogueIsNull_thenThrowsException() throws InstantiationException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueSensor catalogue = null;

        String expectedMessage = "Arguments cannot be null!";

        // Act + Assert
        Exception exception = assertThrows(InstantiationException.class, () -> new GetAllDevicesGroupedByFunctionalityController(house, catalogue));

        // Assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Tests the getAllDevicesGroupedByFunctionality method when there are only devices with no functionality.
     *
     * @throws InstantiationException if an error occurs
     */
    @Test
    public void whenGetDevicesWithNoFunctionality() throws InstantiationException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");

        String roomName = "Living Room";
        int floor = 1;
        int length = 5;
        int width = 5;
        int height = 3;
        Room room = house.addRoom(roomName, floor, width, length, height);

        String deviceName = "Socket";
        room.addDevice(deviceName, new DeviceFactory());

        GetAllDevicesGroupedByFunctionalityController getAllDevicesGroupedByFunctionalityController = new GetAllDevicesGroupedByFunctionalityController(house, catalogue);

        int expected = 0;

        // Act
        int result = getAllDevicesGroupedByFunctionalityController.getAllDevicesGroupedByFunctionality().size();

        // Assert
        assertEquals(expected, result);

    }


    /**
     * Tests the getAllDevicesGroupedByFunctionality method when there is 1 device with 1 functionality.
     *
     * @throws InstantiationException if an error occurs
     */
    @Test
    public void get1DeviceWith1Functionality() throws InstantiationException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");

        String roomName = "Living Room";
        int floor = 1;
        int length = 5;
        int width = 5;
        int height = 3;
        Room room = house.addRoom(roomName, floor, width, length, height);

        String sensorDescription = "Temperature";
        catalogue.addSensorType(sensorDescription, Unit.Temperature, new SensorTypeFactory());

        String deviceName = "Thermostat";
        String sensorModel = "SmartHome.sensors.GA100K";
        Device device = room.addDevice(deviceName, new DeviceFactory());
        device.addSensor(sensorModel, catalogue, new SensorFactory());

        GetAllDevicesGroupedByFunctionalityController getAllDevicesGroupedByFunctionalityController = new GetAllDevicesGroupedByFunctionalityController(house, catalogue);

        int expected = 1;

        // Act
        int result = getAllDevicesGroupedByFunctionalityController.getAllDevicesGroupedByFunctionality().values().size();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Tests the getAllDevicesGroupedByFunctionality method when there is 1 device with 2 functionalities.
     *
     * @throws InstantiationException if an error occurs
     */
    @Test
    public void get1DeviceWith2Functionalities() throws InstantiationException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");

        String roomName = "Living Room";
        int floor = 1;
        int length = 5;
        int width = 5;
        int height = 3;
        Room room = house.addRoom(roomName, floor, width, length, height);

        String sensorDescription1 = "Temperature";
        catalogue.addSensorType(sensorDescription1, Unit.Temperature, new SensorTypeFactory());

        String sensorDescription2 = "Humidity";
        catalogue.addSensorType(sensorDescription2, Unit.Humidity, new SensorTypeFactory());

        String deviceName = "Thermostat & Humidifier";
        String sensorModel1 = "SmartHome.sensors.GA100K";
        String sensorModel2 = "SmartHome.sensors.TSY01";
        Device device = room.addDevice(deviceName, new DeviceFactory());
        device.addSensor(sensorModel1, catalogue, new SensorFactory());
        device.addSensor(sensorModel2, catalogue, new SensorFactory());

        GetAllDevicesGroupedByFunctionalityController getAllDevicesGroupedByFunctionalityController = new GetAllDevicesGroupedByFunctionalityController(house, catalogue);

        int expected = 2;

        // Act
        int result = getAllDevicesGroupedByFunctionalityController.getAllDevicesGroupedByFunctionality().values().size();

        // Assert
        assertEquals(expected, result);
    }


}
