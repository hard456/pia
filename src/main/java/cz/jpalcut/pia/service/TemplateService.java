package cz.jpalcut.pia.service;

import cz.jpalcut.pia.dao.TemplateDAO;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TemplateService {

    @Autowired
    UserService userService;

    @Autowired
    TemplateDAO templateDAO;

    @Autowired
    AccountService accountService;

    public void addTemplate(Template template){
        template.setAccount(accountService.getAccount(userService.getUser()));
        templateDAO.save(template);
    }

    public List<Template> getTemplatesByAccount(Account account){
        return templateDAO.findAllByAccount(account);
    }

    public Template getTemplateById(Integer id){
        return templateDAO.findTemplateById(id);
    }

    public void editTemplate(Template template){
        templateDAO.save(template);
    }

    public void deleteTemplate(Template template){
        templateDAO.delete(template);
    }

    public Page<Template> getTemplatesByAccountPageable(Account account, Pageable pageable) {
        return templateDAO.findAllByAccount(account, pageable);
    }

}
