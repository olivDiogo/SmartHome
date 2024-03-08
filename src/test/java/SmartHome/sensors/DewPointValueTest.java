package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DewPointValueTest {

    /**
     * Test if the constructor is properly created.
     */
    @Test
    public void shouldInstantiateDewPointValue() {
        //Arrange
        int value = 1;
        //Act
        new DewPointValue(value);

    }

    /**
     * Test if the constructor is properly created when the value is negative.
     */
    @Test
    public void shouldInstantiateDewPointValueWhenValueIsNegative() {
        //Arrange
        int nValue = -100;
        //Act
        new DewPointValue(nValue);

    }

    /**
     * Test if the toString method returns the correct value.
     */
    @Test
    public void shouldReturnDewPointValueInString() {
        //Arrange
        int value = 1;
        DewPointValue dewPointValue = new DewPointValue(value);

        String expected = "1";
        //Act
        String result = dewPointValue.toString();
        //Assert
        assertEquals(expected, result);
    }


}
