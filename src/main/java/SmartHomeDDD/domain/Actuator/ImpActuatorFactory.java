package SmartHomeDDD.domain.Actuator;

import SmartHomeDDD.valueObject.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ImpActuatorFactory implements ActuatorFactory {


    /**
     * Create an actuator instance based on the provided parameters
     *
     * @param deviceID             Device ID
     * @param modelPath            Model path
     * @param actuatorTypeID       Actuator type ID
     * @param actuatorName         Actuator name
     * @param additionalParameters Additional parameters required by the actuator
     * @return Actuator instance
     */

    @Override
    public Actuator createActuator(DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName, Object... additionalParameters) {
        try {
            Class<?> actuatorClass = Class.forName(modelPath.toString());
            Constructor<?> constructor = findMatchingConstructor(actuatorClass, deviceID, modelPath, actuatorTypeID, actuatorName, additionalParameters);

            if (constructor != null) {
                // Create an array to hold all parameters including additionalParameters
                Object[] allParameters = new Object[additionalParameters.length + 4];
                allParameters[0] = deviceID;
                allParameters[1] = modelPath;
                allParameters[2] = actuatorTypeID;
                allParameters[3] = actuatorName;

                // Copy individual parameters into allParameters array
                System.arraycopy(additionalParameters, 0, allParameters, 4, additionalParameters.length);

                // Invoke the constructor with allParameters
                return (Actuator) constructor.newInstance(allParameters);
            } else {
                throw new InstantiationException("No matching constructor found for class: " + modelPath);
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + modelPath);
        } catch (InstantiationException e) {
            System.err.println("Error instantiating actuator: " + e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.err.println("Error invoking constructor: " + e.getMessage());
        }

        return null;
    }

    /**
     * Find a constructor that matches the provided parameters
     * @param actuatorClass
     * @param deviceID
     * @param modelPath
     * @param actuatorTypeID
     * @param actuatorName
     * @param additionalParameters
     * @return
     */

    private Constructor<?> findMatchingConstructor(Class<?> actuatorClass, DeviceID deviceID, ModelPath modelPath, ActuatorTypeID actuatorTypeID, ActuatorName actuatorName, Object... additionalParameters) {
        Constructor<?>[] constructors = actuatorClass.getConstructors();

        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();

            if (parameterTypes.length == additionalParameters.length + 4 && parameterTypes[0].isAssignableFrom(DeviceID.class) && parameterTypes[1].isAssignableFrom(ModelPath.class) && parameterTypes[2].isAssignableFrom(ActuatorTypeID.class) && parameterTypes[3].isAssignableFrom(ActuatorName.class)) {
                boolean typesMatch = true;
                for (int i = 4, k = 0; i < parameterTypes.length; i++, k++) {
                    // Check if parameter type is an array and if so, compare the component type
                    if (parameterTypes[i].isArray()) {
                        Class<?> componentType = parameterTypes[i].getComponentType();
                        if (!componentType.isAssignableFrom(additionalParameters[k].getClass())) {
                            typesMatch = false;
                            break;
                        }
                    } else {
                        // If parameter type is not an array, compare directly
                        if (!parameterTypes[i].isAssignableFrom(additionalParameters[k].getClass())) {
                            typesMatch = false;
                            break;
                        }
                    }
                }
                if (typesMatch) {
                    return constructor;
                }
            }
        }
        // No matching constructor found
        return null;
    }
}