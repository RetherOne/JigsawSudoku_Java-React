import React from 'react';
import {NavLink} from "react-router-dom";

const Main = () => (
    <div className="center">
        <NavLink className="floating-button" to="/singin">Sing In</NavLink><br/>
        <NavLink className="floating-button" to="/register">Registration</NavLink>
    </div>
);


export default Main;
