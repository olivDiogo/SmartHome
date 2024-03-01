package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureSensorValueTest {

    @Test
    void seeIfConstructorWorks(){
        //Arrange
        int nValue = 10;

        //Act
        new TemperatureSensorValue(nValue);
    }

    @Test
    void seeIfCloneWorks() {
        // Arrange
        int nValue = 10;
        TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(nValue);

        // Act
        TemperatureSensorValue clonedValue = temperatureSensorValue.clone();

        // Assert
        assertEquals(temperatureSensorValue.toString(), clonedValue.toString());
    }

    @Test
    void seeIfToStringWorks() {
        // Arrange
        int nValue = 10;
        TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(nValue);

        // Act
        String actualString = temperatureSensorValue.toString();

        // Assert
        assertEquals("10", actualString);
    }

}