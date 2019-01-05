package cz.jpalcut.pia.dao;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Rozhraní pro přístup k uživatelským žádostím
 */
@Repository
public interface UserRequestDAO extends JpaRepository<UserRequest, Integer> {

    /**
     * Vrátí stránku uživatelských požadavků podle omezení
     *
     * @param pageable omezení pro výběr uživatelských požadavků
     * @return stánka uživatelských požadavků
     */
    Page<UserRequest> findAll(Pageable pageable);

    /**
     * Vrátí uživatelský požadavek podle id
     *
     * @param id id uživatelského požadavku
     * @return uživatelský požadavek
     */
    UserRequest findUserRequestById(Integer id);

    List<UserRequest> findAllByAccount(Account account);

    UserRequest findUserRequestByTypeAndAccount(String type, Account account);

}
