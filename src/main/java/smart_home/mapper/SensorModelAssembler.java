package smart_home.mapper;

import org.apache.commons.lang3.Validate;
import smart_home.ddd.IAssembler;
import smart_home.domain.sensor_model.SensorModel;
import smart_home.dto.SensorModelDTO;
import smart_home.utils.Validator;

import java.util.List;

public class SensorModelAssembler implements IAssembler<SensorModel, SensorModelDTO> {

    @Override
    public SensorModelDTO domainToDTO(SensorModel domainEntity) {
        Validator.validateNotNull(domainEntity, "Sensor Model");

        String sensorModelID = domainEntity.getID().toString();
        String sensorModelName = domainEntity.getSensorModelName().toString();
        String sensorModelPath = domainEntity.getModelPath().toString();

        SensorModelDTO sensorModelDTO = new SensorModelDTO(sensorModelID, sensorModelName, sensorModelPath);
        return sensorModelDTO;
    }

    @Override
    public List<SensorModelDTO> domainToDTO(List<SensorModel> domainEntities) {
        if (domainEntities == null || domainEntities.isEmpty()) {
            throw new IllegalArgumentException("The list of Sensor Models cannot be null or empty.");
        }
        List<SensorModelDTO> sensorModelsDTO = domainEntities.stream().map(this::domainToDTO).toList();
        return sensorModelsDTO;
    }


}


