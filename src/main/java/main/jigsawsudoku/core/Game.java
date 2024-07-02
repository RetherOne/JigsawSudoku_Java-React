package main.jigsawsudoku.core;

import main.jigsawsudoku.Content.Field;
import main.jigsawsudoku.Content.Tile;
import main.jigsawsudoku.Enums.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import main.entity.Account;
import main.entity.Score;
import main.jigsawsudoku.UI.EndUI;
import main.jigsawsudoku.UI.ContentUI;
import main.jigsawsudoku.UI.EnterUI;


import java.io.File;
import java.sql.Timestamp;
import java.util.Scanner;

public class Game {

    @Autowired
    private EnterUI enter;

    @Autowired
    private EndUI endUI;

    @Autowired
    private DataPush dataPush;

    @Autowired
    private Score score;

    private static final String ANSI_RED = "\u001B[38;5;196m";
    private static final String ANSI_GREEN = "\u001B[38;5;46m";
    private static final String ANSI_YELLOW = "\u001B[38;5;226m";
    private static final String ANSI_ORANGE = "\u001B[38;5;208m";
    private static final String ANSI_BLUE = "\u001B[38;5;39m";
    private static final String ANSI_PURPLE = "\u001B[38;5;201m";
    private static final String ANSI_WHITE = "\u001B[38;5;231m";

    private Scanner sysInput = new Scanner(System.in);

    private int rowInt = -1;

    private int columnInt = -1;

    private int valueInt = -1;

    private int scoreInt = 0;

    private int difficulty = 0;

    private File level = null;

    private String gameName = "JigsawSudoku";

    private GameState status = GameState.INPROCESS;


    private boolean handleInput(){

        System.out.print("Row input: ");
        String row = sysInput.nextLine();
        if (row.equals("e")){
            status = GameState.EXIT;
            return false;
        }
        else if (!row.matches("[1-9]")) {
            System.out.println("Row error: Incorrect input! Please enter a number between 1 and 9.");
            return false;
        }
        rowInt = Integer.parseInt(row) - 1;

        System.out.print("Column input: ");
        String column = sysInput.nextLine();
        if (column.equals("e")){
            status = GameState.EXIT;
            return false;
        }
        else if (!column.matches("[1-9]")) {
            System.out.println("Column error: Incorrect input! Please enter a number between 1 and 9.");
            return false;
        }
        columnInt = Integer.parseInt(column) - 1;

        System.out.print("Value input: ");
        String value = sysInput.nextLine();
        if (value.equals("e")){
            status = GameState.EXIT;
            return false;
        }
        else if (!value.matches("[1-9]")) {
            System.out.println("Value error: Incorrect input! Please enter a number between 1 and 9.");
            return false;
        }
        valueInt = Integer.parseInt(value);

        return true;
    }

    public GameState isSolved(ContentUI content){
        Tile[][] mapArray = content.getMapArray();
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (mapArray[i][j].getState().equals("HIDDEN")){
                    return GameState.INPROCESS;
                }
            }
        }
        return GameState.SOLVED;
    }

    private void reset(){
        sysInput = new Scanner(System.in);

        rowInt = -1;

        columnInt = -1;

        valueInt = -1;

        scoreInt = 0;

        difficulty = 0;

        level = null;

        status = GameState.INPROCESS;
    }

    public void play() {
        Account account = null;

        while (account == null) {
            account = enter.signIn();
            if (account == null) {
                System.out.println("Something wrong!");
            }
        }

        while (status == GameState.INPROCESS) {
            while (level == null) {
                level = enter.getLevel();
            }
            while (difficulty <= 0) {
                difficulty = enter.getDifficulty();
            }

            Field field = new Field(level, difficulty);
            ContentUI content = new ContentUI(field);


            while (status != GameState.SOLVED && status != GameState.EXIT) {
                content.render(scoreInt);
                if (handleInput()) {
                    if (content.getField().mapSet(rowInt, columnInt, valueInt)) {
                        scoreInt += 10 * difficulty;
                    }
                }
                if (status != GameState.EXIT) {
                    status = isSolved(content);
                }
            }

            if (status == GameState.SOLVED) {
                endUI.win();
            } else if (status == GameState.EXIT) {
                endUI.exit();
            }

            score.setGame(gameName);
            score.setPlayer(account.getPlayer());
            score.setPoints(scoreInt);
            score.setPlayedOn(new Timestamp(System.currentTimeMillis()));

            dataPush.pushScore(score);

            while (!endUI.end(gameName, account.getPlayer())) {
                System.out.println("Something wrong with comment or rating!");
            }
            System.out.print("If you want to continue the game, please enter \"y\"\nOr any other if you don't want: ");
            String restart = sysInput.nextLine();
            if (restart.equals("y")){
                System.out.println(ANSI_WHITE + "╒════════════════════════════════════════════════╕");
                System.out.println("|         "+ ANSI_RED + "__"+ ANSI_ORANGE +" ____" + ANSI_YELLOW + " ______" + ANSI_GREEN + " _____" + ANSI_BLUE + "  ___" + ANSI_PURPLE + "  _       __" + ANSI_WHITE + "  |");
                System.out.println("|        " + ANSI_RED + "/ /"+ ANSI_ORANGE +"/  _/" + ANSI_YELLOW + "/ ____/" + ANSI_GREEN + "/ ___/" + ANSI_BLUE + " /   |" + ANSI_PURPLE + "| |     / /" + ANSI_WHITE + "  |");
                System.out.println("|   " + ANSI_RED + "__  / /"+ ANSI_ORANGE +" / /" + ANSI_YELLOW + " / / __" + ANSI_GREEN + "  \\__ \\" + ANSI_BLUE + " / /| |" + ANSI_PURPLE + "| | /| / /" + ANSI_WHITE + "   |");
                System.out.println("|  " + ANSI_RED + "/ /_/ /"+ ANSI_ORANGE +"_/ /" + ANSI_YELLOW + " / /_/ /" + ANSI_GREEN + " ___/ /" + ANSI_BLUE + "/ ___ |" + ANSI_PURPLE + "| |/ |/ /" + ANSI_WHITE + "    |");
                System.out.println("|  " + ANSI_RED + "\\____/"+ ANSI_ORANGE +"/___/" + ANSI_YELLOW + " \\____/" + ANSI_GREEN + " /____/" + ANSI_BLUE + "/_/  |_|" + ANSI_PURPLE + "|__/|__/" + ANSI_WHITE + "     |");
                System.out.println("|                                       ...sudoku|");
                System.out.println("╘════════════════════════════════════════════════╛");

                reset();

            }
        }
    }
}
