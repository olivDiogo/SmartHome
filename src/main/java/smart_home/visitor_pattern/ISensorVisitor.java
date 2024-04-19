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

public interface ISensorVisitor {
    String visitTemperatureSensor(TemperatureSensor temperatureSensor);
    String visitHumiditySensor(HumiditySensor humiditySensor);
    String visitSunsetTimeSensor(SunsetTimeSensor sunsetTimeSensor);
    String visitWindSensor(WindSensor windSensor);
    String visitSwitchSensor(SwitchSensor switchSensor);
    String visitSunriseTimeSensor(SunriseTimeSensor sunriseTimeSensor);
    String visitSolarIrradianceSensor(SolarIrradianceSensor solarIrradianceSensor);
    String visitPercentageSensor(PercentagePositionSensor percentagePositionSensor);
    String visitInstantPowerSensor(InstantPowerConsumptionSensor instantPowerConsumptionSensor);
    String visitDewPointSensor(DewPointSensor dewPointSensor);
    String visitAveragePowerConsumptionSensor(AveragePowerConsumptionSensor averagePowerConsumptionSensor);
    String visitElectricConsumptionWhSensor(ElectricConsumptionWhSensor electricConsumptionWhSensor);

}
