package smartHome.controller;

import smartHome.assembler.*;
import smartHome.assembler.sensorVOAssembler.ISensorVOAssembler;
import smartHome.assembler.sensorVOAssembler.SensorVOAssemblerImpl;
import smartHome.domain.device.Device;
import smartHome.domain.room.Room;
import smartHome.domain.sensor.ISensor;
import smartHome.domain.sensorModel.SensorModel;
import smartHome.domain.sensorType.SensorType;
import smartHome.dto.*;
import smartHome.dto.sensorDataDto.ISensorDataDTO;
import smartHome.dto.sensorDataDto.SensorDataGenericDTOImp;
import smartHome.service.*;
import smartHome.valueObject.*;

import java.util.Collections;
import java.util.List;


public class AddSensorToDeviceController {
    private RoomService _roomService;
    private RoomAssembler _roomAssembler;
    private DeviceService _deviceService;
    private DeviceAssembler _deviceAssembler;
    private SensorModelService _sensorModelService;
    private SensorModelAssembler _sensorModelAssembler;
    private SensorTypeService _sensorTypeService;
    private SensorTypeAssembler _sensorTypeAssembler;
    private SensorAssembler _sensorAssembler;
    private SensorService _sensorService;

    /**
     * Constructor for the GetListOfRoomsController class.
     *
     * @param roomService   The room service.
     * @param roomAssembler The room assembler.
     */
    public AddSensorToDeviceController(
            RoomService roomService,
            RoomAssembler roomAssembler,
            DeviceService deviceService,
            DeviceAssembler deviceAssembler,
            SensorModelService sensorModelService,
            SensorModelAssembler sensorModelAssembler,
            SensorTypeService sensorTypeService,
            SensorTypeAssembler sensorTypeAssembler,
            SensorAssembler sensorAssembler,
            SensorService sensorService) {
        validateRoomService(roomService);
        validateRoomAssembler(roomAssembler);
        validateDeviceService(deviceService);
        validateDeviceAssembler(deviceAssembler);
        validateSensorModelService(sensorModelService);
        validateSensorModelAssembler(sensorModelAssembler);
        validateSensorTypeService(sensorTypeService);
        validateSensorTypeAssembler(sensorTypeAssembler);
        validateSensorAssembler(sensorAssembler);
        validateSensorService(sensorService);
    }

    /**
     * Validates the room service.
     *
     * @param roomService The room service.
     */
    private void validateRoomService(RoomService roomService) {
        if (roomService == null) {
            throw new IllegalArgumentException("Please enter a valid room service.");
        } else {
            this._roomService = roomService;
        }
    }

    /**
     * Validates the room assembler.
     *
     * @param roomAssembler The room assembler.
     */
    private void validateRoomAssembler(RoomAssembler roomAssembler) {
        if (roomAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid room assembler.");
        } else {
            this._roomAssembler = roomAssembler;
        }
    }

    /**
     * Validates the device service.
     *
     * @param deviceService The device service.
     */
    private void validateDeviceService(DeviceService deviceService) {
        if (deviceService == null) {
            throw new IllegalArgumentException("Please enter a valid device service.");
        } else {
            this._deviceService = deviceService;
        }
    }

    /**
     * Validates the device assembler.
     *
     * @param deviceAssembler The device assembler.
     */
    private void validateDeviceAssembler(DeviceAssembler deviceAssembler) {
        if (deviceAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid device assembler.");
        } else {
            this._deviceAssembler = deviceAssembler;
        }
    }

    /**
     * Validates the sensor model service.
     *
     * @param sensorModelService The sensor model service.
     */
    private void validateSensorModelService(SensorModelService sensorModelService) {
        if (sensorModelService == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model service.");
        } else {
            this._sensorModelService = sensorModelService;
        }
    }

    /**
     * Validates the sensor model assembler.
     *
     * @param sensorModelAssembler The sensor model assembler.
     */
    private void validateSensorModelAssembler(SensorModelAssembler sensorModelAssembler) {
        if (sensorModelAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model assembler.");
        } else {
            this._sensorModelAssembler = sensorModelAssembler;
        }
    }

    /**
     * Validates the sensor type service.
     *
     * @param sensorTypeService The sensor type service.
     */
    private void validateSensorTypeService(SensorTypeService sensorTypeService) {
        if (sensorTypeService == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type service.");
        } else {
            this._sensorTypeService = sensorTypeService;
        }
    }

