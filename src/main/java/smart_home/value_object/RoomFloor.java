package smart_home.value_object;

import smart_home.ddd.IValueObject;

public class RoomFloor implements IValueObject {
    int _floor;

    public RoomFloor(int floor) {
        validateFloor(floor);
    }

    private void validateFloor(int floor) {
        if (Math.max(floor, -35) != floor)
            throw new IllegalArgumentException("Invalid floor number.");
        if (Math.min(floor, 162) != floor)
            throw new IllegalArgumentException("Invalid floor number.");
        this._floor = floor;
    }

    public int getFloor() {
        return _floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o instanceof RoomFloor objectRoomFloor) {

            return this._floor == objectRoomFloor._floor;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(_floor);
    }


    @Override
    public String toString() {
        return "RoomFloor{" +
                "_floor=" + _floor +
                '}';
    }

}
