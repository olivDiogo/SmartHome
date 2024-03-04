package SmartHome.sensors;

import org.shredzone.commons.suncalc.SunTimes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class SunTimesProvider implements ISunTimesProviders {
    public LocalTime getSunsetTime(double latitude, double longitude) throws IllegalArgumentException{
        SunTimes time = SunTimes.compute().on(LocalDate.now()).at(latitude, longitude).execute();
        LocalTime sunset = Objects.requireNonNull(time.getSet()).toLocalTime();
        return sunset;
    }
    public LocalTime getSunriseTime(double latitude, double longitude) throws IllegalArgumentException {
        SunTimes time = SunTimes.compute().on(LocalDate.now()).at(latitude, longitude).execute();
        LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
        return sunrise;
    }
    public LocalTime getSunsetTime(double latitude, double longitude, LocalDate date) throws IllegalArgumentException {
        SunTimes time = SunTimes.compute().on(date).at(latitude, longitude).execute();
        LocalTime sunset = Objects.requireNonNull(time.getSet()).toLocalTime();
        return sunset;
    }
    public LocalTime getSunriseTime(double latitude, double longitude, LocalDate date) throws IllegalArgumentException {
        SunTimes time = SunTimes.compute().on(date).at(latitude, longitude).execute();
        LocalTime sunrise = Objects.requireNonNull(time.getRise()).toLocalTime();
        return sunrise;
    }

}
