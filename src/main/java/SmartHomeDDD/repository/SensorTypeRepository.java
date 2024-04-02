package SmartHomeDDD.repository;


import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.SensorType.SensorType;
import SmartHomeDDD.valueObject.SensorTypeID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class SensorTypeRepository implements Repository<SensorTypeID, SensorType> {

    private final Map<SensorTypeID, SensorType> _sensorTypeData = new LinkedHashMap<>();


    /**
     * Save a SensorType. If the SensorType is null, throw an IllegalArgumentException.
     *
     * @param sensorType the SensorType to save.
     * @return the saved SensorType.
     */
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

    /**
     * Find all SensorTypes.
     *
     * @return a list of all SensorTypes.
     */
    @Override
    public List<SensorType> findAll() {
        List<SensorType> allSensorTypes = _sensorTypeData.values().stream().toList();
        return allSensorTypes;
    }

    /**
     * Find a SensorType by its identity.
     *
     * @param sensorTypeID the identity of the SensorType to find.
     * @return the SensorType if found, otherwise Optional.empty().
     */
    @Override
    public Optional<SensorType> ofIdentity(SensorTypeID sensorTypeID) {
        Optional<SensorType> sensorType = Optional.ofNullable(_sensorTypeData.get(sensorTypeID));
        return sensorType;
    }

    /**
     * Check if a SensorType exists by its identity.
     *
     * @param sensorTypeID the identity of the SensorType to check.
     * @return true if the SensorType exists, otherwise false.
     */
    @Override
    public boolean containsOfIdentity(SensorTypeID sensorTypeID) {
        return _sensorTypeData.containsKey(sensorTypeID);
    }

}
