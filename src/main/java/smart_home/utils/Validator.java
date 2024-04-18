package smart_home.utils;

import smart_home.domain.actuator.set_decimal_actuator.SetDecimalActuatorLimits;
import smart_home.domain.actuator.set_integer_actuator.SetIntegerActuatorLimits;
import smart_home.value_object.*;

public  class Validator {


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

    public static void validateNotNull(ActuatorName actuatorName) {
        if (actuatorName == null) {
            throw new IllegalArgumentException("ActuatorName is required");
        }
    }

    public static void validateNotNull(ActuatorID actuatorID){
        if (actuatorID == null) {
            throw new IllegalArgumentException("ActuatorID is required");
        }
    }

    public static void validateNotNull(ActuatorTypeID actuatorTypeID){
        if (actuatorTypeID == null) {
            throw new IllegalArgumentException("ActuatorTypeID is required");
        }
    }

    public static void validateNotNull(SetDecimalActuatorLimits limits) {
        if (limits == null) {
            throw new IllegalArgumentException("SetDecimalActuatorLimits are required");
        }
    }

    public static void validateNotNull(SetIntegerActuatorLimits limits) {
        if (limits == null) {
            throw new IllegalArgumentException("SetIntegerActuatorLimits are required");
        }
    }



}
