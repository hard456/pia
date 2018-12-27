package cz.jpalcut.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserListController {

    @RequestMapping(path = "/users")
    public ModelAndView showAboutUsPage()
    {
        return new ModelAndView("user_list");
    }

}
