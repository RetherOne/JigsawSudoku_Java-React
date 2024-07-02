import React, {useEffect, useState} from 'react';
import {gameName} from "../api/main.service";
import {getTopScores} from "../api/score.service";
import {Table} from "react-bootstrap";


function Score () {

    const [scores, setScores] = useState([]);

    const getData = () => {
        getTopScores(gameName).then(response => {
            setScores(response.data);
        });
    }

    useEffect(() => {
        getData();
    });



    return (
         <div>
             <Table striped bordered hover>
                 <thead>
                 <tr>
                     <th>Player</th>
                     <th>Score</th>
                     <th>Date</th>
                     <th>Level</th>
                     <th>Difficult</th>
                 </tr>
                 </thead>
                 <tbody>

                 {scores.map(score => (
                     <tr key={score.ident}>
                         <th>{score.player}</th>
                         <th>{score.points}</th>
                         <th>{new Date(score.playedOn).toLocaleString()}</th>
                         <th>{score.level}</th>
                         <th>{score.difficult}</th>
                     </tr>
                 ))}
                 </tbody>
             </Table>
         </div>
    )
}


export default Score;
