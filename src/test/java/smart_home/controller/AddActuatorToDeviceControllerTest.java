package smart_home.controller;

import org.junit.jupiter.api.Test;
import smart_home.mapper.*;
import smart_home.domain.actuator.ActuatorFactoryImpl;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.domain.actuator_model.ActuatorModelFactoryImpl;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.domain.actuator_type.ActuatorTypeFactoryImpl;
import smart_home.domain.device.Device;
import smart_home.domain.device.DeviceFactoryImpl;
import smart_home.domain.house.House;
import smart_home.domain.house.HouseFactoryImpl;
import smart_home.domain.room.Room;
import smart_home.domain.room.RoomFactoryImpl;
import smart_home.domain.service.IActuatorService;
import smart_home.domain.unit.UnitFactoryImpl;
import smart_home.dto.*;
import smart_home.persistence.mem.*;
import smart_home.service.*;
import smart_home.value_object.*;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddActuatorToDeviceControllerTest {
    RoomRepository roomRepository = new RoomRepository();
    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    RoomAssembler roomAssembler = new RoomAssembler();
    HouseRepository houseRepository = new HouseRepository();
    UnitRepository unitRepository = new UnitRepository();
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    ActuatorRepository actuatorRepository = new ActuatorRepository();
    ActuatorFactoryImpl actuatorFactory = new ActuatorFactoryImpl();
    ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
    ActuatorModelFactoryImpl actuatorModelFactory = new ActuatorModelFactoryImpl();
    DeviceRepository deviceRepository = new DeviceRepository();
    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    ActuatorModelAssembler actuatorModelAssembler = new ActuatorModelAssembler();
    RoomServiceImpl roomServiceImpl =
            new RoomServiceImpl(roomRepository, roomFactory, roomAssembler, houseRepository);
    ActuatorModelServiceImpl actuatorModelServiceImpl =
            new ActuatorModelServiceImpl(actuatorModelRepository, actuatorModelFactory);
    ActuatorTypeServiceImpl ActuatorTypeServiceImpl =
            new ActuatorTypeServiceImpl(actuatorTypeRepository, actuatorTypeFactory, unitRepository);
    ActuatorTypeAssembler actuatorTypeAssembler = new ActuatorTypeAssembler();
    ActuatorAssembler actuatorAssembler = new ActuatorAssembler();
    IActuatorService actuatorService =
            new ActuatorServiceImpl(actuatorRepository, actuatorFactory, deviceRepository);
    DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    DeviceAssembler deviceAssembler = new DeviceAssembler();
    PostalCodeFactory postalCodeFactory = new PostalCodeFactory();
    HouseFactoryImpl houseFactory = new HouseFactoryImpl();
    HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);

    private void loadHouseAndRoom() {
        String street = "Rua Do Isep";
        String doorNumber = "122A";
        String countryCode = "PT";
        String postalCode = "4000-007";
        double latitude = 41.178;
        double longitude = -8.608;
        String name = "Quarto da Maria";
        int width = 10;
        int length = 10;
        int height = 10;
        int floor = 2;
        RoomName roomName1 = new RoomName(name);
        GPS newGPS = new GPS(latitude, longitude);
        Address newAddress =
                new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
        House house = houseServiceImpl.addHouse(newAddress, newGPS);
        HouseID houseID = house.getID();
        Dimension dimension = new Dimension(width, length, height);
        RoomFloor roomFloor = new RoomFloor(floor);
        roomServiceImpl.addRoom(houseID, roomName1, dimension, roomFloor);
    }

    private Device loadDevice(RoomID roomID) {
        String name1 = "Lampada";
        String nameDevice = "Ar Condicionado";
        DeviceName deviceName = new DeviceName(name1);
        DeviceName deviceName2 = new DeviceName(nameDevice);
        DeviceStatus deviceStatus = new DeviceStatus(true);
        DeviceTypeID deviceTypeID = new DeviceTypeID("1");
        deviceServiceImpl.addDevice(roomID, deviceName, deviceStatus, deviceTypeID);
        deviceServiceImpl.addDevice(roomID, deviceName2, deviceStatus, deviceTypeID);
        List<Device> devices = deviceServiceImpl.getDevicesByRoomId(roomID);
        return devices.get(0);
    }

    /**
     * Test to check if the AddActuatorToDeviceController is being created correctly.
     */
    @Test
    void shouldCreateAddActuatorToDeviceController() throws InstantiationException {
        // Assert
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

        // Act
        try {
            new AddActuatorToDeviceController(
                    null,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid room service.", e.getMessage());
        }
    }

    /**
     * Should throw exception when room assembler is null.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowException_whenRoomAssemblerIsNull() throws InstantiationException {
        // Assert
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
        // Act
        try {
            new AddActuatorToDeviceController(
                    roomServiceImpl,
                    null,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid room assembler.", e.getMessage());
        }
    }

    /**
     * Should throw exception when device service is null.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowException_whenDeviceServiceIsNull() throws InstantiationException {
        // Assert
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
        // Act
        try {
            new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    null,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid device service.", e.getMessage());
        }
    }

    /**
     * Should throw exception when device assembler is null.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowException_whenDeviceAssemblerIsNull() throws InstantiationException {
        // Assert
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
        // Act
        try {
            new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    null,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid device assembler.", e.getMessage());
        }
    }

    /**
     * Should throw exception when actuator model service is null.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowException_whenActuatorModelServiceIsNull() throws InstantiationException {
        // Assert
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
        // Act
        try {
            new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    null,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid actuator model service.", e.getMessage());
        }
    }

    /**
     * Should throw exception when actuator model assembler is null.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowException_whenActuatorModelAssemblerIsNull() throws InstantiationException {
        // Assert
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
        // Act
        try {
            new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    null,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid actuator model assembler.", e.getMessage());
        }
    }

    /**
     * Should throw exception when actuator configuration service is null.
     */
    @Test
    void shouldThrowException_whenConfigurationServiceIsNull() {
        // Act
        try {
            new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid actuator type service.", e.getMessage());
        }
    }

    /**
     * Should throw exception when actuator type service is null.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowException_whenActuatorTypeServiceIsNull() throws InstantiationException {
        // Assert
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
        // Act
        try {
            new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    null,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid actuator type service.", e.getMessage());
        }
    }

    /**
     * Should throw exception when actuator type assembler is null.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowException_whenActuatorTypeAssemblerIsNull() throws InstantiationException {
        // Assert
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
        // Act
        try {
            new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    null,
                    actuatorAssembler,
                    actuatorService);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid actuator type assembler.", e.getMessage());
        }
    }

    /**
     * Should throw exception when actuator assembler is null.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowException_whenActuatorAssemblerIsNull() throws InstantiationException {
        // Assert
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
        // Act
        try {
            new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    null,
                    actuatorService);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid actuator assembler.", e.getMessage());
        }
    }

    /**
     * Should throw exception when actuator service is null.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowException_whenActuatorServiceIsNull() throws InstantiationException {
        // Assert
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);
        // Act
        try {
            new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    ActuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    null);
        } catch (IllegalArgumentException e) {
            assertEquals("Please enter a valid actuator service.", e.getMessage());
        }
    }

    /**
     * Should throw exception when room service is null.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldReturnEmptyList_whenThereAreNoRooms() throws InstantiationException {
        // Arrange
        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);

        // Act
        List<RoomDTO> result = controller.getRooms();

        // Assert
        assertTrue(result.isEmpty());
    }

    /**
     * Should get rooms from house when given valid house ID.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldGetRoomsFromHouse_WhenGivenValidHouseID() throws InstantiationException {
        // Arrange
        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);
        loadHouseAndRoom();
        List<Room> rooms = roomRepository.findAll();
        List<RoomDTO> expectedRoomsDTOList = roomAssembler.domainToDTO(rooms);
        String expectedRoomDTOName = expectedRoomsDTOList.get(0).roomName;
        String expectedRoomDTOID = expectedRoomsDTOList.get(0).roomId;
        List<String> expectedList = List.of(expectedRoomDTOName, expectedRoomDTOID);

        // Act
        List<RoomDTO> roomsDTOList = controller.getRooms();
        String actualRoomDTOName = roomsDTOList.get(0).roomName;
        String actualRoomDTOID = roomsDTOList.get(0).roomId;
        List<String> actualList = List.of(actualRoomDTOName, actualRoomDTOID);

        // Assert
        assertEquals(expectedList, actualList);
    }

    /**
     * Should throw exception when room ID does not exist in repository.
     *
     * @throws InstantiationException
     */
    @Test
    void shouldThrowException_WhenRoomIDDoesNotExistInRepository() throws InstantiationException {
        // Arrange
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);

        String roomID = "123";
        RoomID nonExistentRoomID = new RoomID(roomID);

        String roomName = "Quarto da Maria";
        String dimension = "10x10x10";
        String floor = "2";
        RoomDTO roomDTO = new RoomDTO(roomName, dimension, floor, nonExistentRoomID.toString());

        String expectedMessage = "Room with ID " + nonExistentRoomID + " not found.";

        // Act & Assert
        Exception exception =
                assertThrows(IllegalArgumentException.class, () -> controller.getDevicesFromRoom(roomDTO));

        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Should get devices from room when given valid room ID.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldGetDevicesFromRoom_WhenParametersAreValid() throws InstantiationException {
        // Arrange
        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);
        loadHouseAndRoom();
        List<Room> rooms = roomRepository.findAll();
        RoomID roomID = rooms.get(0).getID();
        loadDevice(roomID);
        List<Device> devices = deviceServiceImpl.getDevicesByRoomId(roomID);
        List<DeviceDTO> deviceDTOListExpected = deviceAssembler.domainToDTO(devices);
        List<RoomDTO> roomsDTOList = controller.getRooms();
        RoomDTO roomDTO = roomsDTOList.get(0);
        String expectedDeviceDTOID = deviceDTOListExpected.get(0).deviceID;
        String expectedDeviceDTOName = deviceDTOListExpected.get(0).deviceName;
        List<String> expectedDeviceDTOList = List.of(expectedDeviceDTOID, expectedDeviceDTOName);
        // Act
        List<DeviceDTO> devicesDTOList = controller.getDevicesFromRoom(roomDTO);

        String actualDeviceDTOID = devicesDTOList.get(0).deviceID;
        String actualDeviceDTOName = devicesDTOList.get(0).deviceName;
        List<String> actualDeviceDTOList = List.of(actualDeviceDTOID, actualDeviceDTOName);
        // Assert
        assertEquals(expectedDeviceDTOList, actualDeviceDTOList);
    }

    /**
     * Should get available actuator types list.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldGetAvailableActuatorTypesList() throws InstantiationException {
        // Arrange
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

        TypeDescription typeDescription = new TypeDescription("BlindRoller");
        UnitID unit = new UnitID("Percent");
        ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
        ActuatorTypeServiceImpl.addActuatorType(actuatorType);

        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);

        List<ActuatorTypeDTO> ActuatorTypeDTOList = controller.getActuatorTypes();

        List<ActuatorType> actuatorTypes = actuatorTypeRepository.findAll();
        List<ActuatorTypeDTO> expectedActuatorTypeDTOList =
                actuatorTypeAssembler.domainToDTO(actuatorTypes);
        // Assert

        assertEquals(
                expectedActuatorTypeDTOList.get(0).actuatorTypeID,
                ActuatorTypeDTOList.get(0).actuatorTypeID);
    }

    /**
     * Should get available actuator models list.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldGetAvailableActuatorModelsList() throws InstantiationException {
        // Arrange
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

        TypeDescription typeDescription = new TypeDescription("BlindRoller");
        UnitID unit = new UnitID("Percent");
        ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
        ActuatorTypeServiceImpl.addActuatorType(actuatorType);

        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);

        ActuatorTypeDTO dto = actuatorTypeAssembler.domainToDTO(actuatorType);
        List<ActuatorModelDTO> ActuatorModelDTOList = controller.getActuatorModels(dto);

        List<ActuatorModel> actuatorModels = actuatorModelRepository.findAll();
        List<ActuatorModelDTO> expectedActuatorModelDTOList =
                actuatorModelAssembler.domainToDTO(actuatorModels);
        // Assert

        assertEquals(
                expectedActuatorModelDTOList.get(0).actuatorModelID,
                ActuatorModelDTOList.get(0).actuatorModelID);
    }

    /**
     * Should throw exception when actuator model repository is empty.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowException_WhenActuatorModelRepositoryIsEmpty() throws InstantiationException {
        // Arrange
        TypeDescription typeDescription = new TypeDescription("BlindRoller");
        UnitDescription unitDescription = new UnitDescription("Celsius");
        UnitID unitID = new UnitID("Celsius");
        UnitSymbol unitSymbol = new UnitSymbol("C");
        UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
        unitServiceImpl.addMeasurementType(unitDescription, unitSymbol);
        ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unitID);
        ActuatorTypeServiceImpl.addActuatorType(actuatorType);

        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);

        ActuatorTypeDTO dto = actuatorTypeAssembler.domainToDTO(actuatorType);

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> controller.getActuatorModels(dto));

        // Assert
        assertEquals("No actuator models found.", exception.getMessage());
    }

    /**
     * Should throw exception when actuator type repository is empty.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowException_WhenActuatorTypeRepositoryIsEmpty() throws InstantiationException {

        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> controller.getActuatorTypes());

        // Assert
        assertEquals("No actuator types found.", exception.getMessage());
    }

    /**
     * Should return empty list when there are no actuator types.
     *
     * @throws InstantiationException
     */
    @Test
    void shouldReturnEmptyList_whenThereAreNoActuatorTypes() throws InstantiationException {
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);

        TypeDescription typeDescription = new TypeDescription("Temperature");
        UnitID unit = new UnitID("Celsius");
        ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
        ActuatorTypeServiceImpl.addActuatorType(actuatorType);

        TypeDescription nonExistintTypeDescription = new TypeDescription("Humidity");
        UnitID unitID = new UnitID("Percent");
        ActuatorType nonExistintActuatorType =
                ActuatorTypeServiceImpl.createActuatorType(nonExistintTypeDescription, unitID);

        ActuatorTypeDTO actuatorTypeDTO = actuatorTypeAssembler.domainToDTO(nonExistintActuatorType);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class, () -> controller.getActuatorModels(actuatorTypeDTO));

        // Assert
        assertEquals(
                "Actuator type with ID " + nonExistintTypeDescription + " not found.",
                exception.getMessage());
    }

    /**
     * should add blind roller actuator to device when parameters are valid.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldAddActuatorToDevice_whenParametersAreValidBlindRoller() throws InstantiationException {
        // Arrange
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

        loadHouseAndRoom();
        List<Room> rooms = roomRepository.findAll();
        RoomID roomID = rooms.get(0).getID();
        Device device = loadDevice(roomID);

        TypeDescription typeDescription = new TypeDescription("BlindRoller");
        UnitID unit = new UnitID("Percent");
        ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
        ActuatorTypeServiceImpl.addActuatorType(actuatorType);

        String modelPath = "smart_home.domain.actuator.blind_roller_actuator.BlindRollerActuator";
        String actuatorName = "Actuator";

        int expected = 1;

        actuatorTypeAssembler.domainToDTO(actuatorType);

        ActuatorDataDTO actuatorDataDTO =
                new ActuatorDataDTO(device.getID().toString(), modelPath, actuatorName, actuatorType.getID().getID());

        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);
        // Act
        ActuatorDTO actuatorDTO = controller.addActuatorToDevice(actuatorDataDTO);
        int result = actuatorRepository.findAll().size();

        // Assert
        assertNotNull(actuatorDTO);
        assertEquals(expected, result);
    }


    /**
     * Should add set integer actuator to device when parameters are valid.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldAddActuatorToDevice_whenParametersAreValidSwitchActuator() throws InstantiationException {
        // Arrange
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

        loadHouseAndRoom();
        List<Room> rooms = roomRepository.findAll();
        RoomID roomID = rooms.get(0).getID();
        Device device = loadDevice(roomID);

        TypeDescription typeDescription = new TypeDescription("Switch");
        UnitID unit = new UnitID("Percent");
        ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
        ActuatorTypeServiceImpl.addActuatorType(actuatorType);

        String modelPath = "smart_home.domain.actuator.switch_actuator.SwitchActuator";
        String actuatorName = "Actuator";

        int expected = 1;

        actuatorTypeAssembler.domainToDTO(actuatorType);

        ActuatorDataDTO actuatorDataDTO =
                new ActuatorDataDTO(
                        device.getID().toString(), modelPath, actuatorName, actuatorType.getID().getID());
        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);
        // Act
        ActuatorDTO actuatorDTO = controller.addActuatorToDevice(actuatorDataDTO);
        int result = actuatorRepository.findAll().size();

        // Assert
        assertNotNull(actuatorDTO);
        assertEquals(expected, result);
    }

    /**
     * Should throw exception when actuator data DTO is null.
     *
     * @throws InstantiationException exception
     */
    @Test
    void shouldThrowIllegalArgumentException_WhenActuatorDataDTOIsNull()
            throws InstantiationException {
        // Arrange
        ActuatorConfigurationService configurationService =
                new ActuatorConfigurationService(
                        actuatorModelRepository, unitRepository, actuatorModelFactory, unitFactory);

        loadHouseAndRoom();
        List<Room> rooms = roomRepository.findAll();
        RoomID roomID = rooms.get(0).getID();
        Device device = loadDevice(roomID);

        TypeDescription typeDescription = new TypeDescription("Switch");
        UnitID unit = new UnitID("Percent");
        ActuatorType actuatorType = ActuatorTypeServiceImpl.createActuatorType(typeDescription, unit);
        ActuatorTypeServiceImpl.addActuatorType(actuatorType);

        actuatorTypeAssembler.domainToDTO(actuatorType);

        ActuatorDataDTO actuatorDataDTO = null;
        AddActuatorToDeviceController controller =
                new AddActuatorToDeviceController(
                        roomServiceImpl,
                        roomAssembler,
                        deviceServiceImpl,
                        deviceAssembler,
                        actuatorModelServiceImpl,
                        actuatorModelAssembler,
                        ActuatorTypeServiceImpl,
                        actuatorTypeAssembler,
                        actuatorAssembler,
                        actuatorService);
        // Act
        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class, () -> controller.addActuatorToDevice(actuatorDataDTO));

        // Assert
        assertEquals("Please enter a valid actuator data DTO.", exception.getMessage());
    }
}
