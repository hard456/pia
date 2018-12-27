package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController
{

    @Autowired
    UserDAO userDaO;

    @RequestMapping(path = "/")
    public ModelAndView showHomePage()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        model.addObject("users",userDaO.findAll());
        return model;
    }

}