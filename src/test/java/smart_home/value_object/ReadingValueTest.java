package smart_home.value_object;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadingValueTest {

    /**
     * Test if the ReadingValue is created successfully
     */
    @Test
    void shouldCreateReadingValue(){
        //Arrange
        String readingValue = "20";
        //Act
        ReadingValue readingValue1 = new ReadingValue(readingValue);
        //Assert
        assertEquals(readingValue, readingValue1.getReadingValue());
    }

    /**
     * Test if the ReadingValue is created successfully
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenReadingValueIsNull(){
        //Arrange
        String readingValue = null;
        String expected = "Reading Value is required";
        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ReadingValue(readingValue));
        //Assert
        assertEquals(expected, exception.getMessage());
    }

    /**
     * Test if getReadingValue returns the correct value
     */
    @Test
    void shouldReturnReadingValue(){
        //Arrange
        String readingValue = "20";
        ReadingValue readingValue1 = new ReadingValue(readingValue);
        //Act
        String result = readingValue1.getReadingValue();
        //Assert
        assertEquals(readingValue, result);
    }

    /**
     * Test if equals with Reflexivity
     */
    @Test
    void shouldReturnTrueWhenComparingReadingValueWithItself(){
        //Arrange
        String readingValue = "20";
        ReadingValue readingValue1 = new ReadingValue(readingValue);
        //Act
        boolean result = readingValue1.equals(readingValue1);
        //Assert
        assertTrue(result);
    }

    /**
     * Test if equals with Symmetry
     */
    @Test
    void shouldReturnTrueWhenComparingTwoReadingValuesWithSameReadingValue(){
        //Arrange
        String readingValue = "20";
        ReadingValue readingValue1 = new ReadingValue(readingValue);
        ReadingValue readingValue2 = new ReadingValue(readingValue);
        //Act
        boolean result = readingValue1.equals(readingValue2);
        //Assert
        assertTrue(result);
    }

    /**
     * Test if equals with Transitivity
     */
    @Test
    void shouldReturnTrueWhenComparingThreeReadingValuesWithSameReadingValue(){
        //Arrange
        String readingValue = "20";
        ReadingValue readingValue1 = new ReadingValue(readingValue);
        ReadingValue readingValue2 = new ReadingValue(readingValue);
        ReadingValue readingValue3 = new ReadingValue(readingValue);
        //Act
        boolean result = readingValue1.equals(readingValue2) && readingValue2.equals(readingValue3);
        //Assert
        assertTrue(result);
    }

    /**
     * Test if equals with Consistency
     */
    @Test
    void shouldReturnTrueWhenComparingTwoReadingValuesWithSameReadingValueMultipleTimes(){
        //Arrange
        String readingValue = "20";
        ReadingValue readingValue1 = new ReadingValue(readingValue);
        ReadingValue readingValue2 = new ReadingValue(readingValue);
        //Act
        boolean result = readingValue1.equals(readingValue2);
        //Assert
        assertTrue(result);
    }

    /**
     * Test if equals with null
     */
    @Test
    void shouldReturnFalseWhenComparingReadingValueWithNull(){
        //Arrange
        String readingValue = "20";
        ReadingValue readingValue1 = new ReadingValue(readingValue);
        //Act
        boolean result = readingValue1.equals(null);
        //Assert
        assertFalse(result);
    }

    /**
     * Test the hashCode method
     */
    @Test
    void shouldReturnHashCode(){
        //Arrange
        String readingValue = "20";
        ReadingValue readingValue1 = new ReadingValue(readingValue);
        //Act
        int result = readingValue1.hashCode();
        //Assert
        assertEquals(readingValue.hashCode(), result);
    }
}
