package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.UserRequestDAO;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.UserRequest;
import cz.jpalcut.pia.service.interfaces.IUserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Služba pro správu uživatelských žádostí
 */
@Service
@Transactional
public class UserRequestService implements IUserRequestService {

    private UserRequestDAO userRequestDAO;

    /**
     * Konstruktor třídy
     *
     * @param userRequestDAO UserRequestDAO
     */
    @Autowired
    public UserRequestService(UserRequestDAO userRequestDAO) {
        this.userRequestDAO = userRequestDAO;
    }

    /**
     * Vrátí stránku žádostí uživatelů k zobrazení podle omezení
     *
     * @param pageable omezení pro výběr uživatelů
     * @return stránka obsahující žádosti uživatele
     */
    @Override
    public Page<UserRequest> getAllUserRequestPageable(Pageable pageable) {
        return userRequestDAO.findAll(pageable);
    }

    /**
     * Vrátí žádost uživatele podle id
     *
     * @param id id žádosti
     * @return žádost uživatele
     */
    @Override
    public UserRequest getUserRequestById(Integer id) {
        return userRequestDAO.findUserRequestById(id);
    }

    /**
     * Uloží žádost uživatele
     *
     * @param userRequest žádost uživatele k uložení
     * @return žádost uživatele
     */
    @Override
    public UserRequest saveUserRequest(UserRequest userRequest) {
        return userRequestDAO.save(userRequest);
    }

    /**
     * Smaže žádost uživatele
     *
     * @param userRequest žádost uživatele k smazáí
     */
    @Override
    public void deleteUserRequest(UserRequest userRequest) {
        userRequestDAO.delete(userRequest);
    }

    /**
     * Vrátí všechny žádosti uživatele podle bankovního účtu
     *
     * @param account bankovní účet
     * @return seznam žádostí uživatele
     */
    @Override
    public List<UserRequest> getUserRequestsByAcount(Account account) {
        return userRequestDAO.findAllByAccount(account);
    }

    /**
     * Vrátí žádost uživatele podle typu a bankovního účtu
     *
     * @param type    typ žádosti
     * @param account bankovní účet
     * @return žádost uživatele
     */
    @Override
    public UserRequest getUserRequestByTypeAndAccount(String type, Account account) {
        return userRequestDAO.findUserRequestByTypeAndAccount(type, account);
    }

    /**
     * Smaže požadavky uživatele podle bankovního účtu
     *
     * @param account bankovní účet
     */
    @Override
    public void deleteUserRequestByAccount(Account account) {
        userRequestDAO.deleteByAccount(account);
    }

}
