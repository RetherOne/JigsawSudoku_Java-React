package main.service;

import main.entity.Account;

public interface AccountService {
    void addAccount(Account account);

    boolean existAccount(String player);

    boolean accountCheck(String player, String password);

    void reset();
}
