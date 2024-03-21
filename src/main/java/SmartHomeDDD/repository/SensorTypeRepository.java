package SmartHomeDDD.repository;


import SmartHomeDDD.ValueObject.TypeDescription;
import SmartHomeDDD.ddd.Repository;

import SmartHomeDDD.domain.SensorType.SensorType;

import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class SensorTypeRepository implements Repository<TypeDescription, SensorType> {

    private final Map<TypeDescription, SensorType> _sensorTypeData = new LinkedHashMap<>();


    @Override
    public SensorType save(SensorType sensorType) {
        if (sensorType == null) {
            throw new IllegalArgumentException("SensorType cannot be null.");
        } else if (containsOfIdentity(sensorType.getID())) {
            throw new IllegalArgumentException("SensorType already exists.");
        } else {
            _sensorTypeData.put(sensorType.getID(), sensorType);
        }
        return sensorType;
    }

    @Override
    public List<SensorType> findAll() {
        List <SensorType> allSensorTypes = _sensorTypeData.values().stream().toList();
        return allSensorTypes;
    }

    @Override
    public Optional<SensorType> ofIdentity(TypeDescription typeDescription) {
        Optional<SensorType> sensorType = Optional.ofNullable(_sensorTypeData.get(typeDescription));
        return sensorType;
    }

    @Override
    public boolean containsOfIdentity(TypeDescription typeDescription) {
        return _sensorTypeData.containsKey(typeDescription);
    }

}
