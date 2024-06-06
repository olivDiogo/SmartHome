import React, { useContext, useState } from 'react';
import AppContext from '../context/AppContext.jsx';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import {setBlindRoller} from "../context/Actions.jsx";

const BlindRollerControl = ({ deviceId }) => {
    const { state, dispatch } = useContext(AppContext);
    const [value, setValue] = useState('');

    // Safely access actuators data
    const actuators = state.actuators || { data: [] }; // Ensure actuators object and data array are defined
    const actuatorId = actuators.data.length > 0 ? actuators.data[0].id : null;

    const handleValueChange = (event) => {
        setValue(event.target.value);
    };

    const handleSubmit = () => {
        if (value && actuatorId) {
            setBlindRoller(dispatch, deviceId, actuatorId, value);
        } else {
            console.error('Actuator ID is not available or invalid input.');
        }
    };

    return (
        <div>
            <Typography variant="h6">Set Blind Roller Position</Typography>
            <TextField
                label="Position"
                variant="outlined"
                type="number"
                value={value}
                onChange={handleValueChange}
                margin="normal"
            />
            <Button
                variant="contained"
                color="primary"
                onClick={handleSubmit}
                disabled={!actuatorId || value === ''}
            >
                Set Position
            </Button>
        </div>
    );
};

export default BlindRollerControl;
