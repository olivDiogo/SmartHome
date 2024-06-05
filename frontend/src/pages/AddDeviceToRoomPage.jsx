import React, {useContext, useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import {
    Alert,
    Box,
    Button,
    CircularProgress,
    Container,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    Snackbar,
    TextField,
    Typography
} from '@mui/material';
import {addDeviceToRoom, fetchDeviceTypes, fetchRoomById} from '../context/Actions.jsx';
import AppContext from '../context/AppContext.jsx';

function AddDeviceToRoomPage() {
    const {roomId} = useParams();
    const [deviceName, setDeviceName] = useState('');
    const [deviceType, setDeviceType] = useState('');
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState('success');
    const navigate = useNavigate();
    const {state, dispatch} = useContext(AppContext);

    useEffect(() => {
        fetchRoomById(dispatch, roomId);
        fetchDeviceTypes(dispatch);
    }, [roomId, dispatch]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (deviceName && deviceType) {
            try {
                const device = {name: deviceName, type: deviceType};
                await addDeviceToRoom(dispatch, roomId, device);
                setSnackbarMessage('Device added successfully!');
                setSnackbarSeverity('success');
            } catch (error) {
                setSnackbarMessage(`Failed to add device: ${error.message}`);
                setSnackbarSeverity('error');
            }
        } else {
            setSnackbarMessage('Please fill in all fields.');
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

    const {room, deviceTypes} = state;

    if (room.loading || deviceTypes.loading) {
        return <CircularProgress/>;
    }

    if (room.error) {
        return <Alert severity="error">Error loading room: {room.error}</Alert>;
    }

    if (deviceTypes.error) {
        return <Alert severity="error">Error loading device types: {deviceTypes.error}</Alert>;
    }

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
                    Add New Device to {room.data?.name || 'Room'}
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
                            {deviceTypes.data.map((type, index) => (
                                <MenuItem key={index} value={type.description}>
                                    {type.description}
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
