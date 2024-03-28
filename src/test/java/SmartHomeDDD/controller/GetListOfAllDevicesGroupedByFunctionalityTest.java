package SmartHomeDDD.controller;

import SmartHomeDDD.domain.DeviceType.DeviceTypeFactory;
import SmartHomeDDD.domain.House.House;
import SmartHomeDDD.domain.House.ImpHouseFactory;
import SmartHomeDDD.domain.Room.ImpRoomFactory;
import SmartHomeDDD.DTO.DeviceDTO;
import SmartHomeDDD.assembler.DeviceAssembler;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.domain.Device.ImpDeviceFactory;
import SmartHomeDDD.domain.DeviceType.DeviceType;
import SmartHomeDDD.domain.DeviceType.ImpDeviceTypeFactory;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.repository.DeviceTypeRepository;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.service.DeviceService;
import SmartHomeDDD.service.DeviceTypeService;
import SmartHomeDDD.service.HouseService;
import SmartHomeDDD.service.RoomService;
import SmartHomeDDD.valueObject.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetListOfAllDevicesGroupedByFunctionalityTest {
    /**
     * Test to ensure that GetListOfAllDevicesGroupedByFunctionality can be instantiated successfully.
     */
    @Test
    public void shouldInstantiateGetListOfAllDevicesGroupedByFunctionality_whenConstructorArgumentsAreValid() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        RoomRepository roomRepository = new RoomRepository();
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
        DeviceTypeFactory deviceTypeFactory = new ImpDeviceTypeFactory();
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);

        //Act
        new GetListOfAllDevicesGroupedByFunctionality(deviceService, deviceAssembler, deviceTypeService);

        //Assert
    }

    /**
     * Test to ensure that GetListOfAllDevicesGroupedByFunctionality throws an IllegalArgumentException when the DeviceService is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenGetListOfAllDevicesGroupedByFunctionalityIsCalledWithNullDeviceService() {
        //Arrange
        DeviceAssembler deviceAssembler = new DeviceAssembler();
        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
        DeviceTypeFactory deviceTypeFactory = new ImpDeviceTypeFactory();
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new GetListOfAllDevicesGroupedByFunctionality(null, deviceAssembler, deviceTypeService), "DeviceService cannot be null.");
    }

    /**
     * Test to ensure that GetListOfAllDevicesGroupedByFunctionality throws an IllegalArgumentException when the DeviceAssembler is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenGetListOfAllDevicesGroupedByFunctionalityIsCalledWithNullDeviceAssembler() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        RoomRepository roomRepository = new RoomRepository();
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

        DeviceTypeFactory deviceTypeFactory = new ImpDeviceTypeFactory();
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new GetListOfAllDevicesGroupedByFunctionality(deviceService, null, deviceTypeService), "DeviceAssembler cannot be null.");
    }

    /**
     * Test to ensure that GetListOfAllDevicesGroupedByFunctionality throws an IllegalArgumentException when the DeviceTypeRepository is null.
     */
    @Test
    public void shouldThrowIllegalArgumentException_WhenGetListOfAllDevicesGroupedByFunctionalityIsCalledWithNullDeviceTypeRepository() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        RoomRepository roomRepository = new RoomRepository();
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new GetListOfAllDevicesGroupedByFunctionality(deviceService, deviceAssembler, null), "DeviceTypeService cannot be null.");
    }

    @Test
    public void shouldThrowIllegalArgumentException_WhenGetDevicesDTOGroupedByFunctionalityIsCalledWithEmptyDevicesList() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        RoomRepository roomRepository = new RoomRepository();
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

        DeviceTypeFactory deviceTypeFactory = new ImpDeviceTypeFactory();
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);

        GetListOfAllDevicesGroupedByFunctionality getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionality(deviceService, deviceAssembler, deviceTypeService);

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality(), "No devices found.");
    }

    /**
     * Test to ensure that GetDevicesDTOGroupedByFunctionality returns a map of devices grouped by functionality, when devices have different type.
     */
    @Test
    public void shouldReturnMapOfDevicesGroupedByFunctionality_WhenGetDevicesDTOGroupedByFunctionalityIsCalledWithValidDevicesListAndDevicesHaveDifferentType() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        RoomRepository roomRepository = new RoomRepository();
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();
        DeviceTypeFactory deviceTypeFactory = new ImpDeviceTypeFactory();
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);

        ImpDeviceTypeFactory impDeviceTypeFactory = new ImpDeviceTypeFactory();

        GetListOfAllDevicesGroupedByFunctionality getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionality(deviceService, deviceAssembler, deviceTypeService);

        /* Create a house */
        Address address = new Address("Rua do Ouro", "22");
        ZipCode zipCode = new ZipCode(4444, 222);
        GPS gps = new GPS(22.2, 33.3);

        ImpHouseFactory houseFactory = new ImpHouseFactory();
        HouseRepository houseRepository = new HouseRepository();
        HouseService houseService = new HouseService (houseFactory, houseRepository);

        House house = houseService.addHouse(address, zipCode, gps);

        /* Create a room */
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        String strRoomName = "Bedroom";
        RoomName roomName = new RoomName(strRoomName);
        Dimension dimension = new Dimension(2, 2, 2);
        RoomFloor roomFloor = new RoomFloor(1);
        HouseID houseID = house.getID();

        Room room = roomService.addRoom(houseID, roomName, dimension, roomFloor);

        /* Create and save devices */
        RoomID roomID = room.getID();

        String name = "Light";
        DeviceName deviceName = new DeviceName(name);
        DeviceStatus deviceStatus = new DeviceStatus(true);
        String strDeviceTypeID1 = "Bedroom Light";
        String strDeviceTypeID2 = "Kitchen Light";
        TypeDescription deviceTypeDescription1 = new TypeDescription(strDeviceTypeID1);
        TypeDescription deviceTypeDescription2 = new TypeDescription(strDeviceTypeID2);

        DeviceType deviceType1 = impDeviceTypeFactory.createDeviceType(deviceTypeDescription1);
        DeviceType deviceType2 = impDeviceTypeFactory.createDeviceType(deviceTypeDescription2);

        deviceTypeRepository.save(deviceType1);
        deviceTypeRepository.save(deviceType2);

        Device device1 = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceType1.getID());
        Device device2 = deviceService.addDevice(roomID, deviceName, deviceStatus, deviceType2.getID());

        //Act
        Map<DeviceType, List<DeviceDTO>> result = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();

        //Assert
        assert result.get(deviceType1).get(0).deviceID.toString().equals(device1.getID().getId());
        assert result.get(deviceType2).get(0).deviceID.toString().equals(device2.getID().getId());
    }

    /**
     * Test to ensure that GetDevicesDTOGroupedByFunctionality returns a map of devices grouped by functionality, when devices have the same type.
     */
    @Test
    public void shouldReturnMapOfDevicesGroupedByFunctionality_WhenGetDevicesDTOGroupedByFunctionalityIsCalledWithValidDevicesListAndDevicesHaveSameType() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();
        RoomRepository roomRepository = new RoomRepository();
        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);
        DeviceAssembler deviceAssembler = new DeviceAssembler();

        DeviceTypeRepository deviceTypeRepository = new DeviceTypeRepository();

        DeviceTypeFactory deviceTypeFactory = new ImpDeviceTypeFactory();
        DeviceTypeService deviceTypeService = new DeviceTypeService(deviceTypeRepository, deviceTypeFactory);

        ImpDeviceTypeFactory impDeviceTypeFactory = new ImpDeviceTypeFactory();

        GetListOfAllDevicesGroupedByFunctionality getListOfAllDevicesGroupedByFunctionality = new GetListOfAllDevicesGroupedByFunctionality(deviceService, deviceAssembler, deviceTypeService);

        /* Create a house */
        Address address = new Address("Rua do Ouro", "22");
        ZipCode zipCode = new ZipCode(4444, 222);
        GPS gps = new GPS(22.2, 33.3);

        ImpHouseFactory houseFactory = new ImpHouseFactory();
        HouseRepository houseRepository = new HouseRepository();
        HouseService houseService = new HouseService (houseFactory, houseRepository);

        House house = houseService.addHouse(address, zipCode, gps);

        /* Create a room */
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        String strRoomName = "Bedroom";
        RoomName roomName = new RoomName(strRoomName);
        Dimension dimension = new Dimension(2, 2, 2);
        RoomFloor roomFloor = new RoomFloor(1);
        HouseID houseID = house.getID();

        Room room = roomService.addRoom(houseID, roomName, dimension, roomFloor);

        /* Create and save devices */
        RoomID roomID = room.getID();

        String name1 = "Light1";
        String name2 = "Light2";
        DeviceName deviceName1 = new DeviceName(name1);
        DeviceName deviceName2 = new DeviceName(name2);
        DeviceStatus deviceStatus = new DeviceStatus(true);
        String strDeviceTypeID = "Bedroom Light";
        TypeDescription deviceTypeDescription = new TypeDescription(strDeviceTypeID);

        DeviceType deviceType = impDeviceTypeFactory.createDeviceType(deviceTypeDescription);

        deviceTypeRepository.save(deviceType);

        Device device1 = deviceService.addDevice(roomID, deviceName1, deviceStatus, deviceType.getID());
        Device device2 = deviceService.addDevice(roomID, deviceName2, deviceStatus, deviceType.getID());

        //Act
        Map<DeviceType, List<DeviceDTO>> result = getListOfAllDevicesGroupedByFunctionality.getDevicesDTOGroupedByFunctionality();

        //Assert
        assert result.get(deviceType).get(0).deviceID.toString().equals(device1.getID().getId());
        assert result.get(deviceType).get(1).deviceID.toString().equals(device2.getID().getId());
    }

}
