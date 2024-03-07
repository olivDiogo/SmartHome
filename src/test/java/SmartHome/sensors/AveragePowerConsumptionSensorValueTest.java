package SmartHome.sensors;

import SmartHome.domain.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AveragePowerConsumptionSensorValueTest {

    /**
     * See if the constructor works.
     */
    @Test
    void seeIfConstructorWorks() {
        //Arrange
        double dValue = 12.3;
        //Act
        new AveragePowerConsumptionSensorValue(dValue);
    }

    /**
     * See if the getValue method works.
     */
    @Test
    void seeIfGetValueWorks() {
        //Arrange
        double dValue = 12.3;
        AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(dValue);
        double expected = 12.3;
        //Act
        double actualResult = averagePowerConsumptionSensorValue.getValue();
        //Assert
        Assertions.assertEquals(expected, actualResult);
    }

    /**
     * See if the clone method works.
     */

    @Test
    void seeIfCloneWorks() {
        //Arrange
        double dValue = 12.3;
        AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(dValue);

        //Act
        Value clonedResult = averagePowerConsumptionSensorValue.clone();

        //Assert
        Assertions.assertEquals(averagePowerConsumptionSensorValue.toString(), clonedResult.toString());
    }

    /**
     * See if the toString method works.
     */

    @Test
    void seeIfToStringWorks() {
        //Arrange
        double dValue = 12.3;
        AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(dValue);
        String expected = "12.3";
        //Act
        String actualString = averagePowerConsumptionSensorValue.toString();

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


        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new AveragePowerConsumptionSensorValue(dValue));

        // Assert
        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * See if the setValue method works with zero value.
     */
    @Test
    void seeIfGetValueWorksWithZero() {
        //Arrange
        double dValue = 0;
        AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(dValue);
        double expected = 0;
        //Act
        double actualResult = averagePowerConsumptionSensorValue.getValue();
        //Assert
        Assertions.assertEquals(expected, actualResult);
    }

}
