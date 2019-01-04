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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ModelAndView showRequestListPage(Pageable pageable)
    {
        ModelAndView model = new ModelAndView("user_request/list");
        Page<UserRequest> pages = userRequestService.getAllUserRequestPageable(pageable);
        model.addObject("pagination", pages);
        model.addObject("requests", pages.getContent());
        model.addObject("bankCode", bankConfig.getBankCode());
        return model;
    }

    @RequestMapping(path = "/request/detail/{id}", name = "id-detail", method = RequestMethod.GET)
    public ModelAndView showRequestDetailPage(@PathVariable("id") Integer userRequestId)
    {
        ModelAndView model = new ModelAndView("user_request/detail");
        model.addObject("request", userRequestService.getUserRequestById(userRequestId));
        model.addObject("bankCode", bankConfig.getBankCode());
        return model;
    }

    @RequestMapping(path = "/request/confirm/{id}", name = "id-confirm", method = RequestMethod.GET)
    public ModelAndView confirmRequest(@PathVariable("id") Integer userRequestId, RedirectAttributes redirectAttributes)
    {
        ModelAndView model = new ModelAndView("redirect:/request/list");
        UserRequest userRequest = userRequestService.getUserRequestById(userRequestId);
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
    public ModelAndView deleteRequest(@PathVariable("id") Integer userRequestId, RedirectAttributes redirectAttributes, HttpServletResponse response)
    {
        ModelAndView model = new ModelAndView("user_request/list");
        UserRequest userRequest = userRequestService.getUserRequestById(userRequestId);

        User user = userService.getUser();

        if(user.getId().equals(userRequest.getAccount().getUser().getId())){
            model.setViewName("redirect:/account");

//            return model;
        }
        else if (user.getRoleList().contains(roleService.getRoleByName("ADMIN"))){
            model.setViewName("redirect:/request/list");
        }
        else{
//            response.setStatus(HttpStatus.FORBIDDEN.value());
            model.setViewName("redirect:/error");
            return model;
        }

        userRequestService.deleteUserRequest(userRequest);

        redirectAttributes.addFlashAttribute("flashMessageSuccess", true);
        redirectAttributes.addFlashAttribute("flashMessageText", "Žádost byla smazána");

        return model;
    }

}
