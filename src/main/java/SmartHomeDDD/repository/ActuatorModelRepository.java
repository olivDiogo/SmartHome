package SmartHomeDDD.repository;

import SmartHomeDDD.domain.ActuatorModel.ActuatorModel;
import SmartHomeDDD.domain.ActuatorModel.ActuatorModelRepo;
import SmartHomeDDD.valueObject.ActuatorModelID;
import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.ModelPath;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ActuatorModelRepository implements ActuatorModelRepo {

    final private Map<ModelPath, ActuatorModel> DATA = new LinkedHashMap<>();

    /**
     * Save an ActuatorModel. If the ActuatorModel is null, throw an IllegalArgumentException.
     *
     * @param actuatorModel is the domain entity to be saved.
     * @return the saved ActuatorModel.
     */
    @Override
    public ActuatorModel save(ActuatorModel actuatorModel) {
        if (actuatorModel == null) {
            throw new IllegalArgumentException("ActuatorModel cannot be null.");
        } else if (containsOfIdentity(actuatorModel.getID())) {
            throw new IllegalArgumentException("ActuatorModel already exists.");
        } else {
            DATA.put(actuatorModel.getID(), actuatorModel);
        }
        return actuatorModel;
    }

    /**
     * Find all ActuatorModels.
     *
     * @return a list of all ActuatorModels.
     */
    @Override
    public List<ActuatorModel> findAll() {

        return List.copyOf(DATA.values().stream().toList());
    }

    /**
     * Find an ActuatorModel by its identity.
     *
     * @param actuatorModelID is the unique identifier of the domain entity.
     * @return the ActuatorModel if found, otherwise Optional.empty().
     */
    @Override
    public Optional<ActuatorModel> ofIdentity(ModelPath actuatorModelID) {

        return Optional.ofNullable(DATA.get(actuatorModelID));
    }


    /**
     * Checks if an ActuatorModel exists by its identity.
     *
     * @param actuatorModelID is the unique identifier of the domain entity.
     * @return true if the ActuatorModel exists, otherwise false.
     */
    public boolean containsOfIdentity(ModelPath actuatorModelID) {
        return DATA.containsKey(actuatorModelID);
    }

    /**
     * Find all ActuatorModels by their ActuatorTypeID.
     */
    @Override
    public List<ActuatorModel> findByActuatorTypeId(ActuatorTypeID actuatorTypeID) {
        return DATA.values().stream().filter(actuatorModel -> actuatorModel.getActuatorTypeID().equals(actuatorTypeID)).toList();
    }
}