export const URL_API = 'http://localhost:8080';

export function fetchRoomsFromServer(success, failure) {
    fetch(`${URL_API}/rooms`)
        .then(res => {
            console.log("Response:", res); // Log the raw response
            return res.json();
        })
        .then(res => {
            console.log("Parsed JSON:", res); // Log the parsed JSON data
            success(res);
        })
        .catch(err => {
            console.log("Error:", err.message); // Log the error message
            failure(err.message);
        });
}

export function fetchRoomByIdFromServer(success, failure, id) {
    fetch(`${URL_API}/rooms/${id}`)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message))
    ;
}

export function fetchDevicesByRoomIdFromServer(success, failure, id) {
    fetch(`${URL_API}/devices?room_id=${id}`)
        .then(res => {
            console.log("Response:", res); // Log the raw response
            return res.json();
        })
        .then(res => {
            console.log("Parsed JSON:", res); // Log the parsed JSON data
            success(res._embedded.deviceDTOList);
        })
        .catch(err => {
            console.log("Error:", err.message); // Log the error message
            failure(err.message);
        });
}

export function fetchSensorModelsFromServer(success, failure) {
    fetch(`${URL_API}/sensorModels`)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message))
    ;
}

export function fetchActuatorModelsFromServer(success, failure) {
    fetch(`${URL_API}/actuatorModels`)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message))
    ;
}

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


