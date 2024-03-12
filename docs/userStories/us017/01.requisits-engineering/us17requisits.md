# US017 - to have a type of sensor that gives the status of a binary switch (ON/OFF)

## 1. Requisits Engineering

### 1.1 User Story Description

As Product Owner, I want the system to have a type of sensor that gives the
status of a binary switch (ON/OFF).

### 1.2 Analysis
#### Classes:

- SwitchSensor
- SwitchSensorValue

#### SwitchSensor

- Attributes:
    - _sensorType
    - _value
- Methods:
    - getSensorType()
    - getValue()

#### SwitchSensorValue

- Attributes:
    - _bvalue
- Methods:
    - toString() - returns the value as a string

### 1.3 Design

- [Class Diagram](diagrams/classDiagram.svg)

