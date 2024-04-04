package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.DomainID;
import SmartHomeDDD.ddd.ValueObject;

public class TypeDescription implements ValueObject, DomainID {
    private String _description;

    public TypeDescription(String description) {
        validate(description);
    }

    private void validate(String description) {
        if (description == null || description.isBlank() || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The value of 'description' should not null, blank, or empty.");
        } else if (description.length() > 50) {
            throw new IllegalArgumentException("The description cannot have more than 50 characters.");
        } else this._description = description;
    }

    public String getDescription() {
        return _description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof TypeDescription objectDescription) {
            return this._description.equals(objectDescription._description);
        }
        return false;
    }

    @Override
    public String toString() {
        return _description;
    }

    /**
     * Get the id of the object as a String
     *
     * @return the id of the object
     */
    @Override
    public String getId() {
        return _description;
    }

    /**
     * Get the hash code of the object
     *
     * @return the hash code of the object
     */
    @Override
    public int hashCode() {
        return _description.hashCode();
    }
}
