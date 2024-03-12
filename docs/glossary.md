## Glossary
In this file we will describe important terms and its definition.

|     Domain      |           Sensors           |   Actuators    |
|:---------------:|:---------------------------:|:--------------:|
|   [Actuator]    | [Average Power Consumption] | [Blind Roller] |
| [Actuator Type] |         [Dew Point]         | [Set Decimal]  |
|    [Address]    |  [Electric Consumption Wh]  | [Set Integer]  |
|    [Device]     |      [Humidity Sensor]      |    [Switch]    |
|      [GPS]      |     [Solar Irradiance]      |                |
|     [House]     |          [Sunrise]          |                |
|   [Location]    |          [Sunset]           |                |
|     [Room]      |          [Switch]           |                |
|    [Sensor]     |        [Temperature]        |                |
|  [Sensor Type]  | [Wind Speed and Direction]  |                |
|     [Unit]      |                             |                |
|     [Value]     |                             |                |
|  [Dimensions]   |                             |                |


## Domain Terms
### Actuator
[actuator]: #actuator
_An actuator is a physical component within a smart home system that directly causes an action or change in the environment.  Actuators receive instructions from smart home software and translate those commands into physical actions._

### Actuator Type
[actuator type]: #actuator-type
_The most common type of actuator in smart homes, using an electric motor to create movement. Electric actuators can be linear (moving in a straight line) or rotary (providing rotational motion)._

### Address
[address]: #address
_The address is the location of the house. It will have a street, a postal code, a city and a country_

### Device
[device]: #device
_The device is a physical unit that will be installed in the [house]. It will have a name, a status, a room and a list of [sensor] and [actuator]_

### Dimensions
[dimensions]: #dimensions
_The dimensions are the measures of the room. It will have a width, a length and a height_

### GPS
[gps]: #gps
_The GPS is a system that will provide the location of the house. It will have a latitude and a longitude_

### House
[house]: #house
_The house is the place where the system will be installed. It will have a name, a location, a list of rooms_

### Location
[location]: #location
_The location is the place where the house is. It will have a [address] and a [GPS]_

### Room
[room]: #room
_The room is a division of the [house]. It will have a name, a floor, a [dimenion] and list of devices_

### Sensor
[sensor]: #sensor
_A device or functionality that detects or measures physical properties of the environment and converts them into electronic signals. It will have a type that will identify the function and corresponding [unit]_

### Sensor Type
[sensor type]: #sensor-type
_The sensor type is the kind of sensor that will be used in the system. It will have a description that will identify the function_

### Unit
[unit]: #unit
_The unit is the measure of the physical quantity that the [sensor] will measure. It will have a name_

### Value
[value]: #value
_The value is the measure of the physical quantity that the [sensor] will measure. It will have a number and a [unit]_

## Supported Sensors
### Average Power Consumption
[average power consumption]: #average-power-consumption
_The average power consumption is the average of the power consumption in a period of time. It will have a value and a unit_

### Dew Point
[dew point]: #dew-point
_The dew point is the temperature at which air becomes saturated with water vapor when it is cooled without changing the air pressure or the water content. It will have a value and a unit_

### Electric Consumption Wh
[electric consumption wh]: #electric-consumption-wh
_The electric consumption wh is the electric consumption in a period of time, it will be read in hourly intervals. It will have a integer value_

### Humidity Sensor
[humidity sensor]: #humidity-sensor
_The humidity sensor is a sensor that measures the humidity in the air. It will have a value and a unit_

### Solar Irradiance
[solar irradiance]: #solar-irradiance
_The solar irradiance is the power per unit area received from the Sun in the form of electromagnetic radiation in the wavelength range of the measuring instrument. It will have a value and a unit_

### Sunrise
[sunrise]: #sunrise
_The sunrise is the time when the upper limb of the Sun appears on the horizon in the morning. It will have a value and a unit_

### Sunset
[sunset]: #sunset
_The sunset is the time when the upper part of the Sun disappears below the horizon in a given location. It will have a time value and a precision to the minute_

### Temperature
[temperature]: #temperature
_The temperature is a measure of the warmth or coldness of an object or substance with reference to some standard value. It will have a value and a unit_

### Wind Speed and Direction
[wind speed and direction]: #wind-speed-and-direction
_The wind speed and direction is the speed and direction of the wind. It will have a value and a unit_

## Supported Actuators
### Blind Roller
[blind roller]: #blind-roller
_The blind roller is a device that will control the blind roller. It will have a value and a unit_

### Set Decimal
[set decimal]: #set-decimal
_The set decimal is a device that will control a decimal value. It will have a value, limits and a unit_

### Set Integer
[set integer]: #set-integer
_The set integer is a device that will control an integer value. It will have a value, limits and a unit_

### Switch
[switch]: #switch
_The switch is a device that will control the switch. It will have a value and a unit_

