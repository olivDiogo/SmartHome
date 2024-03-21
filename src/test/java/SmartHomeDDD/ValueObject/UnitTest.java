package SmartHomeDDD.ValueObject;

import org.junit.jupiter.api.Test;

import javax.swing.text.TableView;

import static org.junit.Assert.assertThrows;

public class UnitTest {

    /**
     * Test the constructor of the class Unit.
     */
    @Test
    public void testConstructor() {
        // Arrange
        String unit = "ºC";

        // Act
        Unit unitObject = new Unit(unit);
    }

    /**
     * Test the constructor of the class Unit with an empty unit.
     */

    @Test
    void testConstructorInvalidUnit() {
        // Arrange
        String unit = " ";

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Unit(unit));
        assert(exception.getMessage().contains("Invalid unit"));
    }

    /**
     * Test the constructor of the class Unit with a null unit.
     */
    @Test
    void testConstructorNullUnit() {
        // Arrange
        String unit = null;

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Unit(unit));
        assert(exception.getMessage().contains("Invalid unit"));
    }

    /**
     * Test the constructor of the class Unit with a unit that has more than 5 characters.
     */
    @Test
    void testConstructorUnitMoreThan5Characters() {
        // Arrange
        String unit = "km/h  ";

        // Act+Assert
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Unit(unit));
        assert(exception.getMessage().contains("Invalid unit"));
    }
}
