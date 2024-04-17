package smart_home.controller;

import smart_home.assembler.SensorTypeAssembler;
import smart_home.assembler.UnitAssembler;
import smart_home.domain.sensor_type.SensorType;
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
    private SensorTypeServiceImpl _sensorTypeServiceImpl;
    private UnitServiceImpl _unitServiceImpl;
    private SensorTypeAssembler _sensorTypeAssembler;
    private UnitAssembler _unitAssembler;

    public AddSensorTypeController(SensorTypeServiceImpl sensorTypeServiceImpl, SensorTypeAssembler sensorTypeAssembler,
                                   UnitServiceImpl unitServiceImpl, UnitAssembler unitAssembler) {
        validateSensorTypeService(sensorTypeServiceImpl);
        validateUnitService(unitServiceImpl);
        validateSensorTypeAssembler(sensorTypeAssembler);
        validateUnitAssembler(unitAssembler);
    }

    private void validateSensorTypeService(SensorTypeServiceImpl sensorTypeServiceImpl) {
        if (sensorTypeServiceImpl == null) {
            throw new IllegalArgumentException("Valid SensorTypeService is required");
        }
        _sensorTypeServiceImpl = sensorTypeServiceImpl;
    }

    private void validateUnitService(UnitServiceImpl unitServiceImpl) {
        if (unitServiceImpl == null) {
            throw new IllegalArgumentException("Valid UnitService is required");
        }
        _unitServiceImpl = unitServiceImpl;
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
        List<Unit> units = _unitServiceImpl.getAllMeasurementTypes();
        return _unitAssembler.domainToDTO(units);
    }

    public SensorTypeDTO addAndSaveSensorType(SensorTypeDataDTO sensorTypeDataDTO) {
        try {
            TypeDescription typeDescription = new TypeDescription(sensorTypeDataDTO.sensorTypeDescription);
            UnitID unitID = new UnitID(sensorTypeDataDTO.unitID);
            SensorType sensorType = _sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
            SensorType savedSensorType = _sensorTypeServiceImpl.addSensorType(sensorType);
            return _sensorTypeAssembler.domainToDTO(savedSensorType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid sensor type data.");
        }
    }
}
