# US25 - To have a type of sensor that gives the average power consumption over a period (W).

## 1. Requirements Engineering

_As Product Owner, I want the system to have a type of sensor that gives the
average power consumption over a period (W)._

## 2. Analysis

### 2.1 Domain Model Excerpt

The relevant domain concepts for this user story:

![Sensor](../../../docs/general/agreggateModels/Sensor.png)

## 3. Design

### 3.1 Functionality Development (System Sequence Diagram)

N/A

### 3.2 Class Diagram

![artifacts/us25_CD.svg](artifacts/us25_CD.svg)

### 3.3 Applied Patterns

* **Single Responsibility Principle** - All classes have only one and well-defined responsibility.
* **Low Coupling** - Dependencies between classes are at their lowest point possible. The use of Services classes reduced the dependency level between them.
* **High Cohesion** - Due to low coupling, the responsibilities of each class are highly focused, therefore cohesion's high.

## 4 Tests

#### Test 1: Get a valid average power consumption over a period [Test](../../../src/test/java/SmartHomeDDD/domain/Sensor/AveragePowerConsumptionSensor/AveragePowerConsumptionSensorTest.java#L143)


