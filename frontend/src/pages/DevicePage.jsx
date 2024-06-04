
import TypesOfSensor from "../components/TypesOfSensor.jsx";
import SensorTypes from "../components/SensorTypes.jsx";
import SensorModels from "../components/SensorModels.jsx";
import AppContext from "../context/AppContext.jsx";
import {useContext, useEffect, useState} from "react";

function DevicePage() {
    const {state, dispatch} = useContext(AppContext);
    const {selectedSensorTypeId} = state;
    const {selectedTypeOfSensor} = state;


    if (selectedTypeOfSensor == null || selectedTypeOfSensor === "") {
        return (
            <div>
                <h1>Device Page</h1>
                <TypesOfSensor/>
            </div>
        );
    }

    if (selectedSensorTypeId == null || selectedSensorTypeId === "") {
        return (
            <div>
                <h1>Device Page</h1>
                <TypesOfSensor/>
                <SensorTypes/>
            </div>
        );
    }

    else if (selectedSensorTypeId != null && selectedSensorTypeId !== "") {
        return (
            <div>
                <h1>Device Page</h1>
                <TypesOfSensor/>
                <SensorTypes/>
                <SensorModels/>

            </div>
        );
    }
}
export default DevicePage;