package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DimensionsTest {
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

}