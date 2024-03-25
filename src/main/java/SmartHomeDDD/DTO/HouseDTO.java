package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

public class HouseDTO implements DTO {
    public final String address;
    public final String zipCode;
    public final String gps;

    /**
     * Constructor for the HouseDTO class.
     *
     * @param address is the address of the House.
     * @param zipCode is the zip-code of the House.
     * @param gps     is the GPS coordinates of the House.
     */
    public HouseDTO(String address, String zipCode, String gps) {
        this.address = address;
        this.zipCode = zipCode;
        this.gps = gps;

    }

    public String toString() {
        return "HouseDTO{" +
                "address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", gps='" + gps + '\'' +
                '}';
    }
}

