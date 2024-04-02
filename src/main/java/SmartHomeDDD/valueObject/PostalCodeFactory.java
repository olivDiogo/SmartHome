package SmartHomeDDD.valueObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PostalCodeFactory {

    public PostalCodeFactory() {
    }

    /**
     * Creates a PostalCode object based on the given postal code and country code.
     *
     * @param postalCode The postal code value.
     * @param countryCode The country code to determine the implementation class.
     * @return A PostalCode object instantiated based on the provided postal code and country code.
     * @throws IllegalArgumentException If the postal code implementation for the given country code is not found.
     * @throws RuntimeException If an error occurs during the instantiation of the postal code implementation.
     */

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
