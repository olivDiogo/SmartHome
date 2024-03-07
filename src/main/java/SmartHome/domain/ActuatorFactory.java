package SmartHome.domain;

import java.lang.reflect.InvocationTargetException;

public class ActuatorFactory {
    /**
     * Constructer for the ActuatorFactory class.
     *
     * @param strModel  The model of the actuator.
     * @param catalogue The catalogue of the actuator.
     * @return The actuator.
     * @throws InstantiationException If the actuator cannot be instantiated.
     */
    public Actuator createActuator(String strModel, CatalogueActuator catalogue) throws InstantiationException {
        try {
            Actuator actuator = (Actuator) Class.forName(strModel).getConstructor(CatalogueActuator.class).newInstance(catalogue);
            return actuator;
        }
        // due to the previous conditions, exception will not throw, but Class.forName... requires the catch
        catch (ClassNotFoundException |
               NoSuchMethodException |
               InstantiationException |
               IllegalAccessException |
               InvocationTargetException e) {
            return null;
        }
    }
}
