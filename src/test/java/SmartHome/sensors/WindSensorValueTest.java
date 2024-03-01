package SmartHome.sensors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WindSensorValueTest {

    @Test
    void shouldInstantiateConstructors_givenValid(){
        //Arrange
        double speed = 10.5;
        double direction = 0.0;
        //Act
        new WindSensorValue(speed, direction);
    }

    @Test
    void getSpeed_returnsCorrectSpeed() {
        // Arrange
        double expectedSpeed = 10.5;
        WindSensorValue windSensorValue = new WindSensorValue(expectedSpeed, 0.0);

        // Act
        double actualSpeed = windSensorValue.getSpeed();

        // Assert
        assertEquals(expectedSpeed, actualSpeed, "The speed returned should match the speed set in the constructor.");
    }

    @Test
    void getDirection_returnsCorrectDirection() {
        // Arrange
        double expectedDirection = Math.PI / 4; // Example direction in radians
        WindSensorValue windSensorValue = new WindSensorValue(0.0, expectedDirection);

        // Act
        double actualDirection = windSensorValue.getDirection();

        // Assert
        assertEquals(expectedDirection, actualDirection, "The direction returned should match the direction set in the constructor.");
    }
}
