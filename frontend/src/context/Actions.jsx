import {
    addDeviceToRoom as addDeviceToRoomService,
    fetchActuatorModelsFromServer,
    fetchDevicesByRoomIdFromServer,
    fetchRoomByIdFromServer,
    fetchRoomsFromServer,
    fetchSensorModelsBySensorTypeIdFromServer,
    fetchLogsByDeviceIdFromServer,
    updateCurrentDeviceFromServer,
    fetchSensorTypesFromServer,
    fetchCurrentPositionValue,
    setBlindRollerValue
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


export function addDeviceToRoom(dispatch, roomId, device) {
    dispatch({type: 'ADD_DEVICE_STARTED'});

    addDeviceToRoomService(
        roomId,
        device,
        data => dispatch({type: 'ADD_DEVICE_SUCCESS', payload: {device: data}}),
        err => dispatch({type: 'ADD_DEVICE_FAILURE', payload: {error: err}})
    );
}


export const FETCH_SENSOR_TYPES_STARTED = 'FETCH_SENSOR_TYPES_STARTED';
export const FETCH_SENSOR_TYPES_SUCCESS = 'FETCH_SENSOR_TYPES_SUCCESS';
export const FETCH_SENSOR_TYPES_FAILURE = 'FETCH_SENSOR_TYPES_FAILURE';

export function fetchSensorTypes(dispatch) {
    const action = {
        type: FETCH_SENSOR_TYPES_STARTED
    }
    dispatch(action);

    const success = (res) => {
        const action = fetchSensorTypesSuccess(res);
        dispatch(action);
    };

    const failure = (err) => {
        const action = fetchSensorTypesFailure(err.message);
        dispatch(action);
    };

    fetchSensorTypesFromServer(success, failure);
}

function fetchSensorTypesSuccess(sensorTypes) {
    return {
        type: FETCH_SENSOR_TYPES_SUCCESS,
        payload: {
            data: sensorTypes
        }
    }
}

function fetchSensorTypesFailure(message) {
    return {
        type: FETCH_SENSOR_TYPES_FAILURE,
        payload: {
            error: message
        }
    }
}


export const FETCH_SENSOR_MODELS_STARTED = 'FETCH_SENSOR_MODELS_STARTED';
export const FETCH_SENSOR_MODELS_SUCCESS = 'FETCH_SENSOR_MODELS_SUCCESS';
export const FETCH_SENSOR_MODELS_FAILURE = 'FETCH_SENSOR_MODELS_FAILURE';

export function fetchSensorModels(dispatch, sensorTypeId) {
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

    fetchSensorModelsBySensorTypeIdFromServer(success, failure, sensorTypeId);
}

function fetchSensorModelsSuccess(sensorModels) {
    return {
        type: FETCH_SENSOR_MODELS_SUCCESS,
        payload: {
            data:
            sensorModels
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

export const FETCH_LOGS_STARTED = 'FETCH_LOGS_STARTED';
export const FETCH_LOGS_SUCCESS = 'FETCH_LOGS_SUCCESS';
export const FETCH_LOGS_FAILURE = 'FETCH_LOGS_FAILURE';

export function fetchLogsByDeviceId(dispatch, deviceId, timeStart, timeEnd) {
    const action = {
        type: FETCH_LOGS_STARTED
    }
    dispatch(action);
    const success = (res) => {
        const action = fetchLogsSuccess(res);
        dispatch(action);
    };
    const failure = (err) => {
        const action = fetchLogsFailure(err);
        dispatch(action);
    };

    fetchLogsByDeviceIdFromServer(success, failure, deviceId, timeStart, timeEnd);
}

function fetchLogsSuccess(logs) {
    return {
        type: FETCH_LOGS_SUCCESS,
        payload: {
            data: logs
        }
    }
}

function fetchLogsFailure(message) {
    return {
        type: FETCH_LOGS_FAILURE,
        payload: {
            error: message
        }
    }
}

export const UPDATE_DEVICE_STARTED = 'UPDATE_DEVICE_STARTED';
export const UPDATE_DEVICE_SUCCESS = 'UPDATE_DEVICE_SUCCESS';
export const UPDATE_DEVICE_FAILURE = 'UPDATE_DEVICE_FAILURE';

export function updateCurrentDevice(dispatch, deviceId, deviceName) {
    const action = {
        type: UPDATE_DEVICE_STARTED
    }
    dispatch(action);
    const success = (res) => {
        const action = updateCurrentDeviceSuccess(res);
        dispatch(action);
    };
    const failure = (err) => {
        const action = updateCurrentDeviceFailure(err.message);
        dispatch(action);
    };

    updateCurrentDeviceFromServer(success, failure, deviceId, deviceName); // Correct function call
}

function updateCurrentDeviceSuccess(device) {
    return {
        type: UPDATE_DEVICE_SUCCESS,
        payload: {
            data: device
        }
    }
}

function updateCurrentDeviceFailure(message) {
    return {
        type: UPDATE_DEVICE_FAILURE,
        payload: {
            error: message
        }
    }
}

export const UPDATE_SELECTED_SENSOR_TYPE_ID = 'UPDATE_SELECTED_SENSOR_TYPE_ID';

export function updateSensorTypeId(dispatch, selectedSensorType) {
    const action = {
        type: UPDATE_SELECTED_SENSOR_TYPE_ID,
        payload: {
            selectedSensorType: selectedSensorType
        }
    }
    dispatch(action);
}

export const UPDATE_SELECTED_SENSOR_MODEL = 'UPDATE_SELECTED_SENSOR_MODEL';

export function updateSelectedSensorModel(dispatch, selectedSensorModel) {
    const action = {
        type: UPDATE_SELECTED_SENSOR_MODEL,
        payload: {
            selectedSensorModel: selectedSensorModel
        }
    }
    dispatch(action);
}


export const UPDATE_SELECTED_TYPE_OF_SENSOR = 'UPDATE_SELECTED_TYPE_OF_SENSOR';

export function updateSelectedTypeOfSensor(dispatch, selectedTypeOfSensor) {
    const action = {
        type: UPDATE_SELECTED_TYPE_OF_SENSOR,
        payload: {
            selectedTypeOfSensor: selectedTypeOfSensor
        }
    }
    dispatch(action);
}

export const FETCH_CURRENT_POSITION_STARTED = 'FETCH_CURRENT_POSITION_STARTED';
export const FETCH_CURRENT_POSITION_SUCCESS = 'FETCH_CURRENT_POSITION_SUCCESS';
export const FETCH_CURRENT_POSITION_FAILURE = 'FETCH_CURRENT_POSITION_FAILURE';

export function fetchCurrentPosition(dispatch, deviceID) {

    dispatch({type: FETCH_CURRENT_POSITION_STARTED});

    const success = (position) => {
        dispatch({
            type: FETCH_CURRENT_POSITION_SUCCESS,
            payload: {data: position}
        });
    };
    const failure = (error) => {
        dispatch({
            type: FETCH_CURRENT_POSITION_FAILURE,
            payload: {error: error.message}
        });
    };

    fetchCurrentPositionValue(success, failure, deviceID);
}

export const SET_BLIND_ROLLER_VALUE_STARTED = 'SET_BLIND_ROLLER_VALUE_STARTED';
export const SET_BLIND_ROLLER_VALUE_SUCCESS = 'SET_BLIND_ROLLER_VALUE_SUCCESS';
export const SET_BLIND_ROLLER_VALUE_FAILURE = 'SET_BLIND_ROLLER_VALUE_FAILURE';

export function setBlindRoller(dispatch, deviceID, actuatorID, value) {

    dispatch({type: SET_BLIND_ROLLER_VALUE_STARTED});

    const success = (data) => {
        dispatch({
            type: SET_BLIND_ROLLER_VALUE_SUCCESS,
            payload: {
                data: data,
                message: 'Blind roller value set successfully'
            }
        });
    };
    const failure = (error) => {
        dispatch({
            type: SET_BLIND_ROLLER_VALUE_FAILURE,
            payload: {
                error: error.message
            }
        });
    };

    setBlindRollerValue(success, failure, deviceID, actuatorID, value);
}

