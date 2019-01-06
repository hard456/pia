package cz.jpalcut.pia.service;

import cz.jpalcut.pia.config.AppUser;
import cz.jpalcut.pia.dao.UserDAO;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Role;
import cz.jpalcut.pia.model.User;
import cz.jpalcut.pia.service.interfaces.IAccountService;
import cz.jpalcut.pia.service.interfaces.IRoleService;
import cz.jpalcut.pia.service.interfaces.IUserService;
import cz.jpalcut.pia.utils.Enum;
import cz.jpalcut.pia.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Slubža pro správu uživatelů
 */
@Service
@Transactional
public class UserService implements UserDetailsService, IUserService {

    private UserDAO userDAO;

    private IAccountService accountService;

    private IRoleService roleService;

    /**
     * Konstruktor třídy
     *
     * @param accountService AccountService
     * @param roleService    RoleService
     * @param userDAO        UserDAO
     */
    @Autowired
    public UserService(IAccountService accountService, IRoleService roleService, UserDAO userDAO) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.userDAO = userDAO;
    }

    /**
     * Načtení uživatele Spring Security
     *
     * @param loginId přihlašovací login
     * @return načtený uživatel Spring Security
     * @throws UsernameNotFoundException vyjímka pro nenalezení uživatele
     */
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userDAO.findUserByLoginId(loginId);

        if (user == null) {
            throw new UsernameNotFoundException("User" + loginId + " not found!");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (user.getRoleList() != null) {
            for (Role role : user.getRoleList()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                grantList.add(authority);
            }
        }

        UserDetails userDetails = new AppUser(user.getLoginId(),
                user.getPin(), user.getFirstname(), user.getLastname(), grantList);

        return userDetails;
    }

    /**
     * Vrátí přihlášeného uživatele
     *
     * @return přihlášený uživatel
     */
    @Override
    public User getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDAO.findUserByLoginId(((UserDetails) principal).getUsername());
    }

    /**
     * Upraví údaje aktuálně přihlášeného uživatele
     *
     * @param user aktuálně přihlášený uživatel
     * @param newUser nová osobní data uživatele k editaci
     * @return uživatel
     */
    @Override
    public User editUser(User user, User newUser) {
        user.setAddress(newUser.getAddress());
        user.setAddressNumber(newUser.getAddressNumber());
        user.setZipCode(newUser.getZipCode());
        user.setState(newUser.getState());
        user.setEmail(newUser.getEmail());
        user.setSex(newUser.getSex());
        user.setTown(newUser.getTown());
        return userDAO.save(user);
    }

    /**
     * Upravení uživatele adminem
     *
     * @param user    aktulní údaje uživatele
     * @param newUser nové údaje uživatele
     * @return úpravený uživatel
     */
    @Override
    public User editUserByAdmin(User user, User newUser) {
        newUser.setId(user.getId());
        newUser.setPin(user.getPin());
        newUser.setLoginId(user.getLoginId());
        newUser.setRoleList(user.getRoleList());
        return userDAO.save(newUser);
    }

    /**
     * Vrátí stránku žádostí uživatelů k zobrazení podle omezení a role
     *
     * @param role     role uživatele
     * @param pageable omezení pro výběr uživatelů
     * @return stránka obsahující uživatele
     */
    @Override
    public Page<User> getAllUsersByRolePageable(String role, Pageable pageable) {
        List<Role> roleList = roleService.getRoleListByName(role);
        return userDAO.findAllByRoleList(roleList, pageable);
    }

    /**
     * Vrátí uživatele podle id
     *
     * @param id id uživatele
     * @return uživatel
     */
    @Override
    public User getUserById(Integer id) {
        return userDAO.findUserById(id);
    }

    /**
     * Přidání uživatele včetně vytvoření bankovního účtu a generování potřebných údajů
     *
     * @param user uživatel k přidání
     * @return přidaný uživatel
     */
    @Override
    public User addUser(User user) {
        String tmp;

        //generování přihlašovacího loginu uživatele
        while (true) {
            tmp = Utils.generateNumber(8);
            if (userDAO.findUserByLoginId(tmp) == null) {
                user.setLoginId(tmp);
                break;
            }
        }

        //vytvoření hesla uživatele
        user.setPin(Utils.hashPassword(Utils.generateNumber(5)));

        //přiřazení rolí uživateli
        user.setRoleList(roleService.getRoleListByName(Enum.Role.valueOf("USER").toString()));
        user = userDAO.save(user);

        Account account = new Account();
        account.setUser(user);
        account.setBalance(0.00);
        account.setBlockedBalance(0.00);
        account.setInternationalPayment(false);
        account.setLimitBelow(0.00);
        account.setCardPin(Utils.hashPassword(Utils.generateNumber(5)));

        //generování čísla účtu
        while (true) {
            tmp = Utils.generateNumber(9);
            if (accountService.getAccountByNumber(tmp) == null) {
                account.setNumber(tmp);
                break;
            }
        }

        //generování čísla kreditní karty
        while (true) {
            tmp = Utils.generateNumber(16);
            if (accountService.getAccountByCardNumber(tmp) == null) {
                account.setCardNumber(tmp);
                break;
            }
        }
        accountService.save(account);
        return user;
    }

}
