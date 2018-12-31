package cz.jpalcut.pia.dao;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateDAO extends JpaRepository<Template, Integer> {

    List<Template> findAllByAccount(Account account);

    Template findTemplateById(Integer id);

}
