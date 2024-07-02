import globalURL from "./main.service";

export const getScoreAxio = function () {
    return globalURL.get("/winCheck/JigsawSudoku/score");
}