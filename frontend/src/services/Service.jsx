export const URL_API = 'http://localhost:8080';

export function fetchRoomsFromServer(success, failure) {
    fetch(`${URL_API}/rooms`)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}

export function fetchRoomByIdFromServer(id) {
    return fetch(`${URL_API}/rooms/${id}`)
        .then(res => {
            if (!res.ok) {
                throw new Error('Network response was not ok');
            }
            return res.json();
        })
        .catch(err => {
            throw new Error(`Fetching room failed: ${err.message}`);
        });
}

export function fetchDevicesByRoomIdFromServer(success, failure, id) {
    fetch(`${URL_API}/devices?room_id=${id}`)
        .then(res => res.json())
        .then(res => success(res._embedded.deviceDTOList))
        .catch(err => failure(err.message));
}

export function fetchSensorModelsFromServer(success, failure) {
    fetch(`${URL_API}/sensorModels`)
        .then(res => res.json())
        .then(res => success(res))
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
        body: JSON.stringify({ name: deviceName })
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
