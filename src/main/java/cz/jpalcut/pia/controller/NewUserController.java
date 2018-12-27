package cz.jpalcut.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewUserController {

    @RequestMapping(path = "/new-user")
    public ModelAndView showAboutUsPage()
    {
        return new ModelAndView("new_user");
    }

}
