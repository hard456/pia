package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.AccountDAO;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Služba pro správu bankovních účtů
 */
@Service
@Transactional
public class AccountService {

    @Autowired
    AccountDAO accountDAO;

    /**
     * Vratí bankovní účet podle uživatele
     *
     * @param user uživatel
     * @return bankovní účet
     */
    public Account getAccount(User user) {
        return accountDAO.findAccountByUser(user);
    }

    /**
     * Vrátí bankovní účet podle čísla účtu
     *
     * @param number číslo bankovního účtu
     * @return bankovní účet
     */
    public Account getAccountByNumber(String number) {
        return accountDAO.findAccountByNumber(number);
    }

    /**
     * Vrátí bankovní účet podle čísla kreditní karty
     *
     * @param number číslo kreditnní karty
     * @return bankovní účet
     */
    public Account getAccountByCardNumber(String number) {
        return accountDAO.findAccountByCardNumber(number);
    }

    /**
     * Uloží nebo úpraví bankovní účet
     *
     * @param account bankovní účet
     * @return bankovní účet
     */
    public Account save(Account account) {
        return accountDAO.save(account);
    }

    /**
     * Vrátí bankovní účet podle ID
     *
     * @param id indentifikace účtu
     * @return bankovní účet
     */
    public Account getAccountById(Integer id) {
        return accountDAO.findAccountById(id);
    }

}
