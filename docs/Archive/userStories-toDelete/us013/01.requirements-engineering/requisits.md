# US 013 - To have a type of actuator that switches a load ON/OFF.

## 1. Requirements Engineering

### 1.1 User Story Description

As Product Owner, I want the system to have a type of actuator that switches a
load ON/OFF.

## 1.2 Objected Oriented Analysis
### Classes:

- SwitchActuator
- SwitchActuatorValue

#### SwitchActuator

- Attributes:
    - `actuatorType`
    - `value`

- Methods:
    - `getActuatorType()` - returns the actuator type
    - `setValue()` - sets the value of the actuator

#### SwitchActuatorValue

- Attributes:
    - `value`

- Methods:
    - `setValueOn()` - sets the value of the actuator to ON
    - `setValueOff()` - sets the value of the actuator to OFF
    - `toString()` - returns the value of the actuator as a string

### 1.3 Class Diagram
 - [Class Diagram](../../../../userStories/us13/artifacts/us13_CD_v1.svg)
