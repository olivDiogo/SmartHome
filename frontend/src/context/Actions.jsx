import {
    addDeviceToRoom as addDeviceToRoomService,
    fetchActuatorModelsFromServer,
    fetchCurrentPositionValue,
    fetchDevicesByRoomIdFromServer,
    fetchDeviceTypesFromServer,
    fetchLogsByDeviceIdFromServer,
    fetchRoomByIdFromServer,
    fetchRoomsFromServer,
    fetchSensorModelsBySensorTypeIdFromServer,
    fetchSensorTypesFromServer,
    setBlindRollerValue,
    updateCurrentDeviceFromServer
} from "../services/Service.jsx";

// Actions
export const FETCH_ROOMS_STARTED = 'FETCH_ROOMS_STARTED';
export const FETCH_ROOMS_SUCCESS = 'FETCH_ROOMS_SUCCESS';
export const FETCH_ROOMS_FAILURE = 'FETCH_ROOMS_FAILURE';
export const ADD_DEVICE_STARTED = 'ADD_DEVICE_STARTED';
export const ADD_DEVICE_SUCCESS = 'ADD_DEVICE_SUCCESS';
export const ADD_DEVICE_FAILURE = 'ADD_DEVICE_FAILURE';
export const FETCH_ACTUATOR_MODELS_STARTED = 'FETCH_ACTUATOR_MODELS_STARTED';
export const FETCH_ACTUATOR_MODELS_SUCCESS = 'FETCH_ACTUATOR_MODELS_SUCCESS';
export const FETCH_ACTUATOR_MODELS_FAILURE = 'FETCH_ACTUATOR_MODELS_FAILURE';
export const FETCH_ROOM_BY_ID_STARTED = 'FETCH_ROOM_BY_ID_STARTED';
export const FETCH_ROOM_BY_ID_SUCCESS = 'FETCH_ROOM_BY_ID_SUCCESS';
export const FETCH_ROOM_BY_ID_FAILURE = 'FETCH_ROOM_BY_ID_FAILURE';
export const FETCH_DEVICES_STARTED = 'FETCH_DEVICES_STARTED';
export const FETCH_DEVICES_SUCCESS = 'FETCH_DEVICES_SUCCESS';
export const FETCH_DEVICES_FAILURE = 'FETCH_DEVICES_FAILURE';
export const FETCH_SENSOR_TYPES_STARTED = 'FETCH_SENSOR_TYPES_STARTED';
export const FETCH_SENSOR_TYPES_SUCCESS = 'FETCH_SENSOR_TYPES_SUCCESS';
export const FETCH_SENSOR_TYPES_FAILURE = 'FETCH_SENSOR_TYPES_FAILURE';
export const FETCH_SENSOR_MODELS_STARTED = 'FETCH_SENSOR_MODELS_STARTED';
export const FETCH_SENSOR_MODELS_SUCCESS = 'FETCH_SENSOR_MODELS_SUCCESS';
export const FETCH_SENSOR_MODELS_FAILURE = 'FETCH_SENSOR_MODELS_FAILURE';
export const FETCH_LOGS_STARTED = 'FETCH_LOGS_STARTED';
export const FETCH_LOGS_SUCCESS = 'FETCH_LOGS_SUCCESS';
export const FETCH_LOGS_FAILURE = 'FETCH_LOGS_FAILURE';
export const UPDATE_DEVICE_STARTED = 'UPDATE_DEVICE_STARTED';
export const UPDATE_DEVICE_SUCCESS = 'UPDATE_DEVICE_SUCCESS';
export const UPDATE_DEVICE_FAILURE = 'UPDATE_DEVICE_FAILURE';
export const SET_CURRENT_ROOM = 'SET_CURRENT_ROOM';
export const UPDATE_SELECTED_SENSOR_TYPE_ID = 'UPDATE_SELECTED_SENSOR_TYPE_ID';
export const UPDATE_SELECTED_SENSOR_MODEL = 'UPDATE_SELECTED_SENSOR_MODEL';
export const UPDATE_SELECTED_TYPE_OF_SENSOR = 'UPDATE_SELECTED_TYPE_OF_SENSOR';
export const FETCH_CURRENT_POSITION_STARTED = 'FETCH_CURRENT_POSITION_STARTED';
export const FETCH_CURRENT_POSITION_SUCCESS = 'FETCH_CURRENT_POSITION_SUCCESS';
export const FETCH_CURRENT_POSITION_FAILURE = 'FETCH_CURRENT_POSITION_FAILURE';
export const SET_BLIND_ROLLER_VALUE_STARTED = 'SET_BLIND_ROLLER_VALUE_STARTED';
export const SET_BLIND_ROLLER_VALUE_SUCCESS = 'SET_BLIND_ROLLER_VALUE_SUCCESS';
export const SET_BLIND_ROLLER_VALUE_FAILURE = 'SET_BLIND_ROLLER_VALUE_FAILURE';
export const FETCH_DEVICE_TYPES_STARTED = 'FETCH_DEVICE_TYPES_STARTED';
export const FETCH_DEVICE_TYPES_SUCCESS = 'FETCH_DEVICE_TYPES_SUCCESS';
export const FETCH_DEVICE_TYPES_FAILURE = 'FETCH_DEVICE_TYPES_FAILURE';
export const FETCH_ACTUATORS_STARTED = 'FETCH_ACTUATORS_STARTED';
export const FETCH_ACTUATORS_SUCCESS = 'FETCH_ACTUATORS_SUCCESS';
export const FETCH_ACTUATORS_FAILURE = 'FETCH_ACTUATORS_FAILURE';

