package main.jigsawsudoku.core;

import org.springframework.beans.factory.annotation.Autowired;
import main.entity.Score;
import main.service.ScoreService;

public class DataPush {

    @Autowired
    private ScoreService scoreService;

    public void pushScore(Score score){
        scoreService.addScore(score);
    }
}
