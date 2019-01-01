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

    @RequestMapping(path = "/user", name = "userUrl", method = RequestMethod.GET)
    public ModelAndView showUserPage(){
        ModelAndView model = new ModelAndView("user");
        model.addObject("activeLink","user");
        model.addObject("states",stateService.getAllStates());
        model.addObject("userForm", userService.getUser());
        return model;
    }

    @RequestMapping(path = "/user/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView("user");
        model.addObject("states",stateService.getAllStates());

        if(bindingResult.hasErrors()){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Nastala chyba při vyplnění formuláře.");
            return model;
        }

        userService.editUser(user);

        //flash message success
        model.addObject("flashMessageSuccess",true);
        model.addObject("flashMessageText","Uživatelský profil byl upraven.");

        return model;
    }

    @RequestMapping(path = "/user/list", method = RequestMethod.GET)
    public ModelAndView showUserListPage()
    {
        ModelAndView model = new ModelAndView("user_list");
        model.addObject("activeLink","user/list");
        model.addObject("users",userService.getAllUsersByRole("USER"));
        return model;
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView showUserDetailPage(@PathVariable("id") Integer userId){
        User user = userService.getUserById(userId);
        ModelAndView model = new ModelAndView("user_edit");
        model.addObject("activeLink","user/list");
        model.addObject("states",stateService.getAllStates());
        model.addObject("userForm", user);
        model.addObject("account", accountService.getAccount(user));
        model.addObject("bankCode", bankConfig.getBankCode());
        return model;
    }

    @RequestMapping(path = "/user/{id}/edit", method = RequestMethod.POST)
    public ModelAndView editUserByAdmin(@Valid @ModelAttribute("userForm") User newUser, BindingResult bindingResult,
                                 @PathVariable("id") Integer userId){

        User user = userService.getUserById(userId);

        ModelAndView model = new ModelAndView("user_edit");
        model.addObject("states",stateService.getAllStates());
        model.addObject("account", accountService.getAccount(user));
        model.addObject("bankCode", bankConfig.getBankCode());

        if(bindingResult.hasErrors()){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Nastala chyba při vyplnění formuláře.");
            return model;
        }

        userService.editUserByAdmin(user, newUser);

        //flash message success
        model.addObject("flashMessageSuccess",true);
        model.addObject("flashMessageText","Uživatel byl úspěšně upraven.");

        return model;
    }

    @RequestMapping(path = "/user/{id}/editStatus", method = RequestMethod.GET)
    public ModelAndView editUserStatus(@PathVariable("id") Integer userId) {

        User user = userService.getUserById(userId);
        Account account = accountService.getAccount(user);
        ModelAndView model = new ModelAndView("user_edit");
        model.addObject("states",stateService.getAllStates());
        model.addObject("account", account);
        model.addObject("bankCode", bankConfig.getBankCode());
        model.addObject("userForm", user);

        accountService.changeAccountStatus(account);
        //flash message success
        model.addObject("flashMessageSuccess",true);
        model.addObject("flashMessageText","Byl změněn stav uživatele.");

        return model;
    }

    @RequestMapping(path = "/user/new", method = RequestMethod.GET)
    public ModelAndView showNewUserPage()
    {
        ModelAndView model = new ModelAndView("new_user");
        model.addObject("activeLink","user/new");
        model.addObject("states",stateService.getAllStates());
        model.addObject("userForm",new User());
        return model;
    }

    @RequestMapping(path = "/user/new/add", method = RequestMethod.POST)
    public ModelAndView saveNewUser(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView("new_user");
        model.addObject("states",stateService.getAllStates());

        if(bindingResult.hasErrors()){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Nastala chyba při vyplnění formuláře.");
            return model;
        }

        userService.addUser(user);
        model.addObject("userForm",new User());

        //flash message success
        model.addObject("flashMessageSuccess",true);
        model.addObject("flashMessageText","Uživatel byl přidán.");

        return model;
    }

}
