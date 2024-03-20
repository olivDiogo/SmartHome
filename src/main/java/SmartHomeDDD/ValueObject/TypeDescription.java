package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class TypeDescription implements ValueObject {
    private String _description;

    public TypeDescription(String description) {
        validate(description);
    }
    private void validate(String description) {
        if (description == null || description.isBlank() || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The value of 'description' should not null, blank, or empty.");
        }
        else if (description.length() > 50) {
            throw new IllegalArgumentException("The description cannot have more than 50 characters.");
        }
        else this._description = description;
    }
    public String getDescription() {
        return _description;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof TypeDescription) {
            TypeDescription objectDescription = (TypeDescription) o;
            if (this._description.equals(objectDescription._description))
                return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return _description;
    }
}
