package main.service;

import main.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class AccountServiceJPA implements AccountService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addAccount(Account account) {
            entityManager.persist(account);
    }
    @Override
    public boolean existAccount(String player){
        Long result = (Long) entityManager.createNamedQuery("Account.checkAccount")
                .setParameter("player", player).getSingleResult();
        return result.intValue() > 0;
    }

    @Override
    public boolean accountCheck(String player, String password) {
        Long result = (Long) entityManager.createNamedQuery("Account.accountCheck")
                .setParameter("player", player).setParameter("password", password).getSingleResult();
        return result.intValue() > 0;
    }

    @Override
    public void reset(){
        entityManager.createNamedQuery("Account.resetAccount").executeUpdate();;
    }
}
