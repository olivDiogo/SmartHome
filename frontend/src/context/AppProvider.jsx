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

    devices: {
        loading: false,
        error: null,
        data: [],
    },

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