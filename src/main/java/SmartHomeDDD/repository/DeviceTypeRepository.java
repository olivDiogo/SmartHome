package SmartHomeDDD.repository;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.DeviceType.DeviceType;
import SmartHomeDDD.valueObject.DeviceTypeID;

import java.util.List;
import java.util.Optional;

public class DeviceTypeRepository implements Repository<DeviceTypeID, DeviceType> {
    @Override
    public DeviceType save(DeviceType entity) {
        return null;
    }

    @Override
    public List<DeviceType> findAll() {
        return null;
    }

    @Override
    public Optional<DeviceType> ofIdentity(DeviceTypeID objectID) {
        return Optional.empty();
    }

    @Override
    public boolean containsOfIdentity(DeviceTypeID objectID) {
        return false;
    }
}
