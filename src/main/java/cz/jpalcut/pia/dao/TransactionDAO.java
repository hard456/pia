package cz.jpalcut.pia.dao;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransactionDAO extends PagingAndSortingRepository<Transaction, Integer> {

    Page<Transaction> findAllByAccount(Account account, Pageable pageable);

    Transaction findTransactionById(Integer id);

    List<Transaction> findTransactionByProcessingDate(Date date);

    List<Transaction> findAllByAccount(Account account);
}
