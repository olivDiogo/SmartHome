package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class Address implements ValueObject {

    private String _street;
    private String _doorNumber;

    /**
     * Constructor of the class Address.
     *
     * @param street is the street of the address.
     * @param doorNumber is the door number of the address.
     */
    public Address(String street, String doorNumber) {
        streetValidation(street);
        doorNumberValidation(doorNumber);
    }

    /**
     * Street validation method.
     *
     * @param street is the street of the address.
     */
    private void streetValidation(String street){
        if (street == null || street.trim().isEmpty() || street.length() > 70 || !street.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Invalid street");
        } else {
            this._street = street;
        }
    }

    /**
     * Door number validation method.
     *
     * @param doorNumber is the door number of the address.
     */

    private void doorNumberValidation(String doorNumber){
        if (doorNumber == null || doorNumber.trim().isEmpty() || doorNumber.length() > 10 || !doorNumber.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Invalid door number");
        } else {
            this._doorNumber = doorNumber;
        }
    }

    /**
     * Equals method for Address.
     *
     * @param object Object.
     * @return boolean.
     */
    public boolean equals(Object object) {

        if (this == object)
            return true;

        if (object instanceof Address) {
            Address address = (Address) object;

            if (this._street.equals(address._street) && this._doorNumber.equals(address._doorNumber))
                return true;
        }
        return false;
    }

    /**
     * Getter for street.
     *
     * @return _street.
     */
    public String getStreet(){
        return this._street;
    }

    /**
     * Getter for door number.
     *
     * @return _doorNumber.
     */
    public String getDoorNumber(){
        return this._doorNumber;
    }

    /**
     * Address object to string
     * @return String
     */
    public String toString(){
        return this._street + ", " + this._doorNumber;
    }


}
