package cz.jpalcut.pia.dao;

import cz.jpalcut.pia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    User findUserByLoginId(String loginId);

    User findUserById(Integer id);

}
