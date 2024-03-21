# US020 - To have a type of sensor that gives the sunrise instant for a given calendar date.

## 1. Requirements Engineering

### 1.1 User Story Description

As Product Owner, I want the system to have a type of sensor that gives the sunrise instant for a given calendar date.

### 1.2 Analysis
#### Classes:
- SunriseTimeSensor
- SunriseTimeValue

#### [SolarIrradianceSensor](../../../../src/main/java/SmartHome/sensors/SunriseTimeSensor.java)
- Attributes:
    - `sensorType`
    - `value`
    - `latitude`
    - `longitude`
- Methods:
    - `getSensorType()` - returns the sensor type
    - `getValue()` - returns the sunrise instant for a given calendar date from the sensor

#### [SolarIrradianceValue](../../../../src/main/java/SmartHome/sensors/SunsetTimeSensor.java)
- Attributes:
    - `value`
- Methods:
    - `toString()` - returns the value as a string
    - `getValues()` - returns the value contained in the object Value

### 1.3 Design
- [Class Diagram](../../us020/01.requirements-engineering/diagrams/classDiagram.svg)
