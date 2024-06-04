import React, {useContext, useEffect} from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import AppContext from "../context/AppContext.jsx";
import {fetchSensorModels, updateSelectedSensorModel} from "../context/Actions.jsx";


const SensorModels = () => {
    const {state, dispatch} = useContext(AppContext);
    const {sensorModels} = state;
    const {loading, error, data } = sensorModels;

    const {selectedSensorTypeId} = state;
    const {selectedTypeOfSensor} = state;

    const {selectedSensorModel} = state;


    useEffect(() => {
        fetchSensorModels(dispatch, selectedSensorTypeId);
    }, [dispatch, selectedSensorTypeId, selectedTypeOfSensor]);

    const handleTypeChange = (event) => {
        updateSelectedSensorModel(dispatch, event.target.value);
    }


    return (
        <Box sx={{ padding: 2 }}>
            <FormControl fullWidth variant="outlined" margin="normal">
                <InputLabel id="type-label">Select Sensor Models</InputLabel>
                <Select
                    labelId="type-label"
                    id="model"
                    value={selectedSensorModel}
                    onChange={handleTypeChange}
                    label="Select Model"
                >
                    <MenuItem value="">
                        <em>None</em>
                    </MenuItem>
                    {data.map((sensorModel) => (
                        <MenuItem key={sensorModel.sensorModelName} value={sensorModel.sensorModelName}>
                            {sensorModel.sensorModelName}
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
};

export default SensorModels;