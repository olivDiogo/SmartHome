export const URL_API = 'http://localhost:8080';

export function fetchRoomsFromServer(success, failure) {
    fetch(`${URL_API}/rooms`)
        .then(res => {
            if (!res.ok) {
                throw new Error(`Network response was not ok: ${res.statusText}`);
            }
            return res.json();
        })
        .then(res => success(res))
        .catch(err => failure(err.message));
}


export function fetchRoomByIdFromServer(success, failure, id) {
    fetch(`${URL_API}/rooms/${id}`)
        .then(res => {
            if (!res.ok) {
                throw new Error('Network response was not ok');
            }
            return res.json();
        })
        .then(res => success(res))
        .catch(err => failure(err.message));
}

export function fetchDevicesByRoomIdFromServer(success, failure, id) {
    fetch(`${URL_API}/devices?room_id=${id}`)
        .then(res => res.json())
        .then(res => success(res._embedded.deviceDTOList))
        .catch(err => failure(err.message));
}

export function fetchActuatorModelsFromServer(success, failure) {
    fetch(`${URL_API}/actuatorModels`)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}

export function addDeviceToRoom(roomId, device, success, failure) {
    fetch(`${URL_API}/devices`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            roomID: roomId,
            deviceTypeDescription: device.type,
            deviceName: device.name
        }),
    })
        .then(res => {
            if (!res.ok) {
                throw new Error('Network response was not ok');
            }
            return res.json();
        })
        .then(data => success(data))
        .catch(err => failure(err.message));
}

export function fetchLogsByDeviceIdFromServer(success, failure, deviceId, timeStart, timeEnd) {
    const url = `${URL_API}/logs?deviceID=${deviceId}&timeStart=${timeStart}&timeEnd=${timeEnd}`;
    console.log("Requesting URL:", url);
    fetch(url)
        .then(res => {
            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }
            return res.json();
        })
        .then(data => {
            success(data);
            console.log(data);
        })
        .catch(err => {
            failure(err.message);
            console.error("Fetch error:", err.message);
        });
}

export function updateCurrentDeviceFromServer(success, failure, deviceId, deviceName) {
    fetch(`${URL_API}/devices/${deviceId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({name: deviceName})
    })
        .then(res => {
            if (!res.ok) {
                throw new Error(`HTTP error! status: ${res.status}`);
            }
            return res.json();
        })
        .then(data => {
            success(data);
            console.log(data);
        })
        .catch(err => {
            failure(err.message);
            console.error("Fetch error:", err.message);
        });
}

export function fetchSensorTypesFromServer(success, failure) {
    fetch(`${URL_API}/sensor-types`)
        .then(res => res.json())
        .then(res => {
            console.log('Fetched sensor types:', res);
            if (res._embedded && res._embedded.sensorTypeDTOList) {
                success(res._embedded.sensorTypeDTOList);
            } else {
                throw new Error('Invalid response structure');
            }
        })
        .catch(err => failure(err.message));
}

export function fetchSensorModelsBySensorTypeIdFromServer(success, failure, sensorTypeID) {
    fetch(`${URL_API}/sensor-models?sensorTypeID=${sensorTypeID}`)
        .then(res => res.json())
        .then(res => {
            console.log(`Fetched sensor models for sensorTypeID ${sensorTypeID}:`, res);
            if (res._embedded && res._embedded.sensorModelDTOList) {
                success(res._embedded.sensorModelDTOList);
            } else {
                throw new Error('Invalid response structure');
            }
        })
        .catch(err => failure(err.message));
}

export function fetchCurrentPositionValue(success, failure, deviceID) {
    fetch(`${URL_API}/logs/get-position-blindRoller?deviceID=${deviceID}`)
        .then(res => res.json())
        .then(res => {
            if (res.status === 404) {
                success('No logs found');
            } else {
                console.log("Success:", res);
                success(res);
            }
        })
        .catch(err => {
            failure(err.message)
        });
}

export function fetchActuatorsByDeviceId(success, failure, deviceId) {
    fetch(`${URL_API}/actuators?deviceID=${deviceId}`)
        .then(res => {
            if (!res.ok) {
                throw new Error('Failed to fetch actuators');
            }
            return res.json();
        })
        .then(data => {
            console.log('Data received:', data);
            success(data);
        })
        .catch(err => failure(err.message));
}

export function setBlindRollerValue(success, failure, deviceID, actuatorID, value) {
    fetch(`${URL_API}/actuators/set-blindRoller`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            deviceID: deviceID,
            actuatorID: actuatorID,
            value: value
        }),
    })
        .then(res => {
            if (!res.ok) {
                throw new Error('Network response was not ok');
            }
            return res.json();
        })
        .then(data => success(data))
        .catch(err => failure(err.message));
}

export function fetchDeviceTypesFromServer(success, failure) {
    fetch(`${URL_API}/device-types`)
        .then(res => res.json())
        .then(res => {
            console.log('Fetched device types:', res);
            if (res._embedded && res._embedded.deviceTypeDTOList) {
                success(res._embedded.deviceTypeDTOList);
            } else {
                throw new Error('Invalid response structure');
            }
        })
        .catch(err => failure(err.message));
}
