package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

public class HousesDTO implements DTO {
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
    public HousesDTO(String address, String zipCode, String gps) {
        this._address = address;
        this._zipCode = zipCode;
        this._gps = gps;

    }

    @Override
    public String toString() {
        return "HousesDTO{" +
                "_address='" + _address + '\'' +
                ", _zipCode='" + _zipCode + '\'' +
                ", _gps='" + _gps + '\'' +
                '}';
    }


}
