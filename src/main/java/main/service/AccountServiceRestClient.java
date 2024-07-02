package main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import main.entity.Account;

public class AccountServiceRestClient implements AccountService{

    private final String url = "http://localhost:8080/api/account";

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void addAccount(Account account) {
        restTemplate.postForEntity(url, account, Account.class);
    }

    @Override
    public boolean existAccount(String player) {
        return restTemplate.getForEntity(url + "/" + player  +"/existAccount", Boolean.class).getBody();
    }

    @Override
    public boolean accountCheck(String player, String password) {
        return restTemplate.getForEntity(url + "/" + player + "/" + password +"/accountCheck", Boolean.class).getBody();
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");
    }
}
