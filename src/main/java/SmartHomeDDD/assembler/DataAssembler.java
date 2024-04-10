package SmartHomeDDD.assembler;

import SmartHomeDDD.DTO.SensorDataDTO;
import SmartHomeDDD.valueObject.*;

import java.util.ArrayList;
import java.util.List;

public class DataAssembler {

    /**
     * Converts the SensorDataDTO to an array of parameters.
     *
     * @param sensorDataDTO The SensorDataDTO to convert.
     * @return An array of parameters.
     */
    private static Object[] getObjects(SensorDataDTO sensorDataDTO) {
        ModelPath modelPath = new ModelPath(sensorDataDTO.sensorModelPath);
        DeviceID deviceID = new DeviceID(sensorDataDTO.deviceID);
        SensorTypeID sensorTypeID = new SensorTypeID(sensorDataDTO.sensorTypeID);
        SensorName sensorName = new SensorName(sensorDataDTO.sensorName);

        GPS gps;
        if (sensorDataDTO.coordinates.isEmpty()) {
            gps = null;
        } else {
            gps = new GPS(sensorDataDTO.coordinates.get(0), sensorDataDTO.coordinates.get(1));
        }

        DatePeriod datePeriod;
        if (sensorDataDTO.dateTimes.isEmpty()) {
            datePeriod = null;
        } else {
            datePeriod = new DatePeriod(sensorDataDTO.dateTimes.get(0), sensorDataDTO.dateTimes.get(1));
        }

        Object[] parametersArray = {deviceID, modelPath, sensorTypeID, sensorName, gps, datePeriod};
        return parametersArray;
    }

    /**
     * Converts the SensorDataDTO to an array of non null parameters.
     *
     * @param sensorDataDTO The SensorDataDTO to convert.
     * @return An array of parameters.
     */
    public static Object[] convertSensorDataToParameters(SensorDataDTO sensorDataDTO) {
        List<Object> parameterList = new ArrayList<>();

        Object[] parametersArray = getObjects(sensorDataDTO);

        for (Object parameter : parametersArray) {
            if (parameter != null) {
                parameterList.add(parameter);
            }
        }
        Object[] convertedParameters = parameterList.toArray(new Object[0]);

        return convertedParameters;
    }
}
