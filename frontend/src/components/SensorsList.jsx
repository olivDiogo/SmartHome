import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

const sensorList = [
    {id: 1, name: 'Temperature Sensor'},
    {id: 2, name: 'Humidity Sensor'},
    {id: 3, name: 'DewPoint Sensor'},
];

function SensorsList({ selectedSensor, setSelectedSensor }) {
    const handleChange = (event) => {
        setSelectedSensor(event.target.value);
    };

    return (
        <Box sx={{ minWidth: 120 }}>
            <FormControl fullWidth>
                <InputLabel id="sensor-select-label">Sensor</InputLabel>
                <Select
                    labelId="sensor-select-label"
                    id="sensor-select"
                    value={selectedSensor}
                    label="Sensor"
                    onChange={handleChange}
                >
                    <MenuItem value="">None</MenuItem>
                    {sensorList.map((sensor) => (
                        <MenuItem key={sensor.id} value={sensor.id}>{sensor.name}</MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
}

export default SensorsList;
