package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.service.UserListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserListController {

    @Autowired
    UserListService userListService;

    @RequestMapping(path = "/users")
    public ModelAndView showUserListPage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("users",userListService.getAllUsersByRole("USER"));
        model.setViewName("user_list");
        return model;
    }

}
