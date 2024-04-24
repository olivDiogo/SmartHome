package smart_home.persistence.spring_data.log;

import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import smart_home.domain.log.Log;
import smart_home.domain.repository.ILogRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.LogDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.LogID;
import smart_home.value_object.SensorTypeID;

public class LogSpringDataRepository implements ILogRepository {

  ILogSpringDataRepository repository;
  EntityManagerFactory factory;
  IDataModelAssembler<LogDataModel, Log> assembler;

  /**
   * LogSpringDataRepository constructor
   *
   * @param repository ILogSpringDataRepository object
   * @param assembler  IDataModelAssembler object
   */
  public LogSpringDataRepository(
      ILogSpringDataRepository repository, IDataModelAssembler<LogDataModel, Log> assembler) {

    Validator.validateNotNull(repository, "Log repository");
    this.repository = repository;
    Validator.validateNotNull(assembler, "Log data model assembler");
    this.assembler = assembler;
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

    repository.save(dataModel);
    return entity;
  }

  /**
   * Method to find all domain entities.
   *
   * @return
   */
  @Override
  public List<Log> findAll() {
    List<LogDataModel> listDataModelSaved = repository.findAll();
    return assembler.toDomain(listDataModelSaved);
  }

  /**
   * Method to find a domain entity by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  @Override
  public Optional<Log> ofIdentity(LogID objectID) {
    Optional<LogDataModel> dataModelSaved = repository.findById(objectID.getID());
    if (dataModelSaved.isPresent()) {
      Log domain = assembler.toDomain(dataModelSaved.get());
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
    return repository.existsById(objectID.getID());
  }

  /**
   * Method to find logs by device ID and time period
   *
   * @param deviceID DeviceID object
   * @param period   object which contains a LocalDateTime start and end
   * @return List of Log
   */
  @Override
  public List<Log> findByDeviceIDAndDatePeriodBetween(DeviceID deviceID, DatePeriod period) {
    List<LogDataModel> models =
        repository.findByDeviceIDAndTimestampBetween(
            deviceID, period.getStartDate(), period.getEndDate());
    return assembler.toDomain(models);
  }

  @Override
  public List<Log> findByDeviceIDAndSensorTypeAndDatePeriod(DeviceID deviceID,
      SensorTypeID sensorTypeID, DatePeriod period) {
    return null;
  }
}
