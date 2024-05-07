package smarthome.persistence.spring_data.sensor;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.repository.ISensorRepository;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.SensorID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.jpa.data_model.SensorDataModel;
import smarthome.utils.Validator;


public class SensorSpringDataRepository implements ISensorRepository {

  private final ISensorSpringDataRepository repository;

  private final IDataModelAssembler<SensorDataModel, ISensor> assembler;

  /**
   * Constructs a new repository instance with the specified entity manager factory and data model
   * assembler.
   *
   * @param repository         the repository to use
   * @param dataModelAssembler the converter to transform data models to domain models and vice
   *                           versa
   */
  public SensorSpringDataRepository(ISensorSpringDataRepository repository,
      IDataModelAssembler<SensorDataModel, ISensor> dataModelAssembler) {
    Validator.validateNotNull(dataModelAssembler, "Data model assembler");
    assembler = dataModelAssembler;
    Validator.validateNotNull(repository, "Repository");
    this.repository = repository;
  }

  /**
   * Saves the specified sensor entity to the database.
   *
   * @param sensor the sensor entity to save
   * @return the saved sensor entity
   * @throws IllegalArgumentException if the entity is null
   */
  @Override
  public ISensor save(ISensor sensor) {
    Validator.validateNotNull(sensor, "Sensor");
    SensorDataModel sensorDataModel = new SensorDataModel(sensor);
    repository.save(sensorDataModel);
    return sensor;
  }

  /**
   * Finds all sensor entities in the database.
   *
   * @return a list of all sensor entities
   */
  @Override
  public List<ISensor> findAll() {
    List<SensorDataModel> sensorDataModels = this.repository.findAll();
    List<ISensor> sensors = assembler.toDomain(sensorDataModels);
    return sensors;
  }

  /**
   * Finds the sensor entity with the specified identity.
   *
   * @param objectID the identity of the sensor entity to find
   * @return the sensor entity with the specified identity
   */
  @Override
  public Optional<ISensor> ofIdentity(SensorID objectID) {
    Optional<SensorDataModel> sensorDataModel = this.repository.findById(objectID.getID());

    if (sensorDataModel.isPresent()) {
      SensorDataModel sensorDataModel1 = sensorDataModel.get();
      ISensor sensor = assembler.toDomain(sensorDataModel1);
      return Optional.of(sensor);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Checks if the repository contains the specified sensor entity.
   *
   * @param objectID the identity of the sensor entity to check
   * @return true if the repository contains the sensor entity, false otherwise
   */
  @Override
  public boolean containsOfIdentity(SensorID objectID) {
    return this.repository.existsById(objectID.getID());
  }


}

