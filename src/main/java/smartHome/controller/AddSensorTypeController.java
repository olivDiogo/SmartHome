package smartHome.controller;

import smartHome.assembler.SensorTypeAssembler;
import smartHome.assembler.UnitAssembler;
import smartHome.domain.sensorType.SensorType;
import smartHome.domain.unit.Unit;
import smartHome.dto.SensorTypeDTO;
import smartHome.dto.SensorTypeDataDTO;
import smartHome.dto.UnitDTO;
import smartHome.service.SensorTypeService;
import smartHome.service.UnitService;
import smartHome.valueObject.TypeDescription;
import smartHome.valueObject.UnitID;

import java.util.List;

public class AddSensorTypeController {
    private SensorTypeService _sensorTypeService;
    private UnitService _unitService;
    private SensorTypeAssembler _sensorTypeAssembler;
    private UnitAssembler _unitAssembler;

    public AddSensorTypeController(SensorTypeService sensorTypeService, SensorTypeAssembler sensorTypeAssembler,
                                   UnitService unitService, UnitAssembler unitAssembler) {
        validateSensorTypeService(sensorTypeService);
        validateUnitService(unitService);
        validateSensorTypeAssembler(sensorTypeAssembler);
        validateUnitAssembler(unitAssembler);
    }

    private void validateSensorTypeService(SensorTypeService sensorTypeService) {
        if (sensorTypeService == null) {
            throw new IllegalArgumentException("Valid SensorTypeService is required");
        }
        _sensorTypeService = sensorTypeService;
    }

    private void validateUnitService(UnitService unitService) {
        if (unitService == null) {
            throw new IllegalArgumentException("Valid UnitService is required");
        }
        _unitService = unitService;
    }

    private void validateSensorTypeAssembler(SensorTypeAssembler sensorTypeAssembler) {
        if (sensorTypeAssembler == null) {
            throw new IllegalArgumentException("Valid SensorTypeAssembler is required");
        }
        _sensorTypeAssembler = sensorTypeAssembler;
    }

    private void validateUnitAssembler(UnitAssembler unitAssembler) {
        if (unitAssembler == null) {
            throw new IllegalArgumentException("Valid UnitAssembler is required");
        }
        _unitAssembler = unitAssembler;
    }

    public List<UnitDTO> getSupportedUnits() {
        List<Unit> units = _unitService.getAllMeasurementTypes();
        return _unitAssembler.domainToDTO(units);
    }

    public SensorTypeDTO addAndSaveSensorType(SensorTypeDataDTO sensorTypeDataDTO) {
        try {
            TypeDescription typeDescription = new TypeDescription(sensorTypeDataDTO.sensorTypeDescription);
            UnitID unitID = new UnitID(sensorTypeDataDTO.unitID);
            SensorType sensorType = _sensorTypeService.createSensorType(typeDescription, unitID);
            SensorType savedSensorType = _sensorTypeService.saveSensorType(sensorType);
            return _sensorTypeAssembler.domainToDTO(savedSensorType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid sensor type data.");
        }
    }
}
