import React, { useContext } from 'react';
import { Button } from '@mui/material';
import FormDataContext from "../context/FormDataContext.jsx";
import AppContext from "../context/AppContext.jsx";
import {
    addDateSensorToDevice, addDecimalActuatorToDevice,
    addGenericActuatorToDevice,
    addGenericSensorToDevice,
    addGPSSensorToDevice, addIntegerActuatorToDevice
} from "../context/Actions.jsx";

const SubmitButton = () => {
    const { formState, formDispatch } = useContext(FormDataContext);
    const { latitude, longitude, sensorName, startDate, endDate, actuatorName, minLimit, maxLimit } = formState;

    const {state} = useContext(AppContext);
    const {selectedTypeOfSensor, selectedSensorTypeId, currentDevice, selectedSensorModelPath, selectedTypeOfActuator, selectedActuatorTypeId, selectedActuatorModelPath} = state;
    const { deviceId } = currentDevice;

    const handleSubmit = () => {
        if (selectedTypeOfSensor === 'dateSensor') {
            addDateSensorToDevice(formDispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, startDate, endDate)
        }
        if (selectedTypeOfSensor === 'gpsSensor') {
            addGPSSensorToDevice(formDispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, latitude, longitude)
        }
        if (selectedTypeOfSensor === 'genericSensor'){
            addGenericSensorToDevice(formDispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName)
        }
        if (selectedTypeOfActuator === 'decimalActuator'){
            addDecimalActuatorToDevice(formDispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, minLimit, maxLimit)
        }
        if (selectedTypeOfActuator === 'integerActuator'){
            addIntegerActuatorToDevice(formDispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, minLimit, maxLimit)
        }
        if (selectedTypeOfActuator === 'genericActuator'){
            addGenericActuatorToDevice(formDispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName)
        }
    };

    return (
        <Button variant="contained" onClick={handleSubmit}>
            Submit
        </Button>
    );
};

export default SubmitButton;