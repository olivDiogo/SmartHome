package smarthome.persistence.jpa.data_model;

import jakarta.persistence.*;
import smarthome.domain.device.Device;
import smarthome.utils.Validator;


/**
 * The {@code DeviceDataModel} class represents the persistence model for a device in the database.
 * This class maps to the DEVICE table and defines the structure and relationships of the device data.
 *
 * @Entity Indicates that this class is an entity to be managed by the JPA.
 * @Table Specifies the name of the table in the database for this entity.
 */
@Entity
@Table(name = "DEVICE")
public class DeviceDataModel {

    @Id
    private String deviceID;
    @Column(name = "Room ID")
    private String roomID;
    @Column(name = "Device Name")
    private String deviceName;
    @Column(name = "Device Type ID")
    private String deviceTypeID;
    @Column(name = "Device Status")
    private boolean deviceStatus;
    @Version
    private long version;

    /**
     * Default constructor for JPA.
     */
    public DeviceDataModel() {}

    /**
     * Constructs a new DeviceDataModel instance from a domain {@link Device} object.
     *
     * @param device The domain Device object to convert to a data model.
     * @throws IllegalArgumentException if the device parameter is null.
     */
    public DeviceDataModel(Device device) {
        Validator.validateNotNull(device, "Device");
        this.deviceID = device.getID().getID();
        this.roomID = device.getRoomID().getID();
        this.deviceName = device.getDeviceName().getName();
        this.deviceTypeID = device.getDeviceTypeID().getID();
        this.deviceStatus = device.getDeviceStatus().getStatus();
    }


    /**
     * Returns the device ID.
     *
     * @return the device ID.
     */
    public String getDeviceID() {
        return deviceID;
    }

    /**
     * Returns the room ID associated with the device.
     *
     * @return the room ID.
     */
    public String getRoomID() {
        return roomID;
    }

    /**
     * Returns the name of the device.
     *
     * @return the device name.
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Returns the device type ID.
     *
     * @return the device type ID.
     */
    public String getDeviceTypeID() {
        return deviceTypeID;
    }

    /**
     * Returns the status of the device.
     *
     * @return true if the device is active, false otherwise.
     */
    public boolean getDeviceStatus() {
        return deviceStatus;
    }

    /**
     * Updates the device data model from the domain.
     */
    public boolean updateFromDomain(Device device){
        this.deviceID = device.getID().getID();
        this.roomID = device.getRoomID().getID();
        this.deviceName = device.getDeviceName().getName();
        this.deviceTypeID = device.getDeviceTypeID().getID();
        this.deviceStatus = device.getDeviceStatus().getStatus();
        return true;
    }
}

