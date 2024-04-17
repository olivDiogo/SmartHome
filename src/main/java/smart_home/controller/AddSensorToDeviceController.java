package smart_home.controller;

import smart_home.assembler.*;
import smart_home.assembler.sensor_vo_assembler.ISensorVOAssembler;
import smart_home.assembler.sensor_vo_assembler.SensorVOAssemblerImpl;
import smart_home.domain.device.Device;
import smart_home.domain.room.Room;
import smart_home.domain.sensor.ISensor;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.domain.sensor_type.SensorType;
import smart_home.dto.*;
import smart_home.dto.sensor_data_dto.ISensorDataDTO;
import smart_home.service.*;
import smart_home.value_object.RoomID;
import smart_home.value_object.SensorTypeID;

import java.util.Collections;
import java.util.List;


public class AddSensorToDeviceController {
    private RoomServiceImpl _roomServiceImpl;
    private RoomAssembler _roomAssembler;
    private DeviceServiceImpl _deviceServiceImpl;
    private DeviceAssembler _deviceAssembler;
    private SensorModelServiceImpl _sensorModelServiceImpl;
    private SensorModelAssembler _sensorModelAssembler;
    private SensorTypeServiceImpl _sensorTypeServiceImpl;
    private SensorTypeAssembler _sensorTypeAssembler;
    private SensorAssembler _sensorAssembler;
    private SensorServiceImpl _sensorServiceImpl;

    /**
     * Constructor for the GetListOfRoomsController class.
     *
     * @param roomServiceImpl   The room service.
     * @param roomAssembler The room assembler.
     */
    public AddSensorToDeviceController(
            RoomServiceImpl roomServiceImpl,
            RoomAssembler roomAssembler,
            DeviceServiceImpl deviceServiceImpl,
            DeviceAssembler deviceAssembler,
            SensorModelServiceImpl sensorModelServiceImpl,
            SensorModelAssembler sensorModelAssembler,
            SensorTypeServiceImpl sensorTypeServiceImpl,
            SensorTypeAssembler sensorTypeAssembler,
            SensorAssembler sensorAssembler,
            SensorServiceImpl sensorServiceImpl) {
        validateRoomService(roomServiceImpl);
        validateRoomAssembler(roomAssembler);
        validateDeviceService(deviceServiceImpl);
        validateDeviceAssembler(deviceAssembler);
        validateSensorModelService(sensorModelServiceImpl);
        validateSensorModelAssembler(sensorModelAssembler);
        validateSensorTypeService(sensorTypeServiceImpl);
        validateSensorTypeAssembler(sensorTypeAssembler);
        validateSensorAssembler(sensorAssembler);
        validateSensorService(sensorServiceImpl);
    }

    /**
     * Validates the room service.
     *
     * @param roomServiceImpl The room service.
     */
    private void validateRoomService(RoomServiceImpl roomServiceImpl) {
        if (roomServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid room service.");
        } else {
            this._roomServiceImpl = roomServiceImpl;
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
     * @param deviceServiceImpl The device service.
     */
    private void validateDeviceService(DeviceServiceImpl deviceServiceImpl) {
        if (deviceServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid device service.");
        } else {
            this._deviceServiceImpl = deviceServiceImpl;
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
     * @param sensorModelServiceImpl The sensor model service.
     */
    private void validateSensorModelService(SensorModelServiceImpl sensorModelServiceImpl) {
        if (sensorModelServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid sensor model service.");
        } else {
            this._sensorModelServiceImpl = sensorModelServiceImpl;
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
     * @param sensorTypeServiceImpl The sensor type service.
     */
    private void validateSensorTypeService(SensorTypeServiceImpl sensorTypeServiceImpl) {
        if (sensorTypeServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid sensor type service.");
        } else {
            this._sensorTypeServiceImpl = sensorTypeServiceImpl;
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
     * @param sensorServiceImpl The sensor service.
     */
    private void validateSensorService(SensorServiceImpl sensorServiceImpl) {
        if (sensorServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid sensor service.");
        } else {
            this._sensorServiceImpl = sensorServiceImpl;
        }
    }

    /**
     * Gets the list of rooms.
     *
     * @return The list of rooms.
     */
    public List<RoomDTO> getRooms() {

        List<Room> listOfRooms = _roomServiceImpl.getAllRooms();
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

        if (!_roomServiceImpl.getRoomById(roomID).isPresent()) {
            throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
        }

        List<Device> devices = _deviceServiceImpl.getDevicesByRoomId(roomID);

        List<DeviceDTO> deviceDTOList = _deviceAssembler.domainToDTO(devices);

        return List.copyOf(deviceDTOList);
    }

    /**
     * Gets all sensor types.
     *
     * @return a list of sensor types.
     */
    public List<SensorTypeDTO> getSensorTypes() {
        List<SensorType> sensorTypeList = _sensorTypeServiceImpl.getAllSensorTypes();
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

        if (!_sensorTypeServiceImpl.getSensorTypeByID(sensorTypeID).isPresent()) {
            throw new IllegalArgumentException("Sensor type with ID " + sensorTypeID + " not found.");
        }

        List<SensorModel> sensorModels =
                _sensorModelServiceImpl.getSensorModelsBySensorTypeId(sensorTypeID);
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

        ISensor sensor = _sensorServiceImpl.addSensor(sensorParameters);

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
