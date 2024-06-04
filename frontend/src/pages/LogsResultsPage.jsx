import React, { useContext, useEffect } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import { CircularProgress, Typography, List, ListItem, ListItemText } from '@mui/material';
import AppContext from "../context/AppContext.jsx";
import { fetchLogsByDeviceId } from '../context/Actions.jsx';

function useQuery() {
    return new URLSearchParams(useLocation().search);
}

function LogsResultsPage() {
    const { deviceId } = useParams();
    const { state, dispatch } = useContext(AppContext);
    const query = useQuery();
    const startPeriod = query.get('start');
    const endPeriod = query.get('end');

    useEffect(() => {
        if (deviceId && startPeriod && endPeriod) {
            fetchLogsByDeviceId(dispatch, deviceId, startPeriod, endPeriod);
        }
    }, [dispatch, deviceId, startPeriod, endPeriod]);

    const { logs } = state;
    const { loading, error, data } = logs;

    if (loading) {
        return <CircularProgress />;
    }

    if (error) {
        return <Typography color="error">{error}</Typography>;
    }

    return (
        <div className="logs-results-page">
            <h1>Logs for Device</h1>
            <List>
                {data && data.length > 0 ? (
                    data.map((log) => (
                        <ListItem key={log.id}>
                            <ListItemText
                                primary={`Name: ${log.sensorTypeID}, Reading: ${log.reading}, Unit: ${log.unitID}`}
                                secondary={`Date: ${log.timestamp}`}
                            />
                        </ListItem>
                    ))
                ) : (
                    <Typography>No readings yet</Typography>
                )}
            </List>
        </div>
    );
}

export default LogsResultsPage;
