import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import {useEffect, useState} from "react";
import {fetchActuatorModelsByActuatorTypeIdFromServer, fetchActuatorTypesFromServer} from "../services/Service.jsx";

function ActuatorsList({ selectedActuator, setSelectedActuator }) {
    const [actuatorTypes, setActuatorTypes] = useState('');
    const [selectedActuatorType, setSelectedActuatorType] = useState('');
    const [actuatorModels, setActuatorModels] = useState([]);

    useEffect(() => {
        fetchActuatorTypesFromServer((data) => {
            console.log('Fetched actuator types:', data);
            if (data._embedded && Array.isArray(data._embedded.actuatorTypeDTOList)) {
                setActuatorTypes(data._embedded.actuatorTypeDTOList);
            }
        }, console.error);
    }, []);

    useEffect(() => {
        if (selectedActuatorType) {
            fetchActuatorModelsByActuatorTypeIdFromServer((data) => {
                console.log('Fetched actuator models:', data);
                setActuatorModels(data);
            }, console.error, selectedActuatorType);
        }
    }, [selectedActuatorType]);

    const handleActuatorTypeChange = (event) => {
        setSelectedActuatorType(event.target.value);
    };

    const handleActuatorModelChange = (event) => {
        setSelectedActuator(event.target.value);
    };

    return (
        <Box sx={{ minWidth: 120 }}>
            <FormControl fullWidth>
                <InputLabel id="actuator-type-select-label">Actuator Type</InputLabel>
                <Select
                    labelId="actuator-type-select-label"
                    id="actuator-type-select"
                    value={selectedActuatorType}
                    label="Actuator Type"
                    onChange={handleActuatorTypeChange}
                >
                    <MenuItem value="">None</MenuItem>
                    {Array.isArray(actuatorTypes) && actuatorTypes.map((actuatorType, index) => (
                        <MenuItem key={index} value={actuatorType.actuatorTypeID}>{actuatorType.actuatorTypeID}</MenuItem>
                    ))}
                </Select>
            </FormControl>
            <FormControl fullWidth>
                <InputLabel id="actuator-select-label">Actuator</InputLabel>
                <Select
                    labelId="actuator-select-label"
                    id="actuator-select"
                    value={selectedActuator}
                    label="Actuator"
                    onChange={handleActuatorModelChange}
                >
                    <MenuItem value="">None</MenuItem>
                    {Array.isArray(actuatorModels) && actuatorModels.map((actuatorModel, index) => (
                        <MenuItem key={index} value={actuatorModel.actuatorModelID}>{actuatorModel.actuatorModelID}</MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
}

export default ActuatorsList;