# US025 - To have a type of sensor that gives the average power consumption over a period (W).

## 1. Requirements Engineering

### 1.1 User Story Description

As Product Owner, I want the system to have a type of sensor that gives the
average power consumption over a period (W).

### 1.2 Analysis
#### Classes:
- AveragePowerConsumptionSensor
- AveragePowerConsumptionValue

#### SolarIrradianceSensor
- Attributes:
    - `sensorType`
    - `value`
    - `powerConsumptions`
- Methods:
    - `getSensorType()` - returns the sensor type
    - `addReading()` - adds a reading to the sensor
    - `getValue()` - returns the average power consumption over a period from the sensor

#### SolarIrradianceValue
- Attributes:
    - `value`
- Methods:
    - `toString()` - returns the value as a string
    - `getValues()` - returns the value contained in the object Value

### 1.3 Design
- [Class Diagram](../../../../userStories/us25/artifacts/us25_CD_v1.svg)
