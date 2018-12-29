package cz.jpalcut.pia.dao;

import cz.jpalcut.pia.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<Account, Long> {

}
