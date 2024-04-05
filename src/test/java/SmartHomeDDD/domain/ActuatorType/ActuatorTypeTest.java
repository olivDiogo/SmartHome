package SmartHomeDDD.domain.ActuatorType;

import SmartHomeDDD.valueObject.ActuatorTypeID;
import SmartHomeDDD.valueObject.TypeDescription;
import SmartHomeDDD.valueObject.UnitID;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ActuatorTypeTest {

        /**
        * Test of constructor of class ActuatorType, when arguments are valid.
        */
        @Test
        void shouldCreateActuatorType_whenAttributesAreValid() {
            // Arrange
            String typeDescription = "typeDescription";
            String id = "unitID";

            TypeDescription actuatorName = new TypeDescription(typeDescription);
            UnitID unitID = new UnitID(id);

            // Act
            ActuatorType actuatorType = new ActuatorType(actuatorName, unitID);

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
            String id = "unitID";
            UnitID unitID = new UnitID(id);

            // Act
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(actuatorName, unitID));

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
            String id = "unitID";

            TypeDescription actuatorName = new TypeDescription(typeDescription);
            UnitID unitID = new UnitID(id);

            ActuatorType actuatorType = new ActuatorType(actuatorName, unitID);

            // Act
            ActuatorTypeID result = actuatorType.getID();

            // Assert
            assertNotNull(result);
        }

        /**
         * Test of method equals of class ActuatorType when the instances are equal.
         */
        @Test
        void shouldReturnGetUnit() {
            // Arrange
            String typeDescription = "typeDescription";
            String id = "unitID";

            TypeDescription actuatorName = new TypeDescription(typeDescription);
            UnitID unitID = new UnitID(id);

            ActuatorType actuatorType = new ActuatorType(actuatorName, unitID);

            // Act
            UnitID result = actuatorType.getUnit();

            // Assert
            assertEquals(unitID, result);
        }

        /**
         * Test method equals of class ActuatorType when the instance is compared to itself.
         */
        @Test
        void shouldReturnTrue_whenInstanceIsComparedToItself() {
            // Arrange
            String typeDescription = "typeDescription";
            String id = "unitID";

            TypeDescription actuatorName = new TypeDescription(typeDescription);
            UnitID unitID = new UnitID(id);

            ActuatorType actuatorType = new ActuatorType(actuatorName, unitID);

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
            String id = "unitID";

            TypeDescription actuatorName = new TypeDescription(typeDescription);
            UnitID unitID = new UnitID(id);


                ActuatorType actuatorType1 = new ActuatorType(actuatorName, unitID);
                ActuatorType actuatorType2 = new ActuatorType(actuatorName, unitID);

            // Use reflection to set _actuatorTypeID to the same value for both instances
            Field actuatorTypeIDField = ActuatorType.class.getDeclaredField("_id");
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
            String id = "unitID";
            String typeDescription2 = "typeDescription2";
            String id2 = "unitID2";

            TypeDescription actuatorName = new TypeDescription(typeDescription);
            UnitID unitID = new UnitID(id);
            TypeDescription actuatorName2 = new TypeDescription(typeDescription2);
            UnitID unitID2 = new UnitID(id2);

            ActuatorType actuatorType1 = new ActuatorType(actuatorName, unitID);
            ActuatorType actuatorType2 = new ActuatorType(actuatorName2, unitID2);

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
            String id = "unitID";

            TypeDescription actuatorName = new TypeDescription(typeDescription);
            UnitID unitID = new UnitID(id);

            ActuatorType actuatorType = new ActuatorType(actuatorName, unitID);

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
            String id = "unitID";

            TypeDescription actuatorName = new TypeDescription(typeDescription);
            UnitID unitID = new UnitID(id);

            ActuatorType actuatorType = new ActuatorType(actuatorName, unitID);

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
            String id = "unitID";

            TypeDescription actuatorName = new TypeDescription(typeDescription);
            UnitID unitID = new UnitID(id);

            ActuatorType actuatorType = new ActuatorType(actuatorName, unitID);

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
            String id = "unitID";

            TypeDescription actuatorName = new TypeDescription(typeDescription);
            UnitID unitID = new UnitID(id);

            ActuatorType actuatorType = new ActuatorType(actuatorName, unitID);

            // Act
            TypeDescription result = actuatorType.getActuatorTypeName();

            // Assert
            assertEquals(actuatorName, result);
        }
}