## US07 

## 0. Description

To add a Sensor to an existing Device in a Room.

## 1. Requirements
_As a Power User (or Administrator), I want to add a sensor to an existing device in
a room. The sensor must be of a model of an existing type of sensor._

_The Power User (or Administrator) wants to add a sensor to an existing device in a room. The sensor must be of a model of an existing type of sensor. So to add a sensor to a device we will first provide all the rooms in the house, then all the devices in a room and finally all the sensor models supported by the system._

### 1.1. System Sequence Diagram
![System Sequence Diagram](./artifacts/US07_SSD.svg)

### 1.2. Use Case description

_To add a sensor to an existing device in a room_

    Use Case Name: Add a sensor to an existing device in a room
    
    Actor: Power User (or Administrator)
    
    Goal: To add a sensor to an existing device in a room
    
    Preconditions:
    The Power User (or Administrator) has access to the sensor management interface within the system.
    The system has a mechanism for storing and accessing the rooms.
    The system has a mechanism for storing and accessing the devices.
    The system has a mechanism for storing and accessing the sensor models.
    
    Trigger: The Power User (or Administrator) selects the option to add a sensor to an existing device in a room.
    
    Basic Flow:
    The Power User (or Administrator) selects the option to add a sensor to an existing device in a room.
    The system provides a list with all the rooms in the house.
    The Power User (or Administrator) selects a room from the list.
    The system provides a list with all the devices in the room.
    The Power User (or Administrator) selects a device from the list.
    The system provides a list with all the sensor models supported by the system.
    The Power User (or Administrator) selects a sensor model from the list.
    The system provides a form to fill in the sensor description.
    The Power User (or Administrator) fills in the sensor description.
    The Power User (or Administrator) submits the form.
    The system adds the new sensor to the device.
    
    Alternative Flows:
    Non-existing list: If there are no rooms in the house, the list is empty.
    Non-existing list: If there are no devices in the room, the list is empty.
    Non-existing list: If there are no sensor models supported by the system, the list is empty.
    Invalid description: If the sensor description is null or empty, the system throws an exception.
    Invalid description: If the sensor description contains special characters, the system throws an exception.
    Invalid description: If the sensor description is not unique, the system throws an exception.

### 1.3. Dependency of another user story
_This user story has dependency on US03 and US06.

_However we assume that the system already has configured rooms, devices and sensor types_

### 1.4. Relevant domain model aggregates
![Room](../../ooa/4.agreggateModels/Room.png)
![Device](../../ooa/4.agreggateModels/Device.png)
![Sensor](../../ooa/4.agreggateModels/Sensor.png)
![SensorType](../../ooa/4.agreggateModels/sensorTypeAggregate.png)
![SensorModel](../../ooa/4.agreggateModels/SensorModel.png)

## 2. Analysis
_To tackle the current US we will be using the Sensor Service. Since Sensor only exists with an associated Device the service should know the Device repository, to provide a list of devices and check if the given deviceID is on the Repo._

### 2.1. Class Diagram
![ClassDiagram](./artifacts/US07_CD.svg)
### 2.2. Sequence Diagram
![SequenceDiagram](./artifacts/US07_SD.svg)

- Factory Method: The sensor is created using the factory pattern, that is responsible for creating all supported sensor models
- Single Responsibility Principle: Each class has a single responsibility, this promotes a better code organization and maintainability.
- Data Transfer Object: The sensorDTO is used to transfer the sensor data between the controller and the service layer.
- Repository: The sensorRepository is used to store and retrieve sensor data.

## 3. Tests
_The team will design the best way to test the requirements._

- The SensorController should be able to add a sensor to a device - [Test](../../../src/test/java/SmartHomeDDD/controller/AddSensorToDeviceControllerTest.java#L1420)



