package SmartHomeDDD.assembler;


import SmartHomeDDD.DTO.DeviceTypeDTO;
import SmartHomeDDD.ddd.Assembler;
import SmartHomeDDD.domain.DeviceType.DeviceType;
import SmartHomeDDD.valueObject.DeviceTypeID;
import SmartHomeDDD.valueObject.TypeDescription;

import java.util.List;

/**
 * The DeviceTypeAssembler class is responsible for converting DeviceType domain entities to DeviceTypeDTO data transfer objects.
 */
public class DeviceTypeAssembler implements Assembler<DeviceType, DeviceTypeDTO> {



    /**
     * Converts a DeviceType domain entity to a DeviceTypeDTO data transfer object.
     *
     * @return The DeviceTypeDTO data transfer object.
     * @throws IllegalArgumentException if deviceType is null.
     */
    @Override
    public DeviceTypeDTO domainToDTO(DeviceType domainEntity) {
        if (domainEntity == null) {
            throw new IllegalArgumentException("The DeviceType cannot be null.");
        }

        String deviceTypeID = domainEntity.getID().toString();
        String deviceTypeDescription = domainEntity.getDescription().toString();

        DeviceTypeDTO deviceTypeDTO = new DeviceTypeDTO(deviceTypeID, deviceTypeDescription);
        return deviceTypeDTO;
    }

    /**
     * Converts a list of DeviceType domain entities to a list of DeviceTypeDTO data transfer objects.
     *
     * @param deviceTypes the list of domain entities to be converted.
     * @return The list of DeviceTypeDTO data transfer objects.
     * @throws IllegalArgumentException if deviceTypes is null, empty, or contains null elements.
     */
    @Override
    public List<DeviceTypeDTO> domainToDTO(List<DeviceType> deviceTypes) {
        if (deviceTypes == null || deviceTypes.isEmpty() || deviceTypes.contains(null)) {
            throw new IllegalArgumentException("The list of DeviceTypes cannot be null, empty, or contain null elements.");
        }
        // Convert each DeviceType entity to DeviceTypeDTO using domainToDTO method
        List<DeviceTypeDTO> deviceTypesDTOS = deviceTypes.stream().map(this::domainToDTO).toList();
        return deviceTypesDTOS;
    }
}
