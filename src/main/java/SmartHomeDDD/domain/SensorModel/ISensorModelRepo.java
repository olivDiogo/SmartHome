package SmartHomeDDD.domain.SensorModel;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.valueObject.ModelPath;
import SmartHomeDDD.valueObject.SensorTypeID;

import java.util.List;

public interface ISensorModelRepo extends Repository<ModelPath, SensorModel> {

    List<SensorModel> findBySensorTypeId(SensorTypeID sensorTypeID);
}
