import React, { useState } from "react";
import SensorsList from "./SensorsList.jsx";
import ActuatorsList from "./ActuatorsList.jsx";
import Accordion from "@mui/material/Accordion";
import AccordionSummary from "@mui/material/AccordionSummary";
import { Typography } from "@material-ui/core";
import AccordionDetails from "@mui/material/AccordionDetails";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Button from '@mui/material/Button';

function ConfigureDevice() {
    const [selectedSensor, setSelectedSensor] = useState('');
    const [selectedActuator, setSelectedActuator] = useState('');
    const [modalMessage, setModalMessage] = useState(null);
    const [modalTitle, setModalTitle] = useState('');
    const [open, setOpen] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();

        if (selectedSensor === '' && selectedActuator === '') {
            setModalTitle("Error");
            setModalMessage("Both sensor and actuator cannot be 'None'");
            setOpen(true);
            return;
        }

        const deviceConfiguration = {
            sensor: selectedSensor,
            actuator: selectedActuator
        };

        // Send deviceConfiguration to your API

        // Success message
        setModalTitle("Success");
        setModalMessage("Functionalities added successfully");
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    return (
        <form onSubmit={handleSubmit}>
            <Accordion>
                <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                    <Typography>Sensors List</Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <SensorsList selectedSensor={selectedSensor} setSelectedSensor={setSelectedSensor} />
                </AccordionDetails>
            </Accordion>

            <Accordion>
                <AccordionSummary expandIcon={<ExpandMoreIcon />}>
                    <Typography>Actuators List</Typography>
                </AccordionSummary>
                <AccordionDetails>
                    <ActuatorsList selectedActuator={selectedActuator} setSelectedActuator={setSelectedActuator} />
                </AccordionDetails>
            </Accordion>
            <button type="submit">Add Functionalities</button>

            <Dialog open={open} onClose={handleClose}>
                <DialogTitle>{modalTitle}</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        {modalMessage}
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary" autoFocus>
                        OK
                    </Button>
                </DialogActions>
            </Dialog>
        </form>
    );
}

export default ConfigureDevice;
