# US023 - To have a sensor that gives the solar irradiance.

## 1. Requirements Engineering

### 1.1 User Story Description

As a Product Owner, I want the system to have a type of sensor that gives
the solar irradiance (W/m^2).


### 1.2 Analysis
#### Classes:
- SolarIrradianceSensor
- SolarIrradianceValue

#### SolarIrradianceSensor
- Attributes:
    - sensorType
    - value
- Methods:
    - getSensorType() - returns the sensor type
    - getValue() - returns the reading from the sensor

#### SolarIrradianceValue
- Attributes:
    - value
- Methods:
    - toString() - returns the value as a string

### 1.3 Design
- [Class Diagram](../../../../userStories/us23/artifacts/us23_CD_v1.svg)
