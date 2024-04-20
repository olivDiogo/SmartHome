package smart_home.persistence.jpa.repository;

import jakarta.persistence.*;
import smart_home.domain.repository.ISensorRepository;
import smart_home.domain.sensor.ISensor;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.SensorDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.SensorID;
import smart_home.visitor_pattern.ISensorVisitorForDataModel;

import java.util.List;
import java.util.Optional;

public class SensorRepositoryJPAImp implements ISensorRepository {
    private EntityManagerFactory _factory;
    private IDataModelAssembler<SensorDataModel, ISensor> _dataModelAssembler;
    private ISensorVisitorForDataModel _sensorVisitorForDataModel;

    public SensorRepositoryJPAImp(IDataModelAssembler<SensorDataModel, ISensor> dataModelAssembler, ISensorVisitorForDataModel sensorVisitorForDataModel) {
        Validator.validateNotNull(dataModelAssembler, "Data model assembler");
        _dataModelAssembler = dataModelAssembler;
        Validator.validateNotNull(sensorVisitorForDataModel, "Sensor visitor for data model");
        _sensorVisitorForDataModel = sensorVisitorForDataModel;
        _factory = Persistence.createEntityManagerFactory("smart_home");

    }
    private EntityManager getEntityManager() {
        return _factory.createEntityManager();
    }

    @Override
    public ISensor save(ISensor sensor) {
        Validator.validateNotNull(sensor, "Sensor");
        sensor.accept(_sensorVisitorForDataModel);
        SensorDataModel sensorDataModel = _sensorVisitorForDataModel.getSensorDataModel();
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(sensorDataModel);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return sensor;
    }

    @Override
    public List<ISensor> findAll() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM SensorDataModel e");
            List<SensorDataModel> listDataModel = query.getResultList();
            return _dataModelAssembler.toDomain(listDataModel);
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<ISensor> ofIdentity(SensorID sensorID)
    {
        EntityManager em = getEntityManager();
        try {
            SensorDataModel sensorDataModel = em.find(SensorDataModel.class, sensorID);
            if (sensorDataModel == null) {
                return Optional.empty();
            }
            ISensor sensor = _dataModelAssembler.toDomain(sensorDataModel);
            return Optional.of(sensor);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean containsOfIdentity(SensorID sensorID) {
        return ofIdentity(sensorID).isPresent();
    }
}
