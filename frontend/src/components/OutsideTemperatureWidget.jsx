import React, {useEffect, useContext} from "react";
import {Card, CardContent, Typography} from "@material-ui/core";
import Box from "@mui/material/Box";
import {configureWeatherService} from "../services/Service";
import {fetchTemperature} from "../context/Actions.jsx";
import AppContext from "../context/AppContext.jsx";


function OutsideTemperatureWidget() {
    const {state, dispatch} = useContext(AppContext);
    const {temperature} = state;
    const {loading, error, data} = temperature;

    useEffect(() => {
        let timeoutId;
        let intervalId;

        const configureAndFetchTemperature = async () => {
            try {
                await configureWeatherService();
                fetchTemperature(dispatch);

                const now = new Date();
                const minutes = now.getMinutes();
                const seconds = now.getSeconds();
                const milliseconds = now.getMilliseconds();

                // Calculate delay until the next 15-minute mark
                const minutesToNextQuarterHour = 15 - (minutes % 15);
                let delay =
                    minutesToNextQuarterHour * 60 * 1000 -
                    seconds * 1000 -
                    milliseconds;

                console.log("Current time:", now);
                console.log("Delay until next 15-minute mark:", delay);

                // Set timeout to start fetching at the next 15-minute mark
                timeoutId = setTimeout(() => {
                    fetchTemperature(dispatch);

                    // Set interval to fetch every 15 minutes
                    intervalId = setInterval(() => {
                        console.log("Fetching temperature at:", new Date());
                        fetchTemperature(dispatch);
                    }, 15 * 60 * 1000);
                }, delay);
            } catch (err) {
                console.error("Error configuring weather service:", err);
            }
        };

        configureAndFetchTemperature();

        // Cleanup function to clear the timeout and interval
        return () => {
            if (timeoutId) clearTimeout(timeoutId);
            if (intervalId) clearInterval(intervalId);
        };
    }, [dispatch]);

    if (loading === true) {
        return <h1>Loading ....</h1>;
    } else if (error !== null) {
        return <h1>Error ....</h1>;
    } else if (data) {
        return (
            <Box>
                <Card
                    style={{
                        backgroundColor: "lightgray",
                        border: "1px solid black",
                        borderRadius: "10px",
                        display: "flex",
                        flexDirection: "column",
                        alignItems: "center",
                        justifyContent: "center",
                        height: 100,
                    }}
                >
                    <CardContent>
                        <Typography variant="h6" component="h2">
                            Outside temperature: {data}
                        </Typography>
                    </CardContent>
                </Card>
            </Box>
        );
    } else {
        return null; // Render nothing if data is not available
    }
}

export default OutsideTemperatureWidget;

