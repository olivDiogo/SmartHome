package smart_home.persistence.jpa.data_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import smart_home.domain.house.House;
import smart_home.utils.Validator;

/**
 * Class to represent the house data model

 */
@Entity
public class HouseDataModel {
    @Id
    @Column(name = "houseID")
    private String _houseID;
    @Column(name = "latitude")
    private double _latitude;
    @Column(name = "longitude")
    private double _longitude;
    @Column(name = "street")
    private String _street;
    @Column(name = "doorNumber")
    private String _doorNumber;
    @Column(name = "countryCode")
    private String _countryCode;
    @Column(name = "postalCode")
    private String _postalCode;
    @Version
    private long version;

    /**
     * Empty constructor so that JPA can instantiate the class
     */
    public HouseDataModel() {
    }

    /**
     * Constructor to create a house data model
     * This constructor will validate the house and set the house data model
     * @param house
     */
    public HouseDataModel(House house) {
        Validator.validateNotNull(house, "House");
        this._houseID = house.getID().getID();
        this._latitude = house.getGps().getLatitude();
        this._longitude = house.getGps().getLongitude();
        this._street = house.getAddress().getStreet();
        this._doorNumber = house.getAddress().getDoorNumber();
        this._countryCode = house.getAddress().getCountryCode();
        this._postalCode = house.getAddress().getPostalCode().getCode();
    }

    /**
     * Method to return the house ID.
     * @return
     */
    public String getHouseID() {
        return this._houseID;
    }

    /**
     * Method to return the latitude.
     * @return
     */
    public double getLatitude() {
        return this._latitude;
    }

    /**
     * Method to return the longitude.
     * @return
     */
    public double getLongitude() {
        return this._longitude;
    }

    /**
     * Method to return the street.
     * @return
     */
    public String getStreet() {
        return this._street;
    }

    /**
     * Method to return the door number.
     * @return
     */
    public String getDoorNumber() {
        return this._doorNumber;
    }

    /**
     * Method to return the country code.
     * @return
     */
    public String getCountryCode() {
        return this._countryCode;
    }

    /**
     * Method to return the postal code.
     * @return
     */
    public String getPostalCode() {
        return this._postalCode;
    }

    @Override
    public String toString() {
        return "HouseDataModel: " +
                "houseID='" + _houseID + '\'' +
                ", latitude=" + _latitude +
                ", longitude=" + _longitude +
                ", street='" + _street + '\'' +
                ", doorNumber='" + _doorNumber + '\'' +
                ", countryCode='" + _countryCode + '\'' +
                ", postalCode='" + _postalCode + '.';
    }

}
