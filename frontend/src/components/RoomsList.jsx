import React, { useContext, useEffect } from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionActions from '@mui/material/AccordionActions';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';
import AppContext from '../context/AppContext.jsx';
import { fetchRooms } from '../context/Actions.jsx';

function RoomsList() {
    const { state, dispatch } = useContext(AppContext);
    const { rooms } = state;
    const { loading, error, data } = rooms;
    const navigate = useNavigate();

    useEffect(() => {
        fetchRooms(dispatch);
    }, [dispatch]);

    const handleAddDeviceClick = (roomId) => {
        navigate(`/rooms/${roomId}`);
    };

    const handleOpenDevicesClick = (roomId) => {
        navigate(`/rooms/${roomId}/devices`);
    };

    if (loading === true) {
        return <h1>Loading ....</h1>;
    } else {
        if (error !== null) {
            return <h1>Error ....</h1>;
        } else {
            if (data.length > 0) {
                return (
                    <div>
                        {data.map((room) => (
                            <Accordion key={room.roomId} sx={{ fontFamily: 'Roboto, sans-serif', marginBottom: 2 }}>
                                <AccordionSummary
                                    expandIcon={<ExpandMoreIcon />}
                                    aria-controls={`panel${room.roomId}-content`}
                                    id={`panel${room.roomId}-header`}
                                >
                                    <Typography sx={{ fontFamily: 'Roboto, sans-serif' }}>
                                        {room.roomName}
                                    </Typography>
                                </AccordionSummary>
                                <AccordionDetails>
                                    <Typography sx={{ fontFamily: 'Roboto, sans-serif' }}>
                                        Floor: {room.floor}
                                    </Typography>
                                </AccordionDetails>
                                <AccordionActions>
                                    <Button
                                        size="small"
                                        color="primary"
                                        onClick={() => handleOpenDevicesClick(room.roomId)}
                                    >
                                        Open Devices
                                    </Button>
                                    <Button
                                        size="small"
                                        color="primary"
                                        onClick={() => handleAddDeviceClick(room.roomId)}
                                    >
                                        Add Device
                                    </Button>
                                </AccordionActions>
                            </Accordion>
                        ))}
                    </div>
                );
            } else {
                return <h1>No data ....</h1>;
            }
        }
    }
}

export default RoomsList;
