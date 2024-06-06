import React, { useContext } from 'react';
import { Button } from '@mui/material';
import FormDataContext from "../context/FormDataContext.jsx";
import AppContext from "../context/AppContext.jsx";
import {addDateSensorToDevice, addGenericSensorToDevice, addGPSSensorToDevice} from "../context/Actions.jsx";

const SubmitButton = () => {
    const { formState, formDispatch } = useContext(FormDataContext);
    const { latitude, longitude, sensorName, startDate, endDate } = formState;

    const {state} = useContext(AppContext);
    const {selectedTypeOfSensor, selectedSensorTypeId, currentDevice, selectedSensorModelPath} = state;
    const { deviceId } = currentDevice;

    const handleSubmit = () => {
        if (selectedTypeOfSensor === 'dateSensor') {
            addDateSensorToDevice(formDispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, startDate, endDate)
        }
        if (selectedTypeOfSensor === 'gpsSensor') {
            addGPSSensorToDevice(formDispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName, latitude, longitude)
        }
        addGenericSensorToDevice(formDispatch, selectedTypeOfSensor, deviceId, selectedSensorModelPath, selectedSensorTypeId, sensorName)
    };

    return (
        <Button variant="contained" onClick={handleSubmit}>
            Submit
        </Button>
    );
};

export default SubmitButton;