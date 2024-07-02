package main.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NamedQuery( name = "Comment.getComments",
        query = "SELECT c FROM Comment c WHERE c.game=:game")
@NamedQuery( name = "Comment.resetScores",
        query = "DELETE FROM Comment")

public class Comment implements Serializable {

    @Id
    @GeneratedValue
    private int ident;
    private String game;

    private String player;

    private String comment;

    private Timestamp commentedon;

    public Comment(String game, String player, String comment, Timestamp commentedon){
        this.game = game;
        this.player = player;
        this.comment = comment;
        this.commentedon = commentedon;
    }

    public Comment(){}

    public String getGame() {
        return game;
    }

    public String getPlayer() {
        return player;
    }

    public String getComment() {
        return comment;
    }

    public Timestamp getCommentedon() {
        return commentedon;
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

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentedon(Timestamp commentedon) {
        this.commentedon = commentedon;
    }
}
