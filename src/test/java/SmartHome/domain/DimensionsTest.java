package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DimensionsTest {

    /**
     * Test of constructor, of class Dimensions, with valid input.
     */
    @Test
    void testConstructor_validInput() {
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
    void testConstructor_negativeWidth() {
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
    void testConstructor_zeroWidth() {
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
    void testConstructor_invalidLength() {
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
    void testConstructor_invalidZeroLength() {
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
    void testConstructor_invalidHeight() {
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
    void testConstructor_validZeroHeight() {
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
    void testGetWidth() {
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
    void testGetLength() {
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
    void testGetHeight() {
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