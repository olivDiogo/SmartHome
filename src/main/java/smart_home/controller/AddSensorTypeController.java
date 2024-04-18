package smart_home.controller;

import smart_home.assembler.SensorTypeAssembler;
import smart_home.assembler.UnitAssembler;
import smart_home.ddd.IAssembler;
import smart_home.domain.sensor_type.SensorType;
import smart_home.domain.service.ISensorTypeService;
import smart_home.domain.service.IUnitService;
import smart_home.domain.unit.Unit;
import smart_home.dto.SensorTypeDTO;
import smart_home.dto.SensorTypeDataDTO;
import smart_home.dto.UnitDTO;
import smart_home.service.SensorTypeServiceImpl;
import smart_home.service.UnitServiceImpl;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import java.util.List;

public class AddSensorTypeController {
    private ISensorTypeService _sensorTypeService;
    private IUnitService _unitService;
    private IAssembler<SensorType, SensorTypeDTO> _sensorTypeAssembler;
    private IAssembler<Unit, UnitDTO> _unitAssembler;

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
        validateSensorTypeService(sensorTypeService);
        validateUnitService(unitService);
        validateSensorTypeAssembler(sensorTypeAssembler);
        validateUnitAssembler(unitAssembler);
    }

    /**
     * Validates the sensor type service.
     *
     * @param sensorTypeService The sensor type service to validate.
     */
    private void validateSensorTypeService(ISensorTypeService sensorTypeService) {
        if (sensorTypeService == null) {
            throw new IllegalArgumentException("Valid SensorTypeService is required");
        }
        _sensorTypeService = sensorTypeService;
    }

    /**
     * Validates the unit service.
     *
     * @param unitService The unit service to validate.
     */
    private void validateUnitService(IUnitService unitService) {
        if (unitService == null) {
            throw new IllegalArgumentException("Valid UnitService is required");
        }
        _unitService = unitService;
    }

    /**
     * Validates the sensor type assembler.
     *
     * @param sensorTypeAssembler The sensor type assembler to validate.
     */
    private void validateSensorTypeAssembler(IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler) {
        if (sensorTypeAssembler == null) {
            throw new IllegalArgumentException("Valid SensorTypeAssembler is required");
        }
        _sensorTypeAssembler = sensorTypeAssembler;
    }

    /**
     * Validates the unit assembler.
     *
     * @param unitAssembler The unit assembler to validate.
     */
    private void validateUnitAssembler(IAssembler<Unit, UnitDTO> unitAssembler) {
        if (unitAssembler == null) {
            throw new IllegalArgumentException("Valid UnitAssembler is required");
        }
        _unitAssembler = unitAssembler;
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
