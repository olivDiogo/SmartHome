package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

public class LogID implements IDomainID {

  private final String id;

  /**
   * Constructor for LogID
   *
   * @param id String
   */
  public LogID(String id) {
    validateLogID(id);
    this.id = id.trim();
  }

  /**
   * Method to validate the LogID
   *
   * @param logID String
   */
  private void validateLogID(String logID) {
    if (logID == null || logID.isBlank()) {
      throw new IllegalArgumentException("The value of 'logID' should not null, blank, or empty.");
    }
  }

  /**
   * Getter for ID
   *
   * @return the logID
   */
  @Override
  public String getID() {
    return id;
  }

  /**
   * Equals method for LogID
   *
   * @param o Object
   * @return boolean
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o instanceof LogID objectLogID) {

      return this.id.equals(objectLogID.id);
    }
    return false;
  }

  /**
   * HashCode method for LogID
   *
   * @return the hashcode as an int
   */
  @Override
  public int hashCode() {
    return id.hashCode();
  }

  /**
   * toString method for LogID
   *
   * @return the logID as a string
   */
  @Override
  public String toString() {
    return id;
  }

}
