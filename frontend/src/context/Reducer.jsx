import {
    ADD_DEVICE_FAILURE,
    ADD_DEVICE_STARTED,
    ADD_DEVICE_SUCCESS,
    FETCH_DEVICES_FAILURE,
    FETCH_DEVICES_STARTED,
    FETCH_DEVICES_SUCCESS,
    FETCH_ROOM_BY_ID_FAILURE,
    FETCH_ROOM_BY_ID_STARTED,
    FETCH_ROOM_BY_ID_SUCCESS,
    FETCH_ROOMS_FAILURE,
    FETCH_ROOMS_STARTED,
    FETCH_ROOMS_SUCCESS,
    FETCH_SENSOR_MODELS_FAILURE,
    FETCH_SENSOR_MODELS_STARTED,
    FETCH_SENSOR_MODELS_SUCCESS,
    FETCH_LOGS_STARTED,
    FETCH_LOGS_SUCCESS,
    FETCH_LOGS_FAILURE,
    UPDATE_DEVICE_STARTED,
    UPDATE_DEVICE_SUCCESS,
    UPDATE_DEVICE_FAILURE,
    SET_CURRENT_ROOM,
    FETCH_SENSOR_TYPES_FAILURE,
    FETCH_SENSOR_TYPES_STARTED,
    FETCH_SENSOR_TYPES_SUCCESS,
    UPDATE_SELECTED_TYPE_OF_SENSOR,
    UPDATE_SELECTED_SENSOR_MODEL,
    UPDATE_SELECTED_SENSOR_TYPE_ID,
    FETCH_CURRENT_POSITION_STARTED,
    FETCH_CURRENT_POSITION_SUCCESS,
    FETCH_CURRENT_POSITION_FAILURE,
    SET_BLIND_ROLLER_VALUE_STARTED,
    SET_BLIND_ROLLER_VALUE_SUCCESS,
    SET_BLIND_ROLLER_VALUE_FAILURE
} from './Actions';
import {
    FETCH_TEMPERATURE_FAILURE,
    FETCH_TEMPERATURE_STARTED,
    FETCH_TEMPERATURE_SUCCESS
} from './TemperatureActions';

function reducer(state, action) {
    switch (action.type) {
        case FETCH_ROOMS_STARTED:
            return {
                ...state,
                rooms: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_ROOMS_SUCCESS:
            return {
                ...state,
                rooms: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case FETCH_ROOMS_FAILURE:
            return {
                ...state,
                rooms: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case SET_CURRENT_ROOM:
            return {
                ...state,
                currentRoom: {
                    roomId: action.payload.roomId,
                    roomName: action.payload.roomName
                }
            };

        case FETCH_ROOM_BY_ID_STARTED:
            return {
                ...state,
                room: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_ROOM_BY_ID_SUCCESS:

            return {
                ...state,
                room: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case FETCH_ROOM_BY_ID_FAILURE:
            return {
                ...state,
                room: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case FETCH_DEVICES_STARTED:
            return {
                ...state,
                devices: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_DEVICES_SUCCESS:
            return {
                ...state,
                devices: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case FETCH_DEVICES_FAILURE:
            return {
                ...state,
                devices: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case FETCH_TEMPERATURE_STARTED:
            return {
                ...state,
                temperature: {
                    loading: true,
                    error: null,
                    data: null
                }
            }

        case FETCH_TEMPERATURE_SUCCESS:
            return {
                ...state,
                temperature: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            }

        case FETCH_TEMPERATURE_FAILURE:
            return {
                ...state,
                temperature: {
                    loading: false,
                    error: action.payload.error,
                    data: null
                }
            }

        case FETCH_SENSOR_MODELS_STARTED:
            return {
                ...state,
                sensorModels: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_SENSOR_MODELS_SUCCESS:
            return {
                ...state,
                sensorModels: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }


        case FETCH_SENSOR_MODELS_FAILURE:
            return {
                ...state,
                sensorModels: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }


        case ADD_DEVICE_STARTED:
            return {
                ...state,
                addingDevice: true,
                addDeviceError: null
            };

        case ADD_DEVICE_SUCCESS:
            return {
                ...state,
                addingDevice: false,
                addDeviceError: null,
                devices: {
                    ...state.devices,
                    data: [...state.devices.data, action.payload.device]
                }
            };

        case ADD_DEVICE_FAILURE:
            return {
                ...state,
                addingDevice: false,
                addDeviceError: action.payload.error
            };

        case FETCH_SENSOR_TYPES_STARTED:
            return {
                ...state,
                sensorTypes: {
                    loading: true,
                    error: null,
                    data: []
                }
            };

        case FETCH_SENSOR_TYPES_SUCCESS:
            return {
                ...state,
                sensorTypes: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            };

        case FETCH_SENSOR_TYPES_FAILURE:
            return {
                ...state,
                sensorTypes: {
                    loading: false,
                    error: action.payload.error,
                    data: []
                }
            };


        case FETCH_LOGS_STARTED:
            return {
                ...state,
                logs: {
                    loading: true,
                    error: null,
                    data: []
                }
            };

        case FETCH_LOGS_SUCCESS:
            return {
                ...state,
                logs: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            };

        case FETCH_LOGS_FAILURE:
            return {
                ...state,
                logs: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            };

        case UPDATE_DEVICE_STARTED:
            return {
                ...state,
                updateDevice: {
                    loading: true,
                    error: null,
                    data: null
                }
            };

        case UPDATE_DEVICE_SUCCESS:
            return {
                ...state,
                updateDevice: {
                    loading: false,
                    error: null,
                    data: action.payload.data
                }
            };

        case UPDATE_DEVICE_FAILURE:
            return {
                ...state,
                updateDevice: {
                    loading: false,
                    error: action.payload.error,
                    data: null
                }
            };

        case UPDATE_SELECTED_SENSOR_TYPE_ID:
            return {
                ...state,
                selectedSensorTypeId: action.payload.selectedSensorType
            };


        case UPDATE_SELECTED_SENSOR_MODEL:
            return {
                ...state,
                selectedSensorModel: action.payload.selectedSensorModel
            };

        case UPDATE_SELECTED_TYPE_OF_SENSOR:
            return {
                ...state,
                selectedTypeOfSensor: action.payload.selectedTypeOfSensor
            };

        case FETCH_CURRENT_POSITION_STARTED:
            return {
                ...state,
                position: {
                    ...state.position,
                    loading: true,
                    error: null
                }
            };
        case FETCH_CURRENT_POSITION_SUCCESS:
            return {
                ...state,
                position: {
                    ...state.position,
                    loading: false,
                    data: action.payload.data,
                    error: null
                }
            };
        case FETCH_CURRENT_POSITION_FAILURE:
            return {
                ...state,
                position: {
                    ...state.position,
                    loading: false,
                    data: null,
                    error: action.payload.error
                }
            };

        case SET_BLIND_ROLLER_VALUE_STARTED:
            return {
                ...state,
                setBlindRollerStatus: {
                    loading: true,
                    error: null,
                    message: null
                }
            };

        case SET_BLIND_ROLLER_VALUE_SUCCESS:
            return {
                ...state,
                setBlindRollerStatus: {
                    loading: false,
                    error: null,
                    message: action.payload.message
                }
            };

        case SET_BLIND_ROLLER_VALUE_FAILURE:
            return {
                ...state,
                setBlindRollerStatus: {
                    loading: false,
                    error: action.payload.error,
                    message: null
                }
            };

        default:
            return state;
    }

}


export default reducer;
