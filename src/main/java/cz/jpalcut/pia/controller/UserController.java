package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.config.BankConfig;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.User;
import cz.jpalcut.pia.service.AccountService;
import cz.jpalcut.pia.service.StateService;
import cz.jpalcut.pia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    StateService stateService;

    @Autowired
    private UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    private BankConfig bankConfig;

    @RequestMapping("/user")
    public ModelAndView showUserPage(){
        ModelAndView model = new ModelAndView();
        model.setViewName("user");
        model.addObject("states",stateService.getAllStates());
        model.addObject("userForm", userService.getUser());
        return model;
    }

    @RequestMapping(path = "/user/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView("user");
        model.addObject("states",stateService.getAllStates());

        if(bindingResult.hasErrors()){
            return model;
        }

        userService.editUser(user);

        return model;
    }

    @RequestMapping(path = "/user/list")
    public ModelAndView showUserListPage()
    {
        ModelAndView model = new ModelAndView();
        model.addObject("users",userService.getAllUsersByRole("USER"));
        model.setViewName("user_list");
        return model;
    }

    @RequestMapping(path = "/user/{id}")
    public ModelAndView showUserDetailPage(@PathVariable("id") Integer userId){
        User user = userService.getUserById(userId);
        ModelAndView model = new ModelAndView("user_edit");
        model.addObject("states",stateService.getAllStates());
        model.addObject("userForm", user);
        model.addObject("account", accountService.getAccount(user));
        model.addObject("bankCode", bankConfig.getBankCode());
        return model;
    }

    @RequestMapping(path = "/user/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@Valid @ModelAttribute("userForm") User newUser, BindingResult bindingResult,
                                 @PathVariable("id") Integer userId){

        User user = userService.getUserById(userId);

        ModelAndView model = new ModelAndView("user_edit");
        model.addObject("states",stateService.getAllStates());
        model.addObject("account", accountService.getAccount(user));
        model.addObject("bankCode", bankConfig.getBankCode());

        if(bindingResult.hasErrors()){
            return model;
        }

        userService.editUserByAdmin(user, newUser);

        return model;
    }

    @RequestMapping(path = "/user/{id}/editStatus", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable("id") Integer userId) {

        User user = userService.getUserById(userId);
        Account account = accountService.getAccount(user);
        ModelAndView model = new ModelAndView("user_edit");
        model.addObject("states",stateService.getAllStates());
        model.addObject("account", account);
        model.addObject("bankCode", bankConfig.getBankCode());
        model.addObject("userForm", user);

        accountService.changeAccountStatus(account);

        return model;
    }

}
