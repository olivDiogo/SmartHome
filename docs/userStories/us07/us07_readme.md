## US07 Add a sensor to an existing device in a room.

## 0. Requirements
_As a Power User (or Administrator), I want to add a sensor to an existing device in
a room. The sensor must be of a model of an existing type of sensor._

## 1. Analysis
_A list of rooms should be given to choose one. 
Next a list of the devices existing in that room should be given to choose one
The list of the available sensor models should be provided to choose one.
With the information given the sensor with the chosen model should be added to the chosen device._

### 1.1. System Sequence Diagram
![System Sequence Diagram](.../artifacts/US07SSD.puml)

### 1.2. Use Case description
ref UC03
ref UC06
_To add sensor to device_

        Use Case Name: To add sensor to device

        Actor: Power User / Administrator
    
        Goal: to add a sensor of a chosen model to a chosen device in a chosen room
    
        Preconditions:
        The Power User / Administrator has access to the rooms in the house.
        The Power User / Administrator has acess to the devices in a chosen room.
        The Power User / Administrator has acess to the avaiable sensor models.
        The system has a mechanism for addeding and storing a sensor to a device.

        Trigger: The Power User / Administrator selects the option to add a sensor to a device.
    
        Basic Flow:
        The Power User / Administrator selects the option to get all rooms in the house.
        The system provides a list with all the rooms in the house.
        The Power User / Administrator selects the option to get all devices in a room.
        The system provides a list with all the devices in the room.
        The Power User / Administrator selects the option to get the available sensor models in the system.
        The system provides a list with all the sensor models available.
        The Power User / Administrator selects the option to add the chosen sensor model to the chosen device.
    
        Alternative Flows:
        Non-existing list: If there are no rooms in the house, the list is null. 
        If there are no devices in the house, the list is null. 
        If there are no sensor models in the house, the list is null. 
        If there was a problem adding the sensor to the device, the system returns the exception.

### 1.3. Dependency of another user story
_This user story depends on US03 and US06._

### 1.4. Relevant domain aggregate model
![Sensor](.../.../agreggateModels/Sensor.puml)

### 1.5. Required classes
_Controller_ -> for the management of the services and the interface

_SensorService_ -> for the sensor management

_SensorRepository_ -> for the sensor storage

_SensorAssembler_ -> for the sensor data transfer object management

_SensorDTO_ -> for the sensor data transfer object

## 2. Design
_The team will design the best way to implement the requirements._
### 2.1. Class Diagram

### 2.2. Sequence Diagram
![Sequence Diagram](.../artifacts/US07SD.puml)

### 2.3. Applied Patterns
- Single Responsibility Principle: Each class has a single responsibility, which promotes a better code organization
  and maintainability.
- Data Transfer Object: The DeviceDTO is used to transfer the Device
  data, respectively, between the service layer and the controller, which guarantees protection for the domain.
- Repository: The DeviceRepository is used to store and retrieve the Device data.


## 3. Tests
_The team will design the best way to test the requirements._
### 3.1. Integration Tests
- Should return a list of devices grouped by functionality
- Should throw exception if no devices exists

### 3.2. Acceptance Tests
- The list of devices grouped by functionality should be returned
