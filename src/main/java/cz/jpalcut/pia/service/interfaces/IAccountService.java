package cz.jpalcut.pia.service.interfaces;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.User;

/**
 * Rozhraní pro službu spravující bankovní účty
 */
public interface IAccountService {


    /**
     * Vratí bankovní účet podle uživatele
     *
     * @param user uživatel
     * @return bankovní účet
     */
    Account getAccount(User user);

    /**
     * Vrátí bankovní účet podle čísla účtu
     *
     * @param number číslo bankovního účtu
     * @return bankovní účet
     */
    Account getAccountByNumber(String number);

    /**
     * Vrátí bankovní účet podle čísla kreditní karty
     *
     * @param number číslo kreditnní karty
     * @return bankovní účet
     */
    Account getAccountByCardNumber(String number);

    /**
     * Uloží nebo úpraví bankovní účet
     *
     * @param account bankovní účet
     * @return bankovní účet
     */
    Account save(Account account);

    /**
     * Vrátí bankovní účet podle ID
     *
     * @param id indentifikace účtu
     * @return bankovní účet
     */
    Account getAccountById(Integer id);

}
