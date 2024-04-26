package smarthome.utils;

public class Validator {

    public static <T> void validateNotNull(T object, String objectName) {
        if (object == null) {
            throw new IllegalArgumentException(objectName +" is required");
        }
    }
}
