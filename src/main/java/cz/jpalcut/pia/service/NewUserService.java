package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.AccountDAO;
import cz.jpalcut.pia.dao.RoleDAO;
import cz.jpalcut.pia.dao.UserDAO;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.User;
import cz.jpalcut.pia.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class NewUserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    RoleDAO roleDAO;

    public void addUser(User user){
        String tmp;

        //Generování přihlašovacího loginu uživatele
        while(true){
            tmp = Utils.generateNumber(8);
            if(userDAO.findUserByLoginId(tmp) == null){
                user.setLoginId(tmp);
                break;
            }
        }

        //Vytvoření hesla uživatele
        user.setPin(Utils.hashPassword(Utils.generateNumber(5)));

        user.setRoleList(roleDAO.findRoleListByName("USER"));
        user = userDAO.save(user);

        Account account = new Account();
        account.setUser(user);
        account.setActive(true);
        account.setBalance(0.00);

        //Generování čísla účtu
        while(true){
            tmp = Utils.generateNumber(9);
            if(accountDAO.findAccountByNumber(tmp) == null){
                account.setNumber(tmp);
                break;
            }
        }

        //Generování čísla kreditní karty
        while(true){
            tmp = Utils.generateNumber(16);
            if(accountDAO.findAccountByCardNumber(tmp) == null){
                account.setCardNumber(tmp);
                break;
            }
        }
        accountDAO.save(account);
    }

}
