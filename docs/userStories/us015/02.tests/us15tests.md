# US 015 - To have an actuator that sets an integer value in a defined range.


## Set Integer Actuator Tests Description
### Business Acceptance Tests
#### [Test 01: Validate class constructor](../../../../src/test/java/SmartHome/actuators/SetIntegerActuatorTest.java#L20)
#### [Test 02: Validate class constructor with invalid parameters](../../../../src/test/java/SmartHome/actuators/SetIntegerActuatorTest.java#L37)
#### [Test 04: Get value equal to 0 when lower limit is 0](../../../../src/test/java/SmartHome/actuators/SetIntegerActuatorTest.java#L79)
#### [Test 05: Get value equal to 100 when upper limit is 100](../../../../src/test/java/SmartHome/actuators/SetIntegerActuatorTest.java#L110)
#### [Test 07: Throws exception when value is below range](../../../../src/test/java/SmartHome/actuators/SetIntegerActuatorTest.java#L172)
#### [Test 08: Throws exception when value is above range](../../../../src/test/java/SmartHome/actuators/SetIntegerActuatorTest.java#L203)

### Other Tests
#### [Test 03: Get correct actuator type](../../../../src/test/java/SmartHome/actuators/SetIntegerActuatorTest.java#L56)
#### [Test 06: Get null when value is not of the correct type](../../../../src/test/java/SmartHome/actuators/SetIntegerActuatorTest.java#L141)
