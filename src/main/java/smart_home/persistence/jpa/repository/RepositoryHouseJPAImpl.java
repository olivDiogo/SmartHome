package smart_home.persistence.jpa.repository;

import jakarta.persistence.*;
import smart_home.domain.house.House;
import smart_home.domain.repository.IHouseRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.HouseDataModel;
import smart_home.value_object.HouseID;

import java.util.List;
import java.util.Optional;

public class RepositoryHouseJPAImpl implements IHouseRepository {
        private IDataModelAssembler<HouseDataModel, House> _dataModelConverter;
        private EntityManagerFactory _factory;

        public RepositoryHouseJPAImpl(IDataModelAssembler<HouseDataModel, House> dataModelConverter) {
            _dataModelConverter = dataModelConverter;
            _factory = Persistence.createEntityManagerFactory("smartHome");
        }
        private EntityManager getEntityManager() {
            EntityManager manager = _factory.createEntityManager();
            return manager;
        }

        @Override
        public House save(House house) {
            if (house == null) {
                throw new IllegalArgumentException();
            }
            HouseDataModel houseDataModel = new HouseDataModel(house);
            EntityManager em = getEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(houseDataModel);
            tx.commit();
            em.close();
            return house;
        }

        @Override
        public List<House> findAll() {
            Query query = getEntityManager().createQuery(
                    "SELECT e FROM HouseDataModel e");
            List<HouseDataModel> listDataModel = query.getResultList();
            List<House> listDomain = _dataModelConverter.toDomain(listDataModel);
            return listDomain;
        }

        @Override
        public Optional<House> ofIdentity(HouseID objectID) {
            HouseDataModel houseDataModel = getEntityManager().find(HouseDataModel.class, objectID);
            if (houseDataModel == null) {
                return Optional.empty();
            }
            else {
                House house = _dataModelConverter.toDomain(houseDataModel);
                return Optional.of(house);
            }
        }

        @Override
        public boolean containsOfIdentity(HouseID objectID) {
            Optional<House> houseDataModel = ofIdentity(objectID);
            return houseDataModel.isPresent();
        }
    }

