package smart_home.controller;

import smart_home.ddd.IAssembler;
import smart_home.domain.actuator.IActuator;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.domain.device.Device;
import smart_home.domain.room.Room;
import smart_home.domain.service.*;
import smart_home.dto.*;
import smart_home.dto.actuator_data_dto.IActuatorDataDTO;
import smart_home.mapper.actuator_vo_assembler.ActuatorVOAssemblerImpl;
import smart_home.mapper.actuator_vo_assembler.IActuatorVOAssembler;
import smart_home.utils.Validator;
import smart_home.value_object.*;

import java.util.Collections;
import java.util.List;

public class AddActuatorToDeviceController {
    private final IRoomService _roomService;
    private final IAssembler<Room, RoomDTO> _roomAssembler;
    private final IDeviceService _deviceService;
    private final IAssembler<Device, DeviceDTO> _deviceAssembler;
    private final IActuatorModelService _actuatorModelService;
    private final IAssembler<ActuatorModel, ActuatorModelDTO> _actuatorModelAssembler;
    private final IActuatorTypeService _actuatorTypeService;
    private final IAssembler<ActuatorType, ActuatorTypeDTO> _actuatorTypeAssembler;
    private final IAssembler<IActuator, ActuatorDTO> _actuatorAssembler;
    private final IActuatorService _actuatorService;


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
      Validator.validateNotNull(roomService, "Room service");
      Validator.validateNotNull(roomAssembler, "Room assembler");
      Validator.validateNotNull(deviceService, "Device service");
      Validator.validateNotNull(deviceAssembler, "Device assembler");
      Validator.validateNotNull(actuatorModelService, "Actuator model service");
      Validator.validateNotNull(actuatorModelAssembler, "Actuator model assembler");
      Validator.validateNotNull(actuatorTypeService, "Actuator type service");
      Validator.validateNotNull(actuatorTypeAssembler, "Actuator type assembler");
      Validator.validateNotNull(actuatorAssembler, "Actuator assembler");
      Validator.validateNotNull(actuatorService, "Actuator service");

      this._roomService = roomService;
      this._roomAssembler = roomAssembler;
      this._deviceService = deviceService;
      this._deviceAssembler = deviceAssembler;
      this._actuatorModelService = actuatorModelService;
      this._actuatorModelAssembler = actuatorModelAssembler;
      this._actuatorTypeService = actuatorTypeService;
      this._actuatorTypeAssembler = actuatorTypeAssembler;
      this._actuatorAssembler = actuatorAssembler;
      this._actuatorService = actuatorService;

    }

    /**
     * Gets the list of rooms.
     *
     * @return The list of rooms.
     */
    public List<RoomDTO> getRooms() {

        List<Room> listOfRooms = _roomService.getAllRooms();
        if (listOfRooms == null || listOfRooms.isEmpty()) {
            return Collections.emptyList();
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

        if (_roomService.getRoomById(roomID).isEmpty()) {
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
        if (_actuatorTypeService.getActuatorTypeByID(actuatorTypeID).isEmpty()) {
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
     * @param actuatorDataDTOImp is the actuator data DTO.
     * @return the actuator DTO.
     */
    public ActuatorDTO addActuatorToDevice(IActuatorDataDTO actuatorDataDTOImp) {
      Validator.validateNotNull(actuatorDataDTOImp, "Actuator data DTO");

        IActuatorVOAssembler actuatorVOAssembler = new ActuatorVOAssemblerImpl();
        Object[] actuatorParameters = actuatorVOAssembler.getActuatorParameters(actuatorDataDTOImp);

        IActuator actuator = _actuatorService.addActuator(actuatorParameters);

        return  _actuatorAssembler.domainToDTO(actuator);
    }



}
