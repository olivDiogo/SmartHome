package smartHome.dto;

/**
 * The data needed to create a device.
 */
public class DeviceDataDTO {
    public final String deviceTypeID;

    public final String deviceName;

    public final boolean deviceStatus;

    public final String roomID;

    /**
     * Constructs a new DeviceDataDTO object with the specified device details.
     *
     * @param deviceTypeID
     * @param deviceName
     * @param deviceStatus
     * @param roomID
     */
    public DeviceDataDTO(String deviceTypeID, String deviceName, boolean deviceStatus, String roomID) {
        this.deviceTypeID = deviceTypeID;
        this.deviceName = deviceName;
        this.deviceStatus = deviceStatus;
        this.roomID = roomID;
    }


}
