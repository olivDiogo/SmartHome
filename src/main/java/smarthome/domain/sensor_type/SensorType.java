package smarthome.domain.sensor_type;

import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.Validator;

public class SensorType implements IAggregateRoot<SensorTypeID> {

  private final TypeDescription name;
  private final UnitID unitID;
  private SensorTypeID id;

  /**
   * Creates a new {@link SensorType} instance using the provided sensor type name and unit.
   *
   * @param name the sensor type name, must not be null
   * @param unitID the unit of the sensor type, must not be null
   */
  SensorType(TypeDescription name, UnitID unitID) {
    Validator.validateNotNull(name, "Name");
    this.name = name;

    Validator.validateNotNull(unitID, "Unit");
    this.unitID = unitID;

    generateID(name);
  }

  /**
   * Creates a new {@link SensorType} instance using the provided sensor type ID, name and unit.
   *
   * @param sensorTypeID the sensor type ID, must not be null
   * @param name         the sensor type name, must not be null
   * @param unitID       the unit of the sensor type, must not be null
   */
  SensorType(SensorTypeID sensorTypeID, TypeDescription name, UnitID unitID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");
    Validator.validateNotNull(name, "Name");
    Validator.validateNotNull(unitID, "Unit");

    this.name = name;
    this.unitID = unitID;

  }


  /**
   * Creates a new {@link SensorTypeID} instance.
   */
  private void generateID(TypeDescription name) {
    id = new SensorTypeID(name.toString());
  }

  /**
   * Return the ID of the sensor type.
   *
   * @return the ID of the sensor type
   */
  @Override
  public SensorTypeID getID() {
    return id;
  }

  /**
   * Gets the name of the sensor type.
   *
   * @return the name of the sensor type
   */
  public TypeDescription getName() {
    return name;
  }

  /**
   * Gets the unit of the sensor type.
   *
   * @return the unit of the sensor type
   */
  public UnitID getUnitID() {
    return unitID;
  }

  /**
   * Compares the sensor type with another object.
   *
   * @param object is the object to compare with
   * @return true if the sensor type is the same as the object, false otherwise
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof SensorType sensorTypeObject) {
      return id.equals(sensorTypeObject.id);
    }
    return false;

  }

  /**
   * Returns the hash code of the sensor type.
   */
  @Override
  public int hashCode() {
    return id.hashCode();
  }

  /**
   * Returns the attributes of the sensor type as a string.
   *
   * @return the attributes of the sensor type as a string
   */
  @Override
  public String toString() {
    return "SensorType:" +
        "id=" + id +
        ", name=" + name +
        ", unit=" + unitID;

  }
}
