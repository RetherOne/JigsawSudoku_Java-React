package main.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@NamedQuery( name = "Account.checkAccount",
        query = "SELECT COUNT(a) FROM Account a WHERE a.player =: player")
@NamedQuery( name = "Account.accountCheck",
        query = "SELECT COUNT(a) FROM Account a WHERE a.player =: player AND a.password =: password")
@NamedQuery( name = "Account.resetAccount",
        query = "DELETE FROM Account")

public class Account implements Serializable{

    @Id
    @GeneratedValue
    private int ident;

    @Column(unique = true)
    private String player;

    private String password;

    public Account(String player, String password) {
        this.player = player;
        this.password = password;
    }

    public Account(){}

    public void setPlayer(String player) {
        this.player = player;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayer() {
        return player;
    }

    public String getPassword() {
        return password;
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }
}
