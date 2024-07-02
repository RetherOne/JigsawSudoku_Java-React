package main.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import main.entity.Account;
import main.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountServiceRest {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public void addAccount(@RequestBody Account account){
        accountService.addAccount(account);
    }

    @GetMapping("/{player}/{password}/accountCheck")
    public boolean accountCheck(@PathVariable String player,@PathVariable String password) {
        return accountService.accountCheck(player, password);
    }
    @GetMapping("/{player}/existAccount")
    public boolean existAccount(@PathVariable String player){
        return accountService.existAccount(player);
    }

}
