package smart_home.domain.house;

import smart_home.ddd.IAggregateRoot;
import smart_home.utils.Validator;
import smart_home.value_object.Address;
import smart_home.value_object.GPS;
import smart_home.value_object.HouseID;

import java.util.UUID;

/**
 * Represents a house in the Smart Home domain.
 * This class includes details about the house's identification, location, and geographical positioning.
 * It implements the AggregateRoot interface with HouseID as its identifier.
 */
public class House implements IAggregateRoot<HouseID> {
    private HouseID _houseID;
    private Address _address;
    private GPS _gps;

    /**
     * Constructs a new House instance with the specified address, zip code, and GPS coordinates.
     * Validates the provided address, zip code, and GPS coordinates to ensure they are not null.
     *
     * @param address The physical address of the house. Must not be null.
     * @param gps     The GPS coordinates of the house. Must not be null.
     * @throws IllegalArgumentException if any of the parameters are null.
     */
    House(Address address, GPS gps) {
        generateID();
        Validator.validateNotNull(address, "Address");
        Validator.validateNotNull(gps, "GPS");
        this._address = address;
        this._gps = gps;

    }
    House(HouseID houseID, Address address, GPS gps) {
        Validator.validateNotNull(houseID, "HouseID");
        Validator.validateNotNull(address, "Address");
        Validator.validateNotNull(gps, "GPS");

        this._houseID = houseID;
        this._address = address;
        this._gps = gps;
    }


    /**
     * Generates a unique identifier for the House instance.
     */
    private void generateID()    {
        _houseID = new HouseID(UUID.randomUUID().toString());
    }


    /**
     * Returns the unique identifier of the House instance.
     *
     * @return The HouseID that uniquely identifies the house.
     */
    @Override
    public HouseID getID() {
        return _houseID;
    }

    /**
     * Returns the address of the house.
     *
     * @return The Address of the house.
     */
    public Address getAddress() {
        return _address;
    }

    /**
     * Returns the GPS coordinates of the house.
     *
     * @return The GPS coordinates of the house.
     */
    public GPS getGps() {
        return _gps;
    }

    /**
     * Checks if this House instance is equal to another object.
     * Equality is based solely on the unique identifier of the house (_houseID).
     * This method overrides the {@link Object#equals(Object)} method.
     *
     * @param o the object to be compared with this House instance for equality
     * @return true if the specified object is a House and has the same _id as this house; false otherwise
     */
    public boolean equals(Object o) {
        if (o instanceof House houseObject) {
            return _houseID.equals(houseObject._houseID);
        }
        return false;
    }

    /**
     * Returns a string representation of this House instance.
     * The string includes the class name, along with the _houseID, _address, _zipCode, and _gps properties.
     * This method overrides the {@link Object#toString()} method.
     *
     * @return a string representation of this House instance, containing values of its properties
     */
    public String toString() {
        return "House:" +
                "houseID=" + _houseID +
                ", address=" + _address +
                ", gps=" + _gps;
    }

    /**
     * Returns a hash code value for the House instance.
     * The hash code is based on the unique identifier of the house (_houseID).
     * This method overrides the {@link Object#hashCode()} method.
     *
     * @return a hash code value for this House instance
     */
    public int hashCode() {
        return _houseID.hashCode();
    }
}
