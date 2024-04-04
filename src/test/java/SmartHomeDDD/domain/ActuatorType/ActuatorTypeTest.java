package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActuatorTypeTest {

        /**
        * Test of constructor of class ActuatorType, when arguments are valid.
        */
        @Test
        void shouldCreateActuatorType_whenAttributesAreValid() {
            // Arrange
            String typeDescription = "typeDescription";

            TypeDescription actuatorName = new TypeDescription(typeDescription);

            // Act
            ActuatorType actuatorType = new ActuatorType(actuatorName);

            // Assert
            assertNotNull(actuatorType);
        }

        /**
         * Test of constructor of class ActuatorType, when typeDescription is null.
         * @throws Exception
         */
        @Test
        void shouldThrowIllegalArgumentException_whenTypeDescriptionIsNull() {
            // Arrange
            TypeDescription actuatorName = null;

            // Act
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(actuatorName));

            // Assert
            assertEquals("Actuator type name must not be null.", exception.getMessage());
        }

        /**
         * Test of method getID of class ActuatorType.
         */
        @Test
        void shouldReturnActuatorTypeID_whenGetIDisCalled() {
            // Arrange
            String typeDescription = "typeDescription";

            TypeDescription actuatorName = new TypeDescription(typeDescription);

            ActuatorType actuatorType = new ActuatorType(actuatorName);

            // Act
            ActuatorTypeID result = actuatorType.getID();

            // Assert
            assertNotNull(result);
        }

        /**
         * Test method equals of class ActuatorType when the instance is compared to itself.
         */
        @Test
        void shouldReturnTrue_whenInstanceIsComparedToItself() {
            // Arrange
            String typeDescription = "typeDescription";

            TypeDescription actuatorName = new TypeDescription(typeDescription);

            ActuatorType actuatorType = new ActuatorType(actuatorName);

            // Act
            boolean result = actuatorType.equals(actuatorType);

            // Assert
            assertTrue(result);
        }

        /**
         * Tests that two instances of ActuatorType with the same id are equal.
         * This test ensures that the equals method correctly evaluates the identity of ActuatorType instances.
         */
        @Test
        void shouldReturnTrue_WhenTwoActuatorTypeInstancesHaveSameID() throws NoSuchFieldException, IllegalAccessException {
            // Arrange
            String typeDescription = "typeDescription";

            TypeDescription actuatorName = new TypeDescription(typeDescription);


                ActuatorType actuatorType1 = new ActuatorType(actuatorName);
                ActuatorType actuatorType2 = new ActuatorType(actuatorName);

            // Use reflection to set _actuatorTypeID to the same value for both instances
            Field actuatorTypeIDField = ActuatorType.class.getDeclaredField("_actuatorTypeID");
            actuatorTypeIDField.setAccessible(true);
            actuatorTypeIDField.set(actuatorType1, actuatorType2.getID());
            actuatorTypeIDField.set(actuatorType2, actuatorType1.getID());

                // Act
                boolean result = actuatorType1.equals(actuatorType2);

                // Assert
                assertTrue(result);
        }

        /**
         * Test of method equals of class ActuatorType, when the instances are not equal.
         */
        @Test
        void shouldReturnFalse_whenInstancesAreNotEqual() {
            // Arrange
            String typeDescription = "typeDescription";

            TypeDescription actuatorName = new TypeDescription(typeDescription);

            ActuatorType actuatorType1 = new ActuatorType(actuatorName);
            ActuatorType actuatorType2 = new ActuatorType(actuatorName);

            // Act
            boolean result = actuatorType1.equals(actuatorType2);

            // Assert
            assertFalse(result);
        }

        /**
         * Test of method equals of class ActuatorType, when the instance is compared to a null object.
         */
        @Test
        void shouldReturnFalse_whenComparedWithNull() {
            // Arrange
            String typeDescription = "typeDescription";

            TypeDescription actuatorName = new TypeDescription(typeDescription);

            ActuatorType actuatorType = new ActuatorType(actuatorName);

            // Act
            boolean isEqual = actuatorType.equals(null);

            // Assert
            assertFalse(isEqual, "ActuatorType should not be equal to null");
        }

        /**
         * Test of method equals of class ActuatorType, when the instance is compared to an object of a different class.
         */
        @Test
        void shouldReturnFalse_whenComparedWithDifferentClass() {
            // Arrange
            String typeDescription = "typeDescription";

            TypeDescription actuatorName = new TypeDescription(typeDescription);

            ActuatorType actuatorType = new ActuatorType(actuatorName);

            // Act
            boolean isEqual = actuatorType.equals(new Object());

            // Assert
            assertFalse(isEqual, "ActuatorType should not be equal to an object of a different class");
        }

        /**
         * Test of method toString of class ActuatorType.
         */
        @Test
        void shouldReturnString_whenToStringIsCalled() {
            // Arrange
            String typeDescription = "typeDescription";

            TypeDescription actuatorName = new TypeDescription(typeDescription);

            ActuatorType actuatorType = new ActuatorType(actuatorName);

            // Act
            String result = actuatorType.toString();

            // Assert
            assertNotNull(result);
        }

        /**
         * Test of method getActuatorTypeName of class ActuatorType.
         */
        @Test
        void shouldReturnActuatorTypeName_whenGetActuatorTypeNameIsCalled() {
            // Arrange
            String typeDescription = "typeDescription";

            TypeDescription actuatorName = new TypeDescription(typeDescription);

            ActuatorType actuatorType = new ActuatorType(actuatorName);

            // Act
            TypeDescription result = actuatorType.getActuatorTypeName();

            // Assert
            assertEquals(actuatorName, result);
        }
}