package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.RoleDAO;
import cz.jpalcut.pia.dao.UserDAO;
import cz.jpalcut.pia.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserListService {

    @Autowired
    RoleDAO roleDAO;

    public List<User> getAllUsersByRole(String role){
        return roleDAO.findAllByName(role).getUsers();
    }

}
