package smart_home.persistence.spring_data.device;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import smart_home.domain.device.Device;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.DeviceDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.DeviceID;
import smart_home.value_object.RoomID;


/**
 * Implements the device repository using Spring Data JPA.
 * This class provides a concrete implementation of the {@link IDeviceRepository} for device entity management.
 */
public class DeviceSpringDataRepository implements IDeviceRepository {

  private final IDeviceSpringDataRepository _repository;
    private EntityManagerFactory _factory;
  private final IDataModelAssembler<DeviceDataModel, Device> _assembler;

    /**
     * Constructs a new DeviceSpringDataRepository with necessary dependencies.
     *
     * @param repository the Spring Data repository handling Device data models.
     * @param assembler the assembler to convert between Device domain objects and data models.
     */
    public DeviceSpringDataRepository(IDeviceSpringDataRepository repository,
        IDataModelAssembler<DeviceDataModel, Device> assembler) {
        Validator.validateNotNull(repository, "Device repository");
        this._repository = repository;
        Validator.validateNotNull(assembler, "Device data model assembler");
        this._assembler = assembler;
    }

    /**
     * Provides an EntityManager for JPA operations.
     *
     * @return the EntityManager instance.
     * @throws RuntimeException if the EntityManager cannot be created.
     */
    private EntityManager getEntityManager() {
            EntityManager manager = _factory.createEntityManager();
            return manager;
    }

    /**
     * Finds all devices associated with a specific room.
     *
     * @param roomId the unique identifier of the room.
     * @return a list of Device domain objects.
     */
    @Override
    public List<Device> findBy_roomID(RoomID roomId) {
        List<DeviceDataModel> deviceDataModels = this._repository.findBy_roomID(roomId.toString());
        return _assembler.toDomain(deviceDataModels);
    }

    /**
     * Updates an existing device entity in the database.
     *
     * @param device the Device domain object to update.
     * @return the updated Device domain object, or null if update was not successful.
     */
    @Override
    public Device update(Device device) {
        DeviceDataModel deviceDataModel = getEntityManager().find(DeviceDataModel.class, device.getID().getID());

        if (deviceDataModel != null) {
            boolean isUpdated = deviceDataModel.updateFromDomain(device);
            if (isUpdated) {
                _repository.save(deviceDataModel);
                return device;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Saves a new device entity to the database.
     *
     * @param entity the Device to save.
     * @return the saved Device domain object.
     */
    @Override
    public Device save(Device entity) {
        Validator.validateNotNull(entity, "Device");
        DeviceDataModel dataModel = new DeviceDataModel(entity);
        _repository.save(dataModel);
        return entity;
    }

    /**
     * Retrieves all devices from the database.
     *
     * @return a list of Device domain objects.
     */
    @Override
    public List<Device> findAll() {
        List<DeviceDataModel> listDeviceDataModelSaved = _repository.findAll();
        return _assembler.toDomain(listDeviceDataModelSaved);
    }

    /**
     * Retrieves a device by its unique identifier.
     *
     * @param objectID the unique identifier of the device.
     * @return an Optional containing the found Device or empty if no device is found.
     */
    @Override
    public Optional<Device> ofIdentity(DeviceID objectID) {
        Optional<DeviceDataModel> dataModelSaved = _repository.findById(objectID.getID());
        if (dataModelSaved.isPresent()) {
            Device domain = _assembler.toDomain(dataModelSaved.get());
            return Optional.of(domain);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Checks whether a device with a specific identifier exists in the database.
     *
     * @param objectID the unique identifier of the device.
     * @return true if the device exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(DeviceID objectID) {
        return _repository.existsById(objectID.getID());
    }
}
