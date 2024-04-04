package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.DomainID;
import SmartHomeDDD.ddd.ValueObject;

public class SensorTypeDescription implements ValueObject, DomainID {
    private String _description;

    public SensorTypeDescription(String description) {
        validate(description);
    }

    private void validate(String description) {
        if (description == null || description.isBlank() || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The value of 'description' should not null, blank, or empty.");
        } else if (description.length() > 50) {
            throw new IllegalArgumentException("The description cannot have more than 50 characters.");
        } else this._description = description;
    }

    @Override
    public String getId() {
        return _description;
    }

    @Override
    public int hashCode() {
        return _description.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SensorTypeDescription objectDescription) {
            return (this._description.equals(objectDescription._description));
        }
        return false;
    }
}
