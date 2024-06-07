import React, {useContext, useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import {
    Box,
    Button,
    CircularProgress,
    Container,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField,
    Typography
} from '@mui/material';
import {toast, ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import {addDeviceToRoom, fetchDeviceTypes, fetchRoomById} from '../context/Actions.jsx';
import AppContext from '../context/AppContext.jsx';

function AddDeviceToRoomPage() {
    const {roomId} = useParams();
    const [deviceName, setDeviceName] = useState('');
    const [deviceType, setDeviceType] = useState('');
    const navigate = useNavigate();
    const {state, dispatch} = useContext(AppContext);

    useEffect(() => {
        fetchRoomById(dispatch, roomId);
        fetchDeviceTypes(dispatch);
    }, [roomId, dispatch]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!deviceName || !deviceType) {
            toast.error('Please fill in all fields.');
            return;
        }

        const device = {name: deviceName, type: deviceType};
        await addDeviceToRoom(dispatch, roomId, device);
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
            <ToastContainer/>
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
            </Box>
        </Container>
    );
}

export default AddDeviceToRoomPage;
