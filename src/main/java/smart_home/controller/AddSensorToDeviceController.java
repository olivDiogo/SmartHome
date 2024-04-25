package smart_home.controller;

import smart_home.mapper.sensor_vo_assembler.ISensorVOAssembler;
import smart_home.mapper.sensor_vo_assembler.SensorVOAssemblerImpl;
import smart_home.ddd.IAssembler;
import smart_home.domain.device.Device;
import smart_home.domain.room.Room;
import smart_home.domain.sensor.ISensor;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.domain.sensor_type.SensorType;
import smart_home.domain.service.*;
import smart_home.dto.*;
import smart_home.dto.sensor_data_dto.ISensorDataDTO;
import smart_home.value_object.SensorTypeID;
import java.util.List;

import static smart_home.utils.Validator.validateNotNull;


public class AddSensorToDeviceController {
    private IRoomService roomService;
    private IAssembler<Room, RoomDTO> roomAssembler;
    private IDeviceService deviceService;
    private IAssembler<Device, DeviceDTO> deviceAssembler;
    private ISensorModelService sensorModelService;
    private IAssembler<SensorModel, SensorModelDTO> sensorModelAssembler;
    private ISensorTypeService sensorTypeService;
    private IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler;
    private IAssembler<ISensor, SensorDTO> sensorAssembler;
    private ISensorService sensorService;

    /**
     * Constructor for the GetListOfRoomsController class.
     *
     * @param roomService   The room service.
     * @param roomAssembler The room assembler.
     */
    public AddSensorToDeviceController(
            IRoomService roomService,
            IAssembler<Room, RoomDTO> roomAssembler,
            IDeviceService deviceService,
            IAssembler<Device, DeviceDTO> deviceAssembler,
            ISensorModelService sensorModelService,
            IAssembler<SensorModel, SensorModelDTO> sensorModelAssembler,
            ISensorTypeService sensorTypeService,
            IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler,
            IAssembler<ISensor, SensorDTO> sensorAssembler,
            ISensorService sensorService) {
      validateNotNull(roomService, "Room service");
      validateNotNull(roomAssembler, "Room assembler");
      validateNotNull(deviceService, "Device service");
      validateNotNull(deviceAssembler, "Device assembler");
      validateNotNull(sensorModelService, "Sensor model service");
      validateNotNull(sensorModelAssembler, "Sensor model assembler");
      validateNotNull(sensorTypeService, "Sensor type service");
      validateNotNull(sensorTypeAssembler, "Sensor type assembler");
      validateNotNull(sensorAssembler, "Sensor assembler");
      validateNotNull(sensorService, "Sensor service");
      this.roomService = roomService;
      this.roomAssembler = roomAssembler;
      this.deviceService = deviceService;
      this.deviceAssembler = deviceAssembler;
      this.sensorModelService = sensorModelService;
      this.sensorModelAssembler = sensorModelAssembler;
      this.sensorTypeService = sensorTypeService;
      this.sensorTypeAssembler = sensorTypeAssembler;
      this.sensorAssembler = sensorAssembler;
      this.sensorService = sensorService;
         }

    /**
     * Gets all sensor types.
     *
     * @return a list of sensor types.
     */
    public List<SensorTypeDTO> getSensorTypes() {
        List<SensorType> sensorTypeList = sensorTypeService.getAllSensorTypes();
        if (sensorTypeList.isEmpty()) {
            throw new IllegalArgumentException("No sensor types found.");
        }
        List<SensorTypeDTO> sensorTypeDTOList = sensorTypeAssembler.domainToDTO(sensorTypeList);
        return List.copyOf(sensorTypeDTOList);
    }

    /**
     * Gets all sensor models.
     *
     * @return a list of sensor models.
     */
    public List<SensorModelDTO> getSensorModels(SensorTypeDTO sensorTypeDTO) {
        SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeDTO.sensorTypeID);

        if (!sensorTypeService.getSensorTypeByID(sensorTypeID).isPresent()) {
            throw new IllegalArgumentException("Sensor type with ID " + sensorTypeID + " not found.");
        }

        List<SensorModel> sensorModels =
                sensorModelService.getSensorModelsBySensorTypeId(sensorTypeID);
        if (sensorModels == null || sensorModels.isEmpty()) {
            throw new IllegalArgumentException("No sensor models found.");
        }
        List<SensorModelDTO> sensorModelDTOList = sensorModelAssembler.domainToDTO(sensorModels);

        return List.copyOf(sensorModelDTOList);
    }

    /**
     * Adds a sensor to a device.
     *
     * @param sensorDataDTOImp is the sensor data to add.
     * @return the sensor DTO.
     */
    public SensorDTO addSensorToDevice(ISensorDataDTO sensorDataDTOImp) {
        validateNotNull(sensorDataDTOImp, "Sensor data DTO");
        ISensorVOAssembler sensorVOAssembler = new SensorVOAssemblerImpl();
        Object[] sensorParameters = sensorVOAssembler.getSensorParameters(sensorDataDTOImp);

        ISensor sensor = sensorService.addSensor(sensorParameters);

        return sensorAssembler.domainToDTO(sensor);
    }

}
