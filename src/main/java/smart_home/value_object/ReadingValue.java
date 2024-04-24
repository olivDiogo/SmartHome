package smart_home.value_object;

import smart_home.ddd.IValueObject;
import smart_home.utils.Validator;

public class ReadingValue implements IValueObject {

    private String readingValue;

    /**
     * Constructor of the class ReadingValue.
     *
     * @param readingValue is the value of the reading.
     */
    public ReadingValue(String readingValue) {
        Validator.validateNotNull(readingValue, "Reading Value");
        this.readingValue = readingValue;
    }

    /**
     * Getter for the reading value.
     *
     * @return readingValue.
     */
    public String getReadingValue() {
        return readingValue;
    }

    /**
     * Equals method for ReadingValue.
     *
     * @param o Object.
     * @return boolean.
     */
    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (o instanceof ReadingValue readingValue) {
            return this.readingValue.equals(readingValue.readingValue);
        }
        return false;
    }

    /**
     * HashCode method for ReadingValue.
     *
     * @return the hashcode as an int.
     */
    @Override
    public int hashCode() {
        return readingValue.hashCode();
    }
}
