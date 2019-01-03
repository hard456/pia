package cz.jpalcut.pia.dao;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateDAO extends PagingAndSortingRepository<Template, Integer> {

    Page<Template> findAllByAccount(Account account, Pageable pageable);

    List<Template> findAllByAccount(Account account);

    Template findTemplateById(Integer id);

}
