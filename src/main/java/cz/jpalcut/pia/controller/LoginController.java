package cz.jpalcut.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(path = "/login")
    public ModelAndView showLoginPage()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

}
