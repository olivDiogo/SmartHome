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
            throw new IllegalArgumentException("Invalid post code prefix");
        } else {
            this._zipCodePrefix = postCodePrefix;
        }
    }

    private void zipCodeSuffixValidation(int zipCodeSuffix){
        if (zipCodeSuffix < 100 || zipCodeSuffix > 999) {
            throw new IllegalArgumentException("Invalid post code suffix");
        } else {
            this._zipCodeSuffix = zipCodeSuffix;
        }
    }



}
