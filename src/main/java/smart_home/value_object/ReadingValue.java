package smart_home.value_object;

import smart_home.ddd.IValueObject;
import smart_home.utils.Validator;

public class ReadingValue implements IValueObject {

    private String _readingValue;

    public ReadingValue(String readingValue) {
        Validator.validateNotNull(readingValue, "Reading Value");
        this._readingValue = readingValue;
    }

    public String getReadingValue() {
        return _readingValue;
    }

    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (o instanceof ReadingValue readingValue) {
            return this._readingValue.equals(readingValue._readingValue);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return _readingValue.hashCode();
    }
}
