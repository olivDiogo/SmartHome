package SmartHome.controller;

import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.SensorTypeFactory;
import SmartHome.domain.Unit;
import SmartHome.dto.SensorTypeDTO;
import SmartHome.dto.SensorTypeMapper;

import java.util.List;
import java.util.Optional;

public class AddSensorTypeController {
    private CatalogueSensor _catalogue;

    public AddSensorTypeController(CatalogueSensor catalogue) throws IllegalArgumentException {
        if (!checksIfCatalogueInValidState(catalogue))
            throw new IllegalArgumentException("Catalogue is not in a valid state");
        else _catalogue = catalogue;
    }

    private boolean checksIfCatalogueInValidState(CatalogueSensor catalogue) {
        return (catalogue != null);
    }

    public List<String> getAllSupportedUnits() {
        List<String> units = Unit.getAllSupportedUnits();
        return units;
    }

    public Optional<SensorTypeDTO> addSensorType(String type, String unit) throws InstantiationException {
        Unit unitEnum;

        try {
            unitEnum = Unit.valueOf(unit);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }

        SensorType sensorType = _catalogue.addSensorType(type, unitEnum, new SensorTypeFactory());
        Optional<SensorTypeDTO> sensorTypeDTO = Optional.of(SensorTypeMapper.domain2DTO(sensorType));
        return sensorTypeDTO;
    }
}
