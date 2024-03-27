package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.ValueObject;

public class ZipCode implements ValueObject {

    private final int _zipCodePrefix;
    private final int _zipCodeSuffix;

    /**
     * Constructor for ZipCode class
     *
     * @param zipCodePrefix is the zip code prefix of ZipCode
     * @param zipCodeSuffix is the zip code suffix of ZipCode
     */
    public ZipCode(int zipCodePrefix, int zipCodeSuffix) {
        validateZipCodePrefix(zipCodePrefix);
        validateZipCodeSuffix(zipCodeSuffix);
        this._zipCodePrefix = zipCodePrefix;
        this._zipCodeSuffix = zipCodeSuffix;
    }

    /**
     * Validates the zip code prefix
     *
     * @param postCodePrefix is the zip code prefix of ZipCode
     */
    private void validateZipCodePrefix(int postCodePrefix) {
        if (postCodePrefix < 1000 || postCodePrefix > 9999) {
            throw new IllegalArgumentException("Invalid zip code prefix");
        }
    }

    /**
     * Validates the zip code suffix
     *
     * @param zipCodeSuffix is the zip code suffix of ZipCode
     */
    private void validateZipCodeSuffix(int zipCodeSuffix) {
        if (zipCodeSuffix < 100 || zipCodeSuffix > 999) {
            throw new IllegalArgumentException("Invalid zip code suffix");
        }
    }

    /**
     * Equals method for ZipCode.
     *
     * @param object Object.
     * @return boolean.
     */
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object instanceof ZipCode) {
            ZipCode zipCode = (ZipCode) object;
            if (this._zipCodePrefix == zipCode._zipCodePrefix && this._zipCodeSuffix == zipCode._zipCodeSuffix)
                return true;
        }
        return false;
    }

    /**
     * Getter for zip code prefix.
     *
     * @return _zipCodePrefix.
     */
    public int getZipCodePrefix() {
        return _zipCodePrefix;
    }

    /**
     * Getter for zip code suffix.
     *
     * @return _zipCodeSuffix.
     */
    public int getZipCodeSuffix() {
        return _zipCodeSuffix;
    }

    /**
     * Address object to string
     *
     * @return String
     */
    @Override
    public String toString() {
        return _zipCodePrefix + "-" + _zipCodeSuffix;
    }


}
