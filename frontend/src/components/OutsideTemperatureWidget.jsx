import React, {useState, useEffect} from "react";
import {Card, CardContent, Typography} from "@material-ui/core";
import Box from "@mui/material/Box";

function OutsideTemperatureWidget() {
    const [temperature, setTemperature] = useState(""); // Set to empty initially
    const [error, setError] = useState(null); // State to track errors

    const configureWeatherService = () => {
        return fetch("http://10.9.24.170:8080/WeatherServiceConfiguration", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                "groupNumber": 1,
                "latitude": 40.00,
                "longitude": 80.00
            })
        }).then(response => {
            if (!response.ok) {
                throw new Error("Weather service configuration failed");
            }
            return response.json();
        });
    };

    const fetchTemperature = () => {
        const now = new Date();
        const hour = now.getHours();

        fetch(`http://10.9.24.170:8080/InstantaneousTemperature?groupNumber=1&hour=${hour}`)
            .then((response) => response.json())
            .then((data) => {
                if (data && data.measurement !== "NaN" && data.unit === "C") {
                    setTemperature(`${data.measurement}ºC`);
                } else {
                    throw new Error(data.info || "Invalid API response structure");
                }
            })
            .catch((err) => {
                console.error("Error fetching temperature:", err);
                setError("Error fetching temperature");
            });
    };

    useEffect(() => {
        let timeoutId;
        let intervalId;

        configureWeatherService()
            .then(() => {
                // Fetch the temperature immediately
                fetchTemperature();

                // Get the current date and time
                const now = new Date();

                // Calculate how many milliseconds have passed since the last 15-minute mark
                const delay = (15 - (now.getMinutes() % 15)) * 60 * 1000 - now.getSeconds() * 1000 - now.getMilliseconds();

                // Set timeout to start the interval at the next 15-minute mark
                timeoutId = setTimeout(() => {
                    fetchTemperature();
                    intervalId = setInterval(fetchTemperature, 15 * 60 * 1000);
                }, delay);
            })
            .catch((err) => {
                console.error("Error configuring weather service:", err);
                setError("Error configuring weather service");
            });

        // Cleanup function to clear the timeout and interval
        return () => {
            if (timeoutId) clearTimeout(timeoutId);
            if (intervalId) clearInterval(intervalId);
        };
    }, []);

    return (
        <Box>
            <Card style={{
                backgroundColor: 'lightgray',
                border: '1px solid black',
                borderRadius: '10px',
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                justifyContent: 'center',
                height: 100
            }}>
                <CardContent>
                    <Typography variant="h6" component="h2">
                        Outside temperature: {error ? error : temperature}
                    </Typography>
                </CardContent>
            </Card>
        </Box>
    );
}

export default OutsideTemperatureWidget;
