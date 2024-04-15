## UC01

## 0. Description

To Configure Location

## 1. Analysis
The location the house will have an address, GPS a Postal Code. The location the house will be added to the house.

### 1.1. Dependency of another use cases
No dependencies.

### 1.2. Relevant domain model aggregates
![House](../../ooa/4.agreggateModels/House.png)

## 2. Design

### 2.1. Class Diagram
![ClassDiagram](artifacts/uc01_CD_v2.png)

### 2.2. Sequence Diagram
![SequenceDiagram](artifacts/US01SD.png)

### 2.3. Applied Patterns
- Single Responsibility Principle: Each class has a single responsibility, which promotes a better code organization
- Factory Method: The HouseAssembler class will be used to create the data transfer objects.
- Data Transfer Object: The HouseDTO class will be used to transfer data between the layers of the application.
- Repository: The HouseRepository is used to store and retrieve house data.