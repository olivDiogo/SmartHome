package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class DatePeriodTest {
    @Test
    void shouldThrowExceptionWhenStartDateIsNull() {
        //Arrange
        LocalDateTime startDate = null;
        LocalDateTime endDate = LocalDateTime.now();
        String expectedMessage = "Start date cannot be null.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DatePeriod(startDate, endDate));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenEndDateIsNull() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = null;
        String expectedMessage = "End date cannot be null.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DatePeriod(startDate, endDate));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldThrowExceptionWhenStartDateIsAfterEndDate() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().minusMinutes(1);
        String expectedMessage = "Start date cannot be after end date.";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new DatePeriod(startDate, endDate));
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void shouldReturnZeroWhenGetDurationInMinutesIsCalledWithSameDates() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        int expected = 0;
        //Act
        int result = datePeriod.getDurationInMinutes();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnTimeElapsedInMinutesWhenGetDurationInMinutesIsCalled() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusMinutes(77);
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        int expected = 77;
        //Act
        int result = datePeriod.getDurationInMinutes();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnStartDateWhenGetStartDateIsCalled() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        LocalDateTime expected = startDate.truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
        //Act
        LocalDateTime result = datePeriod.getStartDate();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnEndDateWhenGetEndDateIsCalled() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        LocalDateTime expected = endDate.truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
        //Act
        LocalDateTime result = datePeriod.getEndDate();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnDurationInMinutesWhenGetDurationInMinutesIsCalled() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        //Act
        int result = datePeriod.getDurationInMinutes();
        //Assert
        assertEquals(1, result);
    }
    @Test
    void shouldReturnTrueWhenEqualsIsCalledWithSameDatePeriod() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        DatePeriod datePeriod2 = new DatePeriod(startDate, endDate);
        //Act
        boolean result = datePeriod.equals(datePeriod2);
        //Assert
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseWhenEqualsIsCalledWithDifferentDatePeriod() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        DatePeriod datePeriod2 = new DatePeriod(startDate, endDate.plusMinutes(1));
        //Act
        boolean result = datePeriod.equals(datePeriod2);
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnFalseWhenEqualsIsCalledWithDifferentObject() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        //Act
        boolean result = datePeriod.equals(new Object());
        //Assert
        assertFalse(result);
    }
    @Test
    void shouldReturnStringWhenToStringIsCalled() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endDate = LocalDateTime.now().plusMinutes(1).truncatedTo(ChronoUnit.MINUTES);
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        String expected = "DatePeriod{startDate=" + startDate + ", endDate=" + endDate + "}";
        //Act
        String result = datePeriod.toString();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnSameHashCodeWhenHashCodeIsCalledWithSameDatePeriod() {
        //Arrange
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
        DatePeriod datePeriod = new DatePeriod(startDate, endDate);
        DatePeriod datePeriod2 = new DatePeriod(startDate, endDate);
        //Act
        int result = datePeriod.hashCode();
        int result2 = datePeriod2.hashCode();
        //Assert
        assertEquals(result, result2);
    }


}