    /**
     * Validates the sensor type assembler.
     *
     * @param sensorTypeAssembler The sensor type assembler.
     */
    private void validateSensorTypeAssembler(SensorTypeAssembler sensorTypeAssembler) {
        if (sensorTypeAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type assembler.");
        } else {
            this._sensorTypeAssembler = sensorTypeAssembler;
        }
    }

    /**
     * Validates the sensor assembler.
     *
     * @param sensorAssembler The sensor assembler.
     */
    private void validateSensorAssembler(SensorAssembler sensorAssembler) {
        if (sensorAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid sensor assembler.");
        } else {
            this._sensorAssembler = sensorAssembler;
        }
    }

    /**
     * Validates the sensor service.
     *
     * @param sensorService The sensor service.
     */
    private void validateSensorService(SensorService sensorService) {
        if (sensorService == null) {
            throw new IllegalArgumentException("Please enter a valid sensor service.");
        } else {
            this._sensorService = sensorService;
        }
    }

    /**
     * Gets the list of rooms.
     *
     * @return The list of rooms.
     */
    public List<RoomDTO> getRooms() {

        List<Room> listOfRooms = _roomService.getRooms();
        if (listOfRooms == null || listOfRooms.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if there are no devices.
        }
        List<RoomDTO> listOfRoomsDTO = _roomAssembler.domainToDTO(listOfRooms);

        return List.copyOf(listOfRoomsDTO);
    }

    /**
     * Gets all devices from a room.
     *
     * @param roomDTO is the room to get the devices from.
     * @return a list of devices.
     */
    public List<DeviceDTO> getDevicesFromRoom(RoomDTO roomDTO) {
        RoomID roomID = new RoomID(roomDTO.roomId);

        if (!_roomService.getRoomById(roomID).isPresent()) {
            throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
        }

        List<Device> devices = _deviceService.getDevicesByRoomId(roomID);

        List<DeviceDTO> deviceDTOList = _deviceAssembler.domainToDTO(devices);

        return List.copyOf(deviceDTOList);
    }

    /**
     * Gets all sensor types.
     *
     * @return a list of sensor types.
     */
    public List<SensorTypeDTO> getSensorTypes() {
        List<SensorType> sensorTypeList = _sensorTypeService.findAllSensorTypes();
        if (sensorTypeList.isEmpty()) {
            throw new IllegalArgumentException("No sensor types found.");
        }
        List<SensorTypeDTO> sensorTypeDTOList = _sensorTypeAssembler.domainToDTO(sensorTypeList);
        return List.copyOf(sensorTypeDTOList);
    }

    /**
     * Gets all sensor models.
     *
     * @return a list of sensor models.
     */
    public List<SensorModelDTO> getSensorModels(SensorTypeDTO sensorTypeDTO) {
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeDTO.sensorTypeID);

        if (!_sensorTypeService.findSensorTypeByID(sensorTypeID).isPresent()) {
            throw new IllegalArgumentException("Sensor type with ID " + sensorTypeID + " not found.");
        }

        List<SensorModel> sensorModels =
                _sensorModelService.getSensorModelsBySensorTypeId(sensorTypeID);
        if (sensorModels == null || sensorModels.isEmpty()) {
            throw new IllegalArgumentException("No sensor models found.");
        }
        List<SensorModelDTO> sensorModelDTOList = _sensorModelAssembler.domainToDTO(sensorModels);

        return List.copyOf(sensorModelDTOList);
    }

    /**
     * Adds a sensor to a device.
     *
     * @param sensorDataDTOImp is the sensor data to add.
     * @return the sensor DTO.
     */
    public SensorDTO addSensorToDevice(ISensorDataDTO sensorDataDTOImp) {
        validateSensorDataDTO(sensorDataDTOImp);
        ISensorVOAssembler sensorVOAssembler = new SensorVOAssemblerImpl();
        Object[] sensorParameters = sensorVOAssembler.getSensorParameters(sensorDataDTOImp);

        ISensor sensor = _sensorService.addSensor(sensorParameters);

        return _sensorAssembler.domainToDTO(sensor);
    }

    /**
     * Validates the sensor data DTO.
     *
     * @param sensorDataDTOImp The sensor data DTO.
     */
    private void validateSensorDataDTO(ISensorDataDTO sensorDataDTOImp) {
        if (sensorDataDTOImp == null) {
            throw new IllegalArgumentException("Please enter a valid sensor data DTO.");
        }
    }
}
