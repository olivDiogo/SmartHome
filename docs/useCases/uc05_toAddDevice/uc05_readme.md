## US05 

## 0. Description

To add Device to Room

## 0. Requirements
_As a Power User (or Administrator), I want to add a device to a room._

## 1. Analysis
_The device will be added to a room. The device will have a name, a status, a type, a location, and a reference ID._

### 1.1. System Sequence Diagram
![System Sequence Diagram](artifacts/US05SSD.svg)

### 1.2. Use Case description
_To add a device to a room_

    Use Case Name: To add a device to a room
    
        Actor: Power User / Administrator
        
        Goal: To add a device to a room
        
        Preconditions:
        The Power User / Administrator has access to the device management interface within the system.
        The system has a mechanism for storing and accessing the devices in the house.
        The system has a mechanism for storing and accessing the rooms in the house.
        
        Trigger: The Power User / Administrator selects the option to add a device to a room.
        
        Basic Flow:
        The Power User / Administrator selects the option to add a device to a room.
        The system provides a list with all the devices in the house.
        The Power User / Administrator selects the device to add to the room.
        The system provides a list with all the rooms in the house.
        The Power User / Administrator selects the room to add the device.
        The system adds the device to the room.
        
        Alternative Flows:
        Non-existing list: If there are no devices in the house, the list is null.
        Non-existing list: If there are no rooms in the house, the list is null.

### 1.3. Dependency of another user story
Depends on US03 because the List of Rooms is needed to add a device to a room.

### 1.4. Relevant domain aggregate model
![Device](../../ooa/4.agreggateModels/Device.png)

### 1.5. Required classes
_Controller_ -> for the management of the services and the interface

_DeviceService_ -> for the device management

_DeviceRepository_ -> for the device storage

_DeviceAssembler_ -> for the device data transfer object management

_DeviceDTO_ -> for the device data transfer object

_RoomService_ -> for the room management

_RoomRepository_ -> for the room storage

_RoomAssembler_ -> for the room data transfer object management

_RoomDTO_ -> for the room data transfer object

## 2. Design
_The team will design the best way to implement the requirements._

### 2.1. Class Diagram
![ClassDiagram](./artifacts/US05CD.svg)

### 2.2. Sequence Diagram
![SequenceDiagram](./artifacts/US05SD.svg)

### 2.3. Applied Patterns
- Single Responsibility Principle: Each class has a single responsibility, which promotes a better code organization and maintainability.
- Data Transfer Object: The deviceDTO is used to transfer the device data between the controller and the service layer.
- Repository: The deviceRepository is used to store and retrieve device data.

## 3. Tests
_The team will design the best way to test the requirements._

- Should return list of rooms, checking if the returned data matches the expected.
- Should throw exception if list of rooms is empty
- Should add device with valid name
- Should throw exception if room does not exist
