import Main from './components/Main';
import Header from './components/Header';
import {Route, Routes} from 'react-router-dom';
import SingIn from './components/SingIn';
import Register from './components/Register';
import Game from "./components/Game";
import {useState} from "react";
import Play from "./components/Play";
import End from "./components/End";


function App() {
    const [player, setPlayer] = useState("none");
    const [level, setLevel] = useState(0);
    const [difficult, setDifficult] = useState(0);
    const [globalScore, setGlobalScore] = useState(0);
    const handlerPlayer = (playerName) => {
        setPlayer(playerName);
    }
    const setUp = (level, diff) => {
        setLevel(level);
        setDifficult(diff);
    }
    const handleScore = (num) => {
        setGlobalScore(num);
    }


    return (
            <div className="wrapper">
                <Header/>
                <Routes>
                    <Route path="/" element={<Main/>}/>
                    <Route path="/play" element={<Play level = {level} diff = {difficult} setScore = {handleScore}/>}/>
                    <Route path="/game" element={<Game setUp = {setUp}/>}/>
                    <Route path="/end" element={<End name = {player} score = {globalScore} setScore = {handleScore} level = {level} diff = {difficult}/>}/>
                    <Route path="/singin" element={<SingIn handlerPlayer = {handlerPlayer}/>}/>
                    <Route path="/register" element={<Register handlerPlayer = {handlerPlayer}/>}/>
                </Routes>
            </div>
    );
}

export default App;
