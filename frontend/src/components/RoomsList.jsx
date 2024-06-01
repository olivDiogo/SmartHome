import React, {useEffect, useState} from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionActions from '@mui/material/AccordionActions';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import {useNavigate} from 'react-router-dom';

const simulatedRooms = [
    {roomId: '1', name: 'Living Room'},
    {roomId: '2', name: 'Bedroom'},
    {roomId: '3', name: 'Kitchen'},
    {roomId: '4', name: 'Bathroom'},
    {roomId: '5', name: 'Garage'},
    {roomId: '6', name: 'Office'},
    {roomId: '7', name: 'Basement'},
    {roomId: '8', name: 'Attic'},
    {roomId: '9', name: 'Patio'},
    {roomId: '10', name: 'Backyard'}
];

function RoomsList() {
    const [rooms, setRooms] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        // Simulate API call
        setRooms(simulatedRooms);
    }, []);

    const handleAddDeviceClick = (roomId) => {
        navigate(`/rooms/${roomId}`);
    };

    const handleOpenDevicesClick = (roomId) => {
        navigate(`/rooms/${roomId}/devices`);
    };

    return (
        <div>
            {rooms.map((room) => (
                <Accordion key={room.roomId}>
                    <AccordionSummary
                        expandIcon={<ExpandMoreIcon/>}
                        aria-controls={`panel${room.roomId}-content`}
                        id={`panel${room.roomId}-header`}
                    >
                        <Typography>{room.name}</Typography>
                    </AccordionSummary>
                    <AccordionDetails>
                        <Typography>
                            Here are some details about {room.name}. You can add more information about the room here.
                        </Typography>
                    </AccordionDetails>
                    <AccordionActions>
                        <Button size="small" color="primary" onClick={() => handleOpenDevicesClick(room.roomId)}>
                            Open Devices
                            </Button>
                        <Button size="small" color="primary" onClick={() => handleAddDeviceClick(room.roomId)}>
                            Add Device
                        </Button>
                    </AccordionActions>
                </Accordion>
            ))}
        </div>
    );
}

export default RoomsList;