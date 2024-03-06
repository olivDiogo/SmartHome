package SmartHome.actuators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * This class contains test cases for the SetDecimalValue class.
 */
class SetDecimalValueTest {
        /**
         * Tests the creation of an instance of SetDecimalValue.
         */
        @Test
        void shouldCreateInstanceOfSetDecimalValue() {
            // Arrange
            double value = 4.5;

            // Act
            SetDecimalValue setDecimalValue = new SetDecimalValue(value);

            // Assert
            assertNotNull(setDecimalValue);
        }

        /**
         * Tests cloning of a SetDecimalValue object.
         */
        @Test
        void shouldCloneSetDecimalValue() {
            // Arrange
            double value = 10.5;
            SetDecimalValue setDecimalValue = new SetDecimalValue(value);

            // Act
            SetDecimalValue clonedSetDecimalValue = setDecimalValue.clone();

            // Assert
            assertNotEquals(setDecimalValue, clonedSetDecimalValue);
        }

        /**
         * Tests the conversion of SetDecimalValue to string.
         */
        @Test
        void shouldConvertToString(){
            // Arrange
            double value = 10.5;
            SetDecimalValue setDecimalValue = new SetDecimalValue(value);

            // Act
            String result = setDecimalValue.toString();

            // Assert
            assertEquals("10.5", result);
        }
    }