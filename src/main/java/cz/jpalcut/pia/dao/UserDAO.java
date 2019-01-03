package cz.jpalcut.pia.dao;

import cz.jpalcut.pia.model.Role;
import cz.jpalcut.pia.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    Page<User> findAllByRoleList(List<Role> roleList, Pageable pageable);

    User findUserByLoginId(String loginId);

    User findUserById(Integer id);

}
