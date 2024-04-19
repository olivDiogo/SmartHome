package smart_home.persistence.mem;

import smart_home.ddd.IRepository;
import smart_home.domain.sensor.ISensor;
import smart_home.utils.Validator;
import smart_home.value_object.DeviceID;
import smart_home.value_object.SensorID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SensorRepository implements IRepository<SensorID, ISensor> {
    private final Map<SensorID, ISensor> _SensorData = new LinkedHashMap<>();

    /**
     * Method to save a domain entity.
     *
     * @param Sensor is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public ISensor save(ISensor Sensor) {
        Validator.validateNotNull(Sensor, "Sensor");

        if (containsOfIdentity(Sensor.getID())) {
            throw new IllegalArgumentException("Sensor already exists.");
        } else {
            _SensorData.put(Sensor.getID(), Sensor);
        }
        return Sensor;
    }

    /**
     * Method to find all domain entities.
     *
     * @return a list of all domain entities.
     */
    @Override
    public List<ISensor> findAll() {
        List<ISensor> allSensors = _SensorData.values().stream().toList();
        return allSensors;
    }

    /**
     * Method to find a domain entity by its unique identifier.
     *
     * @param SensorID is the unique identifier of the domain entity.
     * @return the domain entity if found, otherwise Optional.empty().
     */
    @Override
    public Optional<ISensor> ofIdentity(SensorID SensorID) {
        Optional<ISensor> Sensor = Optional.ofNullable(_SensorData.get(SensorID));
        return Sensor;
    }

    /**
     * Method to check if a domain entity exists by its unique identifier.
     *
     * @param SensorID is the unique identifier of the domain entity.
     * @return true if the domain entity exists, otherwise false.
     */
    @Override
    public boolean containsOfIdentity(SensorID SensorID) {
        return _SensorData.containsKey(SensorID);
    }

    /**
     * Method to find all Sensors in a device.
     *
     * @param deviceID is the unique identifier of the room.
     * @return a list of Sensors in the device.
     */
    public List<ISensor> findByDeviceId(DeviceID deviceID) {
        List<ISensor> Sensors = _SensorData.values().stream().filter(Sensor -> Sensor.getDeviceID().equals(deviceID)).toList();
        return Sensors;
    }

}
