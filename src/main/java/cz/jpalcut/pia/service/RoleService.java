package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.RoleDAO;
import cz.jpalcut.pia.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    RoleDAO roleDAO;

    public List<Role> getRoleListByName(String role){
        return roleDAO.findRoleListByName(role);
    }

    public Role getRoleByName(String role){
        return roleDAO.findByName(role);
    }

}
