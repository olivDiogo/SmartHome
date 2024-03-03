package SmartHome.sensors;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ISunTimesProviders {

    public LocalTime getSunsetTime();
    public LocalTime getSunriseTime();
    public LocalTime getSunsetTime(LocalDate date);
    public LocalTime getSunriseTime(LocalDate date);
}
