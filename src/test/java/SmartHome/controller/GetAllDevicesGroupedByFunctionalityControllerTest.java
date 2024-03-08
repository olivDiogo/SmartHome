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
    public void whenHouseIsNull_thenThrowsException() throws IllegalArgumentException, InstantiationException {
        // Arrange
        House house = null;
        CatalogueSensor catalogue = new CatalogueSensor("config.properties");

        String expectedMessage = "Invalid arguments";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetAllDevicesGroupedByFunctionalityController(house, catalogue));

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
    public void whenCatalogueIsNull_thenThrowsException() throws IllegalArgumentException {
        // Arrange
        House house = new House(new LocationFactory(), new RoomFactory());
        CatalogueSensor catalogue = null;

        String expectedMessage = "Invalid arguments";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetAllDevicesGroupedByFunctionalityController(house, catalogue));

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
        catalogue.addSensorType(sensorDescription, Unit.TEMPERATURE, new SensorTypeFactory());

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
        catalogue.addSensorType(sensorDescription1, Unit.TEMPERATURE, new SensorTypeFactory());

        String sensorDescription2 = "Humidity";
        catalogue.addSensorType(sensorDescription2, Unit.HUMIDITY, new SensorTypeFactory());

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

    @Test
    void givenTwoDevicesWithTwoFunctionalities_whenGetAllDevicesGroupedByFunctionality_thenReturnTwoDevicesGroupedByFunctionality() throws InstantiationException {
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
        catalogue.addSensorType(sensorDescription1, Unit.TEMPERATURE, new SensorTypeFactory());

        String sensorDescription2 = "Humidity";
        catalogue.addSensorType(sensorDescription2, Unit.HUMIDITY, new SensorTypeFactory());

        String deviceName1 = "Thermostat & Humidifier";
        String sensorModel1 = "SmartHome.sensors.GA100K";
        String sensorModel2 = "SmartHome.sensors.TSY01";
        Device device1 = room.addDevice(deviceName1, new DeviceFactory());
        device1.addSensor(sensorModel1, catalogue, new SensorFactory());
        device1.addSensor(sensorModel2, catalogue, new SensorFactory());

        String deviceName2 = "Thermostat & Humidifier";
        String sensorModel3 = "SmartHome.sensors.GA100K";
        String sensorModel4 = "SmartHome.sensors.TSY01";
        Device device2 = room.addDevice(deviceName2, new DeviceFactory());
        device2.addSensor(sensorModel3, catalogue, new SensorFactory());
        device2.addSensor(sensorModel4, catalogue, new SensorFactory());

        GetAllDevicesGroupedByFunctionalityController getAllDevicesGroupedByFunctionalityController = new GetAllDevicesGroupedByFunctionalityController(house, catalogue);

        int expected = 2;

        // Act
        int result = getAllDevicesGroupedByFunctionalityController.getAllDevicesGroupedByFunctionality().values().size();

        // Assert
        assertEquals(expected, result);
    }
}
