import React, {useEffect, useState} from 'react';
import Comment from "./Comment";
import Rating from "./Rating";
import {addScore} from "../api/score.service";
import {gameName} from "../api/main.service";
import {getScoreAxio} from "../api/winCheck.service";
import Score from "./Score";

function End (inputData){

    const [thisScore, setThisScore] = useState(0);

    useEffect(() => {
        var diffInt = inputData.diff;
        var diffStr = null;
        if(diffInt == "1"){
            diffStr = "EASY";
        }
        else if(diffInt == "2"){
            diffStr = "MID";
        }
        else if(diffInt == "3"){
            diffStr = "HARD";
        }


        getScoreAxio().then(response => {
            inputData.setScore(response.data);
            setThisScore(response.data);
            addScore(gameName, inputData.name, response.data, inputData.level, diffStr)
        })
    }, []);

    return (
        <div>
            <div className="center wrap-login100 gameplay">
                <h1 className="congratulations-text">
                    Your SCORE: {thisScore}
                </h1>
            </div>
            <div className="footer">
                <Rating player={inputData}/><br/>
                <Comment player={inputData}/><br/>
                <Score score = {thisScore} player={inputData}/>
            </div>
        </div>
    )
}

export default End;
