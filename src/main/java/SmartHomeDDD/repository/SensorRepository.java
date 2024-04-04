package SmartHomeDDD.repository;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.Sensor.Sensor;
import SmartHomeDDD.valueObject.DeviceID;
import SmartHomeDDD.valueObject.SensorID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SensorRepository implements Repository<SensorID, Sensor> {


    /**
     * Map to store the Sensor data.
     */
    private final Map<SensorID, Sensor> _SensorData = new LinkedHashMap<>();

    /**
     * Method to save a domain entity.
     *
     * @param Sensor is the domain entity to be saved.
     * @return
     */
    @Override
    public Sensor save(Sensor Sensor) {
        if (Sensor == null) {
            throw new IllegalArgumentException("Sensor cannot be null.");
        } else if (containsOfIdentity(Sensor.getID())) {
            throw new IllegalArgumentException("Sensor already exists.");
        } else {
            _SensorData.put(Sensor.getID(), Sensor);
        }
        return Sensor;
    }

    /**
     * Method to find all domain entities.
     *
     * @return
     */
    @Override
    public List<Sensor> findAll() {
        List<Sensor> allSensors = _SensorData.values().stream().toList();
        return allSensors;
    }

    /**
     * Method to find a domain entity by its unique identifier.
     *
     * @param SensorID is the unique identifier of the domain entity.
     * @return
     */
    @Override
    public Optional<Sensor> ofIdentity(SensorID SensorID) {
        Optional<Sensor> Sensor = Optional.ofNullable(_SensorData.get(SensorID));
        return Sensor;
    }

    /**
     * Method to check if a domain entity exists by its unique identifier.
     *
     * @param SensorID is the unique identifier of the domain entity.
     * @return
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
    public List<Sensor> findByDeviceId(DeviceID deviceID) {
        List<Sensor> Sensors = _SensorData.values().stream().filter(Sensor -> Sensor.getDeviceID().equals(deviceID)).toList();
        return Sensors;
    }

}
