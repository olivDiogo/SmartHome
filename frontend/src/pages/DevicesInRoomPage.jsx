import React, { useContext } from 'react';
import AppContext from "../context/AppContext.jsx";
import DeviceList from '../components/DeviceList';

function DevicesInRoomPage() {
    const { state } = useContext(AppContext);
    const { currentRoom } = state;
    const roomName = currentRoom.roomName;

    return (
        <div className={"devices-in-room-page"}>
            <h1>Devices in {roomName}</h1>
            <DeviceList/>
        </div>
    );
}

export default DevicesInRoomPage;