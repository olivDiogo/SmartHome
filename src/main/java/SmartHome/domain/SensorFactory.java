package SmartHome.domain;

import java.lang.reflect.InvocationTargetException;

public class SensorFactory {
    public Sensor createSensor(String strModel, CatalogueSensors catalogue) throws InstantiationException {
        try {
            //String strModel2 = "SmartHome.".concat(strModel);
            Sensor sensor = (Sensor) Class.forName(strModel).getConstructor(CatalogueSensors.class).newInstance(catalogue);
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
