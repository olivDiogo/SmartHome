package smart_home.utils;

public class Validator {

    private Validator() {
    }

    public static <T> void validateNotNull(T object, String objectName) {
        if (object == null) {
            throw new IllegalArgumentException(objectName +" is required");
        }
    }
}
