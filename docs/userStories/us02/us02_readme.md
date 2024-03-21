## USX02: To Add Room to House

## 0. Requirements
As an Administrator, I want to add a new room to the house, in order to configure it (name, house floor and dimensions).

## 1. Analysis
The room will have a name, a floor and dimensions. The room will be added to the house.

### 1.1. System Sequence Diagram
![somerthing](something)

### 1.3. Dependency of another user story
US001 - a House must exist

### 1.4. Relevant domain model aggregates
![somerthing](something)

## 2. Design

### 2.1. Class Diagram
![somerthing](something)

### 2.2. Sequence Diagram
![something](something)

### 2.3. Applied Patterns

## 3. Tests
- Should add a new room to the house given a valid houseID, name, description, floor and dimensions
- Should throw exception if the house id is invalid
- Should throw exception if the room name is invalid
- Should throw exception if the room floor is invalid
- Should throw exception if the room dimensions are invalid