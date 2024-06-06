
import AppContext from "../context/AppContext.jsx";
import {useContext, useEffect, useState} from "react";
import SubmitButton from "../components/SubmitButton.jsx";
import FormDataContext from "../context/FormDataContext.jsx";
import TypesOfActuator from "../components/TypesOfActuator.jsx";
import ActuatorTypes from "../components/ActuatorTypes.jsx";
import ActuatorModels from "../components/ActuatorModels.jsx";
import ConfigureActuator from "../components/ConfigureActuator.jsx";

function AddActuatorPage() {
    const {state, dispatch} = useContext(AppContext);
    const { formState, formDispatch} = useContext(FormDataContext);
    const { actuatorName } = formState;
    const {selectedActuatorTypeId} = state;
    const {selectedTypeOfActuator} = state;
    const {selectedActuatorModelName} = state;


    if ( selectedTypeOfActuator === "") {
        return (
            <div>
                <h1>Let's add an Actuator</h1>
                <TypesOfActuator/>
            </div>
        );
    }

    if (selectedTypeOfActuator !== "" && selectedActuatorTypeId === "") {
        return (
            <div>
                <h1>Let's add an Actuator</h1>
                <TypesOfActuator/>
                <ActuatorTypes/>
            </div>
        );
    }

    if ( selectedTypeOfActuator !== "" && selectedActuatorTypeId !== "" && selectedActuatorModelName === "") {
        return (
            <div>
                <h1>Let's add an Actuator</h1>
                <TypesOfActuator/>
                <ActuatorTypes/>
                <ActuatorModels/>

            </div>
        );
    }

    if (selectedTypeOfActuator !== "" && selectedActuatorTypeId !== "" && selectedActuatorModelName !== "") {
        return (
            <div>
                <h1>Let's add an Actuator</h1>
                <TypesOfActuator/>
                <ActuatorTypes/>
                <ActuatorModels/>
                <ConfigureActuator/>
                {actuatorName !== "" && <SubmitButton/>}
            </div>
        );
    }



}
export default AddActuatorPage;