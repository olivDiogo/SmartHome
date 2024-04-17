package smart_home.controller;

import smart_home.assembler.*;
import smart_home.domain.actuator.IActuator;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.domain.device.Device;
import smart_home.domain.room.Room;
import smart_home.dto.*;
import smart_home.service.*;
import smart_home.value_object.*;

import java.util.Collections;
import java.util.List;

public class AddActuatorToDeviceController {
    private RoomServiceImpl _roomServiceImpl;
    private RoomAssembler _roomAssembler;
    private DeviceServiceImpl _deviceServiceImpl;
    private DeviceAssembler _deviceAssembler;
    private ActuatorModelServiceImpl _actuatorModelServiceImpl;
    private ActuatorModelAssembler _actuatorModelAssembler;
    private ConfigurationService _configurationService;
    private ActuatorTypeServiceImpl _actuatorTypeServiceImpl;
    private ActuatorTypeAssembler _actuatorTypeAssembler;
    private ActuatorAssembler _actuatorAssembler;
    private ActuatorServiceImpl _actuatorServiceImpl;


    /**
     * Constructor for the GetListOfRoomsController class.
     *
     * @param roomServiceImpl   The room service.
     * @param roomAssembler The room assembler.
     */
    public AddActuatorToDeviceController(RoomServiceImpl roomServiceImpl,
                                         RoomAssembler roomAssembler,
                                         DeviceServiceImpl deviceServiceImpl,
                                         DeviceAssembler deviceAssembler,
                                         ActuatorModelServiceImpl actuatorModelServiceImpl,
                                         ActuatorModelAssembler actuatorModelAssembler,
                                         ActuatorTypeServiceImpl actuatorTypeServiceImpl,
                                         ActuatorTypeAssembler actuatorTypeAssembler,
                                         ActuatorAssembler actuatorAssembler,
                                         ActuatorServiceImpl actuatorServiceImpl) {
        validateRoomService(roomServiceImpl);
        validateRoomAssembler(roomAssembler);
        validateDeviceService(deviceServiceImpl);
        validateDeviceAssembler(deviceAssembler);
        validateActuatorModelService(actuatorModelServiceImpl);
        validateActuatorModelAssembler(actuatorModelAssembler);
        validateActuatorTypeService(actuatorTypeServiceImpl);
        validateActuatorTypeAssembler(actuatorTypeAssembler);
        validateActuatorAssembler(actuatorAssembler);
        validateActuatorService(actuatorServiceImpl);
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
     * Validates the actuator model service.
     *
     * @param actuatorModelServiceImpl The actuator model service.
     */
    private void validateActuatorModelService(ActuatorModelServiceImpl actuatorModelServiceImpl) {
        if (actuatorModelServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid actuator model service.");
        } else {
            this._actuatorModelServiceImpl = actuatorModelServiceImpl;
        }
    }

    /**
     * Validates the actuator model assembler.
     *
     * @param actuatorModelAssembler The actuator model assembler.
     */
    private void validateActuatorModelAssembler(ActuatorModelAssembler actuatorModelAssembler) {
        if (actuatorModelAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid actuator model assembler.");
        } else {
            this._actuatorModelAssembler = actuatorModelAssembler;
        }
    }

    /**
     * Validates the actuator type service.
     *
     * @param actuatorTypeServiceImpl The actuator type service.
     */
    private void validateActuatorTypeService(ActuatorTypeServiceImpl actuatorTypeServiceImpl) {
        if (actuatorTypeServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid actuator type service.");
        } else {
            this._actuatorTypeServiceImpl = actuatorTypeServiceImpl;
        }
    }

    /**
     * Validates the actuator type assembler.
     *
     * @param actuatorTypeAssembler The actuator type assembler.
     */
    private void validateActuatorTypeAssembler(ActuatorTypeAssembler actuatorTypeAssembler) {
        if (actuatorTypeAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid actuator type assembler.");
        } else {
            this._actuatorTypeAssembler = actuatorTypeAssembler;
        }
    }

    private void validateActuatorAssembler(ActuatorAssembler actuatorAssembler) {
        if (actuatorAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid actuator assembler.");
        } else {
            this._actuatorAssembler = actuatorAssembler;
        }
    }

    /**
     * Validates the actuator service.
     *
     * @param actuatorServiceImpl The actuator service.
     */
    private void validateActuatorService(ActuatorServiceImpl actuatorServiceImpl) {
        if (actuatorServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid actuator service.");
        } else {
            this._actuatorServiceImpl = actuatorServiceImpl;
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
     * Gets all actuator types.
     *
     * @return a list of actuator types.
     */
    public List<ActuatorTypeDTO> getActuatorTypes() {
        List<ActuatorType> actuatorTypeList = _actuatorTypeServiceImpl.getAllActuatorTypes();
        if (actuatorTypeList.isEmpty()) {
            throw new IllegalArgumentException("No actuator types found.");
        }
        List<ActuatorTypeDTO> actuatorTypeDTOList = _actuatorTypeAssembler.domainToDTO(actuatorTypeList);
        return List.copyOf(actuatorTypeDTOList);
    }

    /**
     * Gets all actuator models.
     *
     * @return a list of actuator models.
     */

    public List<ActuatorModelDTO> getActuatorModels(ActuatorTypeDTO actuatorTypeDTO) {
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeDTO.actuatorTypeID);
        if (!_actuatorTypeServiceImpl.getActuatorTypeByID(actuatorTypeID).isPresent()) {
            throw new IllegalArgumentException("Actuator type with ID " + actuatorTypeID + " not found.");
        }

        List<ActuatorModel> actuatorModels = _actuatorModelServiceImpl.getActuatorModelsByActuatorTypeId(actuatorTypeID);
        if (actuatorModels == null || actuatorModels.isEmpty()) {
            throw new IllegalArgumentException("No actuator models found.");
        }
        List<ActuatorModelDTO> actuatorModelDTOList = _actuatorModelAssembler.domainToDTO(actuatorModels);

        return List.copyOf(actuatorModelDTOList);
    }


    /**
     * Adds an actuator to a device.
     *
     * @param actuatorDataDTO is the actuator data DTO.
     * @return the actuator DTO.
     */
    public ActuatorDTO addActuatorToDevice(ActuatorDataDTO actuatorDataDTO) {
        validateActuatorDataDTO(actuatorDataDTO);

        ModelPath modelPath = new ModelPath(actuatorDataDTO.actuatorModelPath);
        DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);
        ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);

        IActuator actuator = _actuatorServiceImpl.addActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        return _actuatorAssembler.domainToDTO(actuator);
    }


    /**
     * Validates the actuator data DTO.
     *
     * @param actuatorDataDTO is the actuator data DTO.
     */
    private void validateActuatorDataDTO(ActuatorDataDTO actuatorDataDTO) {
        if (actuatorDataDTO == null) {
            throw new IllegalArgumentException("Please enter a valid actuator data DTO.");
        }
    }


}
