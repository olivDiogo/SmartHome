package SmartHomeDDD.controller;

import SmartHomeDDD.DTO.*;
import SmartHomeDDD.assembler.*;
import SmartHomeDDD.domain.Actuator.Actuator;
import SmartHomeDDD.domain.ActuatorModel.ActuatorModel;
import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.domain.Room.Room;
import SmartHomeDDD.service.*;
import SmartHomeDDD.valueObject.*;

import java.util.Collections;
import java.util.List;

public class AddActuatorToDeviceController {
    private RoomService _roomService;
    private RoomAssembler _roomAssembler;
    private DeviceService _deviceService;
    private DeviceAssembler _deviceAssembler;
    private ActuatorModelService _actuatorModelService;
    private ActuatorModelAssembler _actuatorModelAssembler;
    private ConfigurationService _configurationService;
    private ActuatorTypeService _actuatorTypeService;
    private ActuatorTypeAssembler _actuatorTypeAssembler;
    private ActuatorAssembler _actuatorAssembler;
    private ActuatorService _actuatorService;


    /**
     * Constructor for the GetListOfRoomsController class.
     *
     * @param roomService   The room service.
     * @param roomAssembler The room assembler.
     */
    public AddActuatorToDeviceController(RoomService roomService,
                                         RoomAssembler roomAssembler,
                                         DeviceService deviceService,
                                         DeviceAssembler deviceAssembler,
                                         ActuatorModelService actuatorModelService,
                                         ActuatorModelAssembler actuatorModelAssembler,
                                         ActuatorTypeService actuatorTypeService,
                                         ActuatorTypeAssembler actuatorTypeAssembler,
                                         ActuatorAssembler actuatorAssembler,
                                         ActuatorService actuatorService) {
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
     * Validates the actuator model service.
     *
     * @param actuatorModelService The actuator model service.
     */
    private void validateActuatorModelService(ActuatorModelService actuatorModelService) {
        if (actuatorModelService == null) {
            throw new IllegalArgumentException("Please enter a valid actuator model service.");
        } else {
            this._actuatorModelService = actuatorModelService;
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
     * @param actuatorTypeService The actuator type service.
     */
    private void validateActuatorTypeService(ActuatorTypeService actuatorTypeService) {
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
     * @param actuatorService The actuator service.
     */
    private void validateActuatorService(ActuatorService actuatorService) {
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


    public List<ActuatorTypeDTO> getActuatorTypes() {
        List<ActuatorType> actuatorTypeList = _actuatorTypeService.findAllActuatorTypes();
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
        if (!_actuatorTypeService.findActuatorTypeByID(actuatorTypeID).isPresent()) {
            throw new IllegalArgumentException("Actuator type with ID " + actuatorTypeID + " not found.");
        }

        List<ActuatorModel> actuatorModels = _actuatorModelService.getActuatorModelsByActuatorTypeId(actuatorTypeID);
        if (actuatorModels == null || actuatorModels.isEmpty()) {
            throw new IllegalArgumentException("No actuator models found.");
        }
        List<ActuatorModelDTO> actuatorModelDTOList = _actuatorModelAssembler.domainToDTO(actuatorModels);

        return List.copyOf(actuatorModelDTOList);
    }


    public ActuatorDTO addActuatorToDevice(ActuatorDataDTO actuatorDataDTO) {
        validateActuatorDataDTO(actuatorDataDTO);

        ModelPath modelPath = new ModelPath(actuatorDataDTO.actuatorModelPath);
        DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
        ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);
        ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);

        Actuator actuator = _actuatorService.addActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

        return _actuatorAssembler.domainToDTO(actuator);
    }

    private void validateActuatorDataDTO(ActuatorDataDTO actuatorDataDTO) {
        if (actuatorDataDTO == null) {
            throw new IllegalArgumentException("Please enter a valid actuator data DTO.");
        }
    }


}
