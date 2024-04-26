package smarthome.domain.value_object;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import smarthome.ddd.IValueObject;

public class DatePeriod implements IValueObject {

  private final LocalDateTime startDate;
  private final LocalDateTime endDate;

  public DatePeriod(LocalDateTime startDate, LocalDateTime endDate) {
    validateTimePeriod(startDate, endDate);
    this.startDate = startDate.truncatedTo(ChronoUnit.MINUTES);
    this.endDate = endDate.truncatedTo(ChronoUnit.MINUTES);
  }

  private void validateTimePeriod(LocalDateTime startDate, LocalDateTime endDate) {
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
    return startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public int getDurationInMinutes() {
    return (int) ChronoUnit.MINUTES.between(startDate, endDate);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof DatePeriod objectDatePeriod) {
      return startDate.equals(objectDatePeriod.startDate) && endDate.equals(
          objectDatePeriod.endDate);
    }
    return false;
  }

  @Override
  public String toString() {
    return "DatePeriod:" +
        "startDate=" + startDate +
        ", endDate=" + endDate;
  }

  @Override
  public int hashCode() {
    return startDate.hashCode() + endDate.hashCode();
  }
}
