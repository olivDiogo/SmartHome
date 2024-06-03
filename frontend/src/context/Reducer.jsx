import {
    FETCH_ROOMS_STARTED,
    FETCH_ROOMS_SUCCESS,
    FETCH_ROOMS_FAILURE,
    FETCH_ROOM_BY_ID_STARTED,
    FETCH_ROOM_BY_ID_SUCCESS,
    FETCH_ROOM_BY_ID_FAILURE,
    FETCH_DEVICES_STARTED,
    FETCH_DEVICES_SUCCESS,
    FETCH_DEVICES_FAILURE,
    FETCH_SENSOR_MODELS_STARTED,
    FETCH_SENSOR_MODELS_SUCCESS,
    FETCH_SENSOR_MODELS_FAILURE,
    FETCH_ACTUATOR_MODELS_STARTED,
    FETCH_ACTUATOR_MODELS_SUCCESS,
    FETCH_ACTUATOR_MODELS_FAILURE,
    SET_CURRENT_ROOM,
    FETCH_TEMPERATURE_STARTED,
    FETCH_TEMPERATURE_FAILURE,
    FETCH_TEMPERATURE_SUCCESS
} from './Actions';

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

        case FETCH_ACTUATOR_MODELS_STARTED:
            return {
                ...state,
                actuatorModels: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_ACTUATOR_MODELS_SUCCESS:
            return {
                ...state,
                actuatorModels: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case FETCH_ACTUATOR_MODELS_FAILURE:
            return {
                ...state,
                actuatorModels: {
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

        default:
            return state;
    }

}


export default reducer;
