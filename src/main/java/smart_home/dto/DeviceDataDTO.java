package smart_home.dto;

/**
 * The data needed to create a device.
 */
public class DeviceDataDTO {
    public final String deviceTypeID;

    public final String deviceName;

    public final boolean deviceStatus;

    public final String roomID;

    public String deviceID;

    /**
     * Constructs a new DeviceDataDTO object with the specified device details.
     *
     * @param deviceTypeID The unique identifier of the device type.
     * @param deviceName The name of the device.
     * @param deviceStatus The status of the device.
     * @param roomID The unique identifier of the room.
     */
    public DeviceDataDTO(String deviceTypeID, String deviceName, boolean deviceStatus, String roomID) {
        this.deviceTypeID = deviceTypeID;
        this.deviceName = deviceName;
        this.deviceStatus = deviceStatus;
        this.roomID = roomID;
    }

  /**
   * Constructs a new DeviceDataDTO object with the specified device details, including the deviceID.
   *
   * @param deviceTypeID The unique identifier of the device type.
   * @param deviceName The name of the device.
   * @param deviceStatus The status of the device.
   * @param roomID The unique identifier of the room.
   * @param deviceID The unique identifier of the device.
   */
    public DeviceDataDTO(String deviceTypeID, String deviceName, boolean deviceStatus, String roomID, String deviceID) {
        this.deviceTypeID = deviceTypeID;
        this.deviceName = deviceName;
        this.deviceStatus = deviceStatus;
        this.roomID = roomID;
        this.deviceID = deviceID;
    }


}
