package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class ZipCode implements ValueObject {

    private int _zipCodePrefix;

    private int _zipCodeSuffix;

    public ZipCode(int zipCodePrefix, int zipCodeSuffix) {
        zipCodePrefixValidation(zipCodePrefix);
        zipCodeSuffixValidation(zipCodeSuffix);
    }

    private void zipCodePrefixValidation(int postCodePrefix){
        if (postCodePrefix < 1000 || postCodePrefix > 9999 ) {
            throw new IllegalArgumentException("Invalid zip code prefix");
        } else {
            this._zipCodePrefix = postCodePrefix;
        }
    }

    private void zipCodeSuffixValidation(int zipCodeSuffix){
        if (zipCodeSuffix < 100 || zipCodeSuffix > 999) {
            throw new IllegalArgumentException("Invalid zip code suffix");
        } else {
            this._zipCodeSuffix = zipCodeSuffix;
        }
    }
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

    public int getZipCodePrefix() {
        return _zipCodePrefix;
    }

    public int getZipCodeSuffix() {
        return _zipCodeSuffix;
    }

    @Override
    public String toString() {
        return _zipCodePrefix + "-" + _zipCodeSuffix;
    }


}
