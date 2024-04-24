package smart_home.value_object;

import smart_home.ddd.IValueObject;

public class RoomFloor implements IValueObject {
    int floor;

    public RoomFloor(int floor) {
        validateFloor(floor);
    }

    /**
     * Validates the floor number.
     *
     * @param floor is the floor number.
     */
    private void validateFloor(int floor) {
        if (Math.max(floor, -35) != floor)
            throw new IllegalArgumentException("Invalid floor number.");
        if (Math.min(floor, 162) != floor)
            throw new IllegalArgumentException("Invalid floor number.");
        this.floor = floor;
    }

    /**
     * Getter for the floor number.
     *
     * @return floor.
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Equals method for RoomFloor.
     *
     * @param o Object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof RoomFloor objectRoomFloor) {
            return this.floor == objectRoomFloor.floor;
        }
        return false;
    }

    /**
     * HashCode method for RoomFloor.
     *
     * @return the hashcode as an int.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(floor);
    }


    /**
     * toString method for RoomFloor.
     *
     * @return the floor number as a string.
     */
    @Override
    public String toString() {
        return "RoomFloor:" +
                " floor=" + floor;
    }

}
