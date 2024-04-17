package smart_home.service;

import smart_home.ddd.IRepository;
import smart_home.domain.device.Device;
import smart_home.domain.repository.IDeviceRepository;
import smart_home.domain.sensor.ISensor;
import smart_home.domain.sensor.ISensorFactory;
import smart_home.domain.service.ISensorService;
import smart_home.value_object.DeviceID;
import smart_home.value_object.SensorID;

import java.util.Optional;

public class SensorServiceImpl implements ISensorService {

    private IRepository<SensorID, ISensor> _sensorRepository;
    private ISensorFactory _sensorFactory;
    private IDeviceRepository _deviceRepository;

    /**
     * Constructor for SensorService.
     *
     * @param sensorRepository is the repository for sensors.
     * @param sensorFactory    is the factory for sensors.
     * @param deviceRepository is the repository for devices.
     */
    public SensorServiceImpl(
            IRepository<SensorID, ISensor> sensorRepository,
            ISensorFactory sensorFactory,
            IDeviceRepository deviceRepository) {
        validateSensorRepository(sensorRepository);
        validateSensorFactory(sensorFactory);
        validateDeviceRepository(deviceRepository);
    }

    /**
     * Validates the SensorRepository.
     *
     * @param sensorRepository The SensorRepository to validate.
     */
    private void validateSensorRepository(IRepository<SensorID, ISensor> sensorRepository) {
        if (sensorRepository == null) {
            throw new IllegalArgumentException("Please enter a valid sensor repository.");
        } else {
            this._sensorRepository = sensorRepository;
        }
    }

    /**
     * Validates the SensorFactory.
     *
     * @param sensorFactory The SensorFactory to validate.
     */
    private void validateSensorFactory(ISensorFactory sensorFactory) {
        if (sensorFactory == null) {
            throw new IllegalArgumentException("Please enter a valid sensor factory.");
        } else {
            this._sensorFactory = sensorFactory;
        }
    }

    /**
     * Validates the DeviceRepository.
     *
     * @param deviceRepository The DeviceRepository to validate.
     */
    private void validateDeviceRepository(IDeviceRepository deviceRepository) {
        if (deviceRepository == null) {
            throw new IllegalArgumentException("Please enter a valid device repository.");
        } else {
            this._deviceRepository = deviceRepository;
        }
    }

    /**
     * Adds a sensor to the repository and saves it.
     *
     * @param parameters The parameters required to create a sensor object. The first parameter should
     *                   be of type DeviceID. The rest of the parameters should be the required parameters to create
     *                   a sensor object.
     * @return The created and saved Sensor object.
     */
    @Override
    public ISensor addSensor(Object... parameters) {
        DeviceID deviceID = (DeviceID) parameters[0];

        Optional<Device> deviceOptional = _deviceRepository.ofIdentity(deviceID);
        if (deviceOptional.isEmpty()) {
            throw new IllegalArgumentException("Device with ID " + deviceID + " not found.");
        }

        ISensor sensor = _sensorFactory.create(parameters);
        _sensorRepository.save(sensor);
        return sensor;
    }
}