export function fetchRooms(dispatch) {
    dispatch({type: FETCH_ROOMS_STARTED});

    const success = (res) => {
        // Ensure we handle the case where _embedded might not be defined
        const roomList = res._embedded ? res._embedded.roomDTOList : res;
        dispatch({type: FETCH_ROOMS_SUCCESS, payload: {data: roomList}});
    };

    const failure = (err) => {
        dispatch({type: FETCH_ROOMS_FAILURE, payload: {error: err}});
    };

    fetchRoomsFromServer(success, failure);
}


export function setCurrentRoom(dispatch, roomId, roomName) {
    const action = {
        type: SET_CURRENT_ROOM,
        payload: {roomId, roomName}
    };
    dispatch(action);
}

export function fetchRoomById(dispatch, id) {
    dispatch({type: FETCH_ROOM_BY_ID_STARTED});

    const success = (res) => dispatch(fetchRoomByIdSuccess(res));
    const failure = (err) => dispatch(fetchRoomByIdFailure(err.message));

    fetchRoomByIdFromServer(success, failure, id);
}

function fetchRoomByIdSuccess(room) {
    return {
        type: FETCH_ROOM_BY_ID_SUCCESS,
        payload: {data: room}
    };
}

function fetchRoomByIdFailure(message) {
    return {
        type: FETCH_ROOM_BY_ID_FAILURE,
        payload: {error: message}
    };
}

export function fetchDevicesByRoomId(dispatch, id) {
    dispatch({type: FETCH_DEVICES_STARTED});

    const success = (res) => dispatch(fetchDevicesByRoomIdSuccess(res));
    const failure = (err) => dispatch(fetchDevicesByRoomIdFailure(err.message));

    fetchDevicesByRoomIdFromServer(success, failure, id);
}

function fetchDevicesByRoomIdSuccess(devices) {
    return {
        type: FETCH_DEVICES_SUCCESS,
        payload: {data: devices}
    };
}

function fetchDevicesByRoomIdFailure(message) {
    return {
        type: FETCH_DEVICES_FAILURE,
        payload: {error: message}
    };
}

export function addDeviceToRoom(dispatch, roomId, device) {
    dispatch({type: ADD_DEVICE_STARTED});

    addDeviceToRoomService(
        roomId,
        device,
        (data) => dispatch({type: ADD_DEVICE_SUCCESS, payload: {device: data}}),
        (err) => dispatch({type: ADD_DEVICE_FAILURE, payload: {error: err.message}})
    );
}

export function fetchSensorTypes(dispatch) {
    dispatch({type: FETCH_SENSOR_TYPES_STARTED});

    const success = (res) => dispatch(fetchSensorTypesSuccess(res));
    const failure = (err) => dispatch(fetchSensorTypesFailure(err.message));

    fetchSensorTypesFromServer(success, failure);
}

function fetchSensorTypesSuccess(sensorTypes) {
    return {
        type: FETCH_SENSOR_TYPES_SUCCESS,
        payload: {data: sensorTypes}
    };
}

function fetchSensorTypesFailure(message) {
    return {
        type: FETCH_SENSOR_TYPES_FAILURE,
        payload: {error: message}
    };
}

export function fetchSensorModels(dispatch, sensorTypeId) {
    dispatch({type: FETCH_SENSOR_MODELS_STARTED});

    const success = (res) => dispatch(fetchSensorModelsSuccess(res));
    const failure = (err) => dispatch(fetchSensorModelsFailure(err.message));

    fetchSensorModelsBySensorTypeIdFromServer(success, failure, sensorTypeId);
}

function fetchSensorModelsSuccess(sensorModels) {
    return {
        type: FETCH_SENSOR_MODELS_SUCCESS,
        payload: {data: sensorModels}
    };
}

function fetchSensorModelsFailure(message) {
    return {
        type: FETCH_SENSOR_MODELS_FAILURE,
        payload: {error: message}
    };
}

