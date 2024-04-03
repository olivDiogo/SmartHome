## US04 Add a supported sensor type

## 1. Requirements
_As an Administrator, I want to define a sensor type._

_The Administrator wants to add a new sensor type, a sensor type will have to be from one supported unit. So to add a new sensor type we will first provide all the units supported by the system._

### 1.1. System Sequence Diagram
![System Sequence Diagram](./artifacts/US04SSD.png)

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
![SequenceDiagram](./artifacts/US04SD.png)

- Factory Method: The senorType is created using the factory pattern, that is responsible for creating all supported sensor types
- Single Responsibility Principle: Each class has a single responsibility, this promotes a better code organization and maintainability.
- Data Transfer Object: The sensorTypeDTO is used to transfer the sensorType data between the controller and the service layer.
- Repository: The sensorTypeRepository is used to store and retrieve sensorType data.

## 3. Tests
_The team will design the best way to test the requirements._

- Should add sensorType with valid description and valid unit
- Should throw exception if sensorType already exists
- Should throw exception if unit not supported
- The sensor type description cannot be null or empty
- The sensor type description should only use letters and numbers
- The sensorType description should be unique