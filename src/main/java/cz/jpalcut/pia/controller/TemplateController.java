package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.Template;
import cz.jpalcut.pia.model.User;
import cz.jpalcut.pia.service.AccountService;
import cz.jpalcut.pia.service.TemplateService;
import cz.jpalcut.pia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class TemplateController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    TemplateService templateService;

        @RequestMapping(path = "/template/list", method = RequestMethod.GET)
    public ModelAndView showTemplatesPage()
    {
        Account account = accountService.getAccount(userService.getUser());
        ModelAndView model = new ModelAndView("template_list");
        model.addObject("templates", templateService.getTemplatesByAccount(account));
        return model;
    }

    @RequestMapping(path = "/template/new", method = RequestMethod.GET)
    public ModelAndView showAddTemplatePage(){
        ModelAndView model = new ModelAndView("template_new");
        model.addObject("template", new Template());
        return model;
    }

    @RequestMapping(path = "/template/new/add", method = RequestMethod.POST)
    public ModelAndView addNewTemplate(@Valid @ModelAttribute("template") Template template, BindingResult bindingResult){
        ModelAndView model = new ModelAndView("template_new");

        if(bindingResult.hasErrors()){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Nastala chyba při vyplnění formuláře.");
            return model;
        }

        templateService.addTemplate(template);

        //flash message success
        model.addObject("flashMessageSuccess",true);
        model.addObject("flashMessageText","Šablona byla vytvořena.");

        model.addObject("template", new Template());
        return model;
    }

    @RequestMapping(path = "/template/{id}", method = RequestMethod.GET)
    public ModelAndView showEditTemplatePage(@PathVariable("id") Integer templateId){
        ModelAndView model = new ModelAndView("template_edit");
        model.addObject("template", templateService.getTemplateById(templateId));
        return model;
    }

    @RequestMapping(path = "/template/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editTemplate(@Valid @ModelAttribute("template") Template newTemplate,
                                           BindingResult bindingResult, @PathVariable("id") Integer templateId){

        ModelAndView model = new ModelAndView("template_edit");

        if(bindingResult.hasErrors()){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Nastala chyba při vyplnění formuláře.");
            return model;
        }

        Template template = templateService.getTemplateById(templateId);
        newTemplate.setAccount(template.getAccount());
        templateService.editTemplate(newTemplate);

        //flash message success
        model.addObject("flashMessageSuccess",true);
        model.addObject("flashMessageText","Šablona byla upravena.");

        return model;
    }

    @RequestMapping(path = "/template/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteTemplate(@PathVariable("id") Integer templateId){

        ModelAndView model = new ModelAndView("template_list");

        Template template = templateService.getTemplateById(templateId);
        User user = userService.getUser();

        if(template.getAccount().getUser().getId().equals(user.getId())){
            templateService.deleteTemplate(template);
        }
        else{
            //wrong user
            return model;
        }

        model.addObject("templates", templateService.getTemplatesByAccount(template.getAccount()));

        //flash message success
        model.addObject("flashMessageSuccess",true);
        model.addObject("flashMessageText","Šablona byla smazána.");

        return model;
    }

}
