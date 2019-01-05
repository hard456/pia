package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.RoleDAO;
import cz.jpalcut.pia.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Služba pro správu rolí uživatelů
 */
@Service
@Transactional
public class RoleService {

    @Autowired
    RoleDAO roleDAO;

    /**
     * Vrátí seznam rolí podle názvu role
     *
     * @param role název role
     * @return seznam rolí
     */
    public List<Role> getRoleListByName(String role) {
        return roleDAO.findRoleListByName(role);
    }

    /**
     * Vrátí roli podle názvu role
     *
     * @param role název role
     * @return role
     */
    public Role getRoleByName(String role) {
        return roleDAO.findByName(role);
    }

}
