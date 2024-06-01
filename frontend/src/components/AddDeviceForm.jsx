import React, {useState} from 'react';
import {
    Alert,
    Box,
    Button,
    Container,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    Snackbar,
    TextField,
    Typography
} from '@mui/material';
import {useNavigate} from 'react-router-dom';

const rooms = ["Living Room", "Bedroom", "Kitchen"]; // Example list of rooms

function AddDeviceForm() {
    const [deviceName, setDeviceName] = useState('');
    const [deviceType, setDeviceType] = useState('');
    const [room, setRoom] = useState('');
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState('success');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        // Logic to save the device
        if (deviceName && deviceType && room) {
            console.log({deviceName, deviceType, room});
            setSnackbarMessage('Device added successfully!');
            setSnackbarSeverity('success');
        } else {
            setSnackbarMessage('Failed to add device. Please fill in all fields.');
            setSnackbarSeverity('error');
        }
        setOpenSnackbar(true);
    };

    const handleCloseSnackbar = () => {
        setOpenSnackbar(false);
    };

    const handleBackToHome = () => {
        navigate('/');
    };

    return (
        <Container maxWidth="sm">
            <Box
                display="flex"
                flexDirection="column"
                alignItems="center"
                justifyContent="center"
                minHeight="100vh"
            >
                <Typography variant="h4" gutterBottom>
                    Add New Device
                </Typography>
                <form onSubmit={handleSubmit} style={{width: '100%'}}>
                    <TextField
                        label="Device Name"
                        fullWidth
                        margin="normal"
                        value={deviceName}
                        onChange={(e) => setDeviceName(e.target.value)}
                    />
                    <TextField
                        label="Device Type"
                        fullWidth
                        margin="normal"
                        value={deviceType}
                        onChange={(e) => setDeviceType(e.target.value)}
                    />
                    <FormControl fullWidth margin="normal">
                        <InputLabel>Room</InputLabel>
                        <Select
                            value={room}
                            onChange={(e) => setRoom(e.target.value)}
                        >
                            <MenuItem value="" disabled>
                                Select a room
                            </MenuItem>
                            {rooms.map((room, index) => (
                                <MenuItem key={index} value={room}>
                                    {room}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <Button type="submit" variant="contained" color="primary" fullWidth>
                        Add
                    </Button>
                </form>
                <Button
                    variant="contained"
                    color="secondary"
                    fullWidth
                    onClick={handleBackToHome}
                    style={{marginTop: '20px'}}
                >
                    Back to Home
                </Button>
                <Snackbar
                    open={openSnackbar}
                    autoHideDuration={6000}
                    onClose={handleCloseSnackbar}
                >
                    <Alert onClose={handleCloseSnackbar} severity={snackbarSeverity} sx={{width: '100%'}}>
                        {snackbarMessage}
                    </Alert>
                </Snackbar>
            </Box>
        </Container>
    );
}

export default AddDeviceForm;
