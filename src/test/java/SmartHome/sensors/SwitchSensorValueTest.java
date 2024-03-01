package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwitchSensorValueTest {

    @Test
    void seeIfConstructorWorks(){
        //Arrange
        boolean bValue = true;

        //Act
        new SwitchSensorValue(bValue);
    }

    @Test
    void toStringReturnsON() {
        // Arrange
        boolean bValue = true;
        SwitchSensorValue switchSensorValue = new SwitchSensorValue(bValue);

        // Act
        String actualString = switchSensorValue.toString();

        // Assert
        assertEquals("On", actualString);
    }

    @Test
    void toStringReturnOff() {
        // Arrange
        boolean bValue = false;
        SwitchSensorValue switchSensorValue = new SwitchSensorValue(bValue);

        // Act
        String actualString = switchSensorValue.toString();

        // Assert
        assertEquals("Off", actualString);
    }

    @Test
    void SeeIfCloneWorks() {
        // Arrange
        boolean bValue = true;
        SwitchSensorValue switchSensorValue = new SwitchSensorValue(bValue);

        // Act
        SwitchSensorValue clonedValue = switchSensorValue.clone();

        // Assert
        assertEquals(switchSensorValue.toString(), clonedValue.toString());
    }
}