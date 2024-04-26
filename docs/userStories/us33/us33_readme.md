## US33

## 0. Description

As a Room Owner [or Power User, or Administrator],
I want to get a list of all measurement of a device in a room, in a
given period.

## 1. Analysis

A device will be selected, as well as a time frame. 
The system will then return a list of all measurements recorded by the selected device during the specified period.

### 1.1. Created/Affected Use Cases

* Creates: Use Case 11 - To get a log of all measurements of a device in a room,
in a given period.[UC11](../../useCases/uc11_toGetLogFromDevice/uc11_readme.md)

### 1.2. Acceptance Criteria

* AC1: The system must return all the measurements recorded by the selected device during the specified period.
* AC2: For each measurement the system must display the measurement type and a timestamp.
* AC3: The system should validate that the start date is earlier than the end date
* AC4: If no measurements are available for the given period, the system should return an error with the error message"
  This is a Smart Home, not a time machine!"
* AC5: If the device does not exist, the system should return a LogDTO with the error message "Device not found".

### 1.3 Customer Specifications and Clarifications
N/A
