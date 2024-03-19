package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class RoomName implements ValueObject {
    private String _name;

    /**
     * Class constructor.
     *
     * @param name The room name to set.
     */
    public RoomName(String name) {
        setRoomName(name);
    }

    /**
     * Sets the room name after validating it.
     *
     * @param name The room name to set.
     */
    private void setRoomName(String name) {
        if (name == null || name.isEmpty() || name.isBlank()){
            throw new IllegalArgumentException("The room name cannot be null, blank, or empty.");
        }

        if (!name.matches("[a-zA-Z0-9 ]+")) {
            throw new IllegalArgumentException("The room name can only contain letters and numbers.");
        }

        _name = name.trim();
    }

    /**
     * Gets the room name.
     *
     * @return The room name.
     */
    public String getRoomName() {
        return _name;
    }
    /*
    /The method getRoomName should be replaced by a generic method in the ValueObject interface
    */
}
