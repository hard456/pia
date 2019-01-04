package cz.jpalcut.pia.dao;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRequestDAO extends JpaRepository<UserRequest, Integer> {

    Page<UserRequest> findAll(Pageable pageable);

    UserRequest findUserRequestById(Integer id);

    List<UserRequest> findAllByAccount(Account account);

    UserRequest findUserRequestByTypeAndAccount(String type, Account account);

}
