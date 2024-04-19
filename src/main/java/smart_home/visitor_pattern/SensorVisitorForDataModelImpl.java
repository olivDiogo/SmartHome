package smart_home.visitor_pattern;

import smart_home.domain.sensor.average_power_consumption_sensor.AveragePowerConsumptionSensor;
import smart_home.domain.sensor.dew_point_sensor.DewPointSensor;
import smart_home.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor;
import smart_home.domain.sensor.humidity_sensor.HumiditySensor;
import smart_home.domain.sensor.instant_power_consumption_sensor.InstantPowerConsumptionSensor;
import smart_home.domain.sensor.percentage_position_sensor.PercentagePositionSensor;
import smart_home.domain.sensor.solar_irradiance_sensor.SolarIrradianceSensor;
import smart_home.domain.sensor.sunrise_time_sensor.SunriseTimeSensor;
import smart_home.domain.sensor.sunset_time_sensor.SunsetTimeSensor;
import smart_home.domain.sensor.switch_sensor.SwitchSensor;
import smart_home.domain.sensor.temperature_sensor.TemperatureSensor;
import smart_home.domain.sensor.wind_sensor.WindSensor;
import smart_home.persistence.jpa.data_model.SensorDataModel;


public class SensorVisitorForDataModelImpl implements ISensorVisitorForDataModel{
    private SensorDataModel sensorDataModel;

    public SensorVisitorForDataModelImpl(SensorDataModel sensorDataModel) {
        this.sensorDataModel = sensorDataModel;
    }


    @Override
    public SensorDataModel getSensorDataModel() {
        return sensorDataModel;
    }

    @Override
    public String visitTemperatureSensor(TemperatureSensor temperatureSensor) {
        sensorDataModel.setGenericSensor(temperatureSensor);
        return sensorDataModel.toString();
    }

    @Override
    public String visitHumiditySensor(HumiditySensor humiditySensor) {
        sensorDataModel.setGenericSensor(humiditySensor);

        return sensorDataModel.toString();
    }

    @Override
    public String visitSunsetTimeSensor(SunsetTimeSensor sunsetTimeSensor) {
        sensorDataModel.setGenericSensor(sunsetTimeSensor);
        String latitude = String.valueOf(sunsetTimeSensor.getGPS().getLatitude());
        String longitude = String.valueOf(sunsetTimeSensor.getGPS().getLongitude());
        sensorDataModel.setLatitude(latitude);
        sensorDataModel.setLongitude(longitude);

        return sensorDataModel.toString();
    }

    @Override
    public String visitWindSensor(WindSensor windSensor) {
        sensorDataModel.setGenericSensor(windSensor);

        return sensorDataModel.toString();
    }

    @Override
    public String visitSwitchSensor(SwitchSensor switchSensor) {
        sensorDataModel.setGenericSensor(switchSensor);

        return sensorDataModel.toString();
    }

    @Override
    public String visitSunriseTimeSensor(SunriseTimeSensor sunriseTimeSensor) {
        sensorDataModel.setGenericSensor(sunriseTimeSensor);
        String latitude = String.valueOf(sunriseTimeSensor.getGPS().getLatitude());
        String longitude = String.valueOf(sunriseTimeSensor.getGPS().getLongitude());
        sensorDataModel.setLatitude(latitude);
        sensorDataModel.setLongitude(longitude);

        return sensorDataModel.toString();
    }

    @Override
    public String visitSolarIrradianceSensor(SolarIrradianceSensor solarIrradianceSensor) {
        sensorDataModel.setGenericSensor(solarIrradianceSensor);

        return sensorDataModel.toString();
    }

    @Override
    public String visitPercentageSensor(PercentagePositionSensor percentagePositionSensor) {
        sensorDataModel.setGenericSensor(percentagePositionSensor);

        return sensorDataModel.toString();
    }

    @Override
    public String visitInstantPowerSensor(InstantPowerConsumptionSensor instantPowerConsumptionSensor) {
        sensorDataModel.setGenericSensor(instantPowerConsumptionSensor);

        return sensorDataModel.toString();
    }
    @Override
    public String visitDewPointSensor(DewPointSensor dewPointSensor) {
        sensorDataModel.setGenericSensor(dewPointSensor);

        return sensorDataModel.toString();
    }

    @Override
    public String visitAveragePowerConsumptionSensor(AveragePowerConsumptionSensor averagePowerConsumptionSensor) {
        sensorDataModel.setGenericSensor(averagePowerConsumptionSensor);

        return sensorDataModel.toString();
    }
    @Override
    public String visitElectricConsumptionWhSensor(ElectricConsumptionWhSensor electricConsumptionWhSensor) {
        sensorDataModel.setGenericSensor(electricConsumptionWhSensor);
        String startDate = String.valueOf(electricConsumptionWhSensor.getDatePeriod().getStartDate());
        String endDate = String.valueOf(electricConsumptionWhSensor.getDatePeriod().getEndDate());
        sensorDataModel.setStartDate(startDate);
        sensorDataModel.setEndDate(endDate);

        return sensorDataModel.toString();
    }
}
