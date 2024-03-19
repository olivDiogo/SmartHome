package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class Address implements ValueObject {

    private String _street;
    private String _doorNumber;

    public Address(String street, String doorNumber) {
        streetValidation(street);
        doorNumberValidation(doorNumber);
    }

    private void streetValidation(String street){
        if (street == null || street.trim().isEmpty() || street.length() > 70 || !street.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Invalid street");
        } else {
            this._street = street;
        }
    }

    private void doorNumberValidation(String doorNumber){
        if (doorNumber == null || doorNumber.trim().isEmpty() || doorNumber.length() > 10 || !doorNumber.matches("^[a-zA-Z0-9 ]+$")) {
            throw new IllegalArgumentException("Invalid door number");
        } else {
            this._doorNumber = doorNumber;
        }
    }

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

    public String getStreet(){
        return this._street;
    }

    public String getDoorNumber(){
        return this._doorNumber;
    }


}
