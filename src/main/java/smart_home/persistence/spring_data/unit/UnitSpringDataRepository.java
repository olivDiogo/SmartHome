package smart_home.persistence.spring_data.unit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import smart_home.domain.repository.IUnitRepository;
import smart_home.domain.unit.Unit;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.UnitDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.UnitID;

import java.util.List;
import java.util.Optional;

public class UnitSpringDataRepository implements IUnitRepository {

    IUnitSpringDataRepository _repository;

    EntityManagerFactory _factory;

    IDataModelAssembler<UnitDataModel, Unit> _assembler;

    /**
     * Constructs a new repository instance with the specified repository and data model assembler.
     *
     * @param repository the repository to use
     * @param assembler the converter to transform data models to domain models and vice versa
     */
    public UnitSpringDataRepository(IUnitSpringDataRepository repository, IDataModelAssembler assembler) {
        this._factory = Persistence.createEntityManagerFactory("smart_home");

        Validator.validateNotNull(repository, "Unit repository");
        this._repository = repository;

        Validator.validateNotNull(assembler, "Unit data model assembler");
        this._assembler = assembler;
    }

    /**
     * Saves the specified unit entity to the database.
     *
     * @param entity the unit entity to save
     * @return the saved unit entity
     * @throws IllegalArgumentException if the entity is null
     */
    @Override
    public Unit save(Unit entity) {
        Validator.validateNotNull(entity, "Unit");

        UnitDataModel dataModel = new UnitDataModel(entity);

        _repository.save(dataModel);

        return entity;
    }

    /**
     * Finds all unit entities in the database.
     *
     * @return a list of all unit entities
     */
    @Override
    public List<Unit> findAll() {
        List<UnitDataModel> unitDataModels = _repository.findAll();
        List<Unit> units = _assembler.toDomain(unitDataModels);
        return units;
    }

    /**
     * Finds the unit entity with the specified identity.
     *
     * @param id the identity of the unit entity to find
     * @return an optional containing the unit entity if it exists, otherwise an empty optional
     * @throws IllegalArgumentException if the identity is null
     */
    @Override
    public Optional<Unit> ofIdentity(UnitID id) {
        Optional<UnitDataModel> unitDataModel = _repository.findById(id.getID());

        if (unitDataModel.isPresent()) {
            Unit domain = _assembler.toDomain(unitDataModel.get());
            return Optional.of(domain);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Checks if a unit entity with the specified identity exists in the database.
     *
     * @param id the identity of the unit entity to check
     * @return true if the unit entity exists, false otherwise
     */
    @Override
    public boolean containsOfIdentity(UnitID id) {
        return _repository.existsById(id.getID());
    }

}
