package SmartHome.sensors;
import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ElectricConsumptionWhSensor implements Sensor {
    private SensorType _type;
    private ElectricConsumptionWhValue _ElectricConsumptionWhValue;
    public ElectricConsumptionWhSensor(CatalogueSensor catalogue) throws InstantiationException {
        setSensorType(catalogue);
    }
    private void setSensorType(CatalogueSensor catalogue) throws InstantiationException {
        SensorType sensorType = catalogue.getSensorType("ElectricConsumptionWh");
        if (sensorType == null) {
            throw new InstantiationException("SensorType with description 'ElectricConsumptionWh' does not exist.");
        } else {
            _type = sensorType;
        }
    }
    public SensorType getSensorType() {
        return _type;
    }

    //Method to generate artificial data with readings of 50Wh
    protected Map<LocalDateTime, Integer> artificialDatabaseOfReadingsSetTo50W() {
        Map<LocalDateTime, Integer> mapAllConsumption = new LinkedHashMap<>();
        // Set artificial data for the last 5 days
        LocalDateTime startTime = LocalDateTime.now().minusDays(5).truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime endTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        // Fill the map with readings of 50W every hour
        LocalDateTime currentTimestamp = startTime;
        while (currentTimestamp.isBefore(endTime.plusMinutes(1))) {
            int randomConsumption = 50;
            mapAllConsumption.put(currentTimestamp, randomConsumption);
            currentTimestamp = currentTimestamp.plusMinutes(60);
        }
        return mapAllConsumption;
    }
    //Method to get data from database within a selected time period
    protected Map<LocalDateTime, Integer> getDataFromSelectedTimePeriod(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
        if (startTimestamp.truncatedTo(ChronoUnit.MINUTES).isAfter(endTimestamp.truncatedTo(ChronoUnit.MINUTES)))
            throw new IllegalArgumentException("Start timestamp must be before end timestamp.");

        Map<LocalDateTime, Integer> mapAllConsumption = artificialDatabaseOfReadingsSetTo50W(); // For example purposes, this map is representing a database with all the power consumptions
        Map<LocalDateTime, Integer> mapChosenTime = filterPowerConsumptionsByTime(startTimestamp.truncatedTo(ChronoUnit.MINUTES),
                endTimestamp.truncatedTo(ChronoUnit.MINUTES), mapAllConsumption);
        return mapChosenTime;
    }

    //Method to filter power consumptions by time
    private Map<LocalDateTime, Integer> filterPowerConsumptionsByTime(LocalDateTime startTimestamp, LocalDateTime endTimestamp, Map<LocalDateTime, Integer> mapAllConsumption) {
        if (startTimestamp.isBefore((ChronoLocalDateTime<?>) mapAllConsumption.keySet().toArray()[0])
                || endTimestamp.isAfter((ChronoLocalDateTime<?>) mapAllConsumption.keySet().toArray()[mapAllConsumption.size() - 1]))
            throw new IllegalArgumentException("Timestamps must be within the range of the records.");
        Map<LocalDateTime, Integer> filteredPowerConsumptions = mapAllConsumption.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(startTimestamp) && !entry.getKey().isAfter(endTimestamp))
                .collect(HashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), Map::putAll);
        return filteredPowerConsumptions;
    }

    //Method to calculate consumption in Wh for a given interval
    protected Integer calculateConsumptionInWh(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
        Map<LocalDateTime, Integer> mapChosenTime = getDataFromSelectedTimePeriod(startTimestamp, endTimestamp);
        int consumptionWh = mapChosenTime.values().stream().mapToInt(Integer::intValue).sum();
        return consumptionWh;
    }

    //Default behaviour of the sensor is to return consumption in Wh for the last 24 hours
    public Value getValue(){
        int consumptionWh = calculateConsumptionInWh(LocalDateTime.now().minusHours(24), LocalDateTime.now());
        _ElectricConsumptionWhValue = new ElectricConsumptionWhValue(consumptionWh);
        return _ElectricConsumptionWhValue;
    }
    //Method to get consumption in Wh for a given interval
    public Value getValue(LocalDateTime startTimestamp, LocalDateTime endTimestamp){
        int consumptionWh = calculateConsumptionInWh(startTimestamp, endTimestamp);
        _ElectricConsumptionWhValue = new ElectricConsumptionWhValue(consumptionWh);
        return _ElectricConsumptionWhValue;
    }
}
