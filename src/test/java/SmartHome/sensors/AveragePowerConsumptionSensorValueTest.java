package SmartHome.sensors;

import SmartHome.domain.Actuator;
import SmartHome.domain.CatalogueSensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AveragePowerConsumptionSensorValueTest {

    /**
     * See if the constructor works.
     */
    @Test
    void seeIfConstructorWorks() {
        //Arrange
        double dValue = 12.3;
        //Act
        new PowerConsumptionSensorValue(dValue);
    }

    /**
     * See if the clone method works.
     */

    @Test
    void seeIfCloneWorks() {
        //Arrange
        double dValue = 12.3;
        PowerConsumptionSensorValue powerConsumptionSensorValue = new PowerConsumptionSensorValue(dValue);

        //Act
        Value clonedResult = powerConsumptionSensorValue.clone();

        //Assert
        Assertions.assertEquals(powerConsumptionSensorValue.toString(), clonedResult.toString());
    }

    /**
     * See if the toString method works.
     */

    @Test
    void seeIfToStringWorks() {
        //Arrange
        double dValue = 12.3;
        PowerConsumptionSensorValue powerConsumptionSensorValue = new PowerConsumptionSensorValue(dValue);
        String expected = "12.3";
        //Act
        String actualString = powerConsumptionSensorValue.toString();

        //Assert
        Assertions.assertEquals(expected, actualString);
    }

    /**
     * See if the constructor throws an exception when the value is negative.
     */

    @Test
    void seeIfThrowsExceptionWhenNegativeValue() throws InstantiationException {
        // Arrange
        double dValue = -1;
        String expectedMessage = "Value must be positive";


        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new PowerConsumptionSensorValue(dValue));

        // Assert
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

}
