package cz.jpalcut.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    @RequestMapping(path = "/account")
    public ModelAndView showHomePage()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("account");
        return model;
    }

}
