package SmartHome.domain;

import java.lang.reflect.InvocationTargetException;

public class SensorFactory {

    /**
     * Creates a new Sensor object with the given model and catalogue.
     *
     * @param strModel The model of the sensor.
     * @param catalogue The catalogue of the sensor.
     * @return A new Sensor object with the specified model and catalogue.
     * @throws InstantiationException if the Sensor class for the specified model cannot be instantiated.
     */
    public Sensor createSensor(String strModel, CatalogueSensor catalogue) throws InstantiationException {
        try {
            Sensor sensor = (Sensor) Class.forName(strModel).getConstructor(CatalogueSensor.class).newInstance(catalogue);
            return sensor;
        }
        // due to the previous conditions, exception will not throw, but Class.forName... requires the catch
        catch(  ClassNotFoundException |
                NoSuchMethodException |
                InstantiationException |
                IllegalAccessException |
                InvocationTargetException e )
        {
            return null;
        }
    }
}
