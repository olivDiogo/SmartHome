import React, {useState, useEffect} from "react";
import {Card, CardContent, Typography} from "@material-ui/core";
import Box from "@mui/material/Box";


function SunriseSunsetWidget() {
    const [sunrise, setSunrise] = useState("");
    const [sunset, setSunset] = useState("");

    useEffect(() => {
        fetch("https://api.sunrise-sunset.org/json?lat=41.1784964&lng=-8.6089491")
            .then((response) => response.json())
            .then((data) => {
                setSunrise(data.results.sunrise);
                setSunset(data.results.sunset);
            });
    }, []);

    return (
        <Box>
            <Card style={{backgroundColor: "lightgray",  border: '1px solid black', borderRadius: '10px', display: 'flex', flexDirection: 'column',
                alignItems: 'center', justifyContent: 'center', height: 100}}>
                <CardContent>
                    <Typography variant="h6" component="h3" >
                        Sunrise: {sunrise}
                    </Typography>
                    <Typography variant="h6" component="h5" >
                        Sunset: {sunset}
                    </Typography>
                </CardContent>
            </Card>
        </Box>
    )
}

export default SunriseSunsetWidget;