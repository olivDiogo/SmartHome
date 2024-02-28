package SmartHome.domain;

import java.lang.reflect.InvocationTargetException;

public class ActuatorFactory {
    public Actuator createActuator(String strModel, CatalogueActuator catalogue) throws InstantiationException {
        try {
            //String strModel2 = "SmartHome.".concat(strModel);
            Actuator actuator = (Actuator) Class.forName(strModel).getConstructor(CatalogueActuator.class).newInstance(catalogue);
            return actuator;
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
