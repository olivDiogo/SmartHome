package SmartHomeDDD.DTO;

import SmartHomeDDD.ddd.DTO;

public class HouseDTO implements DTO {
    public final String address;
    public final String gps;

    /**
     * Constructor for the HouseDTO class.
     *
     * @param address is the address of the House.
     * @param gps     is the GPS coordinates of the House.
     */
    public HouseDTO(String address, String gps) {
        this.address = address;
        this.gps = gps;

    }

    public String toString() {
        return address + " " + gps;
    }
}

