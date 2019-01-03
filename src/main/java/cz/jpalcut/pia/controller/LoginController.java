package cz.jpalcut.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(name = "loginController")
public class LoginController {

    @RequestMapping(path = {"/login","/"}, name = "login", method = RequestMethod.GET)
    public ModelAndView showLoginPage(@RequestParam(value = "error", required = false) String error)
    {
        ModelAndView model = new ModelAndView("login");
        model.addObject("activeLink","login");
        if(error != null){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Byly zadány špatné přihlašovací údaje.");
        }

        return model;
    }

    @RequestMapping(path = "/logoutSuccessful", name = "logout", method = RequestMethod.GET)
    public ModelAndView showSuccessFulLogoutPage(){
        ModelAndView model = new ModelAndView("login");
        //flash message success
        model.addObject("flashMessageSuccess",true);
        model.addObject("flashMessageText","Byl jste úspěšně odhlášen z aplikace.");

        return model;
    }

}
