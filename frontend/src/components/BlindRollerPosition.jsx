import React, { useEffect, useContext } from 'react';
import { Card, CardContent, Typography, Box, CircularProgress } from '@material-ui/core';
import AppContext from '../context/AppContext.jsx';
import { fetchCurrentPosition } from '../context/Actions.jsx';
import './BlindRollerPosition.css'; // Import the CSS file for additional styling

const BlindRollerPosition = ({ deviceId }) => {
    const { state, dispatch } = useContext(AppContext);
    const { position, actuators } = state;
    const { loading, error, data } = position;

    const actuator = actuators.data.find(act => act.deviceID === deviceId);

    useEffect(() => {
        fetchCurrentPosition(dispatch, deviceId);
    }, [dispatch, deviceId]);

    const renderContent = () => {
        if (loading) {
            return <CircularProgress />;
        }
        if (error) {
            return <Typography variant="h6">Error: {error}</Typography>;
        }
        if (data) {
            return (
                <Box className="digital-box">
                    <Typography variant="h6" className="digital-label">Current Blind Roller Position:</Typography>
                    <Typography variant="h4" className="digital-number">{data}</Typography>
                </Box>
            );
        }
        return <Typography variant="h6">No data available</Typography>;
    };

    return (
        <Box sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            padding: 2,
            bgcolor: 'background.paper',
            borderRadius: '10px',
            boxShadow: 3,
            maxWidth: 400,
            margin: 'auto',
            mt: 2,  // margin top
        }}>
            <Card sx={{ width: '100%', boxShadow: 3 }}>
                <CardContent>
                    {renderContent()}
                </CardContent>
            </Card>
        </Box>
    );
};

export default BlindRollerPosition;