export function fetchActuatorModels(dispatch) {
    dispatch({type: FETCH_ACTUATOR_MODELS_STARTED});

    const success = (res) => dispatch(fetchActuatorModelsSuccess(res));
    const failure = (err) => dispatch(fetchActuatorModelsFailure(err.message));

    fetchActuatorModelsFromServer(success, failure);
}

function fetchActuatorModelsSuccess(actuatorModels) {
    return {
        type: FETCH_ACTUATOR_MODELS_SUCCESS,
        payload: {data: actuatorModels}
    };
}

function fetchActuatorModelsFailure(message) {
    return {
        type: FETCH_ACTUATOR_MODELS_FAILURE,
        payload: {error: message}
    };
}

export function fetchLogsByDeviceId(dispatch, deviceId, timeStart, timeEnd) {
    dispatch({type: FETCH_LOGS_STARTED});

    const success = (res) => dispatch(fetchLogsSuccess(res));
    const failure = (err) => dispatch(fetchLogsFailure(err.message));

    fetchLogsByDeviceIdFromServer(success, failure, deviceId, timeStart, timeEnd);
}

function fetchLogsSuccess(logs) {
    return {
        type: FETCH_LOGS_SUCCESS,
        payload: {data: logs}
    };
}

function fetchLogsFailure(message) {
    return {
        type: FETCH_LOGS_FAILURE,
        payload: {error: message}
    };
}

export function updateCurrentDevice(dispatch, deviceId, deviceName) {
    dispatch({type: UPDATE_DEVICE_STARTED});

    const success = (res) => dispatch(updateCurrentDeviceSuccess(res));
    const failure = (err) => dispatch(updateCurrentDeviceFailure(err.message));

    updateCurrentDeviceFromServer(success, failure, deviceId, deviceName);
}

function updateCurrentDeviceSuccess(device) {
    return {
        type: UPDATE_DEVICE_SUCCESS,
        payload: {data: device}
    };
}

function updateCurrentDeviceFailure(message) {
    return {
        type: UPDATE_DEVICE_FAILURE,
        payload: {error: message}
    };
}

export function updateSensorTypeId(dispatch, selectedSensorType) {
    const action = {
        type: UPDATE_SELECTED_SENSOR_TYPE_ID,
        payload: {selectedSensorType}
    };
    dispatch(action);
}

export function updateSelectedSensorModel(dispatch, selectedSensorModel) {
    const action = {
        type: UPDATE_SELECTED_SENSOR_MODEL,
        payload: {selectedSensorModel}
    };
    dispatch(action);
}

export function updateSelectedTypeOfSensor(dispatch, selectedTypeOfSensor) {
    const action = {
        type: UPDATE_SELECTED_TYPE_OF_SENSOR,
        payload: {selectedTypeOfSensor}
    };
    dispatch(action);
}

export function fetchCurrentPosition(dispatch, deviceID) {
    dispatch({type: FETCH_CURRENT_POSITION_STARTED});

    const success = (position) => dispatch({type: FETCH_CURRENT_POSITION_SUCCESS, payload: {data: position}});
    const failure = (error) => dispatch({type: FETCH_CURRENT_POSITION_FAILURE, payload: {error: error.message}});

    fetchCurrentPositionValue(success, failure, deviceID);
}

export function setBlindRoller(dispatch, deviceID, actuatorID, value) {
    dispatch({type: SET_BLIND_ROLLER_VALUE_STARTED});

    const success = (data) => dispatch({
        type: SET_BLIND_ROLLER_VALUE_SUCCESS,
        payload: {data, message: 'Blind roller value set successfully'}
    });
    const failure = (error) => dispatch({type: SET_BLIND_ROLLER_VALUE_FAILURE, payload: {error: error.message}});

    setBlindRollerValue(success, failure, deviceID, actuatorID, value);
}

export function fetchDeviceTypes(dispatch) {
    dispatch({type: FETCH_DEVICE_TYPES_STARTED});

    const success = (res) => dispatch({type: FETCH_DEVICE_TYPES_SUCCESS, payload: {data: res}});
    const failure = (err) => dispatch({type: FETCH_DEVICE_TYPES_FAILURE, payload: {error: err.message}});

    fetchDeviceTypesFromServer(success, failure);
}

export function fetchActuators(dispatch, deviceId) {
    dispatch({ type: FETCH_ACTUATORS_STARTED });

    const success = (data) => dispatch({type: FETCH_ACTUATORS_SUCCESS, payload: {actuators: data}});
    const failure = (error) => dispatch({type: FETCH_ACTUATORS_FAILURE, payload: {error}});

    fetchActuatorsByDeviceId(success, failure, deviceId);
}
