package smartHome.assembler.sensorVOAssembler;

import smartHome.dto.sensorDataDto.ISensorDataDTO;
import smartHome.dto.sensorDataDto.SensorDataGenericDTOImp;
import smartHome.dto.sensorDataDto.SensorDataWithDateDTOImp;
import smartHome.dto.sensorDataDto.SensorDataWithGPSDTOImp;
import smartHome.valueObject.*;

import java.time.LocalDateTime;

public class SensorVOAssemblerImpl implements ISensorVOAssembler {
    @Override
    public Object[] getSensorParameters(ISensorDataDTO sensorDataDTO) {
        if (sensorDataDTO instanceof SensorDataGenericDTOImp sensorDataGenericDTOImp) {
            return getSensorParameteres(sensorDataGenericDTOImp);
        } else if (sensorDataDTO instanceof SensorDataWithGPSDTOImp sensorWithGPSDataDTO) {
            return getSensorParameteres(sensorWithGPSDataDTO);
        } else if (sensorDataDTO instanceof SensorDataWithDateDTOImp sensorDataWithDateDTO) {
            return getSensorParameteres(sensorDataWithDateDTO);
        }
        else {
            throw new IllegalArgumentException("Unsupported sensor data DTO");
        }
    }

    private static Object[] getSensorParameteres (SensorDataGenericDTOImp sensorDataDTO) {
        DeviceID deviceID = new DeviceID(sensorDataDTO.deviceID);
        ModelPath modelPath = new ModelPath(sensorDataDTO.sensorModelPath);
        SensorName sensorName = new SensorName(sensorDataDTO.sensorName);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorDataDTO.sensorTypeID);
        return new Object[]{deviceID, modelPath, sensorTypeID, sensorName};
    }
    private static Object[] getSensorParameteres (SensorDataWithGPSDTOImp sensorDataDTO) {
        DeviceID deviceID = new DeviceID(sensorDataDTO.deviceID);
        ModelPath modelPath = new ModelPath(sensorDataDTO.sensorModelPath);
        SensorName sensorName = new SensorName(sensorDataDTO.sensorName);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorDataDTO.sensorTypeID);
        double latitude = Double.parseDouble(sensorDataDTO.latitude);
        double longitude = Double.parseDouble(sensorDataDTO.longitude);
        GPS gps = new GPS(latitude, longitude);
        return new Object[]{deviceID, modelPath, sensorTypeID, sensorName, gps};
    }
    private static Object[] getSensorParameteres (SensorDataWithDateDTOImp sensorDataDTO) {
        DeviceID deviceID = new DeviceID(sensorDataDTO.deviceID);
        ModelPath modelPath = new ModelPath(sensorDataDTO.sensorModelPath);
        SensorName sensorName = new SensorName(sensorDataDTO.sensorName);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorDataDTO.sensorTypeID);
        LocalDateTime startDate = LocalDateTime.parse(sensorDataDTO.startDate);
        LocalDateTime endDate = LocalDateTime.parse(sensorDataDTO.endDate);
        DatePeriod date = new DatePeriod(startDate, endDate);
        return new Object[]{deviceID, modelPath, sensorTypeID, sensorName, date};
    }
}