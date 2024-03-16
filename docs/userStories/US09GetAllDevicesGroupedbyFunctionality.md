## US09 Get all devices grouped by functionality

## 0. Requirements
_As a Power User (or Administrator), I want to get a list of all devices in a house, grouped by device functionality types.
It must include device location._

## 1. Analysis
_A collection with all the devices in the house will be made, initially. After that, the devices will be grouped by each of their functionality type._

### 1.1. System Sequence Diagram
![System Sequence Diagram](../ooa/systemSequenceDiagram/US09GetAllDevicesGroupedbyFunctionality.png)

### 1.2. Use Case description
_To get list of devices grouped by functionality_
    
        Use Case Name: To get list of devices grouped by functionality
    
        Actor: Power User / Administrator
    
        Goal: To get the list of all devices in the house grouped by functionality
    
        Preconditions:
        The Power User / Administrator has access to the device management interface within the system.
        The system has a mechanism for storing and accessing the devices in the house.

        Trigger: The Power User / Administrator selects the option to get all devices in the house grouped by functionality.
    
        Basic Flow:
        The Power User / Administrator selects the option to get all devices grouped by functionality.
        The system provides a list with all the devices in the house, grouped by functionality.
    
        Alternative Flows:
        Non-existing list: If there are no devices in the house, the list is null.

### 1.3. Dependency of another user story
_This user story dependes on another two user stories:_

    _US03 - To get list of room._

    _US06 - To get list of devices in a room._

### 1.4. Relevant domain aggregate model 
![Device](../ooa/agreggateModels/Device.png)

### 1.5. Required classes
_Controller_ -> for the management of the services and the interface

_ServiceDeviceType_ -> for the device type management

_ServiceDevice_ -> for the device management

_RepositoryDeviceType_ -> for the device type storage

_RepositoryDevice_ -> for the device storage

_DeviceTypeAssembler_ -> for the device type data transfer object management

_DeviceTypeDTO_ -> for the device type data transfer object

_DeviceAssembler_ -> for the device data transfer object management

_DeviceDTO_ -> for the device data transfer object


## 2. Design
_The team will design the best way to implement the requirements._
### 2.1. Class Diagram
![ClassDiagram](../ood/classDiagram/US09GetAllDevicesGroupedbyFunctionality.png)
### 2.2. Sequence Diagram
![SequenceDiagram](../ood/sequenceDiagram/US09GetAllDevicesGroupedbyFunctionality.png)
### 2.3. Applied Patterns
- Single Responsibility Principle: Each class has a single responsibility, which promotes a better code organization 
and maintainability.
- Data Transfer Object: The DeviceTypeDTO and DeviceDTO are used to transfer the DeviceType and Device 
data, respectively, between the service layer and the controller, which guarantees protection for the domain.
- Repository: The RepositoryDeviceType and RepositoryDevice are used to store and retrieve DeviceType and Device data.

## 3. Tests
_The team will design the best way to test the requirements._
### 3.1. Integration Tests
- Should return a list of devices grouped by functionality
- Should throw exception if no devices exists

### 3.2. Acceptance Tests
- The list of devices grouped by functionality should be returned