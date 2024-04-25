package smart_home.persistence.spring_data.sensor_model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import smart_home.domain.repository.ISensorModelRepository;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.SensorModelDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorTypeID;

import java.util.List;
import java.util.Optional;

public class SensorModelSpringDataRepository implements ISensorModelRepository {

    private ISensorModelSpringDataRepository repository;
    private IDataModelAssembler<SensorModelDataModel, SensorModel> assembler;

    /**
     * Constructor of the class.
     *
     * @param repository is the sensor model spring data repository.
     * @param assembler  is the data model assembler.
     */

    public SensorModelSpringDataRepository(ISensorModelSpringDataRepository repository, IDataModelAssembler<SensorModelDataModel, SensorModel> assembler) {
        Validator.validateNotNull(repository, "Sensor Model Spring Data Repository");
        Validator.validateNotNull(assembler, "Data Model Assembler");

        this.repository = repository;
        this.assembler = assembler;
    }

    /**
     * Method to save a domain entity.
     *
     * @param sensorModel is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public SensorModel save(SensorModel sensorModel) {
        Validator.validateNotNull(sensorModel, "Sensor Model");

        SensorModelDataModel dataModel = new SensorModelDataModel(sensorModel);

        repository.save(dataModel);

        return sensorModel;
    }


    /**
     * Method to find all domain entities.
     *
     * @return list of domain entities
     */
    @Override
    public List<SensorModel> findAll() {
        List<SensorModelDataModel> listSensorModelDataModelSaved = repository.findAll();
        List<SensorModel> listDomain = assembler.toDomain(listSensorModelDataModelSaved);

        return listDomain;
    }

    /**
     * Method to find a domain entity by its identity.
     *
     * @param objectID is the identity of the domain entity.
     * @return the domain entity.
     */

    @Override
    public Optional<SensorModel> ofIdentity(ModelPath objectID) {

        Optional<SensorModelDataModel> sensorModelDataModel = repository.findById(objectID.getID());

        if (sensorModelDataModel.isPresent()) {
            SensorModel domain = assembler.toDomain(sensorModelDataModel.get());
            return Optional.of(domain);
        } else {
            return Optional.empty();
        }
    }


    /**
     * Method to check if a domain entity exists by its identity.
     *
     * @param objectID is the identity of the domain entity.
     * @return true if the domain entity exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(ModelPath objectID) {
        return repository.existsById(objectID.getID());
    }

    /**
     * Method to find all sensor models by sensor type id.
     *
     * @param sensorTypeID is the sensor type id.
     * @return list of sensor models.
     */

    @Override
    public List<SensorModel> findBySensorTypeId(SensorTypeID sensorTypeID) {

        List<SensorModelDataModel> listDataModel = repository.findBy_sensorTypeID(sensorTypeID.getID());

        return assembler.toDomain(listDataModel);

    }
}
