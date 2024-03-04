package SmartHome.sensors;

import org.junit.jupiter.api.Test;
import org.shredzone.commons.suncalc.SunTimes;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SunTimesProviderTest {
    @Test
    void shouldReturnSunsetTime() {
        //Arrange
        double latitude = 51.5074;
        double longitude = 0.1278;
        SunTimesProvider sunTimesProvider = new SunTimesProvider();
        LocalTime expected = SunTimes.compute().on(LocalDate.now()).at(latitude, longitude).execute().getSet().toLocalTime();
        //Act
        LocalTime result = sunTimesProvider.getSunsetTime(latitude, longitude);
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnSunriseTime() {
        //Arrange
        double latitude = 51.5074;
        double longitude = 0.1278;
        SunTimesProvider sunTimesProvider = new SunTimesProvider();
        LocalTime expected = SunTimes.compute().on(LocalDate.now()).at(latitude, longitude).execute().getRise().toLocalTime();
        //Act
        LocalTime result = sunTimesProvider.getSunriseTime(latitude, longitude);
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnSunsetTimeForGivenDate() {
        //Arrange
        double latitude = 51.5074;
        double longitude = 0.1278;
        LocalDate date = LocalDate.of(2023, 12, 25);
        SunTimesProvider sunTimesProvider = new SunTimesProvider();
        LocalTime expected = SunTimes.compute().on(date).at(latitude, longitude).execute().getSet().toLocalTime();
        //Act
        LocalTime result = sunTimesProvider.getSunsetTime(latitude, longitude, date);
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldReturnSunriseTimeForGivenDate() {
        //Arrange
        double latitude = 51.5074;
        double longitude = 0.1278;
        LocalDate date = LocalDate.of(2023, 12, 25);
        SunTimesProvider sunTimesProvider = new SunTimesProvider();
        LocalTime expected = SunTimes.compute().on(date).at(latitude, longitude).execute().getRise().toLocalTime();
        //Act
        LocalTime result = sunTimesProvider.getSunriseTime(latitude, longitude, date);
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenLongitudeOutRange() {
        //Arrange
        double latitude = 51.5074;
        double longitude = 181.0;
        SunTimesProvider sunTimesProvider = new SunTimesProvider();
        String expected = "Longitude out of range, -180.0 <= 181.0 <= 180.0";

        //Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> sunTimesProvider.getSunsetTime(latitude, longitude));

        //Assert
        String result = e.getMessage();
        assertEquals(expected, result);
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenLatitudeOutRange() {
        //Arrange
        double latitude = 91;
        double longitude = 0.1278;
        SunTimesProvider sunTimesProvider = new SunTimesProvider();
        String expected = "Latitude out of range, -90.0 <= 91.0 <= 90.0";

        //Act
        Exception e = assertThrows(IllegalArgumentException.class, () -> sunTimesProvider.getSunsetTime(latitude, longitude));

        //Assert
        String result = e.getMessage();
        assertEquals(expected, result);
    }




}