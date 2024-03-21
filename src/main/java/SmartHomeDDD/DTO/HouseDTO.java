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

    /**
     * Gets the address of the House.
     *
     * @return the address of the House
     */

    public String getAddress() {
        return address;
    }

    /**
     * Gets the postal code of the House.
     *
     * @return the postal code of the House
     */
    public String getPostalCode() {
        return zipCode;
    }

    /**
     * Gets the GPS coordinates of the House.
     *
     * @return the GPS coordinates of the House
     */
    public String getGPS() {
        return gps;
    }
}

