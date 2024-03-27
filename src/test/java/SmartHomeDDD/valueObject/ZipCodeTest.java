package SmartHomeDDD.valueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ZipCodeTest {

    /**
     * Tests the correct instantiation of ZipCode constructor.
     */
    @Test
    void shouldReturnExpectedZipCodeWhenGivenValidParameters() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 100;

        //Act
        ZipCode zipCode = new ZipCode(zipCodePrefix, zipCodeSuffix);

    }

    /**
     * Test ZipCode constructor with zip code prefix less than 1000.
     */
    @Test
    void shouldThrowExceptionWhenZipCodePrefixIsLessThan1000() {
        //Arrange
        int zipCodePrefix = 999;
        int zipCodeSuffix = 100;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ZipCode(zipCodePrefix, zipCodeSuffix));
        assertEquals("Invalid zip code prefix", exception.getMessage());
    }

    /**
     * Test ZipCode constructor with zip code prefix greater than 9999.
     */
    @Test
    void shouldThrowExceptionWhenZipCodePrefixIsGreaterThan9999() {
        //Arrange
        int zipCodePrefix = 10000;
        int zipCodeSuffix = 100;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ZipCode(zipCodePrefix, zipCodeSuffix));
        assertEquals("Invalid zip code prefix", exception.getMessage());
    }

    /**
     * Test ZipCode constructor with zip code suffix less than 100.
     */
    @Test
    void shouldThrowExceptionWhenZipCodeSuffixIsLessThan100() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 99;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ZipCode(zipCodePrefix, zipCodeSuffix));
        assertEquals("Invalid zip code suffix", exception.getMessage());
    }

    /**
     * Test ZipCode constructor with zip code suffix greater than 999.
     */
    @Test
    void shouldThrowExceptionWhenZipCodeSuffixIsGreaterThan999() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 1000;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ZipCode(zipCodePrefix, zipCodeSuffix));
        assertEquals("Invalid zip code suffix", exception.getMessage());
    }

    /**
     * Test if ZipCode is not equal to null.
     */
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

    /**
     * Test if ZipCode is equal to itself.
     */
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

    /**
     * Test if ZipCode is equal to another ZipCode with same zip code prefix.
     */
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

    /**
     * Test if ZipCode is not equal to another ZipCode with different zip code prefix.
     */
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

    /**
     * Test if ZipCode is equal to another ZipCode with same zip code suffix.
     */
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

    /**
     * Test if ZipCode is not equal to another ZipCode with different zip code suffix.
     */
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

    /**
     * Test if the zip code prefix is returned correctly.
     */
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

    /**
     * Test if the zip code suffix is returned correctly.
     */
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

    /**
     * Test if the ZipCode string is returned correctly.
     */
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
