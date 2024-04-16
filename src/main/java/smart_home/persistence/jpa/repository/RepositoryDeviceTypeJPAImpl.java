package smart_home.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import smart_home.domain.device_type.DeviceType;
import smart_home.domain.repository.IDeviceTypeRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.DeviceTypeDataModel;
import smart_home.value_object.DeviceTypeID;

import java.util.List;
import java.util.Optional;

public class RepositoryDeviceTypeJPAImpl implements IDeviceTypeRepository {

  private IDataModelAssembler<DeviceTypeDataModel, DeviceType> _dataModelAssembler;
  private EntityManagerFactory _factory;

  /**
   * Creates an instance of {@link RepositoryDeviceTypeJPAImpl} with the provided data model
   * assembler.
   */
  public RepositoryDeviceTypeJPAImpl(
      IDataModelAssembler<DeviceTypeDataModel, DeviceType> dataModelAssembler) {
    validateDataModelAssembler(dataModelAssembler);
    this._dataModelAssembler = dataModelAssembler;
    this._factory = Persistence.createEntityManagerFactory("smart_home");
  }

  /**
   * Validates the data model assembler
   *
   * @param entity the data model assembler
   */
  private void validateDataModelAssembler(
      IDataModelAssembler<DeviceTypeDataModel, DeviceType> entity) {
    if (entity == null) throw new IllegalArgumentException("Data model assembler cannot be null.");
  }

  /**
   * Get the entity manager
   *
   * @return the entity manager
   */
  private EntityManager getEntityManager() {
    EntityManager manager = this._factory.createEntityManager();
    return manager;
  }

  /**
   * Saves the device type in the database.
   *
   * @param deviceType is the domain entity to be saved.
   * @return DeviceType
   */
  @Override
  public DeviceType save(DeviceType deviceType) {
    if (deviceType == null) {
      throw new IllegalArgumentException("Device Type cannot be null");
    }

    DeviceTypeDataModel model = new DeviceTypeDataModel(deviceType);

    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(model);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
    return deviceType;
  }

  /**
   * Finds all device types
   *
   * @return a list of device types
   */
  @Override
  public List<DeviceType> findAll() {
    EntityManager em = getEntityManager();
    try {
      List<DeviceTypeDataModel> deviceTypeDataModels =
          em.createQuery("SELECT d FROM DeviceTypeDataModel d", DeviceTypeDataModel.class)
              .getResultList();
      return _dataModelAssembler.toDomain(deviceTypeDataModels);
    } finally {
      em.close();
    }
  }

  /**
   * Finds a device type by its ID
   *
   * @param deviceTypeID the ID of the device type
   * @return the device type if found, otherwise Optional.empty()
   */
  @Override
  public Optional<DeviceType> ofIdentity(DeviceTypeID deviceTypeID) {
    EntityManager em = getEntityManager();
    try {
      DeviceTypeDataModel deviceTypeDataModel = em.find(DeviceTypeDataModel.class, deviceTypeID);
      if (deviceTypeDataModel == null) {
        return Optional.empty();
      }
      return Optional.of(_dataModelAssembler.toDomain(deviceTypeDataModel));
    } finally {
      em.close();
    }
  }

  /**
   * Verifies if a device type exists by {@link java.math.BigDecimal
   *
   * @param deviceTypeID the ID of the device type
   * @return true if the device type exists, otherwise false
   */
  @Override
  public boolean containsOfIdentity(DeviceTypeID deviceTypeID) {
    Optional<DeviceType> deviceTypeModel = ofIdentity(deviceTypeID);

    return deviceTypeModel.isPresent();
  }
}
