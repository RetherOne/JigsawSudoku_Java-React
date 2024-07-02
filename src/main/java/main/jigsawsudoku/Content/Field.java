package main.jigsawsudoku.Content;

import main.jigsawsudoku.Enums.TileState;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Field {

    private Tile[][] mapArray;
    private File level;
    private int rows = 9;
    private int columns = 9;

    private int difficulty;

    public Field(File level, int inputDifficulty) {
        this.level = level;
        if (inputDifficulty == 1){
            this.difficulty = 4;
        } else if (inputDifficulty == 2) {
            this.difficulty = 3;
        }else if (inputDifficulty == 3) {
            this.difficulty = 2;
        }

    }

    private int magicNum = 6;
    private Scanner scanLevel = null;


    public Tile[][] readLevel() {
        mapArray = new Tile[rows][columns];
        String lineLevel;
        Random random = new Random();
        int randomNumber = random.nextInt(magicNum);
        try {
            scanLevel = new Scanner(level);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int i = 0, j = 0;
        for (; i < rows; i++) {
            lineLevel = scanLevel.nextLine();
            lineLevel = scanLevel.nextLine();
            for (char num : lineLevel.toCharArray()) {
                if (num <= '9' && num >= '1') {
                    if (randomNumber <= difficulty) {
                        mapArray[i][j] = new Tile(TileState.SHOWN, num);
                    } else {
                        mapArray[i][j] = new Tile(TileState.HIDDEN, num);
                    }
                    randomNumber = random.nextInt(magicNum);
                    j++;
                }
            }

            j = 0;
        }
        return mapArray;
    }

    public String[][] getColorMap(File level){
        String[][] result = new String[9][9];
        Scanner scanLevel;
        try {
            scanLevel = new Scanner(level);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String fileLine;
        for (int i = 0; i < 9; i++) {
            fileLine = scanLevel.nextLine();
            String[] colorLine = fileLine.split(" ");
            for (int j = 0; j < 9; j++) {
                result[i][j] = colorLine[j];
            }
        }
        return result;
    }

    public boolean mapSet(int row, int column, int value){
        if (mapArray[row][column].getIntValue() == value){
            mapArray[row][column].setState(TileState.SHOWN);
            return true;
        }
        return false;
    }

    public void backSetMap(String[][] backMap){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (backMap[i][j] == " "){
                    mapArray[i][j].setState(TileState.HIDDEN);
                }
            }
        }

    }

    public File getLevel() {
        return level;
    }

    public boolean isSolved(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (mapArray[i][j].getState().equals("HIDDEN")){
                    return false;
                }
            }
        }
        return true;
    }

    public void setLevel(File level) {
        this.level = level;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}