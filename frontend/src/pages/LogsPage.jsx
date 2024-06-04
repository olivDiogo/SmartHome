import React, { useContext, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { TextField, Button, Typography } from '@mui/material';
import AppContext from "../context/AppContext.jsx";

function LogsPage() {
    const { deviceId } = useParams();
    const { state } = useContext(AppContext);
    const { currentDevice } = state;
    const { deviceName } = currentDevice;
    const [startPeriod, setStartPeriod] = useState("2024-06-04T00:00");
    const [endPeriod, setEndPeriod] = useState("2024-06-04T23:59");

    const navigate = useNavigate();

    const handleSubmit = (event) => {
        event.preventDefault();
        navigate(`/logs/${deviceId}/results?start=${startPeriod}&end=${endPeriod}`);
    };

    return (
        <div className="logs-page">
            <h1>Please choose the time period:{deviceName}</h1>
            <form onSubmit={handleSubmit}>
                <TextField
                    label="Start Time"
                    type="datetime-local"
                    value={startPeriod}
                    onChange={(e) => setStartPeriod(e.target.value)}
                    InputLabelProps={{ shrink: true }}
                    required
                />
                <TextField
                    label="End Time"
                    type="datetime-local"
                    value={endPeriod}
                    onChange={(e) => setEndPeriod(e.target.value)}
                    InputLabelProps={{ shrink: true }}
                    required
                />
                <Button type="submit" variant="contained" color="primary">Fetch Logs</Button>
            </form>
        </div>
    );
}

export default LogsPage;
