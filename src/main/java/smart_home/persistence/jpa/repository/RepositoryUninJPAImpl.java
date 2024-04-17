package smart_home.persistence.jpa.repository;

import jakarta.persistence.*;
import smart_home.domain.repository.IUnitRepository;
import smart_home.domain.unit.IUnitFactory;
import smart_home.domain.unit.Unit;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.UnitDataModel;
import smart_home.value_object.UnitID;

import java.util.List;
import java.util.Optional;

public class RepositoryUninJPAImpl implements IUnitRepository {

    private IDataModelAssembler<UnitDataModel, Unit> _dataModelConverter;

    private EntityManagerFactory _factory;

    public RepositoryUninJPAImpl(IDataModelAssembler<UnitDataModel, Unit> dataModelAssembler){
        validateDataModelConverter(dataModelAssembler);
        _dataModelConverter = dataModelAssembler;
        _factory = Persistence.createEntityManagerFactory("smart_home");
    }

    private void validateDataModelConverter(IDataModelAssembler<UnitDataModel, Unit> entity){
        if (entity == null) {
            throw new IllegalArgumentException("Data model assembler cannot be null.");
        }
    }

    private EntityManager getEntityManager(){
        EntityManager manager = _factory.createEntityManager();
        return manager;
    }

    @Override
    public Unit save(Unit unit){
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        UnitDataModel unitDataModel = new UnitDataModel(unit);
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(unitDataModel);
            tx.commit();
        } catch (RuntimeException e) {
            if(tx.isActive())
            {
                tx.rollback();
            }
            throw e;
        }
        finally {
            em.close();
        }
        return unit;
    }

    @Override
    public List<Unit> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            Query query = entityManager.createQuery(
                    "SELECT e FROM UnitDataModel e");
            List<UnitDataModel> listDataModel = query.getResultList();
            List<Unit> listDomain = _dataModelConverter.toDomain(listDataModel);
            return listDomain;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<Unit> ofIdentity(UnitID objectID) {
        EntityManager entityManager = getEntityManager();
        try {
            UnitDataModel unitDataModel = entityManager.find(UnitDataModel.class, objectID);
            if (unitDataModel == null) {
                return Optional.empty();
            } else {
                Unit unit = _dataModelConverter.toDomain(unitDataModel);
                return Optional.of(unit);
            }
        } finally {
            entityManager.close();
        }
    }


    @Override
    public boolean containsOfIdentity(UnitID objectID) {
        Optional<Unit> unitDataModel = ofIdentity(objectID);
        return unitDataModel.isPresent();
    }
}
