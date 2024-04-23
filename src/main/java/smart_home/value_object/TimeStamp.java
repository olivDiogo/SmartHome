package smart_home.value_object;

import smart_home.ddd.IValueObject;
import smart_home.utils.Validator;

import java.time.LocalDateTime;

public class TimeStamp implements IValueObject {
    private final LocalDateTime _timeStamp;

    /**
     * Constructor of the class.
     *
     * @param timeStamp The time stamp.
     */
    public TimeStamp(LocalDateTime timeStamp) {
        Validator.validateNotNull(timeStamp, "timeStamp");
        this._timeStamp = timeStamp;
    }


    /**
     * Getter for the time stamp.
     *
     * @return The time stamp.
     */
    public LocalDateTime getTimeStamp() {
        return _timeStamp;
    }

    /**
     * Equals method for TimeStamp.
     * @param obj The object to compare.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TimeStamp timeStamp) {
            return this._timeStamp.equals(timeStamp._timeStamp);
        }
        return false;
    }

    /**
     * Hashcode method
     */
    @Override
    public int hashCode() {
        return _timeStamp.hashCode();
    }

    /**
     * toString method
     */
    @Override
    public String toString() {
        return "TimeStamp:" +
                " timeStamp=" + _timeStamp;
    }

}
