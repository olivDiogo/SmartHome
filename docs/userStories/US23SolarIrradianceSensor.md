## US23 Solar Irradiance Sensor

## 0. Requirements
_As a Product Owner, I want the system to have a type of sensor that gives the solar irradiance (W/m^2)._

## 1. Analysis
_A sensor model (with a corresponding type) capable of giving the solar irradiance will be included in the system._

### 1.1. System Sequence Diagram
Not applicable.

### 1.2. Use Case description
_To have a sensor that gives the solar irradiance._
    
        Use Case Name: To have a sensor that gives the solar irradiance
    
        Actor: Product Owner
    
        Goal: To have a sensor that gives the solar irradiance


### 1.3. Dependency of another user story
_This user story does not depend on another._

### 1.4. Relevant domain aggregate model 
![Sensor](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1/blob/51003b33c7fdbc39c6a24ed7f3c2f5083f5f187b/docs/ooa/agreggateModels/Sensor.png)

### 1.5. Required classes
_SolarIrradianceSensor_ -> for the sensor class with its functionality

_SolarIrradianceValue_ -> for the value of the solar irradiance

_ImplFactorySensor_ -> for the sensor instantiation


## 2. Design
_The team will design the best way to implement the requirements._
### 2.1. Class Diagram
![ClassDiagram](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1/blob/622fc6fcbcd6a404b10fc10d01a0331bee68b0bd/docs/ood/classDiagram/US23SolarIrradianceSensor.png)
### 2.2. Sequence Diagram
Not applicable.
### 2.3. Applied Patterns
- Single Responsibility Principle: Each class has a single responsibility, which promotes a better code organization 
and maintainability.

## 3. Tests
_The team will design the best way to test the requirements._
### 3.1. Integration Tests
- Should create a new SolarIrradianceSensor
- Should throw exception if no SolarIrradianceSensor object is created
- Should return the value of the solar irradiance

### 3.2. Acceptance Tests
- The SolarIrradianceSensor should be able to return the value of the solar irradiance