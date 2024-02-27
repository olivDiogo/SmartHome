package SmartHome.domain;

public class Address {
    private String _street;
    private String _zipCode;
    private int _doorNumber;

    protected Address(String street, String zipCode, int doorNumber) {
        setStreet(street);
        setZipCode(zipCode);
        setDoorNumber(doorNumber);
    }
    private void setStreet(String street) throws IllegalArgumentException {
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter a valid street for the house.");
        }
        this._street = street;
    }
    private void setZipCode(String zipCode) throws IllegalArgumentException {
        if (zipCode == null || zipCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter a valid zip code for the house.");
        }
        this._zipCode = zipCode;
    }
    private void setDoorNumber(int doorNumber) throws IllegalArgumentException {
        if (doorNumber <= 0) {
            throw new IllegalArgumentException("Please enter a valid door number for the house.");
        }
        this._doorNumber = doorNumber;
    }

    public String getStreet() {
        return _street;
    }

    public String getZipCode() {
        return _zipCode;
    }

    public int getDoorNumber() {
        return _doorNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + _street + '\'' +
                ", zipCode='" + _zipCode + '\'' +
                ", doorNumber=" + _doorNumber +
                '}';
    }
}
