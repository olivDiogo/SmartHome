package smart_home.domain.service;

import smart_home.ddd.IService;
import smart_home.domain.actuator_model.ActuatorModel;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.ModelPath;

import java.util.List;
import java.util.Optional;

public interface IActuatorModelService extends IService {

    /**
     * Get all actuator models in the repository.
     * @return a list of all actuator models.
     */
    List<ActuatorModel> getAllActuatorModels();

    /**
     * Get an actuator model by its model path.
     * @param modelPath the path of the actuator model.
     * @return the actuator model with the provided model path.
     */
    Optional<ActuatorModel> getActuatorModel(ModelPath modelPath);

    /**
     * Get all actuator models of a specific actuator type.
     * @param actuatorTypeID the ID of the actuator type.
     * @return a list of all actuator models of the specified actuator type.
     */
    List<ActuatorModel> getActuatorModelsByActuatorTypeId(ActuatorTypeID actuatorTypeID);
}
