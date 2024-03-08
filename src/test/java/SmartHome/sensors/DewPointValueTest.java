package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class DewPointValueTest {

    /**
     * Should create instance of DewPointValue class.
     */
    @Test
    public void shouldReturnInstantiateDewPointValue() {
        //Arrange
        int value = 1;
        //Act
        new DewPointValue(value);

    }

    /**
     * Should create instance of DewPointValue class when value is negative.
     */
    @Test
    public void shouldInstantiateDewPointValueWhenValueIsNegative() {
        //Arrange
        int nValue = -100;
        //Act
        new DewPointValue(nValue);

    }

    /**
     * Should return the value of the dew point in string.
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

    /**
     * Should throw exception when dew point value is lower than -100.
     */
    @Test
    void shouldThrowException_whenDewPointValueIsLowerThanNegative100 (){
        //Arrange
        int value = -101;

        String expectedMessage = "The value of the dew point cannot be lower than -100.";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new DewPointValue(value)
        );


        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


    }


}
