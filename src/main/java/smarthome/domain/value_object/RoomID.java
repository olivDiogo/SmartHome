package smarthome.domain.value_object;

import smarthome.ddd.IDomainID;

public class RoomID implements IDomainID {

  private final String id;

  /**
   * Constructor of the class RoomID.
   *
   * @param roomID is the ID of the room.
   */
  public RoomID(String roomID) {
    validateRoomID(roomID);
    this.id = roomID;
  }

  /**
   * Validates the ID of the room.
   *
   * @param roomID is the ID of the room.
   */
  private void validateRoomID(String roomID) {
    if (roomID == null || roomID.isBlank()) {
      throw new IllegalArgumentException("'roomID' must be a non-empty string.");
    }
  }

  /**
   * Equals method for RoomID.
   *
   * @param object Object.
   * @return boolean.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object instanceof RoomID roomID) {

      return this.id.equals(roomID.id);
    }
    return false;
  }

  /**
   * Getter for ID.
   *
   * @return _id.
   */
  @Override
  public String getID() {
    return id;
  }

  /**
   * HashCode method for RoomID.
   *
   * @return the hashcode as an int.
   */
  public int hashCode() {
    return id.hashCode();
  }

  /**
   * toString method for RoomID.
   *
   * @return the ID of the room.
   */
  public String toString() {
    return id;
  }
}
