package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.TransactionDAO;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class TransactionService {

    @Autowired
    TransactionDAO transactionDAO;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    public void addTransaction(Transaction transaction){
        Account account = accountService.getAccount(userService.getUser());
        account.setBlockedBalance(account.getBlockedBalance()+transaction.getValue());
        transaction.setProcessingDate(null);
        transaction.setIncome(false);
        transaction.setAccount(account);
        transactionDAO.save(transaction);
    }

    public void addInterBankTransaction(Transaction firstTransaction){
        //první účet
        Account firstAccount = accountService.getAccount(userService.getUser());
        firstAccount.setBlockedBalance(firstAccount.getBlockedBalance()+firstTransaction.getValue());
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
        secondTransaction.setMaturity(firstTransaction.getMaturity());
        secondTransaction.setVariableSymbol(firstTransaction.getVariableSymbol());
        secondTransaction.setSpecificSymbol(firstTransaction.getSpecificSymbol());
        secondTransaction.setConstantSymbol(firstTransaction.getConstantSymbol());
        transactionDAO.save(secondTransaction);
    }

    @Scheduled(fixedDelay = 30000)
    public void processTransactions(){
        Account account = null;
        List<Transaction> transactions = transactionDAO.findTransactionByProcessingDate(null);
        for (Transaction transaction : transactions) {
            transaction.setProcessingDate(new Date(System.currentTimeMillis()));
            account = transaction.getAccount();

            if(transaction.getIncome()){
                account.setBalance(account.getBalance()+transaction.getValue());
            }
            else{
                account.setBalance(account.getBalance()-transaction.getValue());
                account.setBlockedBalance(account.getBlockedBalance()-transaction.getValue());
            }

            transactionDAO.save(transaction);
        }
    }

    public List<Transaction> getTransactionsByAccount(Account account){
        return transactionDAO.findTransactionByAccount(account);
    }

    public Transaction getTransactionById(Integer id){
        return transactionDAO.findTransactionById(id);
    }

}
