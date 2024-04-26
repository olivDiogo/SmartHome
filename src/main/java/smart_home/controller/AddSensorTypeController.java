package smart_home.controller;

import smart_home.ddd.IAssembler;
import smart_home.domain.sensor_type.SensorType;
import smart_home.domain.service.ISensorTypeService;
import smart_home.domain.service.IUnitService;
import smart_home.domain.unit.Unit;
import smart_home.dto.SensorTypeDTO;
import smart_home.dto.SensorTypeDataDTO;
import smart_home.dto.UnitDTO;
import smart_home.utils.Validator;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import java.util.List;

public class AddSensorTypeController {
    private final ISensorTypeService _sensorTypeService;
    private final IUnitService _unitService;
    private final IAssembler<SensorType, SensorTypeDTO> _sensorTypeAssembler;
    private final IAssembler<Unit, UnitDTO> _unitAssembler;

    /**
     * Constructs an AddSensorTypeController with the specified services and assemblers.
     *
     * @param sensorTypeService The service for managing sensor types.
     * @param sensorTypeAssembler The assembler for converting sensor types to DTOs.
     * @param unitService The service for managing units.
     * @param unitAssembler The assembler for converting units to DTOs.
     */
    public AddSensorTypeController(ISensorTypeService sensorTypeService, IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler,
                                   IUnitService unitService, IAssembler<Unit, UnitDTO> unitAssembler) {
      Validator.validateNotNull(sensorTypeService, "Sensor type service");
      Validator.validateNotNull(sensorTypeAssembler, "Sensor type assembler");
      Validator.validateNotNull(unitService, "Unit service");
      Validator.validateNotNull(unitAssembler, "Unit assembler");

      this._sensorTypeService = sensorTypeService;
      this._sensorTypeAssembler = sensorTypeAssembler;
      this._unitService = unitService;
      this._unitAssembler = unitAssembler;

    }

    /**
     * Get all supported units.
     *
     * @return The list of supported units.
     */
    public List<UnitDTO> getSupportedUnits() {
        List<Unit> units = _unitService.getAllMeasurementTypes();
        return _unitAssembler.domainToDTO(units);
    }

    /**
     * Add and save a sensor type.
     *
     * @param sensorTypeDataDTO The sensor type data to add and save.
     * @return The sensor type DTO.
     */
    public SensorTypeDTO addAndSaveSensorType(SensorTypeDataDTO sensorTypeDataDTO) {
        try {
            TypeDescription typeDescription = new TypeDescription(sensorTypeDataDTO.sensorTypeDescription);
            UnitID unitID = new UnitID(sensorTypeDataDTO.unitID);
            SensorType sensorType = _sensorTypeService.createSensorType(typeDescription, unitID);
            SensorType savedSensorType = _sensorTypeService.addSensorType(sensorType);
            return _sensorTypeAssembler.domainToDTO(savedSensorType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid sensor type data.");
        }
    }
}
