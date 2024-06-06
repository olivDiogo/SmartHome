import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import AddDeviceToRoomPage from './pages/AddDeviceToRoomPage.jsx';
import MainPage from "./pages/MainPage";
import DevicesInRoomPage from "./pages/DevicesInRoomPage";
import AddSensorPage from "./pages/AddSensorPage.jsx";
import "./index.css";
import AppProvider from "./context/AppProvider.jsx";
import LogsPage from "./pages/LogsPage.jsx";
import LogsResultsPage from "./pages/LogsResultsPage.jsx";
import FormDataProvider from "./context/FormDataProvider.jsx";
import AddActuatorPage from "./pages/AddActuatorPage.jsx";

ReactDOM.createRoot(document.getElementById('root')).render(
    <AppProvider>
        <React.StrictMode>
            <Router>
                <FormDataProvider>
                    <Routes>
                        <Route path="/" element={<MainPage/>}/>
                        <Route path="/rooms/:roomId" element={<AddDeviceToRoomPage/>}/>
                        <Route path="/rooms/:roomId/devices" element={<DevicesInRoomPage/>}/>
                        <Route path="/devices/:deviceId/add-sensor" element={<AddSensorPage/>}/>
                        <Route path="/devices/:deviceId/add-actuator" element={<AddActuatorPage/>}/>
                        <Route path="/logs/:deviceId" element={<LogsPage/>}/>
                        <Route path="/logs/:deviceId/results" element={<LogsResultsPage/>}/>
                    </Routes>
                </FormDataProvider>
            </Router>
        </React.StrictMode>
    </AppProvider>
);
