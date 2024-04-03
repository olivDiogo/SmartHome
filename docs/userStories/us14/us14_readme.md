## US14 set a blind roller actuator

### 0. Requirements
_As a Product Owner, I want the system to have a type of actuator that sets the position of a blind roller.
defined by [0%, 100%]._

### 1. Analysis
_An actuator model (with a corresponding type) capable of setting the position of a blind roller will be included in the system._

### 1.1. System Sequence Diagram
Not applicable.

### 1.2. Use Case description
_To have an actuator that sets the position of a blind roller_
    
        Use Case Name: To have an actuator that sets the position of a blind roller
        Actor: Product Owner
        Goal: To have an actuator that sets the position of a blind roller

### 1.3. Dependency of another user story
_This user story depends on US12 (add an actuator to an existing
device in a room.)

### 1.4. Relevant domain aggregate model
![Actuator](../../general/agreggateModels/Actuator.png)

### 1.5. Required classes
_BlindRollerActuator_ -> for the actuator class with its functionality

_BlindRollerValue_ -> for the value of the blind roller to be set

_ImplFactoryActuator_ -> for the actuator instantiation

### 2. Design
_The team will design the best way to implement the requirements._

### 2.1. Class Diagram
![ClassDiagram](artifacts/US14CD.svg)

### 2.2. Sequence Diagram
_Implemented in US12.

### 2.3. Applied Patterns
- Single Responsibility Principle: Each class has a single responsibility, which promotes a better code organization

### 3 Acceptance Tests
- The BlindRollerActuator should be able to set the position of a blind roller in the range defined by [0%, 100%] - [Test Link](https://github.com/Departamento-de-Engenharia-Informatica/2023-2024-switch-dev-project-assignment-grupo-1/blob/main/src/test/java/SmartHomeDDD/domain/Actuator/BlindRollerActuatorTest.java)











