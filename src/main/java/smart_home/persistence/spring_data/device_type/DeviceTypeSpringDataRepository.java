package smart_home.persistence.spring_data.device_type;

import java.util.List;
import java.util.Optional;
import smart_home.domain.device_type.DeviceType;
import smart_home.domain.repository.IDeviceTypeRepository;
import smart_home.persistence.assembler.IDataModelAssembler;
import smart_home.persistence.jpa.data_model.DeviceTypeDataModel;
import smart_home.utils.Validator;
import smart_home.value_object.DeviceTypeID;

public class DeviceTypeSpringDataRepository implements IDeviceTypeRepository {
    IDeviceTypeSpringDataRepository repository;
    IDataModelAssembler <DeviceTypeDataModel, DeviceType> assembler;

  public DeviceTypeSpringDataRepository(IDeviceTypeSpringDataRepository repository,
      IDataModelAssembler<DeviceTypeDataModel, DeviceType> assembler) {
        Validator.validateNotNull(repository, "Repository");
        this.repository = repository;
        Validator.validateNotNull(assembler, "Assembler");
        this.assembler = assembler;
    }

    /**
     * Method to save a domain entity.
     *
     * @param entity is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public DeviceType save(DeviceType entity) {
      Validator.validateNotNull(entity, "DeviceType");

        DeviceTypeDataModel dataModel = new DeviceTypeDataModel(entity);
      repository.save(dataModel);
        return entity;
    }

    /**
     * Method to find all domain entities.
     *
     * @return
     */
    @Override
    public List<DeviceType> findAll() {
        List<DeviceTypeDataModel> listDeviceTypeDataModelSaved = repository.findAll();
        List<DeviceType> listDomain = assembler.toDomain(listDeviceTypeDataModelSaved);
        return listDomain;
    }

    /**
     * Method to find a domain entity by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return the domain entity.
     */
    @Override
    public Optional<DeviceType> ofIdentity(DeviceTypeID objectID) {
        Optional<DeviceTypeDataModel> dataModelSaved = repository.findById(objectID.getID());
        if(dataModelSaved.isPresent()){
            DeviceType domain = assembler.toDomain(dataModelSaved.get());
            return Optional.of(domain);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Method to check if a domain entity exists by its unique identifier.
     *
     * @param objectId is the unique identifier of the domain entity.
     * @return true if the domain entity exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(DeviceTypeID objectId) {
        return this.repository.existsById(objectId.getID());
    }
}
