## US04 Add a supported sensor type

## 1. Requirements
_As an Administrator, I want to define a sensor type._

_The Administrator wants to add a new sensor type, a sensor type will have to be from one supported unit. So to add a new sensor type we will first provide all the units supported by the system._

### 1.1. System Sequence Diagram
![System Sequence Diagram](./artifacts/US04SSD.png)

### 1.2. Use Case description
_To add a new sensor type_

    Use Case Name: Add a new sensor type
    
    Actor: Administrator
    
    Goal: To add a new sensor type
    
    Preconditions:
    The Administrator has access to the sensor type management interface within the system.
    The system has a mechanism for storing and accessing the sensor types.
    The system has a mechanism for storing and accessing the supported units.

    Trigger: The Administrator selects the option to add a new sensor type.
    
    Basic Flow:
    The Administrator selects the option to add a new sensor type.
    The system provides a list with all the units supported by the system.
    The Administrator selects a unit from the list.
    The system provides a form to fill in the sensor type description.
    The Administrator fills in the sensor type description.
    The Administrator submits the form.
    The system adds the new sensor type to the system.
    
    Alternative Flows:
    Non-existing list: If there are no units supported by the system, the list is empty.
    Invalid unit: If the unit selected is not supported by the system, the system throws an exception.
    Invalid description: If the sensor type description is null or empty, the system throws an exception.
    Invalid description: If the sensor type description contains special characters, the system throws an exception.
    Invalid description: If the sensor type description is not unique, the system throws an exception.

### 1.3. Dependency of another user story
_At present this US has no dependency on another user story_

_However we assume that the system already has configured units_
### 1.4. Relevant domain model aggregates
![SensorType](../../../docs/general/agreggateModels/sensorTypeAggregate.png)
![Unit](../../../docs/general/agreggateModels/Unit.png)

## 2. Analysis
_To tackle the current US we will be using the SensorType Service. Since SensorType only exists with an associated Unit the service should know the 
Unit repository, to provide a list of supported units and check if the given unitID is on the Repo._
### 2.1. Class Diagram
![ClassDiagram](./artifacts/US04CD.png)
### 2.2. Sequence Diagram
### 2.2.1 Controller SSD
![SequenceDiagram](./artifacts/US04SD.png)

### 2.2.2 Load Units SSD
![SequenceDiagram](./artifacts/SDLoadUnits.png)

- Factory Method: The senorType is created using the factory pattern, that is responsible for creating all supported sensor types
- Single Responsibility Principle: Each class has a single responsibility, this promotes a better code organization and maintainability.
- Data Transfer Object: The sensorTypeDTO is used to transfer the sensorType data between the controller and the service layer.
- Repository: The sensorTypeRepository is used to store and retrieve sensorType data.

## 3. Tests
_The team will design the best way to test the requirements._

- Should add sensorType with valid description and valid unit [Test Link](../../../src/test/java/SmartHomeDDD/controller/AddSensorTypeControllerTest.java#L139)
- Should throw exception if sensorType already exists [Test Link](../../../src/test/java/SmartHomeDDD/controller/AddSensorTypeControllerTest.java#L172)
- Should throw exception if unit not supported [Test Link](../../../src/test/java/SmartHomeDDD/controller/AddSensorTypeControllerTest.java#L205)
- The sensorType description should be unique [Test Link](../../../src/test/java/SmartHomeDDD/controller/AddSensorTypeControllerTest.java#L238)