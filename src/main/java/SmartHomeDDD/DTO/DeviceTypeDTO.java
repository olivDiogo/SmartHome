package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

public class DeviceTypeDTO implements DTO {

    public final String deviceTypeID;
    public final String deviceTypeDescription;

    /**
     * Constructs a new DeviceTypeDTO object.
     *
     * @param deviceTypeID          The description of the device type.
     * @param deviceTypeDescription The description of the device type.
     */
    public DeviceTypeDTO(String deviceTypeID, String deviceTypeDescription) {
        this.deviceTypeID = deviceTypeID;
        this.deviceTypeDescription = deviceTypeDescription;
    }

    /**
     * Returns a string representation of the DeviceTypeDTO object.
     */
    @Override
    public String toString() {
        return deviceTypeID + " " + deviceTypeDescription;
    }
}
