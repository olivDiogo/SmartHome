package SmartHomeDDD.controller;

import SmartHomeDDD.assembler.DeviceAssembler;
import SmartHomeDDD.assembler.RoomAssembler;
import SmartHomeDDD.domain.Device.ImpDeviceFactory;
import SmartHomeDDD.domain.Room.ImpRoomFactory;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.service.DeviceService;
import SmartHomeDDD.service.RoomService;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

class AddDeviceToRoomControllerTest {

    /**
     * Test to verify if the AddDeviceToRoomController is being instantiated correctly.
     */
    @Test
    void shouldInstantiateAddDeviceToRoomController_WhenParametersAreValid(){
        // Arrange
        RoomRepository roomRepository = new RoomRepository();
        ImpRoomFactory roomFactory = new ImpRoomFactory();
        RoomAssembler roomAssembler = new RoomAssembler();
        HouseRepository houseRepository = new HouseRepository();

        RoomService roomService = new RoomService(roomRepository, roomFactory, roomAssembler, houseRepository);

        DeviceRepository deviceRepository = new DeviceRepository();
        ImpDeviceFactory deviceFactory = new ImpDeviceFactory();

        DeviceService deviceService = new DeviceService(deviceRepository, deviceFactory, roomRepository);

        DeviceAssembler deviceAssembler = new DeviceAssembler();

        // Act
        AddDeviceToRoomController addDeviceToRoomController = new AddDeviceToRoomController(roomService, roomAssembler, deviceService, deviceAssembler);
    }


}
