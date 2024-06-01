import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import RoomDetailsPage from './pages/RoomDetailsPage.jsx';
import MainPage from "./pages/MainPage";

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <Router>
            <Routes>
                <Route path="/" element={<MainPage />} />
                <Route path="/rooms/:roomId" element={<RoomDetailsPage/>}/>
            </Routes>
        </Router>
    </React.StrictMode>
);
