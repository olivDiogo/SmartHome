package smart_home.persistence.spring_data.log;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import smart_home.domain.log.Log;
import smart_home.domain.repository.ILogRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.LogDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.LogID;
import java.util.List;
import java.util.Optional;

public class LogSpringDataRepository implements ILogRepository {
  ILogSpringDataRepository _repository;
  EntityManagerFactory _factory;
  IDataModelAssembler<LogDataModel, Log> _assembler;

  /**
   * LogSpringDataRepository constructor
   *
   * @param repository
   * @param assembler
   */
  public LogSpringDataRepository(
      ILogSpringDataRepository repository, IDataModelAssembler assembler) {
    this._factory = Persistence.createEntityManagerFactory("smart_home");

    Validator.validateNotNull(repository, "Log repository");
    this._repository = repository;
    Validator.validateNotNull(assembler, "Log data model assembler");
    this._assembler = assembler;
  }

  /** Method to get the Entity Manager. */
  private EntityManager getEntityManager() {
    try {
      EntityManager manager = _factory.createEntityManager();
      return manager;
    } catch (Exception e) {
      throw new RuntimeException("Error creating the entity manager", e);
    }
  }

  /**
   * Method to save a domain entity.
   *
   * @param entity is the domain entity to be saved.
   * @return the saved domain entity.
   */
  @Override
  public Log save(Log entity) {
    Validator.validateNotNull(entity, "Log");

    LogDataModel dataModel = new LogDataModel(entity);

    _repository.save(dataModel);
    return entity;
  }

  /**
   * Method to find all domain entities.
   *
   * @return
   */
  @Override
  public List<Log> findAll() {
    return _assembler.toDomain(_repository.findAll());
  }

  /**
   * Method to find a domain entity by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  @Override
  public Optional<Log> ofIdentity(LogID objectID) {
    Optional<LogDataModel> dataModelSaved = _repository.findById(objectID.getID());
    if (dataModelSaved.isPresent()) {
      Log domain = _assembler.toDomain(dataModelSaved.get());
      return Optional.of(domain);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Method to delete a domain entity by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  @Override
  public boolean containsOfIdentity(LogID objectID) {
    return _repository.existsById(objectID.getID());
  }

  /**
   * Method to find logs by device ID and time period
   *
   * @param deviceID DeviceID object
   * @param period object which contains a LocalDateTime start and end
   * @return List of Log
   */
  @Override
  public List<Log> findByDeviceIDAndDatePeriodBetween(DeviceID deviceID, DatePeriod period) {
    List<LogDataModel> models =
        _repository.findByDeviceIDAndTimestampBetween(
            deviceID, period.getStartDate(), period.getEndDate());
    return _assembler.toDomain(models);
  }
}
