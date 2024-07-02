/**
 ╒════════════════════════════════════════════════╕
 |         __ ____ ______ _____  ___  _       __  |
 |        / //  _// ____// ___/ /   || |     / /  |
 |   __  / / / / / / __  \__ \ / /| || | /| / /   |
 |  / /_/ /_/ / / /_/ / ___/ // ___ || |/ |/ /    |
 |  \____//___/ \____/ /____//_/  |_||__/|__/     |
 |                                       ...sudoku|
 ╘════════════════════════════════════════════════╛
 **/

package main.jigsawsudoku.UI;

import org.springframework.beans.factory.annotation.Autowired;
import main.entity.Account;
import main.service.AccountService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EnterUI {

    private static final String ANSI_RED = "\u001B[38;5;196m";
    private static final String ANSI_GREEN = "\u001B[38;5;46m";
    private static final String ANSI_YELLOW = "\u001B[38;5;226m";
    private static final String ANSI_ORANGE = "\u001B[38;5;208m";
    private static final String ANSI_BLUE = "\u001B[38;5;39m";
    private static final String ANSI_PURPLE = "\u001B[38;5;201m";
    private static final String ANSI_WHITE = "\u001B[38;5;231m";

    private Scanner sysInput = new Scanner(System.in);

    @Autowired
    private AccountService accountService;

    @Autowired
    private Account account;


    private String login;
    private String password;



    public Account signIn(){


        System.out.println(ANSI_WHITE + "╒════════════════════════════════════════════════╕");
        System.out.println("|         "+ ANSI_RED + "__"+ ANSI_ORANGE +" ____" + ANSI_YELLOW + " ______" + ANSI_GREEN + " _____" + ANSI_BLUE + "  ___" + ANSI_PURPLE + "  _       __" + ANSI_WHITE + "  |");
        System.out.println("|        " + ANSI_RED + "/ /"+ ANSI_ORANGE +"/  _/" + ANSI_YELLOW + "/ ____/" + ANSI_GREEN + "/ ___/" + ANSI_BLUE + " /   |" + ANSI_PURPLE + "| |     / /" + ANSI_WHITE + "  |");
        System.out.println("|   " + ANSI_RED + "__  / /"+ ANSI_ORANGE +" / /" + ANSI_YELLOW + " / / __" + ANSI_GREEN + "  \\__ \\" + ANSI_BLUE + " / /| |" + ANSI_PURPLE + "| | /| / /" + ANSI_WHITE + "   |");
        System.out.println("|  " + ANSI_RED + "/ /_/ /"+ ANSI_ORANGE +"_/ /" + ANSI_YELLOW + " / /_/ /" + ANSI_GREEN + " ___/ /" + ANSI_BLUE + "/ ___ |" + ANSI_PURPLE + "| |/ |/ /" + ANSI_WHITE + "    |");
        System.out.println("|  " + ANSI_RED + "\\____/"+ ANSI_ORANGE +"/___/" + ANSI_YELLOW + " \\____/" + ANSI_GREEN + " /____/" + ANSI_BLUE + "/_/  |_|" + ANSI_PURPLE + "|__/|__/" + ANSI_WHITE + "     |");
        System.out.println("|                                       ...sudoku|");
        System.out.println("╘════════════════════════════════════════════════╛");



        System.out.println("If you want to register, enter \"r\"\n" + "If you want sign in enter \"s\"");
        System.out.print("Your choice: ");
        String choice = sysInput.nextLine();
        if (choice.equals("r")){
            System.out.print("Please enter login: ");
            login = sysInput.nextLine();

            System.out.print("Please enter password: ");
            password = sysInput.nextLine();
            if (login == null || password == null) {
                return null;
            }

            account.setPlayer(login);
            account.setPassword(password);
            if (!accountService.existAccount(account.getPlayer())){
                accountService.addAccount(account);
                return account;
            }
            else{
                System.out.println("User already exists!");
            }
        }
        else if (choice.equals("s")){
            System.out.print("Please enter login: ");
            login = sysInput.nextLine();

            System.out.print("Please enter password: ");
            password = sysInput.nextLine();

            if (login == null || password == null) {
                return null;
            }

            account.setPlayer(login);
            account.setPassword(password);
            if (accountService.accountCheck(account.getPlayer(), account.getPassword())){
                return account;
            }
            else {
                System.out.println("Incorrect password or login!");
            }

        }
        else{
            System.out.println("Incorrect input!");
        }
        return null;
    }

    public File getLevel(){
        System.out.println(ANSI_WHITE + "Now choose level!");
        System.out.println("╒════════╕ " + " ╒════════╕ " + " ╒════════╕");
        System.out.println("|" + ANSI_GREEN + " level1" + ANSI_WHITE + " | " + " |" + ANSI_YELLOW + " level2" + ANSI_WHITE + " | " + " |" + ANSI_RED + " level3" + ANSI_WHITE + " | ");
        System.out.println("╘════════╛ " + " ╘════════╛ " + " ╘════════╛");
        System.out.print("Enter 1, 2 or 3:");
        String input = sysInput.nextLine();
        while (input.matches("[1-3]")){
            String levelPath = ".\\src\\main\\java\\sk\\tuke\\gamestudio\\game\\jigsawsudoku\\levels\\level" + input;
            File file = new File(levelPath);
            try {
                Scanner scan = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("File error: File not exist!");
                return null;
            }
            return file;
        }
        return null;

    }

    public int getDifficulty(){
        System.out.print(ANSI_WHITE + "Choice game difficulty(1 - easy, 2 - middle, 3 - hard): ");
        String input = sysInput.nextLine();
        if (input.matches("[1-3]")){
            return Integer.parseInt(input);
        }
        return -1;
    }
}
