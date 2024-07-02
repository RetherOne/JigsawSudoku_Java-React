import globalURL from "./main.service";
import {formatDate} from "./utils";
//post /api/rating

//get /{game}/average_rating
//get /{game}/{player}

export const getAverageRating = function (game) {
    return globalURL.get('/rating/' + game + "/average_rating");
}

export const getRating = function (game, player) {
    return globalURL.get('/rating/' + game + "/" + player);
}

export const setRating = (game, player, rating) => globalURL.post('/rating',{
    game: game,
    player: player,
    rating: rating,
    ratingon: formatDate(new Date()),
})