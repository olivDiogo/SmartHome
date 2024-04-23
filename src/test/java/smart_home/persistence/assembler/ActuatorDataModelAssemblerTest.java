package smart_home.persistence.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import smart_home.domain.actuator.ActuatorFactoryImpl;
import smart_home.domain.actuator.IActuator;
import smart_home.domain.actuator.IActuatorFactory;
import smart_home.domain.actuator.set_decimal_actuator.SetDecimalActuator;
import smart_home.domain.actuator.set_integer_actuator.SetIntegerActuator;
import smart_home.domain.actuator.switch_actuator.SwitchActuator;
import smart_home.persistence.jpa.data_model.ActuatorDataModel;
import smart_home.value_object.ActuatorName;
import smart_home.value_object.ActuatorTypeID;
import smart_home.value_object.DecimalLimits;
import smart_home.value_object.DeviceID;
import smart_home.value_object.IntegerLimits;
import smart_home.value_object.ModelPath;

class ActuatorDataModelAssemblerTest {

  @Test
  void shouldInstantiateGenericActuatorWhenGivenValidParameters() {
    // Arrange
    String deviceIDValue = "some-device-id";
    String modelPathValue = "smart_home.domain.actuator.switch_actuator.SwitchActuator";
    String actuatorNameValue = "actuatorName";
    String actuatorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    ActuatorName actuatorName = new ActuatorName(actuatorNameValue);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDValue);

    IActuator actuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuator);
    IActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
    ActuatorDataModelAssembler actuatorDataModelAssembler = new ActuatorDataModelAssembler(
        actuatorFactory);
    // Act
    IActuator actuator2 = actuatorDataModelAssembler.toDomain(actuatorDataModel);

    // Assert
    assertEquals(actuator, actuator2);
  }

  @Test
  void shouldInstantiateActuatorWithIntegerLimitsWhenGivenValidParameters() {
    // Arrange
    String deviceIDValue = "some-device-id";
    String modelPathValue = "smart_home.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorNameValue = "actuatorName";
    String actuatorTypeIDValue = "SetInteger";
    Integer min = 0;
    Integer max = 100;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    ActuatorName actuatorName = new ActuatorName(actuatorNameValue);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDValue);
    IntegerLimits integerLimits = new IntegerLimits(min, max);

    IActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName,
        integerLimits);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuator);
    actuatorDataModel.setIntegerLowerBond(min);
    actuatorDataModel.setIntegerUpperBond(max);
    IActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
    ActuatorDataModelAssembler actuatorDataModelAssembler = new ActuatorDataModelAssembler(
        actuatorFactory);
    // Act
    IActuator actuator2 = actuatorDataModelAssembler.toDomain(actuatorDataModel);

    // Assert
    assertEquals(actuator, actuator2);
  }

  @Test
  void shouldInstantiateActuatorWithDecimalLimitsWhenGivenValidParameters() {
    // Arrange
    String deviceIDValue = "some-device-id";
    String modelPathValue = "smart_home.domain.actuator.set_decimal_actuator.SetDecimalActuator";
    String actuatorNameValue = "actuatorName";
    String actuatorTypeIDValue = "SetDecimal";
    Double min = 0.0;
    Double max = 100.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    ActuatorName actuatorName = new ActuatorName(actuatorNameValue);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDValue);
    DecimalLimits decimalLimits = new DecimalLimits(min, max);

    IActuator actuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName,
        decimalLimits);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuator);
    actuatorDataModel.setDecimalLowerBond(min);
    actuatorDataModel.setDecimalUpperBond(max);
    IActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
    ActuatorDataModelAssembler actuatorDataModelAssembler = new ActuatorDataModelAssembler(
        actuatorFactory);
    // Act
    IActuator actuator2 = actuatorDataModelAssembler.toDomain(actuatorDataModel);

    // Assert
    assertEquals(actuator, actuator2);
  }


}