/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper.sensor_vo_assembler;

import java.time.LocalDateTime;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.PathEncoder;
import smarthome.utils.dto.data_dto.sensor_data_dto.ISensorDataDTO;
import smarthome.utils.dto.data_dto.sensor_data_dto.SensorDataGenericDTOImp;
import smarthome.utils.dto.data_dto.sensor_data_dto.SensorDataWithDateDTOImp;
import smarthome.utils.dto.data_dto.sensor_data_dto.SensorDataWithGPSDTOImp;

public class SensorVOAssemblerImpl implements ISensorVOAssembler {

  /**
   * Returns the parameters needed to create a sensor. The generic objects.
   *
   * @param sensorDataDTO The sensor data DTO.
   * @return The parameters needed to create a sensor.
   */
  private static Object[] getSensorParameters(SensorDataGenericDTOImp sensorDataDTO) {
    DeviceID deviceID = new DeviceID(sensorDataDTO.deviceID);
    String decodedModelPath = PathEncoder.decode(sensorDataDTO.sensorModelPath);
    ModelPath modelPath = new ModelPath(decodedModelPath);
    SensorName sensorName = new SensorName(sensorDataDTO.sensorName);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorDataDTO.sensorTypeID);
    return new Object[]{deviceID, modelPath, sensorTypeID, sensorName};
  }

  /**
   * Returns the parameters needed to create a sensor. The generic objects plus the GPS.
   *
   * @param sensorDataDTO The sensor data DTO.
   * @return The parameters needed to create a sensor.
   */
  private static Object[] getSensorParameters(SensorDataWithGPSDTOImp sensorDataDTO) {
    DeviceID deviceID = new DeviceID(sensorDataDTO.deviceID);
    String decodedModelPath = PathEncoder.decode(sensorDataDTO.sensorModelPath);
    ModelPath modelPath = new ModelPath(decodedModelPath);
    SensorName sensorName = new SensorName(sensorDataDTO.sensorName);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorDataDTO.sensorTypeID);
    double latitude = Double.parseDouble(sensorDataDTO.latitude);
    double longitude = Double.parseDouble(sensorDataDTO.longitude);
    GPS gps = new GPS(latitude, longitude);
    return new Object[]{deviceID, modelPath, sensorTypeID, sensorName, gps};
  }

  /**
   * Returns the parameters needed to create a sensor. The generic objects plus the date.
   *
   * @param sensorDataDTO The sensor data DTO.
   * @return The parameters needed to create a sensor.
   */
  private static Object[] getSensorParameters(SensorDataWithDateDTOImp sensorDataDTO) {
    DeviceID deviceID = new DeviceID(sensorDataDTO.deviceID);
    String decodedModelPath = PathEncoder.decode(sensorDataDTO.sensorModelPath);
    ModelPath modelPath = new ModelPath(decodedModelPath);
    SensorName sensorName = new SensorName(sensorDataDTO.sensorName);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorDataDTO.sensorTypeID);
    LocalDateTime startDate = LocalDateTime.parse(sensorDataDTO.startDate);
    LocalDateTime endDate = LocalDateTime.parse(sensorDataDTO.endDate);
    DatePeriod date = new DatePeriod(startDate, endDate);
    return new Object[]{deviceID, modelPath, sensorTypeID, sensorName, date};
  }

  /**
   * Returns the parameters needed to create a sensor.
   *
   * @param sensorDataDTO The sensor data DTO.
   * @return The parameters needed to create a sensor.
   */
  @Override
  public Object[] getSensorParameters(ISensorDataDTO sensorDataDTO) {
    if (sensorDataDTO instanceof SensorDataGenericDTOImp sensorDataGenericDTOImp) {
      return getSensorParameters(sensorDataGenericDTOImp);
    } else if (sensorDataDTO instanceof SensorDataWithGPSDTOImp sensorWithGPSDataDTO) {
      return getSensorParameters(sensorWithGPSDataDTO);
    } else if (sensorDataDTO instanceof SensorDataWithDateDTOImp sensorDataWithDateDTO) {
      return getSensorParameters(sensorDataWithDateDTO);
    } else {
      throw new IllegalArgumentException("Unsupported sensor data DTO");
    }
  }
}