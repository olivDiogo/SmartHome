package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing information about a device.
 */
public class DeviceDTO implements DTO {

    /**
     * The unique identifier of the device.
     */
    public final String deviceId;
    /**
     * The description of the device.
     */
    public final String deviceDescription;
    /**
     * Constructs a new DeviceDTO object with the specified device details.
     *
     * @param deviceId          The unique identifier of the device.
     * @param deviceDescription The description of the device.
     */
    public DeviceDTO(String deviceId, String deviceDescription) {
        this.deviceId = deviceId;
        this.deviceDescription = deviceDescription;
    }
}
