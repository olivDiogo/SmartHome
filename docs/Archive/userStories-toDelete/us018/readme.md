

#US 018 - To want the system to have a type of sensor that gives the current value/position in a scale (%).

[Requirements Engineering](01.requisits-engineering/us18requisits.md)

## 1. Requirements Engineering

### 1.1 User Story Description

As Product Owner, I want the system to have a type of sensor that gives the current value/position in a scale (%).

### 1.2 Analysis
### Classes

- PercentegePositionSensor
- PercentegePositionValue

#### PercentegePositionSensor
- Attributes:
    - sensorType
    - percentegePositionValue
- Methods:
    - PercentegePositionSensor() - constructor
    - setSensorType() - validates the sensor type
    - getSensorType() - returns the sensor type
    - getValue() - returns the percentege position value

### PercentegePositionValue
- Attributes:
    - percented 
- Methods:
    - PercentegePositionValue() - constructor
    - toString() - returns the percentege position value as a string

### 1.3 Design
- [Class Diagram](diagrams/classDiagram.svg)


[Tests](02.tests/us18tests.md)

# US 018 - To want the system to have a type of sensor that gives the current value/position in a scale (%).

## Tests Descriptions

### Test 01. Tests the creation of a valid PercentagePositionSensor.
### Test 02. Tests the creation of an invalid PercentagePositionSensor.
### Test 03. Tests the retrieval of the correct sensor type.
### Test 04. Tests the retrieval of the correct sensor value.
