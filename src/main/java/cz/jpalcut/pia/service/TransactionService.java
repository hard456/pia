package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.TransactionDAO;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
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
        transaction.setProcessingDate(new Date(System.currentTimeMillis()));
        transaction.setIncome(false);
        transaction.setAccount(accountService.getAccount(userService.getUser()));
        transactionDAO.save(transaction);
    }

    public List<Transaction> getTransactionsByAccount(Account account){
        return transactionDAO.findTransactionByAccount(account);
    }

    public Transaction getTransactionById(Integer id){
        return transactionDAO.findTransactionById(id);
    }

}
