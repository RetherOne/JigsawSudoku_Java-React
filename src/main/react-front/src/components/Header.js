import React from 'react';
import {NavLink} from "react-router-dom";


const Header = () => (
    <div className="header">
        <NavLink className="game-name" to="/">JIGSAW Sudoku</NavLink>
    </div>
);


export default Header;
