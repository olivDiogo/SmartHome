package smart_home.controller;

import smart_home.ddd.IAssembler;
import smart_home.domain.actuator.IActuator;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.domain.device.Device;
import smart_home.domain.room.Room;
import smart_home.domain.service.*;
import smart_home.dto.*;
import smart_home.service.*;
import smart_home.value_object.*;

import java.util.Collections;
import java.util.List;

public class AddActuatorToDeviceController {
    private IRoomService _roomService;
    private IAssembler<Room, RoomDTO> _roomAssembler;
    private IDeviceService _deviceService;
    private IAssembler<Device, DeviceDTO> _deviceAssembler;
    private IActuatorModelService _actuatorModelService;
    private IAssembler<ActuatorModel, ActuatorModelDTO> _actuatorModelAssembler;
    private IActuatorTypeService _actuatorTypeService;
    private IAssembler<ActuatorType, ActuatorTypeDTO> _actuatorTypeAssembler;
    private IAssembler<IActuator, ActuatorDTO> _actuatorAssembler;
    private IActuatorService _actuatorService;


    /**
     * Constructor for the GetListOfRoomsController class.
     *
     * @param roomService          The room service.
     * @param roomAssembler        The room assembler.
     * @param deviceService       The device service.
     * @param actuatorModelService The actuator model service.
     * @param actuatorTypeService The actuator type service.
     * @param actuatorService The actuator service.
     */
    public AddActuatorToDeviceController(IRoomService roomService,
                                         IAssembler<Room, RoomDTO> roomAssembler,
                                         IDeviceService deviceService,
                                         IAssembler<Device, DeviceDTO> deviceAssembler,
                                         IActuatorModelService actuatorModelService,
                                         IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler,
                                         IActuatorTypeService actuatorTypeService,
                                         IAssembler<ActuatorType, ActuatorTypeDTO> actuatorTypeAssembler,
                                         IAssembler<IActuator, ActuatorDTO> actuatorAssembler,
                                         IActuatorService actuatorService) {
        validateRoomService(roomService);
        validateRoomAssembler(roomAssembler);
        validateDeviceService(deviceService);
        validateDeviceAssembler(deviceAssembler);
        validateActuatorModelService(actuatorModelService);
        validateActuatorModelAssembler(actuatorModelAssembler);
        validateActuatorTypeService(actuatorTypeService);
        validateActuatorTypeAssembler(actuatorTypeAssembler);
        validateActuatorAssembler(actuatorAssembler);
        validateActuatorService(actuatorService);
    }

    /**
     * Validates the room service.
     *
     * @param roomService The room service.
     */
    private void validateRoomService(IRoomService roomService) {
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
    private void validateRoomAssembler(IAssembler<Room, RoomDTO> roomAssembler) {
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
    private void validateDeviceService(IDeviceService deviceService) {
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
    private void validateDeviceAssembler(IAssembler<Device, DeviceDTO> deviceAssembler) {
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
    private void validateActuatorModelService(IActuatorModelService actuatorModelServiceImpl) {
        if (actuatorModelServiceImpl == null) {
            throw new IllegalArgumentException("Please enter a valid actuator model service.");
        } else {
            this._actuatorModelService = actuatorModelServiceImpl;
        }
    }

    /**
     * Validates the actuator model assembler.
     *
     * @param actuatorModelAssembler The actuator model assembler.
     */
    private void validateActuatorModelAssembler(IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler) {
        if (actuatorModelAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid actuator model assembler.");
        } else {
            this._actuatorModelAssembler = actuatorModelAssembler;
        }
    }

    /**
     * Validates the actuator type service.
     *
     * @param actuatorTypeService The actuator type service.
     */
    private void validateActuatorTypeService(IActuatorTypeService actuatorTypeService) {
        if (actuatorTypeService == null) {
            throw new IllegalArgumentException("Please enter a valid actuator type service.");
        } else {
            this._actuatorTypeService = actuatorTypeService;
        }
    }

    /**
     * Validates the actuator type assembler.
     *
     * @param actuatorTypeAssembler The actuator type assembler.
     */
    private void validateActuatorTypeAssembler(IAssembler<ActuatorType, ActuatorTypeDTO> actuatorTypeAssembler) {
        if (actuatorTypeAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid actuator type assembler.");
        } else {
            this._actuatorTypeAssembler = actuatorTypeAssembler;
        }
    }

    private void validateActuatorAssembler(IAssembler<IActuator, ActuatorDTO> actuatorAssembler) {
        if (actuatorAssembler == null) {
            throw new IllegalArgumentException("Please enter a valid actuator assembler.");
        } else {
            this._actuatorAssembler = actuatorAssembler;
        }
    }

    /**
     * Validates the actuator service.
     *
     * @param actuatorService The actuator service.
     */
    private void validateActuatorService(IActuatorService actuatorService) {
        if (actuatorService == null) {
            throw new IllegalArgumentException("Please enter a valid actuator service.");
        } else {
            this._actuatorService = actuatorService;
        }
    }

    /**
     * Gets the list of rooms.
     *
     * @return The list of rooms.
     */
    public List<RoomDTO> getRooms() {

        List<Room> listOfRooms = _roomService.getAllRooms();
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
     * Gets all actuator types.
     *
     * @return a list of actuator types.
     */
    public List<ActuatorTypeDTO> getActuatorTypes() {
        List<ActuatorType> actuatorTypeList = _actuatorTypeService.getAllActuatorTypes();
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
        if (!_actuatorTypeService.getActuatorTypeByID(actuatorTypeID).isPresent()) {
            throw new IllegalArgumentException("Actuator type with ID " + actuatorTypeID + " not found.");
        }

        List<ActuatorModel> actuatorModels = _actuatorModelService.getActuatorModelsByActuatorTypeId(actuatorTypeID);
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

        IActuator actuator = _actuatorService.addActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        return (ActuatorDTO) _actuatorAssembler.domainToDTO(actuator);
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
