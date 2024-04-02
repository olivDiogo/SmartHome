package SmartHomeDDD.valueObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PostalCodeFactory {

    public PostalCodeFactory() {
    }

    public PostalCode createPostalCode(String postalCode, String countryCode) {
        try{
            String className = "SmartHomeDDD.valueObject.PostalCode" + countryCode + "Impl";
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(String.class);
            return (PostalCode) constructor.newInstance(postalCode);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Postal code implementation not found for country code: " + countryCode);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException("Error instantiating postal code implementation for country code: " + countryCode, e);
        }
    }
}
