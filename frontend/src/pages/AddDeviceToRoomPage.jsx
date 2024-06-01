import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
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

const deviceTypes = ["Fridge", "Blind", "Light"]; // Example device types

function AddDeviceToRoomPage() {
    const {roomId} = useParams();
    const [room, setRoom] = useState(null);
    const [deviceName, setDeviceName] = useState('');
    const [deviceType, setDeviceType] = useState('');
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState('success');
    const navigate = useNavigate();

    useEffect(() => {
        // Simulate API call
        const selectedRoom = simulatedRooms.find(room => room.roomId === roomId);
        setRoom(selectedRoom);
    }, [roomId]);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (deviceName && deviceType) {
            console.log({deviceName, deviceType, roomId});
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

    if (!room) return <div>Loading...</div>;

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
                    {room.name}
                </Typography>
                <form onSubmit={handleSubmit} style={{width: '100%'}}>
                    <TextField
                        label="Device Name"
                        fullWidth
                        margin="normal"
                        value={deviceName}
                        onChange={(e) => setDeviceName(e.target.value)}
                    />
                    <FormControl fullWidth margin="normal">
                        <InputLabel>Device Type</InputLabel>
                        <Select
                            value={deviceType}
                            onChange={(e) => setDeviceType(e.target.value)}
                        >
                            <MenuItem value="" disabled>
                                Select a device type
                            </MenuItem>
                            {deviceTypes.map((type, index) => (
                                <MenuItem key={index} value={type}>
                                    {type}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <Button type="submit" variant="contained" color="primary" fullWidth>
                        Add Device
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

export default AddDeviceToRoomPage;
