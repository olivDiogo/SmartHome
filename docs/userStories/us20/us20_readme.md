# US20 - To have a type of sensor that gives the sunrise instant for a given calendar date.

## 1. Requirements Engineering

As Product Owner, I want the system to have a type of sensor that gives the sunrise time on a given location.

## 2. Analysis

### 2.1 Domain Model Excerpt

The relevant domain concepts for this user story:

![Sensor](../../../docs/general/agreggateModels/Sensor.png)

## 3. Design

### 3.1 Functionality Development (System Sequence Diagram)

N/A

### 3.2 Class Diagram

![artifacts/us20_CD.svg](artifacts/us20_CD.svg)

### 3.3 Applied Patterns

* **Single Responsibility Principle** - All classes have only one and well-defined responsibility.
* **Low Coupling** - Dependencies between classes are at their lowest point possible. The use of Services classes reduced the dependency level between them.
* **High Cohesion** - Due to low coupling, the responsibilities of each class are highly focused, therefore cohesion's high.

### 4. Tests

#### Test 1: Get a valid sunrise time on a given location [Test](../../../src/test/java/SmartHomeDDD/domain/Sensor/SunriseTimeSensorTest.java#L361)



