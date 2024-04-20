package smart_home.domain.actuator_type;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActuatorTypeTest {

    /**
     * Test of the first constructor of class ActuatorType, when arguments are valid.
     */

    @Test
    void shouldCreateActuatorTypeInTheFirstConstructor_whenAttributesAreValid() {
        // Arrange
        TypeDescription actuatorName = mock(TypeDescription.class);
        when(actuatorName.toString()).thenReturn("ValidName");
        UnitID unitID = mock(UnitID.class);

        // Act
        ActuatorType actuatorType = new ActuatorType(actuatorName, unitID);

        // Assert
        assertNotNull(actuatorType);
    }

    /**
     * Test of the second constructor of class ActuatorType, when arguments are valid.
     */
    @Test
    void shouldCreateActuatorTypeInTheSecondConstructor_whenAttributesAreValid() {
        // Arrange
        TypeDescription actuatorName = mock(TypeDescription.class);
        when(actuatorName.toString()).thenReturn("ValidName");
        UnitID unitID = mock(UnitID.class);
        ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
        when(actuatorTypeID.toString()).thenReturn("1");

        // Act
        ActuatorType actuatorType = new ActuatorType(actuatorName, unitID, actuatorTypeID);

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
        TypeDescription typeDescriptionDouble = null;
        UnitID unitID = mock(UnitID.class);

        try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("1");
        })) {
            // Act
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(typeDescriptionDouble, unitID));

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
        UnitID unitID = mock(UnitID.class);
        try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("1");
        })) {
            ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble, unitID);
            // Act
            ActuatorTypeID result = actuatorType.getID();

            // Assert
            assertEquals("1", result.toString());
        }
    }

    /**
     * Test of method equals of class ActuatorType.
     */
    @Test
    void shouldReturnTrue_whenInstanceIsComparedToItself() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble, unitID);

        // Act
        boolean result = actuatorType.equals(actuatorType);
        // Assert
        assertTrue(result);
    }

    /**
     * Test of method equals of class ActuatorType, when the instances are not equal.
     */
    @Test
    void shouldReturnFalse_whenInstancesAreNotEqual() {
        // Arrange
        TypeDescription typeDescriptionDouble1 = mock(TypeDescription.class);
        when(typeDescriptionDouble1.toString()).thenReturn("Name1");
        TypeDescription typeDescriptionDouble2 = mock(TypeDescription.class);
        when(typeDescriptionDouble2.toString()).thenReturn("Name2");
        UnitID unitID1 = mock(UnitID.class);
        UnitID unitID2 = mock(UnitID.class);

        ActuatorType actuatorType1 = new ActuatorType(typeDescriptionDouble1, unitID1);
        ActuatorType actuatorType2 = new ActuatorType(typeDescriptionDouble2, unitID2);

        // Act
        boolean result = actuatorType1.equals(actuatorType2);

        // Assert
        assertFalse(result);
    }

    /**
     * Test of method equals of class ActuatorType, when the instance is compared to an object of a different class.
     */
    @Test
    void shouldReturnFalse_whenComparedWithDifferentClass() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble, unitID);

        // Act
        boolean isEqual = actuatorType.equals(new Object());
        // Assert
        assertFalse(isEqual, "ActuatorType should not be equal to an object of a different class");
    }

    @Test
    void shouldReturnString_whenToStringIsCalled() {
        // Arrange
        TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);
        String expected = "1 " + typeDescriptionDouble + " " + unitID;

        try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("1");
        })) {
            ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble, unitID);

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
        UnitID unitID = mock(UnitID.class);

        try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("1");
        })) {
            ActuatorType actuatorType = new ActuatorType(typeDescriptionDouble, unitID);

            // Act
            TypeDescription result = actuatorType.getActuatorTypeName();

            // Assert
            assertEquals(typeDescriptionDouble, result);
        }
    }

    /**
     * Test of method hashcode of class ActuatorType.
     */
    @Test
    void shouldReturnConsistentHashCodeBasedOnID() {
        // Arrange
        TypeDescription typeDescription = mock(TypeDescription.class);
        UnitID unitID = mock(UnitID.class);

        try (MockedConstruction<ActuatorTypeID> actuatorTypeIdDouble = mockConstruction(ActuatorTypeID.class, (mock, context) -> {
            when(mock.toString()).thenReturn("1");
        })) {
            ActuatorType actuatorType = new ActuatorType(typeDescription, unitID);

            int expected = actuatorType.getID().hashCode();

            // Act
            int result = actuatorType.hashCode();

            // Assert
            assertEquals(expected, result);
        }
    }
}