package cz.jpalcut.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(path = "/login")
    public ModelAndView showLoginPage()
    {
        ModelAndView model = new ModelAndView("login");
        return model;
    }

    @RequestMapping(path = "/logoutSuccessful")
    public ModelAndView showSuccessFulLogoutPage(){
        ModelAndView model = new ModelAndView("login");

        //flash message success
        model.addObject("flashMessageSuccess",true);
        model.addObject("flashMessageText","Byl jste úspěšně odhlášen z aplikace.");

        return model;
    }

}
