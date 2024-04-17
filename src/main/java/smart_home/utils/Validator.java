package smart_home.utils;

import smart_home.value_object.*;

public class Validator {

    Validator() {
    }

    public static void validateNotNull(DeviceID deviceID) {
        if (deviceID == null) {
            throw new IllegalArgumentException("DeviceID is required");
        }
    }

    public static void validateNotNull(ModelPath modelPath) {
        if (modelPath == null) {
            throw new IllegalArgumentException("ModelPath is required");
        }
    }

    public static void validateNotNull(SensorName sensorName) {
        if (sensorName == null) {
            throw new IllegalArgumentException("SensorName is required");
        }
    }

    public static void validateNotNull(SensorID sensorID){
        if (sensorID == null) {
            throw new IllegalArgumentException("SensorID is required");
        }
    }

    public static void validateNotNull(DatePeriod datePeriod) {
        if (datePeriod == null) {
            throw new IllegalArgumentException("DatePeriod is required");
        }
    }

    public static void validateNotNull (GPS gps){
        if (gps == null){
            throw new IllegalArgumentException("GPS is required");
        }
    }

    public static void validateNotNull(SensorTypeID sensorTypeID){
        if (sensorTypeID == null) {
            throw new IllegalArgumentException("SensorTypeID is required");
        }
    }

}
