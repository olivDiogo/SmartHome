import {
    addDeviceToRoom as addDeviceToRoomService,
    fetchActuatorModelsFromServer,
    fetchDevicesByRoomIdFromServer,
    fetchRoomByIdFromServer,
    fetchRoomsFromServer,
    fetchSensorModelsFromServer,
} from "../services/Service.jsx";


export const FETCH_ROOMS_STARTED = 'FETCH_ROOMS_STARTED';
export const FETCH_ROOMS_SUCCESS = 'FETCH_ROOMS_SUCCESS';
export const FETCH_ROOMS_FAILURE = 'FETCH_ROOMS_FAILURE';
export const ADD_DEVICE_STARTED = 'ADD_DEVICE_STARTED';
export const ADD_DEVICE_SUCCESS = 'ADD_DEVICE_SUCCESS';
export const ADD_DEVICE_FAILURE = 'ADD_DEVICE_FAILURE';

export function fetchRooms(dispatch) {
    const action = {
        type: FETCH_ROOMS_STARTED
    }
    dispatch(action);

    const success = (res) => {
        const action = fetchRoomsSuccess(res);
        dispatch(action);
    };

    const failure = (err) => {
        const action = fetchRoomsFailure(err.message);
        dispatch(action);
    };

    fetchRoomsFromServer(success, failure);
}

function fetchRoomsSuccess(rooms) {
    const roomList = rooms._embedded?.roomDTOList || [];
    return {
        type: FETCH_ROOMS_SUCCESS,
        payload: {
            data: roomList
        }

    }
}

function fetchRoomsFailure(message) {
    return {
        type: FETCH_ROOMS_FAILURE,
        payload: {
            error: message
        }
    }

}


export const SET_CURRENT_ROOM = 'SET_CURRENT_ROOM';

export function setCurrentRoom(dispatch, roomId, roomName) {
    const action = {
        type: SET_CURRENT_ROOM,
        payload: {
            roomId: roomId,
            roomName: roomName
        }
    }
    dispatch(action);
}


// ------------------------------ TO BE COMPLETED ------------------------------//

export const FETCH_ROOM_BY_ID_STARTED = 'FETCH_ROOM_BY_ID_STARTED';
export const FETCH_ROOM_BY_ID_SUCCESS = 'FETCH_ROOM_BY_ID_SUCCESS';
export const FETCH_ROOM_BY_ID_FAILURE = 'FETCH_ROOM_BY_ID_FAILURE';

export function fetchRoomById(dispatch, id) {
    const action = {
        type: FETCH_ROOM_BY_ID_STARTED
    }
    dispatch(action);
    const success = (res) => {
        const action = fetchRoomByIdSuccess(res);
        dispatch(action);
    };
    const failure = (err) => {
        const action = fetchRoomByIdFailure(err.message);
        dispatch(action);
    };

    fetchRoomByIdFromServer(success, failure, id);
}

function fetchRoomByIdSuccess(room) {
    return {
        type: FETCH_ROOM_BY_ID_SUCCESS,
        payload: {
            data:
                [...room]
        }

    }
}

function fetchRoomByIdFailure(message) {
    return {
        type: FETCH_ROOM_BY_ID_FAILURE,
        payload: {
            error: message
        }
    }
}

export const FETCH_DEVICES_STARTED = 'FETCH_DEVICES_STARTED';
export const FETCH_DEVICES_SUCCESS = 'FETCH_DEVICES_SUCCESS';
export const FETCH_DEVICES_FAILURE = 'FETCH_DEVICES_FAILURE';

export function fetchDevicesByRoomId(dispatch, id) {

    const action = {
        type: FETCH_DEVICES_STARTED
    }

    dispatch(action);
    const success = (res) => {
        const action = fetchDevicesByRoomIdSuccess(res);
        dispatch(action);
    };
    const failure = (err) => {
        const action = fetchDevicesByRoomIdFailure(err.message);
        dispatch(action);
    };

    fetchDevicesByRoomIdFromServer(success, failure, id);

}

function fetchDevicesByRoomIdSuccess(devices) {
    return {
        type: FETCH_DEVICES_SUCCESS,
        payload: {
            data:
                [...devices]
        }

    }
}

function fetchDevicesByRoomIdFailure(message) {
    return {
        type: FETCH_DEVICES_FAILURE,
        payload: {
            error: message
        }
    }
}

export const FETCH_SENSOR_MODELS_STARTED = 'FETCH_SENSOR_MODELS_STARTED';
export const FETCH_SENSOR_MODELS_SUCCESS = 'FETCH_SENSOR_MODELS_SUCCESS';
export const FETCH_SENSOR_MODELS_FAILURE = 'FETCH_SENSOR_MODELS_FAILURE';

export function fetchSensorModels(dispatch) {
    const action = {
        type: FETCH_SENSOR_MODELS_STARTED
    }
    dispatch(action);
    const success = (res) => {
        const action = fetchSensorModelsSuccess(res);
        dispatch(action);
    };
    const failure = (err) => {
        const action = fetchSensorModelsFailure(err.message);
        dispatch(action);
    };

    fetchSensorModelsFromServer(success, failure);

}

function fetchSensorModelsSuccess(sensorModels) {
    return {
        type: FETCH_SENSOR_MODELS_SUCCESS,
        payload: {
            data:
                [...sensorModels]
        }

    }
}

function fetchSensorModelsFailure(message) {
    return {
        type: FETCH_SENSOR_MODELS_FAILURE,
        payload: {
            error: message
        }
    }
}

export const FETCH_ACTUATOR_MODELS_STARTED = 'FETCH_ACTUATOR_MODELS_STARTED';
export const FETCH_ACTUATOR_MODELS_SUCCESS = 'FETCH_ACTUATOR_MODELS_SUCCESS';
export const FETCH_ACTUATOR_MODELS_FAILURE = 'FETCH_ACTUATOR_MODELS_FAILURE';

export function fetchActuatorModels(dispatch) {
    const action = {
        type: FETCH_ACTUATOR_MODELS_STARTED
    }
    dispatch(action);
    const success = (res) => {
        const action = fetchActuatorModelsSuccess(res);
        dispatch(action);
    };
    const failure = (err) => {
        const action = fetchActuatorModelsFailure(err.message);
        dispatch(action);
    };

    fetchActuatorModelsFromServer(success, failure);
}

function fetchActuatorModelsSuccess(actuatorModels) {
    return {
        type: FETCH_ACTUATOR_MODELS_SUCCESS,
        payload: {
            data:
                [...actuatorModels]
        }

    }
}

function fetchActuatorModelsFailure(message) {
    return {
        type: FETCH_ACTUATOR_MODELS_FAILURE,
        payload: {
            error: message
        }
    }
}


export function addDeviceToRoom(dispatch, roomId, device) {
    dispatch({type: 'ADD_DEVICE_STARTED'});

    addDeviceToRoomService(
        roomId,
        device,
        data => dispatch({type: 'ADD_DEVICE_SUCCESS', payload: {device: data}}),
        err => dispatch({type: 'ADD_DEVICE_FAILURE', payload: {error: err}})
    );
}
