package smart_home.persistence.jpa.repository;

import jakarta.persistence.*;
import smart_home.domain.log.Log;
import smart_home.domain.repository.ILogRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.LogDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.LogID;
import smart_home.value_object.SensorTypeID;
import java.util.List;
import java.util.Optional;

public class LogRepositoryJPAImpl implements ILogRepository {
  private EntityManagerFactory factory;
  private IDataModelAssembler<LogDataModel, Log> dataModelAssembler;

  /**
   * LogRepositoryJPAImpl constructor
   *
   * @param dataModelAssembler IDataModelAssembler object
   */
  public LogRepositoryJPAImpl(IDataModelAssembler<LogDataModel, Log> dataModelAssembler) {
    Validator.validateNotNull(dataModelAssembler, "Data model assembler");

    this.dataModelAssembler = dataModelAssembler;
    factory = Persistence.createEntityManagerFactory("smart_home");
  }

  /**
   * Method to get entity manager
   *
   * @return EntityManager
   */
  private EntityManager getEntityManager() {
    EntityManager manager = factory.createEntityManager();
    return manager;
  }

  /**
   * Method to save log
   *
   * @param log is the domain entity to be saved.
   * @return Log
   */
  @Override
  public Log save(Log log) {
    if (log == null) {
      throw new IllegalArgumentException("Log cannot be null");
    }

    LogDataModel logDataModel = new LogDataModel(log);

    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(logDataModel);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
    return log;
  }

  /**
   * Method to find all logs
   *
   * @return List<Log>
   */
  @Override
  public List<Log> findAll() {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createQuery("SELECT e FROM LogDataModel e");

      List<LogDataModel> logDataModels = query.getResultList();
      List<Log> logs = dataModelAssembler.toDomain(logDataModels);

      return logs;
    } finally {
      em.close();
    }
  }

  /**
   * Method to find log by ID
   *
   * @param logID LogID object
   * @return Optional<Log>
   */
  @Override
  public Optional<Log> ofIdentity(LogID logID) {
    EntityManager em = getEntityManager();
    try {
      LogDataModel logDataModel = em.find(LogDataModel.class, logID);
      if (logDataModel == null) {
        return Optional.empty();
      }
      Log log = dataModelAssembler.toDomain(logDataModel);
      return Optional.of(log);
    } finally {
      em.close();
    }
  }

  /**
   * Method to check if log exists by identity
   *
   * @param logID LogID object
   * @return boolean
   */
  @Override
  public boolean containsOfIdentity(LogID logID) {
    Optional<Log> logDataModel = ofIdentity(logID);
    return logDataModel.isPresent();
  }

  /**
   * Method to find logs by device ID and time period
   *
   * @param deviceID DeviceID object
   * @param period DatePeriod object
   * @return List<Log>
   */
  @Override
  public List<Log> findByDeviceIDAndDatePeriodBetween(DeviceID deviceID, DatePeriod period) {
    EntityManager em = getEntityManager();
    try {
      Query query =
          em.createQuery(
              "SELECT e FROM LogDataModel e WHERE e._deviceID = :deviceID AND e._timestamp BETWEEN :start AND :end");
      query.setParameter("deviceID", deviceID.getID());
      query.setParameter("start", period.getStartDate());
      query.setParameter("end", period.getEndDate());

      List<LogDataModel> logDataModels = query.getResultList();
      List<Log> logs = dataModelAssembler.toDomain(logDataModels);

      return logs;
    } finally {
      em.close();
    }
  }

  /**
   * Method to find logs by device ID, sensor type and time period
   *
   * @param deviceID DeviceID object
   * @param sensorTypeID SensorTypeID object
   * @param period DatePeriod object
   * @return List<Log>
   */
  @Override
  public List<Log> findByDeviceIDAndSensorTypeAndDatePeriodBetween(DeviceID deviceID,
      SensorTypeID sensorTypeID, DatePeriod period) {
    EntityManager em = getEntityManager();
    try {
      Query query =
          em.createQuery(
              "SELECT e FROM LogDataModel e WHERE e._deviceID = :deviceID AND e._description = :sensorTypeID AND e._timestamp BETWEEN :start AND :end");
      query.setParameter("deviceID", deviceID.getID());
      query.setParameter("sensorTypeID", sensorTypeID.getID());
      query.setParameter("start", period.getStartDate());
      query.setParameter("end", period.getEndDate());

      List<LogDataModel> logDataModels = query.getResultList();
      List<Log> logs = dataModelAssembler.toDomain(logDataModels);

      return logs;
    } finally {
      em.close();
    }
  }
}
