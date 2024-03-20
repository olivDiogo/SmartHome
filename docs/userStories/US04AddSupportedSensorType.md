## US04 Add a supported sensor type

## 0. Requirements
_As an Administrator, I want to define a sensor type._

## 1. Analysis
_The sensor type will be used for by the sensor to verify it's of supported typo before being added to the system. The sensor type will have a description, measuring unit and reference ID._

### 1.1. System Sequence Diagram
![System Sequence Diagram](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1/blob/main/docs/ooa/systemSequenceDiagram/US04AddSupportedSensorType.png)

### 1.3. Dependency of another user story
_At present this US has no dependency on another user story_

### 1.4. Relevant domain model aggregates
![SensorType](../ooa/agreggateModels/sensorTypeAggregate.png)

## 2. Design

### 2.1. Class Diagram
![ClassDiagram](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1/blob/main/docs/ood/classDiagram/US04AddSupportedSensorType.png)
### 2.2. Sequence Diagram
![SequenceDiagram](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1/blob/main/docs/ood/sequenceDiagram/US04AddSupportedSensorType.png)
### 2.3. Applied Patterns
- Factory Method: The senorType is created using the factory pattern, that is responsible for creating all supported sensor types
- Single Responsibility Principle: Each class has a single responsibility, this promotes a better code organization and maintainability.
- Data Transfer Object: The sensorTypeDTO is used to transfer the sensorType data between the controller and the service layer.
- Repository: The sensorTypeRepository is used to store and retrieve sensorType data.

## 3. Tests
_The team will design the best way to test the requirements._

- Should add sensorType with valid description
- Should throw exception if sensorType already exists
- Should throw exception if unit not supported
- Should ask for confirmation before adding supported type
- The sensor type description cannot be null or empty
- The sensor type description should only use letters and numbers
- The supported units must be from a given list
- The sensorType description should be unique