package main.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@NamedQuery( name = "Rating.getAverageRating",
        query = "SELECT AVG(r.rating) FROM Rating r WHERE r.game=:game")
@NamedQuery( name = "Rating.getRating",
        query = "SELECT r.rating FROM Rating r WHERE r.game =: game AND r.player =: player")
@NamedQuery( name = "Rating.resetScores",
        query = "DELETE FROM Rating")
@NamedQuery( name = "Rating.getExistRating",
        query = "SELECT r FROM Rating r WHERE r.game =: game AND r.player =: player")
public class Rating implements Serializable {

    @Id
    @GeneratedValue
    private int ident;
    private String game;

    private String player;

    private int rating;

    private Timestamp ratingon;

    public Rating(String game, String player, int rating, Timestamp ratingon) {
        this.game = game;
        this.player = player;
        this.rating = rating;
        this.ratingon = ratingon;
    }
    public  Rating(){}

    public String getGame() {
        return game;
    }

    public String getPlayer() {
        return player;
    }

    public int getRating() {
        return rating;
    }

    public Timestamp getRatingon() {
        return ratingon;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setRatingon(Timestamp ratingon) {
        this.ratingon = ratingon;
    }
}
