package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DimensionsTest {

    /**
     * Test of constructor, of class Dimensions, with valid input.
     */
    @Test
    void shouldCreateDimensionsWhenConstructorIsCalledWithValidInput() {
        // Arrange
        double width = 2;
        double length = 4;
        double height = 6;

        // Act
        Dimensions roomDimensions = new Dimensions(width, length, height);
        String expectedResult = "Dimensions{_width=" + width + ", _length=" + length + ", _height=" + height + "}";
        // Assert
        assertEquals(expectedResult, roomDimensions.toString());
    }

    /**
    * Test of constructor, of class Dimensions, with invalid input.
    */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithNegativeWidth() {
        // Arrange
        double width = -5;
        double length = 5;
        double height = 5;

        // Act and Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Dimensions(width, length, height));
        //Expected message
        assertEquals("Width must be a positive value", thrown.getMessage());
    }

    /**
     * Test of constructor, of class Dimensions, with zero width.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithZeroWidth() {
        // Arrange
        double width = 0;
        double length = 5;
        double height = 5;

        // Act and Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Dimensions(width, length, height));
        //Expected message
        assertEquals("Width must be a positive value", thrown.getMessage());
    }

    /**
     * Test of constructor, of class Dimensions, with invalid length.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithNegativeLength() {
        // Arrange
        double width = 5;
        double length = -5;
        double height = 5;
        // Act and Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Dimensions(width, length, height));
        //Expected message
        assertEquals("Length must be a positive value", thrown.getMessage());
    }

    /**
     * Test of constructor, of class Dimensions, with zero length.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithZeroLength() {
        // Arrange
        double width = 5;
        double length = 0;
        double height = 5;
        // Act and Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Dimensions(width, length, height));
        //Expected message
        assertEquals("Length must be a positive value", thrown.getMessage());
    }

    /**
     * Test of constructor, of class Dimensions, with invalid height.
     */
    @Test
    void shouldThrowIllegalArgumentExceptionWhenConstructorIsCalledWithNegativeHeight() {
        // Arrange
        double width = 5;
        double length = 5;
        double height = -5;

        // Act and Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Dimensions(width, length, height));
        //Expected message
        assertEquals("Height must be a non-negative value", thrown.getMessage());
    }

    /**
     * Test of constructor, of class Dimensions, with zero height.
     */
    @Test
    void shouldCreateDimensionsWhenConstructorIsCalledWithZeroHeight() {
        // Arrange
        double width = 5;
        double length = 5;
        double height = 0;

        // Act
        Dimensions roomDimensions = new Dimensions(width, length, height);
        // Assert
        assertNotNull(roomDimensions);
    }

    /**
     * Test of getWidth method, of class Dimensions.
     */
    @Test
    void shouldReturnWidthWhenGetWidthIsCalled() {
        // Arrange
        double width = 5;
        double length = 5;
        double height = 5;
        Dimensions roomDimensions = new Dimensions(width, length, height);
        // Act
        double result = roomDimensions.getWidth();
        // Assert
        assertEquals(width, result);
    }

    /**
     * Test of getLength method, of class Dimensions.
     */
    @Test
    void shouldReturnLengthWhenGetLengthIsCalled() {
        // Arrange
        double width = 5;
        double length = 5;
        double height = 5;
        Dimensions roomDimensions = new Dimensions(width, length, height);
        // Act
        double result = roomDimensions.getLength();
        // Assert
        assertEquals(length, result);
    }
    /**
     * Test of getHeight method, of class Dimensions.
     */
    @Test
    void shouldReturnHeightWhenGetHeightIsCalled() {
        // Arrange
        double width = 5;
        double length = 5;
        double height = 5;
        Dimensions roomDimensions = new Dimensions(width, length, height);
        // Act
        double result = roomDimensions.getHeight();
        // Assert
        assertEquals(height, result);
    }

}