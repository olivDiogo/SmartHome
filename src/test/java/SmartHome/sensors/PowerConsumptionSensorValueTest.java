package SmartHome.sensors;

import SmartHome.domain.Actuator;
import SmartHome.domain.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class PowerConsumptionSensorValueTest {
 @Test
    void seeIfConstructorWorks(){
     //Arrange
     double dValue = 12.3;
     //Act
     new PowerConsumptionSensorValue(dValue);
 }

 @Test
    void seeIfCloneWorks(){
     //Arrange
     double dValue = 12.3;
     PowerConsumptionSensorValue powerConsumptionSensorValue = new PowerConsumptionSensorValue(dValue);

     //Act
     Value clonedResult = powerConsumptionSensorValue.clone();

     //Assert
     Assertions.assertEquals(powerConsumptionSensorValue.toString(), clonedResult.toString());
 }

 @Test
    void seeIfToStringWorks1(){
     //Arrange
     double dValue = 12.3;
     PowerConsumptionSensorValue powerConsumptionSensorValue = new PowerConsumptionSensorValue(dValue);

     //Act
     String actualString = powerConsumptionSensorValue.toString();

     //Assert
     Assertions.assertEquals("12.3", actualString);
 }
}
