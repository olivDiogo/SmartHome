import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import AddDeviceToRoomPage from './pages/AddDeviceToRoomPage.jsx';
import MainPage from "./pages/MainPage";
import DevicesInRoomPage from "./pages/DevicesInRoomPage";
import DevicePage from "./pages/DevicePage.jsx";
import "./index.css";

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <Router>
            <Routes>
                <Route path="/" element={<MainPage />} />
                <Route path="/rooms/:roomId" element={<AddDeviceToRoomPage/>}/>
                <Route path="/rooms/:roomId/devices" element={<DevicesInRoomPage/>}/>
                <Route path="/devices/:deviceId" element={<DevicePage/>}/>
            </Routes>
        </Router>
    </React.StrictMode>
);
