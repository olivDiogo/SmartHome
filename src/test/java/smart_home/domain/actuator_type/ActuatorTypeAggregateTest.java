package smart_home.domain.actuator_type;

import org.junit.jupiter.api.Test;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.TypeDescription;
import smart_home.value_object.UnitID;

import static org.junit.jupiter.api.Assertions.*;


class ActuatorTypeAggregateTest {

  /**
   * Test of the first constructor of class ActuatorType, when arguments are valid.
   */
  @Test
  void shouldCreateActuatorType_whenAttributesAreValidInTheFirstConstructor() {
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
   * Test of the second constructor of class ActuatorType, when arguments are valid.
   */
  @Test
  void shouldCreateActuatorType_whenAttributesAreValidInTheSecondConstructor() {
    // Arrange
    String typeDescription = "typeDescription";
    String id = "unitID";

    TypeDescription actuatorName = new TypeDescription(typeDescription);
    UnitID unitID = new UnitID(id);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");

    // Act
    ActuatorType actuatorType = new ActuatorType(actuatorName, unitID, actuatorTypeID);

    // Assert
    assertNotNull(actuatorType);
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
   * Test of method equals of class ActuatorType, when the instance is compared to an object of a
   * different class.
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
    String unitID = "unitID";

    TypeDescription actuatorName = new TypeDescription(typeDescription);
    UnitID unitId = new UnitID(unitID);

    ActuatorType actuatorType = new ActuatorType(actuatorName, unitId);
    ActuatorTypeID actuatorTypeID = actuatorType.getID();

    String expected = actuatorTypeID + " " + actuatorName + " " + unitId;

    // Act
    String result = actuatorType.toString();

    // Assert
    assertEquals(expected, result);
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

  /**
   * Test of method hashcode of class ActuatorType.
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    TypeDescription typeDescription = new TypeDescription("ActuatorType1");
    UnitID unitID = new UnitID("Unit1");
    ActuatorType actuatorType = new ActuatorType(typeDescription, unitID);

    int expected = actuatorType.getID().hashCode();

    // Act
    int result = actuatorType.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldThrowException_WhenTypeDescriptionIsNullForFirstConstructor() {
    // Arrange
    TypeDescription actuatorName = null;
    UnitID unitID = new UnitID("unitID");

    String expectedMessage = "Type Description is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(actuatorName, unitID));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  @Test
  void shouldThrowException_WhenUnitIDIsNullForFirstConstructor() {
    // Arrange
    TypeDescription actuatorName = new TypeDescription("typeDescription");
    UnitID unitID = null;

    String expectedMessage = "Unit ID is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(actuatorName, unitID));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  @Test
  void shouldThrowException_WhenTypeDescriptionIsNullForSecondConstructor() {
    // Arrange
    TypeDescription actuatorName = null;
    UnitID unitID = new UnitID("unitID");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");

    String expectedMessage = "Type Description is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(actuatorName, unitID, actuatorTypeID));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  @Test
  void shouldThrowException_WhenActuatorTypeIDIsNullForSecondConstructor() {
    // Arrange
    TypeDescription actuatorName = new TypeDescription("typeDescription");
    UnitID unitID = new UnitID("unitID");
    ActuatorTypeID actuatorTypeID = null;

    String expectedMessage = "Actuator Type ID is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(actuatorName, unitID, actuatorTypeID));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  @Test
  void shouldThrowException_WhenUnitIDIsNullForSecondConstructor() {
    // Arrange
    TypeDescription actuatorName = new TypeDescription("typeDescription");
    UnitID unitID = null;
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("1");

    String expectedMessage = "Unit ID is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorType(actuatorName, unitID, actuatorTypeID));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }



}