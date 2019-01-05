package cz.jpalcut.pia.dao;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Rozhraní pro přístup k šablonám
 */
@Repository
public interface TemplateDAO extends JpaRepository<Template, Integer> {

    /**
     * Vrátí stránku šablon podle omezení a bankovního účtu
     *
     * @param account  bankovní účet
     * @param pageable omezení pro výběr šablon
     * @return stránka šablon
     */
    Page<Template> findAllByAccount(Account account, Pageable pageable);

    List<Template> findAllByAccount(Account account);

    Template findTemplateById(Integer id);

}
