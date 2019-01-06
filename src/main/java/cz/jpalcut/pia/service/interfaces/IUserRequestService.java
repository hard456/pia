package cz.jpalcut.pia.service.interfaces;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Rozhraní pro službu spravující požadavky uživatelů
 */
public interface IUserRequestService {

    /**
     * Vrátí stránku žádostí uživatelů k zobrazení podle omezení
     *
     * @param pageable omezení pro výběr uživatelů
     * @return stránka obsahující žádosti uživatele
     */
    Page<UserRequest> getAllUserRequestPageable(Pageable pageable);

    /**
     * Vrátí žádost uživatele podle id
     *
     * @param id id žádosti
     * @return žádost uživatele
     */
    UserRequest getUserRequestById(Integer id);

    /**
     * Uloží žádost uživatele
     *
     * @param userRequest žádost uživatele k uložení
     * @return žádost uživatele
     */
    UserRequest saveUserRequest(UserRequest userRequest);

    /**
     * Smaže žádost uživatele
     *
     * @param userRequest žádost uživatele k smazáí
     */
    void deleteUserRequest(UserRequest userRequest);

    /**
     * Vrátí všechny žádosti uživatele podle bankovního účtu
     *
     * @param account bankovní účet
     * @return seznam žádostí uživatele
     */
    List<UserRequest> getUserRequestsByAcount(Account account);

    /**
     * Vrátí žádost uživatele podle typu a bankovního účtu
     *
     * @param type    typ žádosti
     * @param account bankovní účet
     * @return žádost uživatele
     */
    UserRequest getUserRequestByTypeAndAccount(String type, Account account);

    /**
     * Smaže požadavky uživatele podle bankovního účtu
     *
     * @param account bankovní účet
     */
    void deleteUserRequestByAccount(Account account);
}
