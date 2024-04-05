## US18 - Percentege Position Sensor

## 0. Requirements
_As Product Owner, To want the system to have a type of sensor that gives the current value/position in a scale (%).

## 1. Analysis
_A sensor model (with a corresponding type) capable of giving the scale of percentege._

### 1.1. System Sequence Diagram
Not applicable.

### 1.2. Use Case description
_To have a sensor that gives the temperature._
    
        Use Case Name: To want the system to have a type of sensor that gives the current value/position in a scale (%).
    
        Actor: Product Owner
    
        Goal: To have a sensor model (with a corresponding type) capable of giving the scale of percentege.


### 1.3. Dependency of another user story
_This user story does not depend on another._

### 1.4. Relevant domain aggregate model 
![Sensor](../../general/agreggateModels/Sensor.png)

### 1.5. Required classes
_PercentegePositionSensor_ -> for the sensor class with its functionality

_PercentegePositionSensorValue_ -> for the value of the scale of percentege

_ImplFactorySensor_ -> for the sensor instantiation


## 2. Design
_The team will design the best way to implement the requirements._
### 2.1. Class Diagram
![ClassDiagram](/Users/WagnerPaiva/Documents/GitHub/2023-2024-switch-dev-project-assignment-grupo-1/docs/userStories/us18/US18CD.png)
### 2.2. Sequence Diagram
Not applicable.
### 2.3. Applied Patterns
- Single Responsibility Principle: Each class has a single responsibility, which promotes a better code organization 
and maintainability.
- Factory Pattern: The factory pattern is used to create the sensor instances.

## 3. Acceptance Tests

- Test to verify that PercentagePositionSensor is properly instantiated when constructor arguments are valid - [Test Link](/Users/WagnerPaiva/Documents/GitHub/2023-2024-switch-dev-project-assignment-grupo-1/src/test/java/SmartHomeDDD/domain/Sensor/PercentagePositionSensorTest.java#L22)