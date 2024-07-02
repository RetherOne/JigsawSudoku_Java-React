import React from 'react';
import {useNavigate} from "react-router-dom";

const FIELD_REMOTE_URL = "http://localhost:8080/api/JigsawSudoku";

function Play(inputData){
    const navigate = useNavigate();

    const handleFrameTasks = (event) => {
        if(event.data === 'uwin' || event.data === 'uexit'){
            navigate('/end');
        }
        console.log(event.data);
    }

    window.addEventListener('message', handleFrameTasks);

    return(
        <div>
            <div className="center wrap-login100 gameplay">
                <iframe
                        src={FIELD_REMOTE_URL + "/play/?level=" + inputData.level + "&diff=" + inputData.diff}
                        width="650px" height="450px"
                        title="gameWindow"
                        style={{border: "0"}}
                />
            </div>
        </div>
    )
}

export default Play;
