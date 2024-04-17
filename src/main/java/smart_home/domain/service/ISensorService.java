package smart_home.domain.service;

import smart_home.ddd.IService;
import smart_home.domain.sensor.ISensor;

public interface ISensorService extends IService {

    /**
     * Adds a new sensor to the repository.
     *
     * @param parameters the parameters of the sensor.
     * @return the sensor that was added.
     */
    ISensor addSensor(Object... parameters);
}
