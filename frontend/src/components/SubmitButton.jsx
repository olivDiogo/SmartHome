import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import {Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle} from '@mui/material';
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
    const { latitude, longitude, sensorName, startDate, endDate, actuatorName, minLimitDecimal, minLimitInteger, maxLimitInteger, maxLimitDecimal } = formState;

    const {state} = useContext(AppContext);
    const {selectedTypeOfSensor, selectedSensorTypeId, currentDevice, selectedSensorModelPath, selectedTypeOfActuator, selectedActuatorTypeId, selectedActuatorModelPath} = state;
    const { deviceId } = currentDevice;

    const [open, setOpen] = React.useState(false);

    const navigate = useNavigate();

    const handleGoToLanding = () => {
       navigate('/');
    }


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
            addDecimalActuatorToDevice(formDispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, minLimitDecimal, maxLimitDecimal)
        }
        if (selectedTypeOfActuator === 'integerActuator'){
            addIntegerActuatorToDevice(formDispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName, minLimitInteger, maxLimitInteger)
        }
        if (selectedTypeOfActuator === 'genericActuator'){
            addGenericActuatorToDevice(formDispatch, selectedTypeOfActuator, deviceId, selectedActuatorModelPath, selectedActuatorTypeId, actuatorName)
        }
        setOpen(true)
    };

    const handleClose = () => {
        setOpen(false);
    };

        return (
            <div>
                <Button variant="contained"  color="success" onClick={handleSubmit}>
                    Submit
                </Button>
                <Dialog
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">{"Success"}</DialogTitle>
                    <DialogContent>
                        <DialogContentText id="alert-dialog-description">
                            Sensor or actuator has been successfully added to the device.
                        </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleGoToLanding} color="primary">
                            Go to Landing Page
                        </Button>
                        <Button onClick={handleClose} color="primary" autoFocus>
                            OK
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        );
    };

export default SubmitButton;