package cz.jpalcut.pia.service;

import cz.jpalcut.pia.config.AppUser;
import cz.jpalcut.pia.dao.AccountDAO;
import cz.jpalcut.pia.dao.RoleDAO;
import cz.jpalcut.pia.dao.UserDAO;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Role;
import cz.jpalcut.pia.model.User;
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

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    AccountService accountService;

    @Autowired
    RoleService roleService;

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

    public User getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDAO.findUserByLoginId(((UserDetails) principal).getUsername());
    }

    public User editUser(User newUser){
        User user = getUser();
        user.setAddress(newUser.getAddress());
        user.setAddressNumber(newUser.getAddressNumber());
        user.setZipCode(newUser.getZipCode());
        user.setState(newUser.getState());
        user.setEmail(newUser.getEmail());
        return userDAO.save(user);
    }

    public User editUserByAdmin(User user, User newUser){
        newUser.setId(user.getId());
        newUser.setPin(user.getPin());
        newUser.setLoginId(user.getLoginId());
        newUser.setRoleList(user.getRoleList());
        return userDAO.save(newUser);
    }

    public List<User> getAllUsersByRole(String role){
        return roleService.getRoleByName(role).getUsers();
    }

    public Page<User> getAllUsersByRolePageable(String role, Pageable pageable){
        List<Role> roleList = roleService.getRoleListByName(role);
        return userDAO.findAllByRoleList(roleList, pageable);
    }

    public User getUserById(Integer id){
        return userDAO.findUserById(id);
    }

    public User addUser(User user){
        String tmp;

        //Generování přihlašovacího loginu uživatele
        while(true){
            tmp = Utils.generateNumber(8);
            if(userDAO.findUserByLoginId(tmp) == null){
                user.setLoginId(tmp);
                break;
            }
        }

        //Vytvoření hesla uživatele
        user.setPin(Utils.hashPassword(Utils.generateNumber(5)));

        //Přiřazení rolí uživateli
        user.setRoleList(roleService.getRoleListByName("USER"));
        user = userDAO.save(user);

        Account account = new Account();
        account.setUser(user);
        account.setBalance(0.00);
        account.setBlockedBalance(0.00);
        account.setInternationalPayment(false);
        account.setLimitPayment(0.00);
        account.setCardPin(Utils.hashPassword(Utils.generateNumber(5)));

        //Generování čísla účtu
        while(true){
            tmp = Utils.generateNumber(9);
            if(accountService.getAccountByNumber(tmp) == null){
                account.setNumber(tmp);
                break;
            }
        }

        //Generování čísla kreditní karty
        while(true){
            tmp = Utils.generateNumber(16);
            if(accountService.getAccountByCardNumber(tmp) == null){
                account.setCardNumber(tmp);
                break;
            }
        }
        accountService.save(account);
        return user;
    }

}
