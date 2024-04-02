package SmartHomeDDD.service;

import SmartHomeDDD.assembler.ActuatorTypeAssembler;
import SmartHomeDDD.domain.ActuatorType.ActuatorType;
import SmartHomeDDD.domain.ActuatorType.ImpActuatorTypeFactory;
import SmartHomeDDD.repository.ActuatorTypeRepository;
import SmartHomeDDD.valueObject.TypeDescription;

public class ActuatorTypeService {
    private ActuatorTypeRepository _actuatorTypeRepository;
    private ImpActuatorTypeFactory _actuatorTypeFactory;
    private ActuatorTypeAssembler _actuatorTypeAssembler;

    /**
     * Constructor for ActuatorTypeService.
     *
     * @param actuatorTypeRepository
     * @param actuatorTypeFactory
     * @param actuatorTypeAssembler
     */
    public ActuatorTypeService(ActuatorTypeRepository actuatorTypeRepository, ImpActuatorTypeFactory actuatorTypeFactory, ActuatorTypeAssembler actuatorTypeAssembler) {

        _actuatorTypeRepository = actuatorTypeRepository;
        _actuatorTypeFactory = actuatorTypeFactory;
        _actuatorTypeAssembler = actuatorTypeAssembler;
    }

    /**
     * Add an ActuatorType.
     * If the ActuatorType already exists, throw an IllegalArgumentException.
     *
     * @param actuatorTypeName is the name of the ActuatorType.
     * @return the ActuatorType.
     */
    public ActuatorType addActuatorType(TypeDescription actuatorTypeName) {
        if (actuatorTypeName == null) {
            throw new IllegalArgumentException("ActuatorType name cannot be null.");
        }
        else if (_actuatorTypeRepository.existsOfName(actuatorTypeName)) {
            throw new IllegalArgumentException("Actuator type already exists.");
        }

        else {
            ActuatorType actuatorType = _actuatorTypeFactory.createActuatorType(actuatorTypeName);

            _actuatorTypeRepository.save(actuatorType);

            return actuatorType;
        }
    }


}
