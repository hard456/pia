package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.config.BankConfig;
import cz.jpalcut.pia.model.User;
import cz.jpalcut.pia.service.AccountService;
import cz.jpalcut.pia.service.CaptchaService;
import cz.jpalcut.pia.service.StateService;
import cz.jpalcut.pia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(name = "userController")
public class UserController {

    @Autowired
    StateService stateService;

    @Autowired
    private UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    private BankConfig bankConfig;

    @Autowired
    private CaptchaService captchaService;

    @RequestMapping(path = "/user", name = "user", method = RequestMethod.GET)
    public ModelAndView showUserPage(){
        ModelAndView model = new ModelAndView("user/profile");
        model.addObject("states",stateService.getAllStates());
        model.addObject("userForm", userService.getUser());
        return model;
    }

    @RequestMapping(path = "/user/edit", name = "edit", method = RequestMethod.POST)
    public ModelAndView editUser(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView("user/profile");
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

    @RequestMapping(path = "/user/list", name = "list", method = RequestMethod.GET)
    public ModelAndView showUserListPage(Pageable pageable)
    {
        ModelAndView model = new ModelAndView("user/list");
        Page<User> pages = userService.getAllUsersByRolePageable("USER", pageable);
        model.addObject("pagination", pages);
        model.addObject("users",pages.getContent());
        return model;
    }

    @RequestMapping(path = "/user/{id}", name = "id", method = RequestMethod.GET)
    public ModelAndView showUserDetailPage(@PathVariable("id") Integer userId, RedirectAttributes redirectAttributes){
        User user = userService.getUserById(userId);
        ModelAndView model = new ModelAndView("user/edit");

        //kontrola existence uživatele
        if(user == null){
            model.setViewName("redirect:/user/list");
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Uživatel neexistuje.");
        }

        model.addObject("states",stateService.getAllStates());
        model.addObject("userForm", user);
        model.addObject("account", accountService.getAccount(user));
        model.addObject("bankCode", bankConfig.getBankCode());
        return model;
    }

    @RequestMapping(path = "/user/edit/{id}", name = "edit-id", method = RequestMethod.POST)
    public ModelAndView editUserByAdmin(@Valid @ModelAttribute("userForm") User newUser, BindingResult bindingResult,
                                 @PathVariable("id") Integer userId, RedirectAttributes redirectAttributes){

        User user = userService.getUserById(userId);
        ModelAndView model = new ModelAndView("user/edit");

        //kontrola existence uživatele
        if(user == null){
            model.setViewName("redirect:/user/list");
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Uživatel neexistuje.");
        }


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

    @RequestMapping(path = "/user/new", name = "new", method = RequestMethod.GET)
    public ModelAndView showNewUserPage()
    {
        ModelAndView model = new ModelAndView("user/new");
        model.addObject("states",stateService.getAllStates());
        model.addObject("userForm",new User());
        return model;
    }

    @RequestMapping(path = "/user/new/add", name = "new-add", method = RequestMethod.POST)
    public ModelAndView saveNewUser(@Valid @ModelAttribute("userForm") User user, BindingResult bindingResult,
                                    HttpServletRequest request){
        ModelAndView model = new ModelAndView("user/new");
        model.addObject("states",stateService.getAllStates());

        if(bindingResult.hasErrors()){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Nastala chyba při vyplnění formuláře.");
            return model;
        }

        String captchaResponse = request.getParameter("g-recaptcha-response");
        if(!captchaService.processResponse(captchaResponse, request.getRemoteAddr())){
            //flash message danger
            model.addObject("flashMessageSuccess",false);
            model.addObject("flashMessageText","Nastala chyba při ověření formuláře - Google reCAPTCHA ");
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
