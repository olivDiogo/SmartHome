package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

public class HouseDTO implements DTO {
    public final String _address;
    public final String _zipCode;
    public final String _gps;

    /**
     * Constructor for the HouseDTO class.
     *
     * @param address is the address of the House.
     * @param zipCode is the zip-code of the House.
     * @param gps     is the GPS coordinates of the House.
     */
    public HouseDTO(String address, String zipCode, String gps) {
        this._address = address;
        this._zipCode = zipCode;
        this._gps = gps;

    }

    /**
     * Returns a string representation of the HouseDTO object.
     *
     * @return A string representation of the HouseDTO object.
     */
    @Override
    public String toString() {
        return "HousesDTO{" +
                "_address='" + _address + '\'' +
                ", _zipCode='" + _zipCode + '\'' +
                ", _gps='" + _gps + '\'' +
                '}';
    }

    /**
     * Gets the address of the House.
     *
     * @return the address of the House
     */

    public String getAddress() {
        return _address;
    }

    /**
     * Gets the postal code of the House.
     *
     * @return the postal code of the House
     */
    public String getPostalCode() {
        return _zipCode;
    }

    /**
     * Gets the GPS coordinates of the House.
     *
     * @return the GPS coordinates of the House
     */
    public String getGPS() {
        return _gps;
    }
}

