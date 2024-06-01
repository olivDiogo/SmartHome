import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import AddDeviceToRoomPage from './pages/AddDeviceToRoomPage.jsx';
import MainPage from "./pages/MainPage";
import DevicesInRoomPage from "./pages/DevicesInRoomPage";
import DevicePage from "./pages/DevicePage.jsx";
import "./index.css";
import AppProvider from "./context/AppProvider.jsx";

ReactDOM.createRoot(document.getElementById('root')).render(
    <AppProvider>
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
    </AppProvider>

);
