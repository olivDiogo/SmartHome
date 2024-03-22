package SmartHomeDDD.domain.House;

import SmartHomeDDD.ValueObject.GPS;
import SmartHomeDDD.ValueObject.Address;
import SmartHomeDDD.ValueObject.HouseID;
import SmartHomeDDD.ValueObject.ZipCode;
import SmartHomeDDD.ddd.AggregateRoot;

import java.util.UUID;

/**
 * Represents a house in the Smart Home domain.
 * This class includes details about the house's identification, location, and geographical positioning.
 * It implements the AggregateRoot interface with HouseID as its identifier.
 */
public class House implements AggregateRoot<HouseID> {
    private HouseID _houseID;
    private Address _address;
    private ZipCode _zipCode;
    private GPS _gps;

    /**
     * Constructs a new House instance with the specified address, zip code, and GPS coordinates.
     * Validates the provided address, zip code, and GPS coordinates to ensure they are not null.
     *
     * @param address The physical address of the house. Must not be null.
     * @param zipCode The postal zip code of the house's location. Must not be null.
     * @param gps The GPS coordinates of the house. Must not be null.
     * @throws IllegalArgumentException if any of the parameters are null.
     */
    House(Address address, ZipCode zipCode, GPS gps) {
        generateID();
        validateAddress(address);
        validateZipCode(zipCode);
        validateGps(gps);
    }

    /**
     * Generates a unique identifier for the House instance.
     */
    private void generateID() {
        _houseID = new HouseID(UUID.randomUUID().toString());
    }

    /**
     * Validates the provided Address object.
     * Sets the house's address if valid or throws an exception if the address is null.
     *
     * @param address The Address to be validated.
     * @throws IllegalArgumentException if address is null.
     */
    private void validateAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address is required");
        } else {
            _address = address;
        }
    }

    /**
     * Validates the provided ZipCode object.
     * Sets the house's zip code if valid or throws an exception if the zip code is null.
     *
     * @param zipCode The ZipCode to be validated.
     * @throws IllegalArgumentException if zipCode is null.
     */
    private void validateZipCode(ZipCode zipCode) {
        if (zipCode == null) {
            throw new IllegalArgumentException("ZipCode is required");
        } else {
            _zipCode = zipCode;
        }
    }

    /**
     * Validates the provided GPS object.
     * Sets the house's GPS coordinates if valid or throws an exception if the GPS data is null.
     *
     * @param gps The GPS to be validated.
     * @throws IllegalArgumentException if gps is null.
     */
    private void validateGps(GPS gps) {
        if (gps == null) {
            throw new IllegalArgumentException("Gps is required");
        } else {
            _gps = gps;
        }
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
     * Returns the zip code of the house.
     *
     * @return The ZipCode of the house.
     */
    public ZipCode getZipCode() {
        return _zipCode;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return _houseID.toString().equals(house._houseID.toString());
    }

    /**
     * Returns a string representation of this House instance.
     * The string includes the class name, along with the _houseID, _address, _zipCode, and _gps properties.
     * This method overrides the {@link Object#toString()} method.
     *
     * @return a string representation of this House instance, containing values of its properties
     */
    public String toString() {
        return "House{" +
                "_houseID=" + _houseID +
                ", _address=" + _address +
                ", _zipCode=" + _zipCode +
                ", _gps=" + _gps +
                '}';
    }
}
