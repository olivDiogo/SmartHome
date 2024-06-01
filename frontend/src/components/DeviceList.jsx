import React, {useEffect, useState} from "react";
import Accordion from '@mui/material/Accordion';
import AccordionActions from '@mui/material/AccordionActions';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import {useNavigate} from 'react-router-dom';

const deviceList = [
    {id: 1, name: 'Thermostat'},
    {id: 2, name: 'Light Bulb'},
    {id: 3, name: 'Tv'},
];

function DeviceList() {
    const [sensors, setDevices] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        // Simulate API call
        setDevices(deviceList);
    }, []);

    const handleAddDeviceOnClick = (deviceID) => {
        navigate(`/devices/${deviceID}`);
    };

    return (
        <div>
            {sensors.map((device) => (
                <Accordion key={device.id}>
                    <AccordionSummary
                        expandIcon={<ExpandMoreIcon/>}
                        aria-controls={`panel${device.id}-content`}
                        id={`panel${device.id}-header`}
                    >
                        <Typography>{device.id}</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        <Typography>
                            Here are some details about {device.name}. You can add more information about the device here.
                        </Typography>
                    </AccordionDetails>
                    <AccordionActions>
                        <Button size="small" color="primary" onClick={() => handleAddDeviceOnClick(device.id)}>
                            Configure Device
                        </Button>
                    </AccordionActions>
                </Accordion>
            ))}
        </div>
    );
}

export default DeviceList;