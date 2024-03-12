# US 024 - To have a type of sensor that gives the instant power consumption.

## 1. Requirements Engineering

### 1.1 User Story Description

As a Product Owner, I want the system to have a type of sensor that gives the power consumption in a given instant (W).

### 1.2 Analysis
### Classes
- InstantPowerConsumptionSensor
- InstantPowerConsumptionValue

#### InstantPowerConsumptionSensor
- Attributes:
  - sensorType
  - instantPowerConsumptionValue
- Methods:
    - setSensorType() - validates the sensor type
    - getSensorType() - returns the sensor type
    - getValue() - returns the instant power consumption value

#### InstantPowerConsumptionValue
- Attributes:
  - instantPowerConsumptionValue
- Methods:
    - toString() - returns the instant power consumption value as a string

### 1.3 Design
- [Class Diagram](diagrams/classDiagram.svg)
```


