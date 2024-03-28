package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.SensorModelDTO;
import SmartHomeDDD.ddd.Assembler;
import SmartHomeDDD.domain.SensorModel.SensorModel;

import java.util.List;

public class SensorModelAssembler implements Assembler<SensorModel, SensorModelDTO> {

    @Override
    public SensorModelDTO domainToDTO(SensorModel domainEntity) {
        if (domainEntity == null) {
            throw new IllegalArgumentException("The Sensor Model cannot be null.");
        }

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


