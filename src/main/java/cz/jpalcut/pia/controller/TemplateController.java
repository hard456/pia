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

        @RequestMapping(path = "/template/list")
    public ModelAndView showTemplatesPage()
    {
        Account account = accountService.getAccount(userService.getUser());
        ModelAndView model = new ModelAndView();
        model.addObject("templates", templateService.getTemplatesByAccount(account));
        model.setViewName("template_list");
        return model;
    }

    @RequestMapping(path = "/template/new")
    public ModelAndView showAddTemplatePage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("template_new");
        model.addObject("template", new Template());
        return model;
    }

    @RequestMapping(path = "/template/new/add")
    public ModelAndView showAddNewTemplate(@Valid @ModelAttribute("template") Template template, BindingResult bindingResult){
        ModelAndView model = new ModelAndView("template_new");

        if(bindingResult.hasErrors()){
            return model;
        }

        templateService.addTemplate(template);

        model.addObject("template", new Template());
        return model;
    }

    @RequestMapping(path = "/template/{id}")
    public ModelAndView showEditTemplatePage(@PathVariable("id") Integer templateId){
        ModelAndView model = new ModelAndView("template_edit");
        model.addObject("template", templateService.getTemplateById(templateId));
        return model;
    }

    @RequestMapping(path = "/template/{id}/edit")
    public ModelAndView showAddNewTemplate(@Valid @ModelAttribute("template") Template newTemplate,
                                           BindingResult bindingResult, @PathVariable("id") Integer templateId){

        ModelAndView model = new ModelAndView("template_edit");

        if(bindingResult.hasErrors()){
            return model;
        }

        Template template = templateService.getTemplateById(templateId);
        newTemplate.setAccount(template.getAccount());
        templateService.editTemplate(newTemplate);
        return model;
    }

    @RequestMapping(path = "/template/{id}/delete")
    public ModelAndView showAddNewTemplate(@PathVariable("id") Integer templateId){

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

        return model;
    }

}
