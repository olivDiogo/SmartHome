package SmartHome.domain;

import SmartHome.sensors.SunsetTimeSensor;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

public class RoomFactoryTest {

    /**
     * Test to check if the createRoom() method returns a mocked Room
     */
    @Test
    void shouldReturnMockedRoom(){
        // Arrange
        String name = "Living Room";
        int floor = 1;
        double length = 10;
        double width = 10;
        double height = 10;

        int excepted = 1;

        try(MockedConstruction<Room> roomDouble = mockConstruction(Room.class,(mock, context) -> {
            when(mock.getName()).thenReturn(name);
            when(mock.getFloor()).thenReturn(floor);
        })){

            RoomFactory roomFactory = new RoomFactory();

            // Act
            Room room = roomFactory.createRoom(name, floor, length, width, height);

            // Assert
            List<Room> rooms = roomDouble.constructed();
            assertEquals(excepted, rooms.size());

            assertEquals(name, room.getName());
            assertEquals(floor, room.getFloor());

        }
    }
}
