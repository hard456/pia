package cz.jpalcut.pia.controller;

import cz.jpalcut.pia.config.BankConfig;
import cz.jpalcut.pia.model.Account;
import cz.jpalcut.pia.model.User;
import cz.jpalcut.pia.model.UserRequest;
import cz.jpalcut.pia.service.AccountService;
import cz.jpalcut.pia.service.RoleService;
import cz.jpalcut.pia.service.UserRequestService;
import cz.jpalcut.pia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(name = "userRequestController")
public class UserRequestController {

    @Autowired
    private UserRequestService userRequestService;

    @Autowired
    private BankConfig bankConfig;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(path = "/request/list", name = "list", method = RequestMethod.GET)
    public ModelAndView showUserRequestListPage(Pageable pageable)
    {
        ModelAndView model = new ModelAndView("user_request/list");
        Page<UserRequest> pages = userRequestService.getAllUserRequestPageable(pageable);
        model.addObject("pagination", pages);
        model.addObject("requests", pages.getContent());
        model.addObject("bankCode", bankConfig.getBankCode());
        return model;
    }

    @RequestMapping(path = "/request/detail/{id}", name = "id-detail", method = RequestMethod.GET)
    public ModelAndView showUserRequestDetailPage(@PathVariable("id") Integer userRequestId, RedirectAttributes redirectAttributes)
    {
        ModelAndView model = new ModelAndView("user_request/detail");
        UserRequest request = userRequestService.getUserRequestById(userRequestId);

        //Kontrola existence požadavku
        if(request == null){
            model.setViewName("redirect:/request/list");
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Požadavek neexistuje.");
            return model;
        }

        model.addObject("request", request);
        model.addObject("bankCode", bankConfig.getBankCode());
        return model;
    }

    @RequestMapping(path = "/request/confirm/{id}", name = "id-confirm", method = RequestMethod.GET)
    public ModelAndView confirmUserRequest(@PathVariable("id") Integer userRequestId, RedirectAttributes redirectAttributes)
    {
        ModelAndView model = new ModelAndView("redirect:/request/list");
        UserRequest userRequest = userRequestService.getUserRequestById(userRequestId);

        //kontrola existence požadavku
        if(userRequest == null){
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Požadavek neexistuje.");
            return model;
        }

        Account account = userRequest.getAccount();

        if(userRequest.getType().equals("change_limit")){
            account.setLimitPayment(userRequest.getValue());
            accountService.save(account);

            //flash message success
            redirectAttributes.addFlashAttribute("flashMessageSuccess", true);
            redirectAttributes.addFlashAttribute("flashMessageText", "Limit účtu pod nulu byl změněn.");

        }
        else if(userRequest.getType().equals("change_international_payment")){
            if(account.getInternationalPayment())   {
                account.setInternationalPayment(false);
                //flash message success
                redirectAttributes.addFlashAttribute("flashMessageSuccess", true);
                redirectAttributes.addFlashAttribute("flashMessageText", "Mezinárdní platba kartou byla zakázána.");
            }
            else{
                account.setInternationalPayment(true);
                //flash message success
                redirectAttributes.addFlashAttribute("flashMessageSuccess", true);
                redirectAttributes.addFlashAttribute("flashMessageText", "Mezinárdní platba kartou byla aktivována.");
            }
            accountService.save(account);

        }

        userRequestService.deleteUserRequest(userRequest);

        return model;
    }

    @RequestMapping(path = "/request/delete/{id}", name = "id-delete", method = RequestMethod.GET)
    public ModelAndView deleteUserRequest(@PathVariable("id") Integer userRequestId, RedirectAttributes redirectAttributes)
    {
        ModelAndView model = new ModelAndView();
        UserRequest userRequest = userRequestService.getUserRequestById(userRequestId);
        User user = userService.getUser();

        //Kontrola existence žádosti
        if(userRequest == null){
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Požadavek neexistuje.");
            if(user.getRoleList().contains(roleService.getRoleByName("ADMIN"))){
                model.setViewName("redirect:/request/list");
                return model;
            }
            else{
                model.setViewName("redirect:/account");
                return model;
            }
        }

        //Kontrola uživatele
        if(user.getId().equals(userRequest.getAccount().getUser().getId())){
            model.setViewName("redirect:/account");
        }
        else if (user.getRoleList().contains(roleService.getRoleByName("ADMIN"))){
            model.setViewName("redirect:/request/list");
        }
        else{
            //flash message danger
            redirectAttributes.addFlashAttribute("flashMessageSuccess", false);
            redirectAttributes.addFlashAttribute("flashMessageText", "Nepovolený požadavek.");
            model.setViewName("redirect:/account");
            return model;
        }

        userRequestService.deleteUserRequest(userRequest);

        redirectAttributes.addFlashAttribute("flashMessageSuccess", true);
        redirectAttributes.addFlashAttribute("flashMessageText", "Žádost byla smazána.");

        return model;
    }

}
