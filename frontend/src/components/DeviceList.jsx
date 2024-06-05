import React, {useEffect, useContext, useState} from "react";
import Accordion from '@mui/material/Accordion';
import AccordionActions from '@mui/material/AccordionActions';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import {useNavigate} from 'react-router-dom';
import AppContext from "../context/AppContext.jsx";
import {fetchActuators, fetchDevicesByRoomId} from "../context/Actions.jsx";
import {updateCurrentDevice} from "../context/Actions.jsx";
import BlindRollerPosition from "./BlindRollerPosition.jsx";

function DeviceList() {
    const {state, dispatch} = useContext(AppContext);
    const {currentRoom, devices, currentDevice, actuators} = state;
    const {roomId} = currentRoom;
    const {loading, error, data} = devices;
    const navigate = useNavigate();

    useEffect(() => {
        fetchDevicesByRoomId(dispatch, roomId);
    }, [dispatch, roomId]);

    const fetchActuatorsForDevice = (deviceId) => {
        fetchActuators(dispatch, deviceId);
    };

    const handleAddDeviceOnClick = (deviceId) => {
        navigate(`/devices/${deviceId}`);
    };

    const handleViewLogsOnClick = (deviceId, deviceName) => {
        updateCurrentDevice(dispatch, deviceId, deviceName);
        navigate(`/logs/${deviceId}`);
    };

    if (loading) {
        return <h1>Loading ....</h1>;
    } else if (error) {
        return <h1>Error ....</h1>;
    } else if (data.length > 0) {
        return (
            <div>
                {data.map((device) => (
                    <Accordion key={device.deviceID}
                        onChange={() => fetchActuatorsForDevice(device.deviceID)}
                        >
                        <AccordionSummary
                            expandIcon={<ExpandMoreIcon/>}
                            aria-controls={`panel${device.deviceID}-content`}
                            id={`panel${device.deviceID}-header`}
                        >
                            <Typography>{device.deviceName}</Typography>
                        </AccordionSummary>
                        <AccordionDetails>
                            <Typography component="div">
                                Here are some details about {device.deviceName}. You can add more information about the
                                device here.
                                {device.deviceName === 'BlindRoller' &&
                                    <BlindRollerPosition deviceId={device.deviceID}/>}
                            </Typography>
                        </AccordionDetails>
                        <AccordionActions>
                            <Button size="small" color="primary"
                                    onClick={() => handleViewLogsOnClick(device.deviceID, device.deviceName)}>
                                View Logs
                            </Button>
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
    return null;
}

export default DeviceList;
