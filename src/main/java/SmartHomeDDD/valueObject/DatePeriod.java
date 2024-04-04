package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.ValueObject;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DatePeriod implements ValueObject {
    private final LocalDateTime _startDate;
    private final LocalDateTime _endDate;

    public DatePeriod(LocalDateTime startDate, LocalDateTime endDate) {
        validateTimePeriod(startDate, endDate);
        this._startDate = startDate.truncatedTo(ChronoUnit.MINUTES);
        this._endDate = endDate.truncatedTo(ChronoUnit.MINUTES);
    }
    private void validateTimePeriod(LocalDateTime startDate, LocalDateTime endDate){
        if (startDate == null) {
            throw new IllegalArgumentException("Start date cannot be null.");
        }
        if (endDate == null) {
            throw new IllegalArgumentException("End date cannot be null.");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }
    }
    public LocalDateTime getStartDate() {
        return _startDate;
    }
    public LocalDateTime getEndDate() {
        return _endDate;
    }

    public int getDurationInMinutes() {
        return (int) ChronoUnit.MINUTES.between(_startDate, _endDate);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DatePeriod objectDatePeriod) {
            return _startDate.equals(objectDatePeriod._startDate) && _endDate.equals(objectDatePeriod._endDate);
        }
        return false;
    }
    @Override
    public String toString() {
        return "DatePeriod{" +
                "startDate=" + _startDate +
                ", endDate=" + _endDate +
                '}';
    }
    @Override
    public int hashCode() {
        return _startDate.hashCode() + _endDate.hashCode();
    }
}
