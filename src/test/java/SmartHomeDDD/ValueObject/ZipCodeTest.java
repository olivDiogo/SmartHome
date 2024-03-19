package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ZipCodeTest {

    @Test
    void shouldReturnExpectedZipCodeWhenGivenValidParameters() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 100;

        //Act
        ZipCode zipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);

    }

    @Test
    void shouldThrowExceptionWhenZipCodePrefixIsLessThan1000() {
        //Arrange
        int zipCodePrefix = 999;
        int zipCodeSuffix = 100;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ZipCode(zipCodePrefix, zipCodeSuffix));
        assertEquals("Invalid zip code prefix", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenZipCodePrefixIsGreaterThan9999() {
        //Arrange
        int zipCodePrefix = 10000;
        int zipCodeSuffix = 100;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ZipCode(zipCodePrefix, zipCodeSuffix));
        assertEquals("Invalid zip code prefix", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenZipCodeSuffixIsLessThan100() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 99;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ZipCode(zipCodePrefix, zipCodeSuffix));
        assertEquals("Invalid zip code suffix", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenZipCodeSuffixIsGreaterThan999() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 1000;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ZipCode(zipCodePrefix, zipCodeSuffix));
        assertEquals("Invalid zip code suffix", exception.getMessage());
    }

    @Test
    void shouldReturnFalseEqualsWithNull() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 100;

        ZipCode zipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);

        //Act
        boolean isEquals = zipCode.equals(null);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    void shouldReturnTrueEqualsWithSameObject() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 100;

        ZipCode zipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);

        //Act
        boolean isEquals = zipCode.equals(zipCode);

        //Assert
        assertTrue(isEquals);
    }

    @Test
    void shouldReturnTrueEqualsWithSameZipCodePrefix() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodePrefix2 = 4000;
        int zipCodeSuffix = 100;

        ZipCode zipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);
        ZipCode zipCode2 = new ZipCode(zipCodePrefix2, zipCodeSuffix);

        //Act
        boolean isEquals = zipCode.equals(zipCode2);

        //Assert
        assertTrue(isEquals);
    }

    @Test
    void shouldReturnFalseWithDifferentZipCodePrefix() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodePrefix2 = 4001;
        int zipCodeSuffix = 100;

        ZipCode zipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);
        ZipCode zipCode2 = new ZipCode(zipCodePrefix2, zipCodeSuffix);

        //Act
        boolean isEquals = zipCode.equals(zipCode2);

        //Assert
        assertFalse(isEquals);
    }

    @Test
    void shouldReturnTrueEqualsWithSameZipCodeSuffix() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 100;
        int zipCodeSuffix2 = 100;

        ZipCode zipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);
        ZipCode zipCode2 = new ZipCode(zipCodePrefix, zipCodeSuffix2);

        //Act
        boolean isEquals = zipCode.equals(zipCode2);

        //Assert
        assertTrue(isEquals);
    }

    @Test
    void shouldReturnFalseWithDifferentZipCodeSuffix() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 100;
        int zipCodeSuffix2 = 101;

        ZipCode zipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);
        ZipCode zipCode2 = new ZipCode(zipCodePrefix, zipCodeSuffix2);

        //Act
        boolean isEquals = zipCode.equals(zipCode2);

        //Assert
        assertFalse(isEquals);
    }


    @Test
    void shouldReturnZipCodePrefix() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 100;

        ZipCode zipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);

        //Act
        int actualZipCodePrefix = zipCode.getZipCodePrefix();

        //Assert
        assertEquals(zipCodePrefix, actualZipCodePrefix);
    }

    @Test
    void shouldReturnZipCodeSuffix() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 100;

        ZipCode zipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);

        //Act
        int actualZipCodeSuffix = zipCode.getZipCodeSuffix();

        //Assert
        assertEquals(zipCodeSuffix, actualZipCodeSuffix);
    }

    @Test
    void toStringShouldReturnTheZipCodeString() {
        //Arrange
        int zipCodePrefix = 4450;
        int zipCodeSuffix = 123;

        ZipCode zipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);
        String expected = "4450-123";

        //Act
        String actualZipCodeString = zipCode.toString();


        //Assert
        assertEquals(expected, actualZipCodeString);
    }
}
