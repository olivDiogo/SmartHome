## US04 Add a supported sensor type

## 0. Requirements
_As an Administrator, I want to define a sensor type._

## 1. Analysis
_The sensor type will be used for by the sensor to verify it's of supported typo before being added to the system. The sensor type will have a description, measuring unit and reference ID._

### 1.1. System Sequence Diagram
![System Sequence Diagram](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1/blob/main/docs/ooa/systemSequenceDiagram/US04AddSupportedSensorType.png)

### 1.2. Use Case description
_Add a new sensor type_
    
        Use Case Name: Add a new sensor type
    
        Actor: Administrator
    
        Goal: The administrator wants to add a new sensor type to the system.
    
        Preconditions:
        The administrator has access to the sensor management interface within the system.
        The system has a mechanism for storing sensor type definitions.

        Trigger: The administrator selects the option to add a new sensor type.
    
        Basic Flow:
        The administrator selects the option to add a new sensor type.
        The system prompts the administrator to choose the sensor type measuring unit.
        The system prompts the administrator to input the sensor type description.
        The system prompts the administrator to confirm that the sensor type is correctly defined.
        The system adds the new sensor type to the system.
    
        Alternative Flows:
        Invalid sensor type description: If the administrator enters an invalid sensor type description, the system displays an appropriate error message and prompts the administrator to re-enter the sensor type description.
        Invalid sensor type measuring unit: If the administrator enters an invalid sensor type measuring unit, the system displays an appropriate error message and prompts the administrator to re-enter the sensor type measuring unit.
        
        Postconditions:
        The new sensor type is added to the system.
### 1.3. Dependency of another user story
_At present this US has no dependency on another user story_

### 1.4. Relevant domain model aggregates
![SensorType](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1/blob/main/docs/ooa/agreggateModels/sensorTypeAggregate.png)

### 1.5. Required classes
_SensorType_ -> for the sensor type definition

_SensorTypeRepository_ -> for the sensor type storage

_SensorTypeService_ -> for the sensor type management

_SensorTypeController_ -> for the sensor type management interface

_SensorTypeAssembler_ -> for the sensor type data transfer object management

_SensorTypeDTO_ -> for the sensor type data transfer object


## 2. Design
_The team will design the best way to implement the requirements._
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
### 3.1. Unit Tests
_ongoing_

### 3.2. Integration Tests
_ongoing_
### 3.3. Acceptance Tests
_ongoing_