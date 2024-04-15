## US17 - Switch Sensor

## 0. Requirements
_As Product Owner, I want the system to have a type of sensor that gives the status of a binary switch (ON/OFF)._

## 1. Analysis
_A sensor model (with a corresponding type) capable of giving the status of a binary switch will be included in the system._

### 1.1. System Sequence Diagram
Not applicable.

### 1.2. Use Case description
_To have a sensor that gives the temperature._
    
        Use Case Name: To have a sensor that gives the status of a binary switch (ON/OFF)
    
        Actor: Product Owner
    
        Goal: To have a sensor that gives the status of a binary switch (ON/OFF)


### 1.3. Dependency of another user story
_This user story does not depend on another._

### 1.4. Relevant domain aggregate model 
![Sensor](../../ooa/4.agreggateModels/Sensor.png)

### 1.5. Required classes
_SwitchSensor_ -> for the sensor class with its functionality

_SwitchSensorValue_ -> for the value of the status of the binary switch

_ImplFactorySensor_ -> for the sensor instantiation


## 2. Design
_The team will design the best way to implement the requirements._
### 2.1. Class Diagram
![ClassDiagram](artifacts/US17CD.png)
### 2.2. Sequence Diagram
Not applicable.
### 2.3. Applied Patterns
- Single Responsibility Principle: Each class has a single responsibility, which promotes a better code organization 
and maintainability.
- Factory Pattern: The factory pattern is used to create the sensor instances.

## 3. Acceptance Tests

- The HumiditySensor should be able to return the value of the humidity - [Test Link](../../../src/test/java/SmartHomeDDD/domain/Sensor/SwitchSensorTest.java#L287)