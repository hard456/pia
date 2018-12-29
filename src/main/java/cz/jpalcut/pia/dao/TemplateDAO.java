package cz.jpalcut.pia.dao;

import cz.jpalcut.pia.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateDAO extends JpaRepository<Template, Long> {

}
