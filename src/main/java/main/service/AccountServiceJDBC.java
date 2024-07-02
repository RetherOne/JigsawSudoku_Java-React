package main.service;


import main.entity.Account;

import java.sql.*;

public class AccountServiceJDBC implements AccountService{
    public static final String URL = "jdbc:postgresql://localhost:5432/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String SELECT_CHECK = "SELECT 1 FROM account WHERE player = ?";
    public static final String INSERT = "INSERT INTO account (player, password) VALUES (?, ?)";
    public static final String DELETE = "DELETE FROM account";


    @Override
    public void addAccount(Account account) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT)
        ) {
            statement.setString(1, account.getPlayer());
            statement.setString(2, account.getPassword());
            statement.executeUpdate();
//            return true;
        } catch (SQLException e) {
            System.out.println("User already exist!");
//            return false;
        }
    }

    @Override
    public boolean existAccount(String player){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_CHECK)) {
                statement.setString(1, player);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean accountCheck(String player, String password) {
        return false;
    }

    @Override
    public void reset() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new CommentException("Problem deleting comment", e);
        }
    }
}
