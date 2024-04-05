## US16: Ser Decimal Actuator

## 0. Requirements

As a Product Owner, I want the system to have a type of actuator that sets an decimal value in the range
defined by [lower limit, upper limit].

## 1. Analysis

An actuator model (with a corresponding type) capable of setting an decimal value between two limits will be included in the system.

### 1.1. Use Case Description
_To have an actuator that sets an decimal value_

        Use Case Name: To have an actuator that sets an decimal value in a range
    
        Actor: Product Owner
    
        Goal: To have an actuator that sets an decimal value in a range


### 1.2. Dependency of another user story
This user story does not depend on any other user story.

### 1.3. Relevant domain model aggregates
![Actuator](../../general/agreggateModels/Actuator.png)

### 1.4. Required classes
_SetDecimalActuator_ -> for the actuator class with its functionality

_SetDecimalValue_ -> for the value of the decimal to be set

_ImplFactoryActuator_ -> for the actuator instantiation

## 2. Design

### 2.1. Class Diagram
![ClassDiagram](artifacts/US16CD.svg)

### 2.2. Sequence Diagram
Not applicable.

### 2.3. Applied Patterns
- Single Responsibility Principle: Each class has a single responsibility, which promotes a better code organization
  and maintainability.

## 3. Tests
- Verifies that SetDecimalActuator is correctly instantiated when elements are valid.
- Verifies that an IllegalArgumentException is thrown when the DeviceID is null.
- Verifies that an IllegalArgumentException is thrown when the ModelPath is null.
- Verifies that an IllegalArgumentException is thrown when the ActuatorTypeID is null.
- Verifies that an IllegalArgumentException is thrown when the ActuatorName is null.
- Verifies that an IllegalArgumentException is thrown when the limits are null.
- Generates an ActuatorID for the SetDecimalActuator.
- Test to verify if the correct actuator name is returned.
- Test to verify if the correct model path is returned.
- Test to verify if the correct actuator type ID is returned.
- Test to verify if the correct device ID is returned.
- Test to verify if the correct limits are returned.
- Test to verify if the correct value is returned within the limits.
- Test to verify if an IllegalArgumentException is thrown when the value is less than the lower limit.
- Test to verify if an IllegalArgumentException is thrown when the value is greater than the upper limit.
- Test to verify if an IllegalArgumentException is thrown when the value is null.
