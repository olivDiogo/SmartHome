# US 014 - As Product Owner, I want the system to have a type of actuator that open/closes a blind roller (0% closed, 100% fully open).

## 1. Requirements Engineering

### 1.1 User Story Description

As Product Owner, I want the system to have a type of actuator that open/closes
a blind roller (0% closed, 100% fully open).

## 1.2 Objected Oriented Analysis
### Classes:

- BlindRollerActuator
- BlindRollerValue

#### BlindRollerActuator

- Attributes:
    - `actuatorType`
    - `value`

- Methods:
    - `getActuatorType()` - returns the actuator type
    - `setValue()` - sets the value of the actuator

#### BlindRollerValue

- Attributes:
    - `value`

- Methods:
    - `validateValue(int nValue)` - validates the value of the actuator
    - `toString()` - returns the value of the actuator as a string

### 1.3 Class Diagram

