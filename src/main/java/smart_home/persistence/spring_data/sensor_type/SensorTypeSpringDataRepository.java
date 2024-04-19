package smart_home.persistence.spring_data.sensor_type;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import smart_home.domain.repository.ISensorTypeRepository;
import smart_home.domain.sensor_type.SensorType;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.SensorTypeDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.SensorTypeID;

import java.util.List;
import java.util.Optional;


public class SensorTypeSpringDataRepository implements ISensorTypeRepository {

    ISensorTypeSpringDataRepository _repository;
    EntityManagerFactory _factory;
    IDataModelAssembler<SensorTypeDataModel, SensorType> _assembler;

    /**
     * Constructor for SensorTypeSpringDataRepository
     *
     * @param repository sensor type spring data repository
     * @param assembler  data model assembler
     */
    public SensorTypeSpringDataRepository(ISensorTypeSpringDataRepository repository, IDataModelAssembler assembler) {
        Validator.validateNotNull(repository, "Sensor type spring data repository");
        Validator.validateNotNull(assembler, "Data model assembler");

        this._factory = Persistence.createEntityManagerFactory("smart_home");
        this._repository = repository;
        this._assembler = assembler;
    }

    /**
     * Method to create an entity manager
     *
     * @return entity manager
     */
    private EntityManager getEntityManager() {
        try  {
            return _factory.createEntityManager();
        } catch (Exception e) {
            throw new RuntimeException("Error creating the entity manager", e);
        }
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

        _repository.save(dataModel);

        return sensorType;
    }

    /**
     * Method to find all domain entities.
     *
     * @return list of domain entities
     */
    @Override
    public List<SensorType> findAll() {
        List<SensorTypeDataModel> listSensorTypeDataModelSaved = _repository.findAll();

        return _assembler.toDomain(listSensorTypeDataModelSaved);
    }

    /**
     * Method to find a domain entity by its unique identifier.
     *
     * @param sensorTypeID is the unique identifier of the domain entity.
     * @return the domain entity.
     */
    @Override
    public Optional<SensorType> ofIdentity(SensorTypeID sensorTypeID) {
        Optional<SensorTypeDataModel> sensorTypeDataModel = _repository.findById(sensorTypeID.getID());

        if (sensorTypeDataModel.isPresent()) {
            SensorType domain = _assembler.toDomain(sensorTypeDataModel.get());
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
        return this._repository.existsById(sensorTypeID.getID());
    }
}
