package SmartHomeDDD.repository;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.SensorModel.SensorModel;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorModelID;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SensorModelRepository implements Repository<SensorModelID, SensorModel> {
    private Map<SensorModelID, SensorModel> DATA = new LinkedHashMap<>();

    @Override
    public SensorModel save(SensorModel entity) {
        if (entity == null)
            throw new IllegalArgumentException("SensorModel cannot be null.");
        else if (containsOfIdentity(entity.getID()))
            throw new IllegalArgumentException("SensorModel already exists.");
        else

            DATA.put(entity.getID(), entity);
        return entity;
    }
    @Override
    public List<SensorModel> findAll() {
        return List.copyOf(DATA.values().stream().toList());
    }

    @Override
    public Optional<SensorModel> ofIdentity(SensorModelID objectID) {
        return Optional.ofNullable(DATA.get(objectID));
    }

    @Override
    public boolean containsOfIdentity(SensorModelID objectID) {
        return DATA.containsKey(objectID);
    }
}
