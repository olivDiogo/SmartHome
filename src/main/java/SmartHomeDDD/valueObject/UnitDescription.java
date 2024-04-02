package SmartHomeDDD.valueObject;

import SmartHomeDDD.ddd.ValueObject;

public class UnitDescription implements ValueObject {

    private final String description;

    public UnitDescription(String description) {
        validate(description);
        this.description = description;
    }

    /**
     * Validates the description
     * @param description
     */
    private void validate (String description){
        if (description == null || description.isBlank() || description.trim().isEmpty()) {
            throw new IllegalArgumentException("The value of 'description' should not null, blank, or empty.");
        }
        else if (description.length() > 50) {
            throw new IllegalArgumentException("The description cannot have more than 50 characters.");
        }
    }

    /**
     * Gets the description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o)
            return true;
        if (o instanceof UnitDescription) {
            UnitDescription objectDescription = (UnitDescription) o;
            if (this.description.equals(objectDescription.description))
                return true;
        }
        return false;
    }
    @Override
    public String toString () {
        return description;
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
