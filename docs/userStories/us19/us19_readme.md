# US019 - To have a type of sensor for wind speed and direction

## 1. Requirements Engineering

As Product Owner, I want the system to have a type of sensor that gives the wind speed (km/h) and direction (8 cardinal points).

### 1.1 System Sequence Diagram

N/A

### 1.2 Dependency of other user stories

N/A

### 1.3 Customer Specifications and Clarifications

- Question 1
  - Q. Existe algum limite de velocidade do vento?
  - A. Não lhe sei dizer.
- Question 2
  - Q. A velocidade do vento dever ser representada por um nº inteiro ou decimal?
  - A. Pode ser decimal.
- Question 3
  - Q. A direção do vento deve ser representada por pontos cardeais ou ângulos?
  - A. Não tinha pensado nisso, mas ângulos em radianos torna a coisa mais flexível. O círculo trignométrico começa a leste. Assim, Norte = Pi/2.

## 2. Analysis

### 2.1 Domain Model Excerpt

The relevant domain concepts for this user story:

![artifacts/us19_DM.svg](artifacts/us19_DM.svg)

## 3. Design

### 3.1 Functionality Development (System Sequence Diagram)

N/A

### 3.2 Class Diagram
![artifacts/us19_CD.svg](artifacts/us19_CD.svg)

### 3.3 Applied Patterns

* **Single Responsibility Principle** - All classes have only one and well-defined responsibility.

* **Low Coupling** - Dependencies between classes are at their lowest point possible. The use of Services classes reduced the dependency level between them.

* **High Cohesion** - Due to low coupling, the responsibilities of each class are highly focused, therefore cohesion's high.

### 3.4 Tests

#### Test 1: Get a valid wind speed and direction

### 3.5 Frontend Design

TBD

## 4. Implementation

## 5. Integration/Demonstration

## 6. Comments