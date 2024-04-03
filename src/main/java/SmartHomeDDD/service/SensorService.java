package SmartHomeDDD.service;

import SmartHomeDDD.ddd.Repository;
import SmartHomeDDD.domain.Device.Device;
import SmartHomeDDD.domain.Device.DeviceRepo;
import SmartHomeDDD.domain.Sensor.Sensor;
import SmartHomeDDD.domain.Sensor.SensorFactory;
import SmartHomeDDD.valueObject.*;

import java.util.Optional;

public class SensorService {

    private Repository<SensorID, Sensor> _sensorRepository;
    private SensorFactory _sensorFactory;
    private DeviceRepo _deviceRepository;

    public SensorService(Repository<SensorID, Sensor> sensorRepository, SensorFactory sensorFactory, DeviceRepo deviceRepository) {
        validateSensorRepository(sensorRepository);
        validateSensorFactory(sensorFactory);
        validateDeviceRepository(deviceRepository);

    }

    /* validate the sensor repository */
    private void validateSensorRepository(Repository<SensorID, Sensor> sensorRepository) {
        if (sensorRepository == null) {
            throw new IllegalArgumentException("Please enter a valid sensor repository.");
        } else {
            this._sensorRepository = sensorRepository;
        }
    }

    /* validate the sensor factory */
    private void validateSensorFactory(SensorFactory sensorFactory) {
        if (sensorFactory == null) {
            throw new IllegalArgumentException("Please enter a valid sensor factory.");
        } else {
            this._sensorFactory = sensorFactory;
        }
    }

    private void validateDeviceRepository(DeviceRepo deviceRepository) {
        if (deviceRepository == null) {
            throw new IllegalArgumentException("Please enter a valid device repository.");
        } else {
            this._deviceRepository = deviceRepository;
        }
    }

    public Sensor addSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName) {
        Optional<Device> deviceOptional = _deviceRepository.ofIdentity(deviceID);
        if (deviceOptional.isEmpty()) {
            throw new IllegalArgumentException("Device with ID " + deviceID + " not found.");
        }

        Sensor sensor = _sensorFactory.create(deviceID, modelPath, sensorTypeID, sensorName);
        _sensorRepository.save(sensor);
        return sensor;
    }

}
