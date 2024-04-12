package smartHome.domain.actuator;

import smartHome.valueObject.ModelPath;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ActuatorFactoryImpl implements IActuatorFactory {


    /**
     * Create an actuator instance based on the provided parameters
     *
     * @param parameters Additional parameters required by the actuator
     * @return Actuator instance
     */

    @Override
    public IActuator createActuator(Object... parameters) {
        try {
            if (parameters.length < 4) {
                throw new IllegalArgumentException("At least 4 parameters are required.");
            }
            ModelPath modelPath = (ModelPath) parameters[1];

            Class<?> actuatorClass = Class.forName(modelPath.toString());
            Constructor<?> constructor = findMatchingConstructor(actuatorClass, parameters);

            if (constructor != null) {
                return (IActuator) constructor.newInstance(parameters);
            } else {
                throw new InstantiationException("No matching constructor found for class: " + modelPath);
            }

        } catch (ClassNotFoundException | InstantiationException | ClassCastException e) {
//            e.printStackTrace(); // Log the exception
        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace(); // Log the exception
        }
        return null;
    }

    /**
     * Find a constructor that matches the provided parameters
     *
     * @param actuatorClass Actuator class
     * @param parameters    Additional parameters required by the actuator
     * @return Constructor instance
     */

    private Constructor<?> findMatchingConstructor(Class<?> actuatorClass, Object... parameters) {
        Constructor<?>[] constructors = actuatorClass.getConstructors();

        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();

            if (parameterTypes.length == parameters.length) {
                boolean match = true;

                for (int i = 0; i < parameterTypes.length; i++) {
                    if (!parameterTypes[i].isAssignableFrom(parameters[i].getClass())) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    return constructor;
                }
            }
        }

        return null;
    }
}