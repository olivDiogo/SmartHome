package smartHome.domain.repository;

import smartHome.ddd.IRepository;
import smartHome.domain.sensorModel.SensorModel;
import smartHome.valueObject.ModelPath;
import smartHome.valueObject.SensorTypeID;

import java.util.List;

public interface ISensorModelRepository extends IRepository<ModelPath, SensorModel> {

    List<SensorModel> findBySensorTypeId(SensorTypeID sensorTypeID);
}
