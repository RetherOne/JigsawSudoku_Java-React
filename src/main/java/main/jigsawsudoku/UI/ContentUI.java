package main.jigsawsudoku.UI;

import main.jigsawsudoku.Content.Field;
import main.jigsawsudoku.Content.Tile;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ContentUI {

    private static final String ANSI_WHITE = "\u001B[38;5;231m";
    private static final String ANSI_GREEN = "\u001B[38;5;46m";
    private static final String ANSI_YELLOW = "\u001B[38;5;226m";

    private Field field;

    private Tile[][] mapArray;

    public ContentUI(Field field) {
        this.field = field;
        mapArray = field.readLevel();
    }

    public Field getField() {
        return field;
    }

    public void render(int score) {
        Scanner scanLevel;
        String lineLevel;
        String preLineLevel = null;
        char symbol = ' ';


        try {
            scanLevel = new Scanner(field.getLevel());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.print("  ");
        for (int i = 1; i <= 9; i++){
            System.out.print("  " + ANSI_GREEN + i + ANSI_WHITE + " ");
        }
        System.out.println(ANSI_YELLOW + "      score:" + ANSI_WHITE + score);

        for (int i = 0; i < 9; i++) {
            lineLevel = scanLevel.nextLine();
            System.out.print(ANSI_GREEN + "  +" + ANSI_WHITE);
            for (int j = 0; j < lineLevel.length(); j++) {

                if (lineLevel.charAt(j) == '-') {
                    System.out.print(ANSI_GREEN + "———" + ANSI_WHITE);
                } else if (lineLevel.charAt(j) == '.') {
                    System.out.print("---");
                } else if (lineLevel.charAt(j) == ' ') {
                    continue;
                }

                if (lineLevel.length() == j + 1 || (preLineLevel != null && j + 1 < preLineLevel.length() && preLineLevel.charAt(j + 1) == '|') || lineLevel.charAt(j) == '-' || (j + 1 < lineLevel.length() && lineLevel.charAt(j + 1) == '-') || (j + 2 < lineLevel.length() && lineLevel.charAt(j + 1) == ' ' && lineLevel.charAt(j + 2) == '-')) {
                    System.out.print(ANSI_GREEN + "+" + ANSI_WHITE);
                } else {
                    System.out.print("+");
                }
            }
            System.out.print(ANSI_GREEN + "\n" + ANSI_WHITE);

            lineLevel = scanLevel.nextLine();
            preLineLevel = lineLevel;


            System.out.print(ANSI_GREEN + (i+1) + " |" + ANSI_WHITE);
            for (int j = 0, q = 0; j < lineLevel.length(); j++) {
                if (lineLevel.charAt(j) != '|') {
                    if (mapArray[i][q].getState().equals("SHOWN")){
                        symbol = mapArray[i][q].getValue();
                    }
                    else {
                        symbol = ' ';
                    }
                    q++;

                    if (j + 1 < lineLevel.length() && lineLevel.charAt(j + 1) == '|') {

                        System.out.print(" " + symbol + ANSI_GREEN + " |" + ANSI_WHITE);
                    } else {
                        if (j == lineLevel.length() - 1) {
                            System.out.print(" " + symbol + ANSI_GREEN + " |" + ANSI_WHITE);
                        } else {
                            System.out.print(" " + symbol + " |");
                        }
                    }
                }
            }

            System.out.print("\n");
        }
        System.out.print("  ");
        for (int j = 0; j < 9; j++) {
            System.out.print(ANSI_GREEN + "+———" + ANSI_WHITE);
        }
        System.out.print(ANSI_GREEN + "+\n" + ANSI_WHITE);
    }

    public Tile[][] getMapArray(){
        return mapArray;
    }

}
