package cz.jpalcut.pia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @RequestMapping("/user")
    public ModelAndView showUserPage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("user");
        return model;
    }

}
