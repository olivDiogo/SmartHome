package smart_home.persistence.spring_data.sensor_type;

import smart_home.domain.repository.ISensorTypeRepository;
import smart_home.domain.sensor_type.SensorType;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.SensorTypeDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.SensorTypeID;

import java.util.List;
import java.util.Optional;


public class SensorTypeSpringDataRepository implements ISensorTypeRepository {

    ISensorTypeSpringDataRepository repository;
    IDataModelAssembler<SensorTypeDataModel, SensorType> assembler;

    /**
     * Constructor for SensorTypeSpringDataRepository
     *
     * @param repository sensor type spring data repository
     * @param assembler  data model assembler
     */
    public SensorTypeSpringDataRepository(ISensorTypeSpringDataRepository repository, IDataModelAssembler<SensorTypeDataModel, SensorType> assembler) {
        Validator.validateNotNull(repository, "Sensor type spring data repository");
        Validator.validateNotNull(assembler, "Data model assembler");

        this.repository = repository;
        this.assembler = assembler;
    }


    /**
     * Method to save a domain entity.
     *
     * @param sensorType is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public SensorType save(SensorType sensorType) {
        Validator.validateNotNull(sensorType, "Sensor type");

        SensorTypeDataModel dataModel = new SensorTypeDataModel(sensorType);

        repository.save(dataModel);

        return sensorType;
    }

    /**
     * Method to find all domain entities.
     *
     * @return list of domain entities
     */
    @Override
    public List<SensorType> findAll() {
        List<SensorTypeDataModel> listSensorTypeDataModelSaved = repository.findAll();

        return assembler.toDomain(listSensorTypeDataModelSaved);
    }

    /**
     * Method to find a domain entity by its unique identifier.
     *
     * @param sensorTypeID is the unique identifier of the domain entity.
     * @return the domain entity.
     */
    @Override
    public Optional<SensorType> ofIdentity(SensorTypeID sensorTypeID) {
        Optional<SensorTypeDataModel> sensorTypeDataModel = repository.findById(sensorTypeID.getID());

        if (sensorTypeDataModel.isPresent()) {
            SensorType domain = assembler.toDomain(sensorTypeDataModel.get());
            return Optional.of(domain);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Method to check if a domain entity exists by its unique identifier.
     *
     * @param sensorTypeID is the unique identifier of the domain entity.
     * @return true if the domain entity exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(SensorTypeID sensorTypeID) {
        return this.repository.existsById(sensorTypeID.getID());
    }
}
