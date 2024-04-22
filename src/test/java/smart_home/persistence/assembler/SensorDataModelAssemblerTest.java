package smart_home.persistence.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import smart_home.domain.sensor.ISensor;
import smart_home.domain.sensor.ISensorFactory;
import smart_home.domain.sensor.SensorFactoryImpl;
import smart_home.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor;
import smart_home.domain.sensor.sunrise_time_sensor.SunriseTimeSensor;
import smart_home.domain.sensor.temperature_sensor.TemperatureSensor;
import smart_home.persistence.jpa.data_model.SensorDataModel;
import smart_home.value_object.DatePeriod;
import smart_home.value_object.DeviceID;
import smart_home.value_object.GPS;
import smart_home.value_object.ModelPath;
import smart_home.value_object.SensorName;
import smart_home.value_object.SensorTypeID;

class SensorDataModelAssemblerTest {

  @Test
  void shouldInstantiateGenericSensorWhenGivenValidParameters() {
    String deviceIDValue = "some-device-id";
    String modelPathValue = "smart_home.domain.sensor.temperature_sensor.TemperatureSensor";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Temperature";
    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);
    SensorDataModel sensorDataModel = new SensorDataModel(sensor);

    ISensorFactory sensorFactory = new SensorFactoryImpl();

    SensorDataModelAssembler sensorDataModelAssembler = new SensorDataModelAssembler(sensorFactory);

    ISensor sensor2 = sensorDataModelAssembler.toDomain(sensorDataModel);

    // Act

    // Assert
    assertEquals(sensor, sensor2);

  }

  @Test
  void shouldInstantiateSensorWithGPS(){
  // Arrange
  String deviceIDValue = "deviceID";
  String modelPathValue = "smart_home.domain.sensor.sunrise_time_sensor.SunriseTimeSensor";
  String sensorNameValue = "sensorName";
  String sensorTypeIDValue = "SunriseTime";
  double GPSLatitude = 90.0;
  double GPSLongitude = 180.0;

  DeviceID deviceID = new DeviceID(deviceIDValue);
  ModelPath modelPath = new ModelPath(modelPathValue);
  SensorName sensorName = new SensorName(sensorNameValue);
  SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
  GPS gps = new GPS(GPSLatitude, GPSLongitude);

  // Act
  SunriseTimeSensor result = new SunriseTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps);
  SensorDataModel sensorDataModel = new SensorDataModel(result);
  sensorDataModel.setLatitude(String.valueOf(GPSLatitude));
  sensorDataModel.setLongitude(String.valueOf(GPSLongitude));

  ISensorFactory sensorFactory = new SensorFactoryImpl();
  SensorDataModelAssembler sensorDataModelAssembler = new SensorDataModelAssembler(sensorFactory);
  ISensor sensor = sensorDataModelAssembler.toDomain(sensorDataModel);

  // Assert
  assertEquals(result, sensor);
  }
  @Test
  void shouldInstantiateSensorWithDatePeriod(){
    //Arrange
    ModelPath modelPath = new ModelPath("smart_home.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    SensorDataModel sensorDataModel = new SensorDataModel(electricConsumptionWhSensor);
    sensorDataModel.setStartDate(startDate.toString());
    sensorDataModel.setEndDate(endDate.toString());

    ISensorFactory sensorFactory = new SensorFactoryImpl();
    SensorDataModelAssembler sensorDataModelAssembler = new SensorDataModelAssembler(sensorFactory);

    //Act
    ISensor sensor = sensorDataModelAssembler.toDomain(sensorDataModel);

    //Assert
    assertEquals(electricConsumptionWhSensor, sensor);

  }
}