# As Product Owner, I want the system to have a type of sensor that gives the electric energy consumption over a period (Wh).

### 1.1. System Sequence Diagram
Not applicable.

### 1.2. Use Case description
_To have a sensor that gives the solar irradiance._

        Use Case Name: Energy consumption over a period (Wh)
    
        Actor: Product Owner
    
        Goal: To have a sensor that gives the electric energy consumption over a period (Wh)
        
        Preconditions:
        The system has a mechanism for storing and accessing the sensors.
        The system has a mechanism for storing and accessing the supported units.
        The sensor should recive a time period to calculate the energy consumption.
        The sensor should have an implementation to acess the energy consumption over a period.


### 1.3. Dependency of another user story
_This user story does not depend on another._

### 1.4. Relevant domain aggregate model
![Sensor](../../general/agreggateModels/Sensor.png)

### 1.5. Required classes
_ElectricEnergyConsumptionWhSensor_ -> for the sensor implementation

_ElectricEnergyConsumptionWhSensorValue_ -> for the sensor value

_ImplFactorySensor_ -> for the sensor instantiation


## 2. Design
### 2.1. Class Diagram

![ClassDiagram](./artifacts/US26CD.png)
### 2.2. Sequence Diagram
Not applicable.
### 2.3. Applied Patterns
- Single Responsibility Principle: Each class has a single responsibility, which promotes a better code organization
  and maintainability.

## 3. Acceptance Tests

- Should throw exception when not given valid date period  - [Test Link](../../../src/test/java/SmartHomeDDD/domain/Sensor/ElectricConsumptionWhSensorTest.java#L112)
- Should throw exception when not given valid sensorType (Sensor Type not supported) - [Test Link](../../../src/test/java/SmartHomeDDD/domain/Sensor/ElectricConsumptionWhSensorTest.java#L74)
- Should return the energy consumption over a period - [Test Link](../../../src/test/java/SmartHomeDDD/domain/Sensor/ElectricConsumptionWhSensorTest.java#L221)
- Should return zero if there is no energy consumption - [Test Link](../../../src/test/java/SmartHomeDDD/domain/Sensor/ElectricConsumptionWhSensorTest.java#L242)