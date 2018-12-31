package cz.jpalcut.pia.service;

import cz.jpalcut.pia.config.AppUser;
import cz.jpalcut.pia.dao.RoleDAO;
import cz.jpalcut.pia.dao.UserDAO;
import cz.jpalcut.pia.model.Role;
import cz.jpalcut.pia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    RoleDAO roleDAO;

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

    public void editUser(User newUser){
        User user = getUser();
        user.setAddress(newUser.getAddress());
        user.setAddressNumber(newUser.getAddressNumber());
        user.setZipCode(newUser.getZipCode());
        user.setState(newUser.getState());
        user.setEmail(newUser.getEmail());
        userDAO.save(user);
    }

    public void editUserByAdmin(User user, User newUser){
        newUser.setId(user.getId());
        newUser.setPin(user.getPin());
        newUser.setLoginId(user.getLoginId());
        newUser.setRoleList(user.getRoleList());
        userDAO.save(newUser);
    }

    public List<User> getAllUsersByRole(String role){
        return roleDAO.findAllByName(role).getUsers();
    }

    public User getUserById(Integer id){
        return userDAO.findUserById(id);
    }

}
