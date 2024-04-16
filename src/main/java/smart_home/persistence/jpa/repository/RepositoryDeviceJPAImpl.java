package smart_home.persistence.jpa.repository;

import jakarta.persistence.*;
import smart_home.domain.device.Device;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.DeviceDataModel;
import smart_home.value_object.DeviceID;
import smart_home.value_object.RoomID;

import java.util.List;
import java.util.Optional;

/**
 * A JPA repository implementation for Device entities, using the Jakarta Persistence API.
 * This repository is responsible for performing database operations on Device entities
 * using a JPA EntityManager.
 */
public class RepositoryDeviceJPAImpl implements IDeviceRepository {

    private EntityManagerFactory _factory;
    private IDataModelAssembler<DeviceDataModel, Device> _dataModelConverter;

    /**
     * Constructs a new RepositoryDeviceJPAImpl.
     *
     * @param dataModelConverter A converter to transform DeviceDataModel objects to Device domain objects.
     */

    public RepositoryDeviceJPAImpl(IDataModelAssembler<DeviceDataModel, Device> dataModelConverter) {
        validateDataModelConverter(dataModelConverter);
        _factory = Persistence.createEntityManagerFactory("smart_home");
        _dataModelConverter = dataModelConverter;
    }

    /**
     * Validates the data model converter.
     */
    private void validateDataModelConverter(IDataModelAssembler<DeviceDataModel, Device> dataModelConverter) {
        if (dataModelConverter == null) {
            throw new IllegalArgumentException("The data model converter must not be null.");
        }
    }

    /**
     * Retrieves an EntityManager instance from the EntityManagerFactory.
     *
     * @return EntityManager to be used for database operations.
     */
    private EntityManager getEntityManager() {
        return _factory.createEntityManager();
    }

    /**
     * Saves a Device entity into the database.
     *
     * @param device The Device entity to be saved.
     * @return The saved Device entity.
     * @throws IllegalArgumentException if the device parameter is null.
     */
    @Override
    public Device save(Device device) {
        if (device == null) {
            throw new IllegalArgumentException("The provided entity must not be null.");
        }
        DeviceDataModel deviceDataModel = new DeviceDataModel(device);
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(deviceDataModel);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return device;
    }

    /**
     * Retrieves all Device entities from the database.
     * @return A list of Device domain objects.
     */

    @Override
    public List<Device> findAll() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM DeviceDataModel e");
            List<DeviceDataModel> listDataModel = query.getResultList();
            return _dataModelConverter.toDomain(listDataModel);
        } finally {
            em.close();
        }
    }

    /**
     * Retrieves a Device entity from the database by its unique identifier.
     * @param deviceID is the unique identifier of the domain entity.
     * @return An Optional containing the found Device, or an empty Optional if no device is found.
     */
    @Override
    public Optional<Device> ofIdentity(DeviceID deviceID) {
        EntityManager em = getEntityManager();
        try {
            DeviceDataModel deviceDataModel = em.find(DeviceDataModel.class, deviceID.getID());
            if (deviceDataModel == null) {
                return Optional.empty();
            } else {
                Device device = _dataModelConverter.toDomain(deviceDataModel);
                return Optional.of(device);
            }
        } finally {
            em.close();

        }

    }
    /**
     * Checks if a Device with a specific identity exists in the database.
     *
     * @param objectID The unique identifier of the Device.
     * @return true if a Device with the specified identifier exists, false otherwise.
     */

    @Override
    public boolean containsOfIdentity(DeviceID objectID) {
        return ofIdentity(objectID).isPresent();
    }

    /**
     * Retrieves devices associated with a specific room.
     *
     * @param roomId The identifier of the room.
     * @return A list of Device domain objects located in the specified room.
     */

    @Override
    public List<Device> getDevicesByRoomId(RoomID roomId) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM DeviceDataModel e WHERE e.roomID = :roomId");
            query.setParameter("roomId", roomId.getID());
            List<DeviceDataModel> listDataModel = query.getResultList();
            return _dataModelConverter.toDomain(listDataModel);
        } finally {
            em.close();
        }

    }
}
