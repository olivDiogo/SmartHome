package SmartHome.domain;

public class Address {
    private String _street;
    private String _zipCode;
    private int _doorNumber;

    /**
     * Constructor for the Address class.
     *
     * @param street     the street of the house.
     * @param zipCode    the zip code of the house.
     * @param doorNumber the door number of the house.
     */
    protected Address(String street, String zipCode, int doorNumber) {
        setStreet(street);
        setZipCode(zipCode);
        setDoorNumber(doorNumber);
    }

    /**
     * Set the street of the house.
     *
     * @param street the street of the house.
     * @throws IllegalArgumentException if the street is null or empty.
     */
    private void setStreet(String street) throws IllegalArgumentException {
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter a valid street for the house.");
        }
        this._street = street;
    }

    /**
     * Set the zip code of the house.
     *
     * @param zipCode the zip code of the house.
     * @throws IllegalArgumentException if the zip code is null or empty.
     */
    private void setZipCode(String zipCode) throws IllegalArgumentException {
        if (zipCode == null || zipCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter a valid zip code for the house.");
        }
        this._zipCode = zipCode;
    }

    /**
     * Set the door number of the house.
     *
     * @param doorNumber the door number of the house.
     * @throws IllegalArgumentException if the door number is less than or equal to 0.
     */
    private void setDoorNumber(int doorNumber) throws IllegalArgumentException {
        if (doorNumber <= 0) {
            throw new IllegalArgumentException("Please enter a valid door number for the house.");
        }
        this._doorNumber = doorNumber;
    }

    /**
     * Get the street of the house.
     *
     * @return the street of the house.
     */
    public String getStreet() {
        return _street;
    }

    /**
     * Get the zip code of the house.
     * @return the zip code of the house.
     */
    /**
     * Get the zip code of the house.
     *
     * @return the zip code of the house.
     */
    public String getZipCode() {
        return _zipCode;
    }

    /**
     * Get the door number of the house.
     *
     * @return the door number of the house.
     */
    public int getDoorNumber() {
        return _doorNumber;
    }

    /**
     * Override the toString method to return the address in a readable format.
     *
     * @return the address in a readable format.
     */
    @Override
    public String toString() {
        return "Address{" +
                "street='" + _street + '\'' +
                ", zipCode='" + _zipCode + '\'' +
                ", doorNumber=" + _doorNumber +
                '}';
    }
}
