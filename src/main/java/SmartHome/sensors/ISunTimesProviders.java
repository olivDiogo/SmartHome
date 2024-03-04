package SmartHome.sensors;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ISunTimesProviders {

    public LocalTime getSunsetTime(double latitude, double longitude);
    public LocalTime getSunriseTime(double latitude, double longitude);
    public LocalTime getSunsetTime(double latitude, double longitude, LocalDate date);
    public LocalTime getSunriseTime(double latitude, double longitude, LocalDate date);
}
