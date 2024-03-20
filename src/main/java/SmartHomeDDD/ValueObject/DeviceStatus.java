package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class DeviceStatus implements ValueObject {

    private final boolean _status;

    /**
     * Constructor of the class DeviceStatus.
     *
     * @param status is the status of the device.
     */
    public DeviceStatus(boolean status) {
        this._status = status;
    }

    /**
     * Equals method for DeviceStatus.
     *
     * @param object Object.
     * @return boolean.
     */
    public boolean equals(Object object) {

        if (this == object)
            return true;

        if (object instanceof DeviceStatus) {
            DeviceStatus deviceStatus = (DeviceStatus) object;
            return this._status == deviceStatus._status;
        }
        return false;
    }
}
