package smart_home.value_object;

import smart_home.ddd.IValueObject;

public class UnitDescription implements IValueObject {

  private final String description;

  public UnitDescription(String description) {
    validate(description);
    this.description = description;
  }

  /**
   * Validates the description
   *
   * @param description The description to validate.
   */
  private void validate(String description) {
    if (description == null || description.isBlank()) {
      throw new IllegalArgumentException(
          "The value of 'description' should not null, blank, or empty.");
    } else if (description.length() > 50) {
      throw new IllegalArgumentException("The description cannot have more than 50 characters.");
    }
  }

  /**
   * Gets the description
   *
   * @return description
   */
  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o instanceof UnitDescription objectDescription) {
      return this.description.equals(objectDescription.description);
    }
    return false;
  }

  @Override
  public String toString() {
    return description;
  }

  @Override
  public int hashCode() {
    return description.hashCode();
  }
}
