import React, { useReducer } from 'react';
import PropTypes from "prop-types";
import { Provider } from './AppContext.jsx';
import reducer from './Reducer.jsx';

const initialState = {
    rooms: {
        loading: true,
        error: null,
        data: [],
    },

    currentRoom: {
        roomId: null,
        roomName: null,
    },

    devices: {
        loading: false,
        error: null,
        data: [],
    },

    currentDevice: {
        deviceId: null,
        deviceName: null,
    },

    temperature: {
        loading: true,
        error: null,
        data: null,
    },

    logs: {
        loading: true,
        error: null,
        data: [],
    },

    timeStart: '',
    timeEnd: '',

    typesOfSensor : ['gpsSensor', 'dateSensor', 'genericSensor'],

    selectedTypeOfSensor: "",

    sensorTypes: {
        loading: true,
        error: null,
        data: [],
    },

    selectedSensorTypeId: "",

    sensorModels: {
        loading: true,
        error: null,
        data: [],
    },

    selectedSensorModel: "",

};

const AppProvider = (props) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <Provider value={{
            state,
            dispatch
        }}>
            {props.children}
        </Provider>
    );
};
AppProvider.propTypes = {
    children: PropTypes.node,
};

export default AppProvider;