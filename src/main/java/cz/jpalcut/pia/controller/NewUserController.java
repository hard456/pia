package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.model.User;
import cz.jpalcut.pia.service.NewUserService;
import cz.jpalcut.pia.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class NewUserController {

    @Autowired
    StateService stateService;

    @Autowired
    NewUserService newUserService;

    @RequestMapping(path = "/new-user")
    public ModelAndView showNewUserPage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("states",stateService.getAllStates());
        model.addObject("userForm",new User());
        model.setViewName("new_user");
        return model;
    }

    @RequestMapping(path = "/new-user/add", method = RequestMethod.POST)
    public ModelAndView saveNewUser(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView("new_user");
        model.addObject("states",stateService.getAllStates());

        if(bindingResult.hasErrors()){
            return model;
        }

        newUserService.addUser(user);
        model.addObject("userForm",new User());

        return model;
    }

}
