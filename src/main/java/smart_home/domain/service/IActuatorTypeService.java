package smart_home.domain.service;

import smart_home.ddd.IService;
import smart_home.domain.actuator_type.ActuatorType;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import java.util.List;
import java.util.Optional;

public interface IActuatorTypeService extends IService {
    /**
     * Create a new actuator type.
     *
     * @param name   the name of the actuator type.
     * @param unitID the unit ID of the actuator type.
     * @return the actuator type that was created.
     */
    ActuatorType createActuatorType(TypeDescription name, UnitID unitID);

    /**
     * Save an actuator type.
     *
     * @param type the actuator type to save.
     * @return the actuator type that was saved.
     */
    ActuatorType addActuatorType(ActuatorType type);

    /**
     * Find all actuator types.
     *
     * @return a list of all actuator types.
     */
    List<ActuatorType> getAllActuatorTypes();

    /**
     * Find an actuator type by its ID.
     *
     * @param typeId the ID of the actuator type.
     * @return the actuator type with the provided ID.
     */
    Optional<ActuatorType> getActuatorTypeByID(ActuatorTypeID typeId);
}
