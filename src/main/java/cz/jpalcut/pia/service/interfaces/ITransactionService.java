package cz.jpalcut.pia.service.interfaces;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Rozhraní pro službu spravující transakce
 */
public interface ITransactionService {

    /**
     * Uložení transakcei
     *
     * @param transaction tranksace k uložení
     * @return transakce
     */
    Transaction addTransaction(Transaction transaction);

    /**
     * Uložení dvou tranksací mezi bankou
     *
     * @param firstTransaction zadaná transakce uživatele
     */
    void addInterBankTransaction(Transaction firstTransaction);

    /**
     * Pravidelné schvalování transakcí podle v opakujícím
     */
    void processTransactions();

    /**
     * Vrátí seznam transakcí podle bankovního účtu
     *
     * @param account bankování účet
     * @return seznam transakcí
     */
    List<Transaction> getTransactionsByAccount(Account account);

    /**
     * Vrátí transakci podle id
     *
     * @param id id transakce
     * @return transakce
     */
    Transaction getTransactionById(Integer id);

    /**
     * Vrátí stránku transakcí k zobrazení podle omezení a bankovního účtu
     *
     * @param account  bankovní účet
     * @param pageable omezení pro výběr transakcí
     * @return stránka obsahující transakce
     */
    Page<Transaction> getTransactionsByAccountPageable(Account account, Pageable pageable);

}
