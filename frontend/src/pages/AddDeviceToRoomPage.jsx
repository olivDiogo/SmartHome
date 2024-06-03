import React, {useContext, useEffect, useRef, useState} from 'react';
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
import {addDeviceToRoom, fetchRoomById} from '../context/Actions.jsx';
import AppContext from '../context/AppContext.jsx';

const deviceTypes = ["PowerMeter", "PowerSource", "TemperatureGarden", "TemperatureBedroom", "AutomaticBlinds", "LightSensor", "GeneralDevice"]; // Example device types

function AddDeviceToRoomPage() {
    const {roomId} = useParams();
    const [deviceName, setDeviceName] = useState('');
    const [deviceType, setDeviceType] = useState('');
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState('success');
    const navigate = useNavigate();
    const {dispatch} = useContext(AppContext);

    // Ref para o elemento de áudio
    const audioRef = useRef(null);

    useEffect(() => {
        fetchRoomById(dispatch, roomId);
    }, [roomId, dispatch]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (deviceName && deviceType) {
            try {
                const device = {name: deviceName, type: deviceType};
                await addDeviceToRoom(dispatch, roomId, device);
                setSnackbarMessage('Device added successfully!');
                setSnackbarSeverity('success');
                console.log("Device added successfully, attempting to play audio...");
                if (audioRef.current) {
                    audioRef.current.play()
                        .then(() => {
                            console.log("Audio played successfully.");
                        })
                        .catch((error) => {
                            console.error("Error playing audio:", error);
                        });
                }
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
                {/* Elemento de áudio oculto para reprodução de som */}
                <audio ref={audioRef} src="/sounds/SuccessSpeech.mp3" preload="auto"
                       onCanPlayThrough={() => console.log("Audio is ready to play")}/>
            </Box>
        </Container>
    );
}

export default AddDeviceToRoomPage;
