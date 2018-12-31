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

    public void changeAccountStatus(Account account){
        if(account.getActive()){
            account.setActive(false);
        }
        else{
            account.setActive(true);
        }
        accountDAO.save(account);
    }

}
