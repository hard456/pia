package cz.jpalcut.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemplateController {

    @RequestMapping(path = "/templates")
    public ModelAndView showTemplatesPage()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("templates");
        return model;
    }

    @RequestMapping(path = "/templates/new")
    public ModelAndView showAddTemplatePage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("new_template");
        return model;
    }

}
