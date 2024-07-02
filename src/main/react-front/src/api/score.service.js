import globalURL from "./main.service";
import {formatDate} from "./utils";


export const getTopScores = function (game) {
    return globalURL.get('/score/' + game);
}

export const addScore = (game, player, points, level, diff) => globalURL.post('/score',{
    game: game,
    player: player,
    points: points,
    playedOn: formatDate(new Date()),
    level: level,
    difficult: diff,
})