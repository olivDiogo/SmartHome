package smart_home.mapper;


import smart_home.ddd.IAssembler;
import smart_home.domain.device_type.DeviceType;
import smart_home.dto.DeviceTypeDTO;
import smart_home.utils.Validator;

import java.util.List;

/**
 * The DeviceTypeAssembler class is responsible for converting DeviceType domain entities to DeviceTypeDTO data transfer objects.
 */
public class DeviceTypeAssembler implements IAssembler<DeviceType, DeviceTypeDTO> {


    /**
     * Converts a DeviceType domain entity to a DeviceTypeDTO data transfer object.
     *
     * @return The DeviceTypeDTO data transfer object.
     * @throws IllegalArgumentException if deviceType is null.
     */
    @Override
    public DeviceTypeDTO domainToDTO(DeviceType domainEntity) {
        Validator.validateNotNull(domainEntity, "DeviceType");

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
        if (deviceTypes == null || deviceTypes.isEmpty()) {
            throw new IllegalArgumentException("The list of DeviceTypes cannot be null, empty");
        }
        // Convert each DeviceType entity to DeviceTypeDTO using domainToDTO method
        List<DeviceTypeDTO> deviceTypesDTOS = deviceTypes.stream().map(this::domainToDTO).toList();
        return deviceTypesDTOS;
    }
}
