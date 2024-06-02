import React, {useEffect, useContext} from "react";
import Accordion from '@mui/material/Accordion';
import AccordionActions from '@mui/material/AccordionActions';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import {useNavigate} from 'react-router-dom';
import AppContext from "../context/AppContext.jsx";
import {fetchDevicesByRoomId} from "../context/Actions.jsx";


function DeviceList() {
    const {state, dispatch} = useContext(AppContext);
    const {currentRoom} = state;
    const roomId = currentRoom.roomId;

    const {devices} = state;
    const {loading, error, data} = devices;

    const navigate = useNavigate();

    useEffect(() => {
        fetchDevicesByRoomId(dispatch, roomId);
    }, [dispatch, roomId]);

    const handleAddDeviceOnClick = (deviceId) => {
        navigate(`/devices/${deviceId}`);
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
                        {data.map((device) => (
                            <Accordion key={device.deviceID}>
                                <AccordionSummary
                                    expandIcon={<ExpandMoreIcon/>}
                                    aria-controls={`panel${device.deviceID}-content`}
                                    id={`panel${device.deviceID}-header`}
                                >
                                    <Typography>{device.deviceName}</Typography>
                                </AccordionSummary>
                                <AccordionDetails>
                                    <Typography>
                                        Here are some details about {device.deviceName}. You can add more information about
                                        the device here.
                                    </Typography>
                                </AccordionDetails>
                                <AccordionActions>
                                    <Button size="small" color="primary"
                                            onClick={() => handleAddDeviceOnClick(device.deviceID)}>
                                        Configure Device
                                    </Button>
                                </AccordionActions>
                            </Accordion>
                        ))}
                    </div>
                );
            }
        }
    }
}

export default DeviceList;