import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

const actuatorList = [
    {id: 1, name: 'BlindRoller'},
    {id: 2, name: 'SetDecimal'},
    {id: 3, name: 'SetInteger'},
];

function ActuatorsList({ selectedActuator, setSelectedActuator }) {
    const handleChange = (event) => {
        setSelectedActuator(event.target.value);
    };

    return (
        <Box sx={{ minWidth: 120 }}>
            <FormControl fullWidth>
                <InputLabel id="actuator-select-label">Actuator</InputLabel>
                <Select
                    labelId="actuator-select-label"
                    id="actuator-select"
                    value={selectedActuator}
                    label="Actuator"
                    onChange={handleChange}
                >
                    <MenuItem value="">None</MenuItem>
                    {actuatorList.map((actuator) => (
                        <MenuItem key={actuator.id} value={actuator.id}>{actuator.name}</MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
}

export default ActuatorsList;