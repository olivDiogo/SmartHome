package SmartHomeDDD.repository;


import SmartHomeDDD.ValueObject.TypeDescription;
import SmartHomeDDD.ddd.Repository;

import SmartHomeDDD.domain.SensorType.SensorType;

import java.util.LinkedHashMap;

import java.util.Map;


public class SensorTypeRepository implements Repository<TypeDescription, SensorType> {

    private final Map<TypeDescription, SensorType> _sensorTypeData = new LinkedHashMap<>();


    @Override
    public SensorType save(SensorType sensorType) {
        if (sensorType == null) {
            throw new IllegalArgumentException("House cannot be null.");
        } else if (containsOfIdentity(sensorType.getID())) {
            throw new IllegalArgumentException("House already exists.");
        } else {
            _sensorTypeData.put(sensorType.getID(), sensorType);
        }
        return sensorType;
    }

}
