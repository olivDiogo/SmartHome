import React, { useEffect, useContext } from 'react';
import { Card, CardContent, Typography, Box } from '@material-ui/core';
import AppContext from '../context/AppContext.jsx';
import { fetchCurrentPosition } from '../context/Actions.jsx';

const BlindRollerPosition = ({ deviceId }) => {
    const { state, dispatch } = useContext(AppContext);
    const { position, actuators} = state;
    const { loading, error, data } = position;

    const actuator = actuators.data.find(act => act.deviceID === deviceId);

    useEffect(() => {
        fetchCurrentPosition(dispatch, deviceId);
    }, [dispatch, deviceId]);

    const renderContent = () => {
        if (loading) {
            return <Typography variant="h6">Loading...</Typography>;
        }
        if (error) {
            return <Typography variant="h6">Error: {error}</Typography>;
        }
        if (data) {
            return <Typography variant="h6">Current Blind Roller position: {data}</Typography>;
        }
        return <Typography variant="h6">No data available</Typography>;
    };

    return (
        <Box sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            height: 100,
            m: 2,  // margin
            bgcolor: 'background.paper',
            borderRadius: '10px',
            boxShadow: 1  // material UI elevation shadow
        }}>
            <Card>
                <CardContent>
                    {renderContent()}
                </CardContent>
            </Card>
        </Box>
    );
};

export default BlindRollerPosition;
