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
    public HouseDTO(String address, String zipCode, String gps) {
        this._address = address;
        this._zipCode = zipCode;
        this._gps = gps;

    }


}
