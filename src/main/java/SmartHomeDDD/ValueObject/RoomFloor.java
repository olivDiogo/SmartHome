package SmartHomeDDD.ValueObject;

import SmartHomeDDD.ddd.ValueObject;

public class RoomFloor implements ValueObject {
    int _floor;

    public RoomFloor(int floor) {
        validateFloor(floor);
    }
    private void validateFloor(int floor) {
        if (floor < -35 || floor > 162)
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

        if (o instanceof RoomFloor) {
            RoomFloor objectRoomFloor = (RoomFloor) o;

            if (this._floor == objectRoomFloor._floor)
                return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "RoomFloor{" +
                "_floor=" + _floor +
                '}';
    }

}
