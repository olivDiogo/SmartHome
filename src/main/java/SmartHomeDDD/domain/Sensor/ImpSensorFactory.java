package SmartHomeDDD.domain.Sensor;

import SmartHomeDDD.valueObject.ModelPath;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ImpSensorFactory implements SensorFactory {

    /**
     * Creates a sensor object based on the given parameters
     *
     * @param parameters are the parameters required to create a sensor object
     * @return Sensor
     */
    @Override
    public Sensor create(Object... parameters) {
        try {
            if (parameters.length < 4) {
                throw new IllegalArgumentException("At least 4 parameters are required.");
            }

            ModelPath modelPath = (ModelPath) parameters[1];

            Class<?> sensorClass = Class.forName(modelPath.toString());
            Constructor<?> constructor = findMatchingConstructor(sensorClass, parameters);

            if (constructor != null) {
                return (Sensor) constructor.newInstance(parameters);
            } else {
                throw new InstantiationException("No matching constructor found for class: " + modelPath);
            }

        } catch (ClassNotFoundException | InstantiationException | ClassCastException e) {
//            e.printStackTrace(); // Handle or log the exception appropriately
        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace(); // Handle or log the exception appropriately
        }

        return null;
    }

    /**
     * Find a constructor that matches the given parameters
     *
     * @param sensorClass is the specific class of the sensor to be instantiated
     * @param parameters  are the parameters required to create a sensor object
     * @return Constructor
     */
    private Constructor<?> findMatchingConstructor(Class<?> sensorClass, Object... parameters) {
        Constructor<?>[] constructors = sensorClass.getConstructors();

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
