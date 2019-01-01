package cz.jpalcut.pia.dao;

import cz.jpalcut.pia.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> {

    Role findByName(String role);

    List<Role> findRoleListByName(String role);

}
