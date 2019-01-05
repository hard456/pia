package cz.jpalcut.pia.service;

import cz.jpalcut.pia.config.BankConfig;
import cz.jpalcut.pia.dao.TransactionDAO;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Transaction;
import cz.jpalcut.pia.service.interfaces.IAccountService;
import cz.jpalcut.pia.service.interfaces.ITransactionService;
import cz.jpalcut.pia.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Služba pro správu transakcí
 */
@Service
@Transactional
public class TransactionService implements ITransactionService {

    private TransactionDAO transactionDAO;

    private IUserService userService;

    private IAccountService accountService;

    /**
     * Konstruktor třídy
     *
     * @param userService    UserService
     * @param accountService AccountService
     * @param transactionDAO TransactionService
     */
    @Autowired
    public TransactionService(UserService userService, AccountService accountService, TransactionDAO transactionDAO) {
        this.userService = userService;
        this.accountService = accountService;
        this.transactionDAO = transactionDAO;
    }

    /**
     * Uložení transakci včetně doplněný výchozích údajů pro vytvoření transakce
     *
     * @param transaction tranksace k uložení
     * @return transakce
     */
    @Override
    public Transaction addTransaction(Transaction transaction) {
        Account account = accountService.getAccount(userService.getUser());
        account.setBlockedBalance(account.getBlockedBalance() + transaction.getValue());
        transaction.setProcessingDate(null);
        transaction.setIncome(false);
        transaction.setAccount(account);
        return transactionDAO.save(transaction);
    }

    /**
     * Uložení dvou tranksací mezi bankou včetně doplnění potřebných výchozích údajů pro vytvoření transakce
     *
     * @param firstTransaction zadaná transakce uživatele
     */
    @Override
    public void addInterBankTransaction(Transaction firstTransaction) {
        //první účet
        Account firstAccount = accountService.getAccount(userService.getUser());
        firstAccount.setBlockedBalance(firstAccount.getBlockedBalance() + firstTransaction.getValue());
        firstTransaction.setProcessingDate(null);
        firstTransaction.setIncome(false);
        firstTransaction.setAccount(firstAccount);
        transactionDAO.save(firstTransaction);

        //druhý účet
        Transaction secondTransaction = new Transaction();
        Account secondAccount = accountService.getAccountByNumber(firstTransaction.getNumber());
        secondTransaction.setProcessingDate(null);
        secondTransaction.setIncome(true);
        secondTransaction.setAccount(secondAccount);
        secondTransaction.setNumber(firstAccount.getNumber());
        secondTransaction.setCode(firstTransaction.getCode());
        secondTransaction.setValue(firstTransaction.getValue());
        secondTransaction.setDueDate(firstTransaction.getDueDate());
        secondTransaction.setVariableSymbol(firstTransaction.getVariableSymbol());
        secondTransaction.setSpecificSymbol(firstTransaction.getSpecificSymbol());
        secondTransaction.setConstantSymbol(firstTransaction.getConstantSymbol());
        transactionDAO.save(secondTransaction);
    }

    /**
     * Pravidelné schvalování transakcí podle v opakujícím se čase podle fixedDelayString
     */
    @Scheduled(fixedDelayString = "${appvar.proccess_transaction_time}")
    @Override
    public void processTransactions() {
        Account account = null;
        List<Transaction> transactions = transactionDAO.findAllByProcessingDate(null);
        for (Transaction transaction : transactions) {
            transaction.setProcessingDate(new Date(System.currentTimeMillis()));
            account = transaction.getAccount();

            if (transaction.getIncome()) {
                account.setBalance(account.getBalance() + transaction.getValue());
            } else {
                account.setBalance(account.getBalance() - transaction.getValue());
                account.setBlockedBalance(account.getBlockedBalance() - transaction.getValue());
            }

            transactionDAO.save(transaction);
        }
    }

    /**
     * Vrátí seznam transakcí podle bankovního účtu
     *
     * @param account bankování účet
     * @return seznam transakcí
     */
    @Override
    public List<Transaction> getTransactionsByAccount(Account account) {
        return transactionDAO.findAllByAccount(account);
    }

    /**
     * Vrátí transakci podle id
     *
     * @param id id transakce
     * @return transakce
     */
    @Override
    public Transaction getTransactionById(Integer id) {
        return transactionDAO.findTransactionById(id);
    }

    /**
     * Vrátí stránku transakcí k zobrazení podle omezení a bankovního účtu
     *
     * @param account  bankovní účet
     * @param pageable omezení pro výběr transakcí
     * @return stránka obsahující transakce
     */
    @Override
    public Page<Transaction> getTransactionsByAccountPageable(Account account, Pageable pageable) {
        return transactionDAO.findAllByAccount(account, pageable);
    }

}
