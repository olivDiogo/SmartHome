package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

/**
 * Data Transfer Object (DTO) representing information about a device.
 */
public class DeviceDTO implements DTO {

    /**
     * The unique identifier of the device.
     */
    public final String _deviceId;
    /**
     * The description of the device.
     */
    public final String _deviceDescription;
    /**
     * Constructs a new DeviceDTO object with the specified device details.
     *
     * @param deviceId          The unique identifier of the device.
     * @param deviceDescription The description of the device.
     */
    public DeviceDTO(String deviceId, String deviceDescription) {
        this._deviceId = deviceId;
        this._deviceDescription = deviceDescription;
    }
}
