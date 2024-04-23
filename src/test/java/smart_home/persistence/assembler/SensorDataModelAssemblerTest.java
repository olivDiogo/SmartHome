package smart_home.persistence.assembler;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
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

  @Test
  void shouldReturnListOfSensorsOfDifferentSubClasses(){
    //Arrange
    //Sensor Temp
    String deviceIDTemp = "some-device-id";
    String modelPathTemp = "smart_home.domain.sensor.temperature_sensor.TemperatureSensor";
    String sensorNameTemp = "sensorName";
    String sensorTypeIDTemp = "Temperature";
    DeviceID deviceID = new DeviceID(deviceIDTemp);
    ModelPath modelPath = new ModelPath(modelPathTemp);
    SensorName sensorName = new SensorName(sensorNameTemp);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDTemp);

    TemperatureSensor temperatureSensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);
    SensorDataModel tempatureSensorDataModel = new SensorDataModel(temperatureSensor);

    //Sensor Sunrise
    String deviceIDSunr = "deviceID";
    String modelPathSunr = "smart_home.domain.sensor.sunrise_time_sensor.SunriseTimeSensor";
    String sensorNameSunr = "sensorName";
    String sensorTypeIDSunr = "SunriseTime";
    double GPSLatitude = 90.0;
    double GPSLongitude = 180.0;

    DeviceID sunrDeviceID = new DeviceID(deviceIDSunr);
    ModelPath sunrModelPath = new ModelPath(modelPathSunr);
    SensorName sunrSensorName = new SensorName(sensorNameSunr);
    SensorTypeID sunrSensorTypeID = new SensorTypeID(sensorTypeIDSunr);
    GPS gps = new GPS(GPSLatitude, GPSLongitude);

    SunriseTimeSensor sunriseTimeSensor = new SunriseTimeSensor(sunrDeviceID, sunrModelPath, sunrSensorTypeID, sunrSensorName, gps);
    SensorDataModel sunriseSensorDataModel = new SensorDataModel(sunriseTimeSensor);
    sunriseSensorDataModel.setLatitude(String.valueOf(GPSLatitude));
    sunriseSensorDataModel.setLongitude(String.valueOf(GPSLongitude));

    List<SensorDataModel> sensorDataModels = List.of(tempatureSensorDataModel, sunriseSensorDataModel);
    List<ISensor> expected = List.of(temperatureSensor, sunriseTimeSensor);

    ISensorFactory sensorFactory = new SensorFactoryImpl();

    SensorDataModelAssembler sensorDataModelAssembler = new SensorDataModelAssembler(sensorFactory);
    //Act

    List<ISensor> result = sensorDataModelAssembler.toDomain(sensorDataModels);
    System.out.println(result);
    System.out.println(expected);

    //Assert
    assertEquals(expected, result);
  }
}