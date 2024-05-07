package smarthome.persistence.mem;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.repository.ISensorModelRepository;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.Validator;

@Repository
public class SensorModelRepository implements ISensorModelRepository {

  private final Map<ModelPath, SensorModel> DATA = new LinkedHashMap<>();

  @Override
  public SensorModel save(SensorModel entity) {
    Validator.validateNotNull(entity, "Sensor Model");

    if (containsOfIdentity(entity.getID())) {
      throw new IllegalArgumentException("SensorModel already exists.");
    } else {

      DATA.put(entity.getModelPath(), entity);
    }
    return entity;
  }

  @Override
  public List<SensorModel> findAll() {
    return List.copyOf(DATA.values().stream().toList());
  }

  @Override
  public Optional<SensorModel> ofIdentity(ModelPath objectID) {
    return Optional.ofNullable(DATA.get(objectID));
  }

  @Override
  public boolean containsOfIdentity(ModelPath objectID) {
    return DATA.containsKey(objectID);
  }


  @Override
  public List<SensorModel> findBySensorTypeId(SensorTypeID sensorTypeID) {
    return DATA.values().stream()
        .filter(sensorModel -> sensorModel.getSensorTypeID().equals(sensorTypeID)).toList();
  }
}
