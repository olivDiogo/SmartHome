import React, {useState} from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';

const ConfigureDevice = ({ sensorTypeID, deviceID, selectedSensorModel, selectedType }) => {
    const [sensorName, setSensorName] = useState('');
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const [latitude, setLatitude] = useState('');
    const [longitude, setLongitude] = useState('');
    const [modalOpen, setModalOpen] = useState(false);
    const [modalTitle, setModalTitle] = useState('');
    const [modalMessage, setModalMessage] = useState('');


    const handleSensorNameChange = (event) => {
        setSensorName(event.target.value);
    };

    const handleDateTimeChange = (newValue, type) => {
        if (type === 'start') {
            setStartDate(newValue);
        } else {
            setEndDate(newValue);
        }
    };

    const handleLocationChange = (event) => {
        if (event.target.name === 'latitude') {
            setLatitude(event.target.value);
        } else {
            setLongitude(event.target.value);
        }
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        const deviceConfiguration = {
            type: selectedSensorModel.selectedType,
            deviceID,
            sensorModelPath: selectedSensorModel.selectedSensorModel,
            sensorName,
            sensorTypeID,
        };

        if (selectedSensorModel.selectedType === 'dateSensor') {
            if (!startDate || !endDate) {
                showModal('Error', 'Please select both start and end date/time.');
                return;
            }
            deviceConfiguration.startDate = startDate.toISOString();
            deviceConfiguration.endDate = endDate.toISOString();
        } else if (selectedSensorModel.selectedType === 'gpsSensor') {
            if (!latitude || !longitude) {
                showModal('Error', 'Please enter both latitude and longitude.');
                return;
            }
            deviceConfiguration.latitude = latitude;
            deviceConfiguration.longitude = longitude;
        }

        sendDeviceConfiguration(deviceConfiguration);
    };

    const showModal = (title, message) => {
        setModalTitle(title);
        setModalMessage(message);
        setModalOpen(true);
    };

    const sendDeviceConfiguration = (deviceConfiguration) => {
        fetch('http://localhost:8080/sensors', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(deviceConfiguration),
        })
            .then(response => response.json())
            .then(data => {
                showModal("Success", "Functionalities added successfully");
            })
            .catch((error) => {
                console.error('Error:', error);
                showModal("Error", "Failed to add functionalities");
            });
    };

    return (
        <Box sx={{ padding: 2 }}>
            <Typography variant="h6">Configure Device</Typography>
            <form onSubmit={handleSubmit}>
                <TextField
                    fullWidth
                    label="Sensor Name"
                    value={sensorName}
                    onChange={handleSensorNameChange}
                    margin="normal"
                    required
                    error={!sensorName}
                    helperText="Please enter a sensor name."
                />

                {selectedType === 'dateSensor' && (
                    <>
                        <LocalizationProvider dateAdapter={AdapterDateFns}>
                            <DateTimePicker
                                label="Start Date and Time"
                                value={startDate}
                                onChange={(newValue) => handleDateTimeChange(newValue, 'start')}
                                renderInput={(props) => <TextField {...props} />}
                                margin="normal"
                            />
                            <DateTimePicker
                                label="End Date and Time"
                                value={endDate}
                                onChange={(newValue) => handleDateTimeChange(newValue, 'end')}
                                renderInput={(props) => <TextField {...props} />}
                                margin="normal"
                            />
                        </LocalizationProvider>
                    </>
                )}

                {selectedType === 'gpsSensor' && (
                    <>
                        <TextField
                            fullWidth
                            label="Latitude"
                            value={latitude}
                            onChange={handleLocationChange}
                            margin="normal"
                            name="latitude"
                        />
                        <TextField
                            fullWidth
                            label="Longitude"
                            value={longitude}
                            onChange={handleLocationChange}
                            margin="normal"
                            name="longitude"
                        />
                    </>
                )}

                <Button variant="contained" color="primary" type="submit">
                    Submit
                </Button>
            </form>

            <Modal open={modalOpen} onClose={() => setModalOpen(false)}>
                <Box sx={{
                    padding: 2,
                    backgroundColor: 'white',
                    margin: 'auto',
                    top: '50%',
                    transform: 'translateY(-50%)'
                }}>
                    <Typography variant="h6">{modalTitle}</Typography>
                    <Typography>{modalMessage}</Typography>
                </Box>
            </Modal>
        </Box>
    );
};

export default ConfigureDevice;