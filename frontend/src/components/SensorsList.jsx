import * as React from 'react';
import { useState, useEffect } from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import { fetchSensorTypesFromServer, fetchSensorModelsBySensorTypeIdFromServer } from '../services/Service.jsx';

function SensorsList({ selectedSensor, setSelectedSensor }) {
    const [sensorTypes, setSensorTypes] = useState('');
    const [selectedSensorType, setSelectedSensorType] = useState('');
    const [sensorModels, setSensorModels] = useState([]);

    useEffect(() => {
        fetchSensorTypesFromServer((data) => {
            console.log('Fetched sensor types:', data);
            if (data._embedded && Array.isArray(data._embedded.sensorTypeDTOList)) {
                setSensorTypes(data._embedded.sensorTypeDTOList);
            }
        }, console.error);
    }, []);

    useEffect(() => {
        if (selectedSensorType) {
            fetchSensorModelsBySensorTypeIdFromServer((data) => {
                console.log('Fetched sensor models:', data);
                setSensorModels(data);
            }, console.error, selectedSensorType);
        }
    }, [selectedSensorType]);

    const handleSensorTypeChange = (event) => {
        setSelectedSensorType(event.target.value);
    };

    const handleSensorModelChange = (event) => {
        setSelectedSensor(event.target.value);
    };

    return (
        <Box sx={{ minWidth: 120 }}>
            <FormControl fullWidth>
                <InputLabel id="sensor-type-select-label">Sensor Type</InputLabel>
                <Select
                    labelId="sensor-type-select-label"
                    id="sensor-type-select"
                    value={selectedSensorType}
                    label="Sensor Type"
                    onChange={handleSensorTypeChange}
                >
                    <MenuItem value="">None</MenuItem>
                    {Array.isArray(sensorTypes) && sensorTypes.map((sensorType, index) => (
                        <MenuItem key={index} value={sensorType.sensorTypeID}>{sensorType.sensorTypeID}</MenuItem>
                    ))}
                </Select>
            </FormControl>
            <FormControl fullWidth>
                <InputLabel id="sensor-select-label">Sensor</InputLabel>
                <Select
                    labelId="sensor-select-label"
                    id="sensor-select"
                    value={selectedSensor}
                    label="Sensor"
                    onChange={handleSensorModelChange}
                >
                    <MenuItem value="">None</MenuItem>
                    {Array.isArray(sensorModels) && sensorModels.map((sensorModel, index) => (
                        <MenuItem key={index} value={sensorModel.sensorTypeID}>{sensorModel.sensorTypeID}</MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
}

export default SensorsList;