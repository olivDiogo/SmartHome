import React, {useState, useEffect} from 'react';
import SunriseSunsetWidget from "../components/SunriseSunsetWidget.jsx";
import OutsideTemperatureWidget from "../components/OutsideTemperatureWidget.jsx";
import Header from "../components/Header.jsx";
import './MainPage.css';


function MainPage() {

    return (
        <div className={"main-page"}>
            <Header/>
            <div className={"main-page-body"}>
                <div className={"main-page-body-widgets"}>
                    <div className={"widget"}>
                        <OutsideTemperatureWidget/>
                    </div>
                    <div className={"widget"}>
                        <SunriseSunsetWidget/>
                    </div>
                </div>
            </div>
            <div className={"main-page-footer"}>
            </div>
        </div>
    )
}

export default MainPage;

