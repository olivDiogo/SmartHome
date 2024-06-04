export function configureWeatherService() {
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
}

export function fetchTemperatureFromWS(success, failure) {
    const now = new Date();
    const hour = now.getHours();

    fetch(`http://10.9.24.170:8080/InstantaneousTemperature?groupNumber=1&hour=${hour}`)
        .then((response) => response.json())
        .then((data) => {
            if (data && data.measurement !== "NaN" && data.unit === "C") {
                success(`${data.measurement}ºC`);
            } else {
                throw new Error(data.info || "Invalid API response structure");
            }
        })
        .catch((err) => {
            console.error("Error fetching temperature:", err);
            failure("Error fetching temperature");
        });
}

export function configureAndFetchTemperature(dispatch, fetchTemperature) {
    let timeoutId;
    let intervalId;

    const startFetching = () => {
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
    };

    configureWeatherService()
        .then(startFetching)
        .catch((err) => {
            console.error("Error configuring weather service:", err);
        });

    return {
        clearTimers: () => {
            if (timeoutId) clearTimeout(timeoutId);
            if (intervalId) clearInterval(intervalId);
        }
    };
}