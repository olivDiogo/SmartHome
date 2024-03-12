# US015 - To have an actuator that sets an integer value in a defined range.

## 1. Requirements Engineering

### 1.1 User Story Description

As a Product Owner, I want the system to have a type of actuator 
that sets an integer value in the range defined by [lower limit, upper limit].


### 1.2 Analysis
#### Classes:
- SetIntegerActuator
- SetIntegerValue

#### SetIntegerActuator
- Attributes:
  - actuatorType
  - value
  - lowerLimit
  - upperLimit
- Methods:
  - getActuatorType() - returns the actuator type
  - setValueInRange() - sets the value within the range

#### SetIntegerValue
- Attributes:
  - value
- Methods:
  - toString() - returns the value as a string

### 1.3 Design
- [Class Diagram](diagrams/classDiagram.svg)

