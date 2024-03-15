# US 022 - To have a type of sensor that gives the dew point.

[Requirements Engineering](01.requirements-engineering/readme.md)

## 1. Requirements Engineering

### 1.1 User Story Description

As a Product Owner, I want the system to a have a type of sensor that gives the dew point (C).

### 1.2 Analysis
### Classes
- DewPointSensor
- DewPointValue

#### DewPointSensor
- Attributes:
  - sensorType
  - dewPointValue
- Methods:
  - setSensorType() - validates the sensor type
  - getSensorType() - returns the sensor type
  - getValue() - returns the dew point value

### DewPointValue
- Attributes:
  - dewPointValue
- Methods:
  - toString() - returns the dew point value as a string

### 1.3 Design
- [Class Diagram](diagrams/classDiagram.svg)
```