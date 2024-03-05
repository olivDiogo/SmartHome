package SmartHome.sensors;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DewPointValueTest {

    /**
     * Test if the constructor is properly created.
      */
    @Test
    public void testConstructor (){
        //Arrange
        int value = 1;
        //Act
        DewPointValue dewPointValue = new DewPointValue(value);
        //Assert
        assertNotNull(dewPointValue);
    }

    /**
     * Test if the toString method returns the correct value.
     */
    @Test
    public void testToString (){
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
     * Test if the clone method returns the correct value.
     */
    @Test
    public void testClone (){
        //Arrange
        int value = 1;
        DewPointValue dewPointValue = new DewPointValue(value);

        //Act
        DewPointValue result = dewPointValue.clone();
        //Assert
        assertEquals(dewPointValue.toString(), result.toString());
    }

}
