package smart_home.mapper.sensor_vo_assembler;

import org.junit.jupiter.api.Test;
import smart_home.dto.sensor_data_dto.ISensorDataDTO;
import smart_home.dto.sensor_data_dto.SensorDataGenericDTOImp;
import smart_home.dto.sensor_data_dto.SensorDataWithDateDTOImp;
import smart_home.dto.sensor_data_dto.SensorDataWithGPSDTOImp;
import smart_home.value_object.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SensorVOAssemblerImplTest {
    @Test
    void shouldThrowIllegalArgumentExceptionWhenUnsupportedSensorDataDTO() {
        // Arrange
        SensorVOAssemblerImpl sensorVOAssembler = new SensorVOAssemblerImpl();
        String message = "Unsupported sensor data DTO";
        ISensorDataDTO sensorDataDTO = null;
        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> sensorVOAssembler.getSensorParameters(sensorDataDTO));
        // Assert
        assertEquals(message, exception.getMessage());
        };

    @Test
    void shouldReturnArrayOfObjectsWhenSensorDataDTOIsSensorWithGPSDataDTO() {
        // Arrange
        String deviceID = "deviceID";
        String sensorModelPath = "sensorModelPath";
        String sensorName = "sensorName";
        String sensorTypeID = "sensorTypeID";
        String latitude = "8.5";
        String longitude = "0.7";
        ISensorDataDTO sensorDataDTO = new SensorDataWithGPSDTOImp(deviceID, sensorModelPath, sensorName, sensorTypeID, latitude, longitude);
        SensorVOAssemblerImpl sensorVOAssembler = new SensorVOAssemblerImpl();

        DeviceID deviceID1 = new DeviceID(deviceID);
        ModelPath modelPath = new ModelPath(sensorModelPath);
        SensorName sensorName1 = new SensorName(sensorName);
        SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeID);
        GPS gps = new GPS(Double.parseDouble(latitude), Double.parseDouble(longitude));
        Object[] expected = {deviceID1, modelPath, sensorTypeID1, sensorName1, gps};

        // Act
        Object[] result = sensorVOAssembler.getSensorParameters(sensorDataDTO);
        // Assert
        assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
        }

    @Test
    void shouldReturnArrayOfObjectsWhenSensorDataDTOIsSensorGenericDataDTOImp() {
        // Arrange
        String deviceID = "deviceID";
        String sensorModelPath = "sensorModelPath";
        String sensorName = "sensorName";
        String sensorTypeID = "sensorTypeID";
        ISensorDataDTO sensorDataDTO = new SensorDataGenericDTOImp(deviceID, sensorModelPath, sensorName, sensorTypeID);
        SensorVOAssemblerImpl sensorVOAssembler = new SensorVOAssemblerImpl();

        DeviceID deviceID1 = new DeviceID(deviceID);
        ModelPath modelPath = new ModelPath(sensorModelPath);
        SensorName sensorName1 = new SensorName(sensorName);
        SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeID);
        Object[] expected = {deviceID1, modelPath, sensorTypeID1, sensorName1};

        // Act
        Object[] result = sensorVOAssembler.getSensorParameters(sensorDataDTO);
        // Assert
        assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
        }
        @Test
    void shouldReturnArrayOfObjectsWhenSensorDataDTOIsSensorDataWithDateDTOImp() {
        // Arrange
        String deviceID = "deviceID";
        String sensorModelPath = "sensorModelPath";
        String sensorName = "sensorName";
        String sensorTypeID = "sensorTypeID";
        String startDate = "2021-08-01T00:00:00";
        String endDate = "2021-08-02T00:00:00";
        ISensorDataDTO sensorDataDTO = new SensorDataWithDateDTOImp(deviceID, sensorModelPath, sensorName, sensorTypeID, startDate, endDate);
        SensorVOAssemblerImpl sensorVOAssembler = new SensorVOAssemblerImpl();

        DeviceID deviceID1 = new DeviceID(deviceID);
        ModelPath modelPath = new ModelPath(sensorModelPath);
        SensorName sensorName1 = new SensorName(sensorName);
        SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeID);
        LocalDateTime startDate1 = LocalDateTime.parse(startDate);
        LocalDateTime endDate1 = LocalDateTime.parse(endDate);
        DatePeriod date = new DatePeriod(startDate1, endDate1);
        Object[] expected = {deviceID1, modelPath, sensorTypeID1, sensorName1, date};

        // Act
        Object[] result = sensorVOAssembler.getSensorParameters(sensorDataDTO);
        // Assert
        assertEquals(Arrays.stream(expected).toList(), Arrays.stream(result).toList());
        }

    }
