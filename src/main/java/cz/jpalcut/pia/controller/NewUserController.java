package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NewUserController {

    @Autowired
    StateService stateService;

    @RequestMapping(path = "/new-user")
    public ModelAndView showNewUserPage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("states",stateService.getAllStates());
        model.setViewName("new_user");
        return model;
    }

}
