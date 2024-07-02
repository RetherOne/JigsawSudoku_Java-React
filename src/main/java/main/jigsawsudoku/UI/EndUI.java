package main.jigsawsudoku.UI;

import main.service.CommentService;
import main.service.RatingService;
import main.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import main.entity.Comment;
import main.entity.Rating;
import main.entity.Score;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class EndUI {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private Rating ratingDB;

    @Autowired
    private Comment commetDB;

    private Scanner sysInput = new Scanner(System.in);

    private static final String ANSI_GREEN = "\u001B[38;5;46m";
    private static final String ANSI_WHITE = "\u001B[38;5;231m";
    private static final String ANSI_YELLOW = "\u001B[38;5;226m";
    private static final String ANSI_BLUE = "\u001B[38;5;39m";
    private static final String ANSI_RED = "\u001B[38;5;196m";



    public boolean end(String game, String player){
        System.out.println(ANSI_YELLOW + "!Top 10 scores!" + ANSI_WHITE);
        for (Score score : scoreService.getTopScores(game)){
            System.out.println(ANSI_BLUE + "Player: " + ANSI_GREEN + score.getPlayer() + "," + ANSI_WHITE + " score: " + ANSI_RED + score.getPoints() + ANSI_WHITE +", date: " + score.getPlayedOn());
        }

        System.out.println(ANSI_YELLOW + "Comments: " + ANSI_WHITE);
        List<Comment> comments = commentService.getComments(game);
        for(Comment comment : comments){
            System.out.println(ANSI_GREEN+ comment.getPlayer() + ANSI_WHITE + ": " + comment.getComment() + " Date:" + comment.getCommentedon());
        }
        System.out.println(ANSI_YELLOW + "Average rating: " + ratingService.getAverageRating(game) + ANSI_WHITE);

        System.out.print("If you want to leave a comment and rating, enter \"y\".\nOr any other if you don't want: ");
        String input = sysInput.nextLine();
        if (input.equals("y")){
            System.out.print("Please enter a comment(less than 300 characters): ");
            String comment = sysInput.nextLine();
            if (comment.length()>300){
                System.out.println("Сomment more than 300 characters!");
                return false;
            }
            System.out.print("Please enter a rating(0 - 5): ");
            String rating = sysInput.nextLine();
            if (!rating.matches("[0-5]")){
                System.out.print("Incorrect input rating!");
                return false;
            }

            Timestamp time =  new Timestamp(System.currentTimeMillis());

            ratingDB.setGame(game);
            ratingDB.setPlayer(player);
            ratingDB.setRating(Integer.parseInt(rating));
            ratingDB.setRatingon(time);

            ratingService.setRating(ratingDB);


            commetDB.setGame(game);
            commetDB.setPlayer(player);
            commetDB.setComment(comment);
            commetDB.setCommentedon(time);

            commentService.addComment(commetDB);

            System.out.println("All complite!");
            return true;
        }
        return true;
    }

    public void win(){
        String[] rainbowColors = {
                "\u001B[38;5;196m",
                "\u001B[38;5;208m",
                "\u001B[38;5;226m",
                "\u001B[38;5;46m",
                "\u001B[38;5;39m",
                "\u001B[38;5;201m"
        };
        String reset = "\u001B[38;5;231m";

        String text = "Congratulations! You won!";

        for (int i = 0; i < text.length(); i++) {
            System.out.print(rainbowColors[i % rainbowColors.length] + text.charAt(i));
        }
        System.out.print(reset);
    }

    public void exit(){
        System.out.println("You're out of the game!");
    }

}
