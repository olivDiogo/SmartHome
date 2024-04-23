package smart_home.domain.sensor.switch_sensor;

import org.junit.jupiter.api.Test;
import smart_home.domain.sensor.switch_sensor.SwitchSensorValue;

import static org.junit.jupiter.api.Assertions.*;

class SwitchSensorValueTest {
  /**
   * Tests the instantiation of SwitchSensorValue when the constructor arguments are valid. The
   * value is true.
   */
  @Test
  void shouldInstantiateSwitchSensorValue_whenConstructorArgumentsAreValidAndEqualsTrue() {
    // Arrange
    boolean value = true;

    // Act
    SwitchSensorValue result = new SwitchSensorValue(value);

    // Assert
    assertNotNull(result);
  }

  /**
   * Tests the instantiation of SwitchSensorValue when the constructor arguments are valid. The
   * value is false.
   */
  @Test
  void shouldInstantiateSwitchSensorValue_whenConstructorArgumentsAreValidAndEqualsFalse() {
    // Arrange
    boolean value = false;

    // Act
    SwitchSensorValue result = new SwitchSensorValue(value);

    // Assert
    assertNotNull(result);
  }

  /** Tests the toString method of SwitchSensorValue when the value is true. */
  @Test
  void shouldReturnOn_whenValueIsTrue() {
    // Arrange
    boolean value = true;
    SwitchSensorValue switchSensorValue = new SwitchSensorValue(value);

    // Act
    String result = switchSensorValue.toString();

    // Assert
    assertEquals("On", result);
  }

  /** Tests the toString method of SwitchSensorValue when the value is false. */
  @Test
  void shouldReturnOff_whenValueIsFalse() {
    // Arrange
    boolean value = false;
    SwitchSensorValue switchSensorValue = new SwitchSensorValue(value);

    // Act
    String result = switchSensorValue.toString();

    // Assert
    assertEquals("Off", result);
  }

  /** Tests the equals method of SwitchSensorValue when is the same object. */
  @Test
  void shouldReturnTrue_whenEqualsIsTheSameObject() {
    // Arrange
    SwitchSensorValue switchSensorValue = new SwitchSensorValue(true);
    SwitchSensorValue switchSensorValue1 = switchSensorValue;

    // Act
    boolean result = switchSensorValue.equals(switchSensorValue1);

    // Assert
    assertTrue(result);
  }

  /** Tests the equals method of SwitchSensorValue when the objects are not equal. */
  @Test
  void shouldReturnFalse_whenObjectsAreNotEqual() {
    // Arrange
    SwitchSensorValue switchSensorValue = new SwitchSensorValue(true);
    SwitchSensorValue switchSensorValue1 = new SwitchSensorValue(false);

    // Act
    boolean result = switchSensorValue.equals(switchSensorValue1);

    // Assert
    assertFalse(result);
  }

  /**
   * Tests the equals method of SwitchSensorValue when the object is not an instance of
   * switchSensorValue.
   */
  @Test
  void shouldReturnFalse_whenObjectIsNotInstanceOfSwitchSensorValue() {
    // Arrange
    SwitchSensorValue switchSensorValue = new SwitchSensorValue(true);
    Object object = new Object();

    // Act
    boolean result = switchSensorValue.equals(object);

    // Assert
    assertFalse(result);
  }

  /** Should return the hashCode */
  @Test
  void shouldReturnHashCode() {
    // Arrange
    SwitchSensorValue switchSensorValue = new SwitchSensorValue(true);
    SwitchSensorValue switchSensorValue1 = new SwitchSensorValue(true);

    // Act
    int result = switchSensorValue.hashCode();
    int result1 = switchSensorValue1.hashCode();

    // Assert
    assertEquals(result, result1);
  }

  /** Should return different hashCode */
  @Test
  void shouldReturnDifferentHashCode() {
    // Arrange
    SwitchSensorValue switchSensorValue = new SwitchSensorValue(true);
    SwitchSensorValue switchSensorValue1 = new SwitchSensorValue(false);

    // Act
    int result = switchSensorValue.hashCode();
    int result1 = switchSensorValue1.hashCode();

    // Assert
    assertNotEquals(result, result1);
  }
//
//  /** Should return different hashCode */
//  @Test
//  void shouldReturnZero_whenHashCodeIsCalledOnFalseValue() {
//    // Arrange
//    SwitchSensorValue switchSensorValue = new SwitchSensorValue(false);
//
//    // Act
//    int result = switchSensorValue.hashCode();
//
//    // Assert
//    assertNotEquals(0, result);
//  }
}
