package smarthome.service;

import smarthome.ddd.IRepository;
import smarthome.domain.device.Device;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor.ISensorFactory;
import smarthome.domain.service.ISensorService;
import smarthome.utils.Validator;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorID;

import java.util.Optional;

public class SensorServiceImpl implements ISensorService {

  private final IRepository<SensorID, ISensor> sensorRepository;
  private final ISensorFactory sensorFactory;
  private final IDeviceRepository deviceRepository;

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

    Validator.validateNotNull(sensorRepository, "Sensor repository.");
    this.sensorRepository = sensorRepository;
    Validator.validateNotNull(sensorFactory, "Sensor factory.");
    this.sensorFactory = sensorFactory;
    Validator.validateNotNull(deviceRepository, "Device repository.");
    this.deviceRepository = deviceRepository;

  }


  /**
   * Adds a sensor to the repository and saves it.
   *
   * @param parameters The parameters required to create a sensor object. The first parameter should
   *                   be of type DeviceID. The rest of the parameters should be the required
   *                   parameters to create a sensor object.
   * @return The created and saved Sensor object.
   */
  @Override
  public ISensor addSensor(Object... parameters) {
    DeviceID deviceID = (DeviceID) parameters[0];

    Optional<Device> deviceOptional = deviceRepository.ofIdentity(deviceID);
    if (deviceOptional.isEmpty()) {
      throw new IllegalArgumentException("Device with ID " + deviceID + " not found.");
    }
    if (!deviceOptional.get().getDeviceStatus().getStatus()) {
      throw new IllegalArgumentException("Device with ID " + deviceID + " is deactivated.");
    }

    ISensor sensor = sensorFactory.create(parameters);
    sensorRepository.save(sensor);
    return sensor;
  }
}
