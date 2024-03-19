package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
        assertEquals("Invalid post code prefix", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenZipCodePrefixIsGreaterThan9999() {
        //Arrange
        int zipCodePrefix = 10000;
        int zipCodeSuffix = 100;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ZipCode(zipCodePrefix, zipCodeSuffix));
        assertEquals("Invalid post code prefix", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenZipCodeSuffixIsLessThan100() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 99;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ZipCode(zipCodePrefix, zipCodeSuffix));
        assertEquals("Invalid post code suffix", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenZipCodeSuffixIsGreaterThan999() {
        //Arrange
        int zipCodePrefix = 4000;
        int zipCodeSuffix = 1000;

        //Act & Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ZipCode(zipCodePrefix, zipCodeSuffix));
        assertEquals("Invalid post code suffix", exception.getMessage());
    }
}
