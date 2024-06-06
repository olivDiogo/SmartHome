
import TypesOfSensor from "../components/TypesOfSensor.jsx";
import SensorTypes from "../components/SensorTypes.jsx";
import SensorModels from "../components/SensorModels.jsx";
import AppContext from "../context/AppContext.jsx";
import {useContext, useEffect, useState} from "react";
import ConfigureDevice from "../components/ConfigureDevice.jsx";
import SubmitButton from "../components/SubmitButton.jsx";
import FormDataContext from "../context/FormDataContext.jsx";

function AddSensorPage() {
    const {state, dispatch} = useContext(AppContext);
    const { formState, formDispatch} = useContext(FormDataContext);
    const { sensorName } = formState;
    const {selectedSensorTypeId} = state;
    const {selectedTypeOfSensor} = state;
    const {selectedSensorModelName} = state;


    if ( selectedTypeOfSensor === "") {
        return (
            <div>
                <h1>Device Page</h1>
                <TypesOfSensor/>
            </div>
        );
    }

    if (selectedTypeOfSensor !== "" && selectedSensorTypeId === "") {
        return (
            <div>
                <h1>Device Page</h1>
                <TypesOfSensor/>
                <SensorTypes/>
            </div>
        );
    }

    if ( selectedTypeOfSensor !== "" && selectedSensorTypeId !== "" && selectedSensorModelName === "") {
        return (
            <div>
                <h1>Device Page</h1>
                <TypesOfSensor/>
                <SensorTypes/>
                <SensorModels/>

            </div>
        );
    }

    if (selectedTypeOfSensor !== "" && selectedSensorTypeId !== "" && selectedSensorModelName !== "") {
        return (
            <div>
                <h1>Device Page</h1>
                <TypesOfSensor/>
                <SensorTypes/>
                <SensorModels/>
                <ConfigureDevice/>
                {sensorName !== "" && <SubmitButton/>}
            </div>
        );
    }



}
export default AddSensorPage;