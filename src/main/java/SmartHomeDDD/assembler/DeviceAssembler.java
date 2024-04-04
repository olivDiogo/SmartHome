package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.DeviceDTO;
import SmartHomeDDD.ddd.Assembler;
import SmartHomeDDD.domain.Device.Device;

import java.util.List;

public class DeviceAssembler implements Assembler<Device, DeviceDTO> {
    /**
     * Constructs a new DeviceAssembler object.
     */
    public DeviceAssembler() {
    }

    /**
     * Converts a Device object to a DeviceDTO object.
     *
     * @param domainEntity The Device object to be converted.
     * @return The DeviceDTO object.
     */
    @Override
    public DeviceDTO domainToDTO(Device domainEntity) {
        if (domainEntity == null) {
            throw new IllegalArgumentException("The Device cannot be null.");
        }

        String deviceID = domainEntity.getID().toString();
        String roomID = domainEntity.getRoomID().toString();
        String deviceName = domainEntity.getDeviceName().toString();
        String deviceStatus = domainEntity.getDeviceStatus().toString();

        return new DeviceDTO(deviceID, roomID, deviceName, deviceStatus);
    }

    /**
     * Converts a list of Device objects to a list of DeviceDTO objects.
     *
     * @param domainEntities The list of Device objects to be converted.
     * @return The list of DeviceDTO objects.
     */
    @Override
    public List<DeviceDTO> domainToDTO(List<Device> domainEntities) {
        if (domainEntities == null || domainEntities.isEmpty() || domainEntities.contains(null)) {
            throw new IllegalArgumentException("The list of Devices cannot be null or empty.");
        }
        return domainEntities.stream().map(this::domainToDTO).toList();
    }
}
