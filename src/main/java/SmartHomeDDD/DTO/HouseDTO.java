package SmartHomeDDD.DTO;

import SmartHomeDDD.ValueObject.Address;
import SmartHomeDDD.ValueObject.GPS;
import SmartHomeDDD.ValueObject.ZipCode;

public class HouseDTO {
    public String _address;
    public String _zipCode;
    public String _gps;

    /**
     * Constructor for the HouseDTO class.
     *
     * @param address is the address of the House.
     * @param zipCode is the zip-code of the House.
     * @param gps     is the GPS coordinates of the House.
     */
    public HouseDTO(Address address, ZipCode zipCode, GPS gps) {
        addressToString(address);
        zipCodeToString(zipCode);
        gpsToString(gps);

    }

    /**
     * Sets the address object into a String.
     *
     * @param address is the object of Address.
     * @return address in a String.
     */
    private String addressToString(Address address) {
        return this._address = address.toString();
    }

    /**
     * Sets the zipCode object into a String.
     *
     * @param zipCode is the object of ZipCode.
     * @return zipCode in a String.
     */
    private String zipCodeToString(ZipCode zipCode) {
        return this._zipCode = zipCode.toString();
    }

    /**
     * Sets the GPS object into a String.
     *
     * @param gps is the object of GPS.
     * @return gps in a String.
     */
    private String gpsToString(GPS gps) {
        return this._gps = gps.toString();
    }


}
