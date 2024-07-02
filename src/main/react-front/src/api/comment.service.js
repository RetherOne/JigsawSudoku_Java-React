import globalURL from "./main.service";
import {formatDate} from "./utils";

export const getComment = function (game) {
    return globalURL.get('/comment/' + game);
}

export const postComment = (game, player, comment) => globalURL.post('/comment',{
    game: game,
    player: player,
    comment: comment,
    commentedon: formatDate(new Date()),
})