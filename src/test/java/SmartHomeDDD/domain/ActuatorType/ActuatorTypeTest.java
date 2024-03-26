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
            TypeDescription typeDescriptionDouble = mock(TypeDescription.class);

            // Act
            new ActuatorType(typeDescriptionDouble);
        }

        /**
         * Test of constructor of class ActuatorType, when typeDescription is null.
         * @throws Exception
         */
        @Test
        void shouldThrowIllegalArgumentException_whenTypeDescriptionIsNull() {
            // Arrange
            TypeDescription typeDescriptionDouble = null;

            try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
                when(mock.toString()).thenReturn("1");
            })) {
                // Act
                IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(typeDescriptionDouble));

                // Assert
                assertEquals("Actuator type name must not be null.", exception.getMessage());
            }
        }

        /**
         * Test of method getID of class ActuatorType.
         */
        @Test
        void shouldReturnActuatorTypeID_whenGetIDisCalled() {
            // Arrange
            TypeDescription typeDescriptionDouble = mock(TypeDescription.class);

            try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
                when(mock.toString()).thenReturn("1");
            })) {
                ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble);

                // Act
                ActuatorTypeID result = actuatorType.getID();

                // Assert
                assertEquals("1", result.toString());
            }
        }

        /**
         * Test of method equals of class ActuatorType, when the instances are equal.
         */
        @Test
        void shouldReturnTrue_whenInstancesAreEqual() {
            // Arrange
            TypeDescription typeDescriptionDouble = mock(TypeDescription.class);


            try (MockedConstruction<ActuatorTypeID> mocked = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
                when(mock.toString()).thenReturn("1");
            })) {
                ActuatorType actuatorType1 = new ActuatorType(typeDescriptionDouble);
                ActuatorType actuatorType2 = new ActuatorType(typeDescriptionDouble);
                // Act
                boolean result = actuatorType1.equals(actuatorType2);

                // Assert
                assertTrue(result);
            }
        }

        /**
         * Test of method equals of class ActuatorType, when the instances are not equal.
         */
        @Test
        void shouldReturnFalse_whenInstancesAreNotEqual() throws NoSuchFieldException, IllegalAccessException {
            // Arrange
            TypeDescription typeDescriptionDouble1 = mock(TypeDescription.class);
            TypeDescription typeDescriptionDouble2 = mock(TypeDescription.class);
            ActuatorTypeID actuatorTypeIDDouble1 = mock(ActuatorTypeID.class);
            ActuatorTypeID actuatorTypeIDDouble2 = mock(ActuatorTypeID.class);

            ActuatorType actuatorType1 = new ActuatorType(typeDescriptionDouble1);
            ActuatorType actuatorType2 = new ActuatorType(typeDescriptionDouble2);

            Field actuatorTypeIDField = ActuatorType.class.getDeclaredField("_actuatorTypeID");
            actuatorTypeIDField.setAccessible(true);
            actuatorTypeIDField.set(actuatorType1, actuatorTypeIDDouble1);
            actuatorTypeIDField.set(actuatorType2, actuatorTypeIDDouble2);

            //Act
            boolean result = actuatorType1.equals(actuatorType2);

            //Assert
            assertFalse(result);
        }

        /**
         * Test of method toString of class ActuatorType.
         */
        @Test
        void shouldReturnString_whenToStringIsCalled() {
            // Arrange
            TypeDescription typeDescriptionDouble = mock(TypeDescription.class);

            String expected = "ActuatorType{_actuatorTypeID=1, _actuatorTypeName=" + typeDescriptionDouble + "}";

            try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
                when(mock.toString()).thenReturn("1");
            })) {
                ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble);

                // Act
                String result = actuatorType.toString();

                // Assert
                assertEquals(expected, result);
            }
        }

        /**
         * Test of method getActuatorTypeName of class ActuatorType.
         */
        @Test
        void shouldReturnActuatorTypeName_whenGetActuatorTypeNameIsCalled() {
            // Arrange
            TypeDescription typeDescriptionDouble = mock(TypeDescription.class);

            try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
                when(mock.toString()).thenReturn("1");
            })) {
                ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble);

                // Act
                TypeDescription result = actuatorType.getActuatorTypeName();

                // Assert
                assertEquals(typeDescriptionDouble, result);
            }
        }
}