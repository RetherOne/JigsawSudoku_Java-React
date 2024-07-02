//get /{player}/{password}/accountCheck
//get /{player}/existAccount
//post /api/account
import globalURL from "./main.service";

export const accountCheck = function (player, password) {
    return globalURL.get("account/" + player + '/' + password + '/accountCheck/');
}

export const existAccount = function (player) {
    return globalURL.get("account/" +player + '/existAccount');
}

export const addAccount = (player, password) => globalURL.post('/account',{
    player: player,
    password: password,
})