package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.AccountDAO;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    AccountDAO accountDAO;

    public Account getAccount(User user){
        return accountDAO.findAccountByUser(user);
    }

    public Account getAccountByNumber(String number){
        return accountDAO.findAccountByNumber(number);
    }

    public Account getAccountByCardNumber(String number){
        return accountDAO.findAccountByCardNumber(number);
    }

    public Account save(Account account){
        return accountDAO.save(account);
    }

    public Account getAccountById(Integer id){
        return accountDAO.findAccountById(id);
    }

}
