package smart_home.mapper;


import smart_home.ddd.IAssembler;
import smart_home.domain.log.Log;
import smart_home.dto.LogDTO;
import smart_home.utils.Validator;


import java.util.List;

public class LogAssembler implements IAssembler<Log, LogDTO> {

    /**
     * Converts a Log object to a LogDTO object.
     * @param domainEntity is the domain entity to be converted.
     * @return the LogDTO object.
     */
    @Override
    public LogDTO domainToDTO(Log domainEntity) {
        Validator.validateNotNull(domainEntity, "Log");

        String logID = domainEntity.getID().toString();
        String deviceID = domainEntity.getDeviceID().toString();
        String sensorID = domainEntity.getSensorID().toString();
        String sensorTypeID = domainEntity.getID().toString();
        String reading = domainEntity.getValue();
        String timestamp = domainEntity.getTimeStamp().toString();
        String unitID = domainEntity.getUnit().toString();

        return new LogDTO(logID, deviceID, sensorID, sensorTypeID, reading, timestamp, unitID);
    }


    @Override
    public List<LogDTO> domainToDTO(List<Log> domainEntities) {
        if (domainEntities == null || domainEntities.isEmpty() || domainEntities.contains(null)) {
            throw new IllegalArgumentException("The list of Logs cannot be null or empty.");
        }
        return domainEntities.stream().map(this::domainToDTO).toList();
    }
}
