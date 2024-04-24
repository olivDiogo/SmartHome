package smart_home.persistence.mem;

import smart_home.ddd.IRepository;
import smart_home.domain.actuator.IActuator;
import smart_home.domain.repository.IActuatorRepository;
import smart_home.utils.Validator;
import smart_home.value_object.ActuatorID;
import smart_home.value_object.DeviceID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ActuatorRepository implements IActuatorRepository {

    /**
     * Map to store the Actuator data.
     */
    private final Map<ActuatorID, IActuator> DATA = new LinkedHashMap<>();

    /**
     * Method to save a domain actuator.
     *
     * @param actuator is the domain actuator to be saved.
     * @return the saved actuator.
     */
    @Override
    public IActuator save(IActuator actuator) {
        Validator.validateNotNull(actuator, "Actuator");

        if (containsOfIdentity(actuator.getID())) {
            throw new IllegalArgumentException("Actuator already exists.");
        } else {
            DATA.put(actuator.getID(), actuator);
        }
        return actuator;
    }

    /**
     * Method to find all domain actuators.
     *
     * @return the list of actuators.
     */
    @Override
    public List<IActuator> findAll() {
        List<IActuator> allActuators = DATA.values().stream().toList();
        return allActuators;
    }

    /**
     * Method to find a domain actuator by its unique identifier.
     *
     * @param actuatorID is the unique identifier of the domain entity.
     * @return the actuator by its ID or null.
     */
    @Override
    public Optional<IActuator> ofIdentity(ActuatorID actuatorID) {
        Optional<IActuator> actuator = Optional.ofNullable(DATA.get(actuatorID));
        return actuator;
    }

    /**
     * Method to check if a domain actuator exists by its unique identifier.
     *
     * @param actuatorID is the unique identifier of the domain entity.
     * @return the ID of the actuator or null.
     */
    @Override
    public boolean containsOfIdentity(ActuatorID actuatorID) {
        return DATA.containsKey(actuatorID);
    }

    /**
     * Method to find all domain actuators by deviceID.
     *
     * @param deviceID is the unique identifier of the device.
     * @return the list of actuators in the device.
     */
    public List<IActuator> findByDeviceID(DeviceID deviceID) {
        List<IActuator> actuators = DATA.values().stream().filter(actuator -> actuator.getDeviceID().equals(deviceID)).toList();
        return actuators;
    }
}
