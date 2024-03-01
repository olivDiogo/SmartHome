package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumiditySensorValueTest {

    @Test
    void seeIfConstructorWorks(){
        //Arrange
        double nValue = 10;

        //Act
        new HumiditySensorValue(nValue);
    }

    @Test
    void seeIfCloneWorks() {
        //Arrange
        double dValue = 15.5;
        HumiditySensorValue humiditySensorValue = new HumiditySensorValue(dValue);

        //Act
        Cloneable clonedValue = humiditySensorValue.clone();

        //Assert
        assertEquals(humiditySensorValue.toString(), clonedValue.toString());
    }

    @Test
    void seeIfToStringWorks() {
        //Arrange
        double dValue = 15.5;
        HumiditySensorValue humiditySensorValue = new HumiditySensorValue(dValue);

        //Act
        String actualString = humiditySensorValue.toString();

        //Assert
        assertEquals("15.5", actualString);
    }
}