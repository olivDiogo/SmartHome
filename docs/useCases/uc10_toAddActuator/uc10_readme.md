# US012

## 0. Description

To add an actuator to an existing Device in a Room.

## 1. Requirements Engineering

As a Power User (or Administrator), I want to add an actuator to an existing device in a room. The actuator must be of a model of an existing type of actuator.

### 1.1 System Sequence Diagram

![US012-SSD](artifacts/us12_SSD.svg)

### 1.2 Dependency of other user stories

* There is a dependency with US03 - To get a list of existing rooms
* There is a dependency with US06 - To get a list of devices in a room

### 1.3 Customer Specifications and Clarifications

- Question 1
  - Q: é possível adicionar um atuador a um device que se encontra desativado? 
  - A: Não.

## 2. Analysis

### 2.1 Domain Model Excerpt

The relevant domain concepts for this user story:

![US012-DM](artifacts/us12_DM.svg)

## 3. Design

### 3.1 Functionality Development (System Sequence Diagram)
![US012-SD](artifacts/us12_SD.svg)
![US012-SD-ref1](artifacts/us12_SD_ref1.svg)
![US012-SD-ref2](artifacts/us12_SD_ref2.svg)
![US012-SD-ref3](artifacts/us12_SD_ref3.svg)
![US012-SD-ref4](artifacts/us12_SD_ref4.svg)
![US012-SD-ref5](artifacts/us12_SD_ref5.svg)

### 3.2 Class Diagram
![US012-CD](artifacts/us12_CD.svg)

### 3.3 Applied Patterns

* **Single Responsibility Principle** - All classes have only one and well-defined responsibility.

* **Controller** - A controller (AddActuatorController) receives and coordinates system operations connecting the UI layer to the App's logic layer.

* **Information Expert** - The flow of this process is entirely made following this principle: for a particular responsibility, it is determined the information needed to fulfill it and where that information is stored.

* **Pure Fabrication** - Services that represent a concept outside the problem's domain, but they have set of responsibilities designed to achieve low coupling, high cohesion and the potential for reuse.

* **Low Coupling** - Dependencies between classes are at their lowest point possible. The use of Services classes reduced the dependency level between them.

* **High Cohesion** - Due to low coupling, the responsibilities of each class are highly focused, therefore cohesion's high.

### 3.4 Tests

#### Test 01: Add Actuator to Device Successfully
#### Test 02: Add Nonexistent Actuator Model to Device
#### Test 03: Add Actuator to Non-Existing Device
#### Test 04: Add Actuator to Deactivated Device

### 3.5 Frontend Design

TBD

## 4. Implementation

## 5. Integration/Demonstration

## 6. Comments